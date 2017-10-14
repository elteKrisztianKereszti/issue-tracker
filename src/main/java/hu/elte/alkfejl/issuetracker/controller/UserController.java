/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker.controller;

import hu.elte.alkfejl.issuetracker.exception.UserNotValidException;
import hu.elte.alkfejl.issuetracker.model.User;
import hu.elte.alkfejl.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.issuetracker.model.User.Role.USER;

/**
 *
 * @author kkereszti
 */

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/greet")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        try { 
            userService.login(user); 
            return redirectToGreeting(user);
        } 
        catch (UserNotValidException e) { 
            model.addAttribute("error", true); 
            return "login"; 
        } 
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setRole(USER);
        userService.register(user);

        return redirectToGreeting(user);
    }

    private String redirectToGreeting(@ModelAttribute User user) {
        return "redirect:/user/greet?name=" + user.getUsername();
    }

}
