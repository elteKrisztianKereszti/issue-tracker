/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker.service;

import hu.elte.alkfejl.issuetracker.exception.UserNotValidException;
import hu.elte.alkfejl.issuetracker.model.User;
import hu.elte.alkfejl.issuetracker.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author kkereszti
 */

@Service
@SessionScope
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User user;
        
    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userRepository.findByUsername(user.getUsername()).get();
        }
        throw new UserNotValidException();
    }

    public void logout() { 
        user = null; 
    } 

    public User register(User user) {
        return this.user = userRepository.save(user);
    }

    public boolean isValid(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }
    
    public boolean isLoggedIn() {
        return user != null;
    }
    
    public User getLoggedInUser() { 
        return user; 
    } 

}

