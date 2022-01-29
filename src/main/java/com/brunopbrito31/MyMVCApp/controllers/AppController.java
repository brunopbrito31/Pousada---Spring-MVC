package com.brunopbrito31.MyMVCApp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.brunopbrito31.MyMVCApp.models.entities.Form;
import com.brunopbrito31.MyMVCApp.models.entities.enums.Gender;
import com.brunopbrito31.MyMVCApp.models.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AppController {


    // @GetMapping("/")
    // public String getPag(){
    //     System.out.println("Entrou aqui");
    //     return "index";
    // }

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ModelAndView getTest(ModelMap model){
        model.addAttribute("form",new Form());
        model.addAttribute("quartos", productRepository.findAll());
        model.addAttribute("");
        return new ModelAndView("index");
    }

    @PostMapping
    public ModelAndView goSave(@ModelAttribute("form") Form form, Model model){
        String name = form.getName();
        Integer genderNum = form.getGender();
        Gender gender = Gender.intToGender(genderNum);
        System.out.println("o genero final é : "+gender.toString());
        System.out.println("Cadastrou o nome"+name);
        form.setName("");
        return new ModelAndView("index");
    }
    
}
