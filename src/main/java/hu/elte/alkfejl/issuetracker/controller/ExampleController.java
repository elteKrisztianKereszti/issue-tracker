/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker.controller;

import hu.elte.alkfejl.issuetracker.annotation.Role;
import hu.elte.alkfejl.issuetracker.model.*;
import static hu.elte.alkfejl.issuetracker.model.User.Role.*;
import hu.elte.alkfejl.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author kkereszti
 */

@RestController
@RequestMapping("/example")
public class ExampleController {
    
    @Autowired
    private UserService userService;
    
    @Role({USER})
    @RequestMapping("/get")
    public String getParameters(@RequestParam(required = false, defaultValue = "Metal", name = "name") String name) {
        User user = userService.getUser();
        if (user == null) {
            return "Hello " + name; 
        }
        
        return "Hello " + name + " (" + user.getRole().toString() + ")"; 
    }
    
    @RequestMapping("/{name}")
    public String pathParameter(@PathVariable String name) {
        return "Hello " + name; 
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public String postParameters(@RequestBody Person person) {
         return "Hello " + person.FirstName; 
    }
}
