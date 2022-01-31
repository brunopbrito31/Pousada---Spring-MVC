package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brunopbrito31.MyMVCApp.models.entities.Contact;
import com.brunopbrito31.MyMVCApp.models.repositories.CardMenResAreRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/restrict-area")
public class ResAreController {

    @Autowired
    private CardMenResAreRepository cardMenResAreRepository;

    @Autowired
    private ContactRepository contactRepository;

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
        model.addAttribute("cards", cardMenResAreRepository.findAll());
        model.addAttribute("userOn", request.getSession().getAttribute("user"));
        return new ModelAndView("/area-restrita/dashboard");
    }

    @GetMapping("/contacts")
    public ModelAndView getContactsList(
        Model model, 
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws IOException{  
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/users/login");
        }
        List<Contact> contacts = contactRepository.findAllActiveContacts();

        model.addAttribute("aut",request.getSession().getAttribute("aut"));
        model.addAttribute("contacts",contacts);
        return new ModelAndView("/area-restrita/list-contacts");
    }
    
}
