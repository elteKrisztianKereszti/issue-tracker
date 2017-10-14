/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker.annotation;

import hu.elte.alkfejl.issuetracker.model.User;

import java.lang.annotation.*;

/**
 *
 * @author kkereszti
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Role {
    User.Role[] value() default { User.Role.GUEST };
}

