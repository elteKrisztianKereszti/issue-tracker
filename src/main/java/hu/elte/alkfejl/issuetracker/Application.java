/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker;

/**
 *
 * @author kkereszti
 */
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    
    @Autowired 
    private HandlerInterceptor authInterceptor; 

    @Override 
    public void addInterceptors(InterceptorRegistry registry) { 
        registry.addInterceptor(authInterceptor); 
    } 

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
