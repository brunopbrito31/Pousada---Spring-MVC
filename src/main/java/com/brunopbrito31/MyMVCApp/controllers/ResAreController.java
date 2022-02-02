package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brunopbrito31.MyMVCApp.models.entities.Contact;
import com.brunopbrito31.MyMVCApp.models.repositories.CardMenResAreRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.ContactRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.InitialPageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/restrict-area")
public class ResAreController {

    @Autowired
    private CardMenResAreRepository cardMenResAreRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private InitialPageRepository initialPageRepository;

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
        HttpServletResponse response,
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize
    ) throws IOException{  
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/users/login");
        }
        Integer aut;
        if(request.getSession().getAttribute("aut") == null){
            aut = 0;
        }else{
            aut = Integer.parseInt(request.getSession().getAttribute("aut").toString());
        }
        Integer startLimit = pageNo * pageSize;
        Long totalItems = 0l;

        if( aut == 0){
            totalItems = contactRepository.persOpenCount(); 
        }else if( aut == 1){
            totalItems = contactRepository.persCount(); 
        }

        Double qtPagesAux = (double) totalItems / pageSize;
        Integer qtPages;
        if(qtPagesAux != qtPagesAux.intValue()){
            qtPages = qtPagesAux.intValue() + 1;
        }else{
            qtPages = qtPagesAux.intValue();
        }

        List<Contact> contacts = null; 
        if( aut == 0 ){
            contacts = contactRepository.findAllActiveOpenContacts(startLimit,pageSize);
        }else if( aut == 1 ){
            contacts = contactRepository.findAllActiveContacts(startLimit,pageSize);
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        model.addAttribute("dateFormater", sdf);
        model.addAttribute("aut",request.getSession().getAttribute("aut"));
        model.addAttribute("contacts",contacts);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("qtPages",qtPages);
        return new ModelAndView("/area-restrita/list-contacts");
    }

    @GetMapping("/users")
    public ModelAndView getUsers(
        Model model,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException{
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/users/login");
        }
        return new ModelAndView("/area-restrita/list-users");
    }

    @GetMapping("/configurations")
    public ModelAndView getAdm(
        Model model,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException{
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/users/login");
        }
        model.addAttribute("config",initialPageRepository.findById(1l).get());
        return new ModelAndView("/area-restrita/adm");
    }

    @GetMapping("/logoff")
    public void getLogoff(
        Model model,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException{
        request.getSession().invalidate();
        response.sendRedirect("/users/login");
    }
    
}
