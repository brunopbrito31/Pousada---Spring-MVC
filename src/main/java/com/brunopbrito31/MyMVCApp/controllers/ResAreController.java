package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/restrict-area")
public class ResAreController {


    @GetMapping("/dashboard")
    public ModelAndView getDash(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Model model
    ) throws IOException
    {
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/users/login");
        }
        model.addAttribute("userOn", request.getSession().getAttribute("user"));
        return new ModelAndView("/area-restrita/dashboard");
    }
    
}
