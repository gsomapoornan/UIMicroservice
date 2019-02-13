package com.visualpath.UIMicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
		
   @RequestMapping(value = "/index")
   public String index() {
      return "index";
   }
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