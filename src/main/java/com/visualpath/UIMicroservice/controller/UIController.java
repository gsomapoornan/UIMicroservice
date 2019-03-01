package com.visualpath.UIMicroservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.visualpath.UIMicroservice.Model.Product;
import com.visualpath.UIMicroservice.Model.User;
import com.visualpath.UIMicroservice.Services.UIService;

@Controller
public class UIController {
	
	@Autowired
	UIService uiService;
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");        
        
       
        return "login";
}
		
   @RequestMapping(value = "/welcome")
   public String welcome(Model model, OAuth2AuthenticationToken authentication) {
	   OAuth2AuthorizedClient client = authorizedClientService
			      .loadAuthorizedClient(
			        authentication.getAuthorizedClientRegistrationId(), 
			          authentication.getName());
	   String userInfoEndpointUri = client.getClientRegistration()
			   .getProviderDetails().getUserInfoEndpoint().getUri();
			  
			 if (!StringUtils.isEmpty(userInfoEndpointUri)) {
			     RestTemplate restTemplate = new RestTemplate();
			     HttpHeaders headers = new HttpHeaders();
			     headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
			       .getTokenValue());
			     HttpEntity entity = new HttpEntity("", headers);
			     ResponseEntity <Map>response = restTemplate
			       .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
			     Map userAttributes = response.getBody();
			     model.addAttribute("name", userAttributes.get("name"));
			 }
      return "welcome";
   }
   //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
   @RequestMapping(value = "/user-accounts")
   public String showProfilesPage() {
      return "admin";
   }
   @RequestMapping(value = "/self-profile",method = RequestMethod.GET)
   public String selfProfilesPage(Model model) {
	   User user=uiService.getUserByID(100);
	   model.addAttribute("user1",user);	   
	   System.out.println("*****"+user.getName());
      return "selfprofile";
   }
   
   @RequestMapping(value = "/user-search")
   @PreAuthorize("hasAuthority('ROLE_ADMIN')")
   public String userSearchPage() {
      return "userprofiles";
   }
   @RequestMapping(value = "/catalog")
   public String catalogPage(Model model) {
	   List<Product> prodList=uiService.getProducts();	   
	   model.addAttribute("prodList", prodList);
      return "catalog";
   }
   @RequestMapping(value = "/showCart")
   public String cartPage() {
	  return "cart";
   }
   
  /* @GetMapping("/searchuser")
   public String searchuserForm(Model model) {
   model.addAttribute("searchuser", new Searchuser());
       return "searchuser";
   }

   @PostMapping("/searchuser")
   public String searchuserSubmit(@ModelAttribute("searchuser") Searchuser searchuser) {
       return "userprofiles";
   }*/
}