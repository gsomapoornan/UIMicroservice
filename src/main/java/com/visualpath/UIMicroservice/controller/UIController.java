package com.visualpath.UIMicroservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {
   @RequestMapping(value = "/index")
   public String index() {
      return "index";
   }
   @RequestMapping(value = "/user-accounts")
   public String showProfilesPage() {
      return "admin";
   }
   @RequestMapping(value = "/self-profile")
   public String selfProfilesPage() {
      return "selfprofile";
   }
   @RequestMapping(value = "/user-search")
   public String userSearchPage() {
      return "userprofiles";
   }
   @RequestMapping(value = "/catalog")
   public String catalogPage() {
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