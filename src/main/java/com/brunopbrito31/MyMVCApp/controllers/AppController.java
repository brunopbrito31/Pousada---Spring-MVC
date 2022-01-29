package com.brunopbrito31.MyMVCApp.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.brunopbrito31.MyMVCApp.models.entities.Adress;
import com.brunopbrito31.MyMVCApp.models.entities.Contact;
import com.brunopbrito31.MyMVCApp.models.entities.Form;
import com.brunopbrito31.MyMVCApp.models.entities.Phone;
import com.brunopbrito31.MyMVCApp.models.entities.User;
import com.brunopbrito31.MyMVCApp.models.entities.enums.Gender;
import com.brunopbrito31.MyMVCApp.models.repositories.AdressRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.ContactRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.PhoneRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.ProductRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.UserRepository;

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


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdressRepository adressRespository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public ModelAndView getTest(ModelMap model){
        model.addAttribute("form",new Form());
        model.addAttribute("quartos", productRepository.findAll());
        model.addAttribute("");
        return new ModelAndView("index");
    }

    @PostMapping
    public ModelAndView goSave(@ModelAttribute("form") Form form, Model model) throws ParseException{
        User userResult;
        Optional<User> searchedUser = userRepository.findByMail(form.getMail());

        if(searchedUser.isPresent()){
            userResult = searchedUser.get();
        }else{
            Adress adressAux = Adress.builder()
            .city(form.getCity()).country("Brasil").district(form.getDistrict())
            .state(form.getState()).street(form.getStreet()).zipCode(form.getZipPostal()).build();
            adressAux = adressRespository.save(adressAux);

            // Phone Tratament
            String areaCode = form.getPhone().substring(1,3);
            String phonePt1 = form.getPhone().substring(5,10);
            String phonePt2 = form.getPhone().substring(11);
            String phoneRec = phonePt1.concat(phonePt2).trim();
            Phone phoneAux = Phone.builder().areaCode(areaCode).number(phoneRec).build();

            // Date Tratament
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd"); 
            Date formatedDate = format.parse(form.getBirthDate()); 

            userResult = User.builder()
            .adress(adressAux).birthDate(formatedDate)
            .gender(Gender.intToGender(form.getGender())).mail(form.getMail()).name(form.getName()).build();
            userResult = userRepository.save(userResult);

            phoneAux.setUser(userResult);
            phoneAux = phoneRepository.save(phoneAux);

            userResult.setPhones(new ArrayList<>());
            userResult.getPhones().add(phoneAux);
        }

        Contact contact = Contact.builder().msg(form.getMessage())
        .sendDate(Date.from(Instant.now())).user(userResult).build();
        contact = contactRepository.save(contact);

        model.addAttribute("form",new Form());
        model.addAttribute("quartos", productRepository.findAll());
        return new ModelAndView("index");
    }
    
}
