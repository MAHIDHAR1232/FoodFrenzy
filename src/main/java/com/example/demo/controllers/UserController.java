package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entities.User;
import com.example.demo.services.UserServices;

@Controller
public class UserController
{
	@Autowired
	private UserServices services;

	@PostMapping("/addingUser")
	// public String  addUser(@ModelAttribute User user)
	// {
	// 	System.out.println(user);
	// 	this.services.addUser(user);
	// 	return "redirect:/admin/services";
	// }
	public String addUser(@ModelAttribute User user, RedirectAttributes redirectAttributes)
	{
		System.out.println(user);
		this.services.addUser(user);
		
		// Add a temporary flash attribute for the success message
		redirectAttributes.addFlashAttribute("registrationSuccess", true);
		
		// Change redirection from /admin/services to the login page
		return "redirect:/login";
	}

	@GetMapping("/updatingUser/{id}")
	public String updateUser(@ModelAttribute User user, @PathVariable("id") int id)
	{
		this.services.updateUser(user, id);
		return "redirect:/admin/services";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id" )int id)
	{
		this.services.deleteUser(id);
		return "redirect:/admin/services";
	}
	


}
