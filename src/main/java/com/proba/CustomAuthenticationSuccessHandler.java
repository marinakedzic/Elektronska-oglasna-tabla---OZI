/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba;


import com.proba.model.Admin;
import com.proba.service.AdminService;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;



/** Out success handler to land logged in user to different page based on his role */
@Component
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler 
{
	/** Getting reference to UserService */
	@Autowired
	private AdminService adminService;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Authentication authentication) 
                    throws IOException, ServletException, RuntimeException 
    {
        HttpSession session = httpServletRequest.getSession();
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Admin admin = adminService.findAdminByEmail(authUser.getUsername());
             
        session.setAttribute("username", authUser.getUsername());
      
        //set our response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> 
                                { 
                                    if(authority.getAuthority().equals("ADMIN")) 
                                    { 
                                    
                                        try
                                        {
                                            //since we have created our custom success handler, its up to us to where
                                            //we will redirect the user after successfully login
                                            httpServletResponse.sendRedirect("/admin/home");
                                        } 
                                        catch (IOException e) 
                                        {
                                            throw new RuntimeException(e);
                                        }                                                                           
                                    }
                                    else if (authority.getAuthority().equals("PARENT"))
                                    {
                                       
                                        try
                                        {
                                            //since we have created our custom success handler, its up to us to where
                                            //we will redirect the user after successfully login
                                            httpServletResponse.sendRedirect("/parents/home");
                                        } 
                                        catch (IOException e) 
                                        {
                                            throw new RuntimeException(e);
                                        }   
                                    }
                                    else if (authority.getAuthority().equals("TEACHER"))
                                    {
                                       
                                        try
                                        {
                                            //since we have created our custom success handler, its up to us to where
                                            //we will redirect the user after successfully login
                                            httpServletResponse.sendRedirect("/teachers/home");
                                        } 
                                        catch (IOException e) 
                                        {
                                            throw new RuntimeException(e);
                                        }   
                                    }
                                });

    }

}