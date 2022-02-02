package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brunopbrito31.MyMVCApp.models.auxiliar.Paginator;
import com.brunopbrito31.MyMVCApp.models.entities.Contact;
import com.brunopbrito31.MyMVCApp.models.repositories.CardMenResAreRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.ContactRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.InitialPageRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.UserRepository;

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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public ModelAndView getDash(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Model model
    ) throws IOException
    {
        isAuthenticated(request,response);
        if(request.getSession().getAttribute("user") != null){
            model.addAttribute("idUsu",request.getSession().getAttribute("user").toString());
            model.addAttribute("cards", cardMenResAreRepository.findAll());
            model.addAttribute("userOn", request.getSession().getAttribute("user"));
        }
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
        isAuthenticated(request,response);

        Integer aut = isAutSessionEmpty(request);
        
        Long totalItems = 0l;
        if( aut == 0){
            totalItems = contactRepository.persOpenCount(); 
        }else if( aut == 1){
            totalItems = contactRepository.persCount(); 
        }

        Paginator paginator = new Paginator(
            pageNo,
            pageSize,
            totalItems
        );

        List<Contact> contacts = null; 
        if( aut == 0 ){
            contacts = contactRepository.findAllActiveOpenContacts(
                paginator.getStartLimit(),
                pageSize
            );

        }else if( aut == 1 ){
            contacts = contactRepository.findAllActiveContacts(
                paginator.getStartLimit(),
                pageSize
            );
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        model.addAttribute("dateFormater", sdf);
        model.addAttribute("aut",request.getSession().getAttribute("aut"));
        model.addAttribute("contacts",contacts);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("qtPages",paginator.getQtPages());
        return new ModelAndView("/area-restrita/list-contacts");
    }

    @GetMapping("/users")
    public ModelAndView getUsers(
        Model model,
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize
    ) throws IOException{
        isAuthenticated(request,response);

        Paginator paginator = new Paginator(
            pageNo,
            pageSize,
            userRepository.count()
        );

        model.addAttribute("pageNo",pageNo);
        model.addAttribute("qtPages",paginator.getQtPages());
        model.addAttribute(
            "users", 
            userRepository.findUsersWithPagination(
                paginator.getStartLimit(),
                pageSize
            )
        );
        return new ModelAndView("/area-restrita/list-users");
    }

    @GetMapping("/configurations")
    public ModelAndView getAdm(
        Model model,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException{
        isAuthenticated(request,response);

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

    private void isAuthenticated(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws IOException{
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/users/login");
        }
    }

    private Integer isAutSessionEmpty(HttpServletRequest req){
        if(req.getSession().getAttribute("aut") == null){
            return 0;
        }else{
            return Integer.parseInt(req.getSession().getAttribute("aut").toString());
        }
    }
    
}
