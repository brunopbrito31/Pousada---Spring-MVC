package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.brunopbrito31.MyMVCApp.models.auxiliar.FormConverter;
import com.brunopbrito31.MyMVCApp.models.auxiliar.MailGenerator;
import com.brunopbrito31.MyMVCApp.models.entities.Adress;
import com.brunopbrito31.MyMVCApp.models.entities.Contact;
import com.brunopbrito31.MyMVCApp.models.entities.Form;
import com.brunopbrito31.MyMVCApp.models.entities.Phone;
import com.brunopbrito31.MyMVCApp.models.entities.User;
import com.brunopbrito31.MyMVCApp.models.entities.enums.Gender;
import com.brunopbrito31.MyMVCApp.models.entities.enums.StatusContact;
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
    public ModelAndView goSave(
        @ModelAttribute("form") Form form,
        Model model,
        HttpServletRequest request
    ) throws ParseException, IOException{

        // Protection for excessive submits
        Date opdate = (Date)request.getSession().getAttribute("DataProxRes");

        if(opdate == null){
            request.getSession().setAttribute("DataProxRes",Date.from(Instant.now()));
            opdate = (Date)request.getSession().getAttribute("DataProxRes");
        }
        Long now = Date.from(Instant.now()).getTime();
        Long proRes = opdate.getTime(); 

        if(proRes <= now ){
            User userResult;
            Optional<User> searchedUser = userRepository.findByMail(form.getMail());
    
            if(searchedUser.isPresent()){
                userResult = searchedUser.get();
    
            }else{
                Optional<Adress> oldAdress = adressRespository.findByZipCode(form.getZipPostal());
                Adress adressAux;
                if(oldAdress.isPresent()){
                    adressAux = oldAdress.get();
                }else{
                    adressAux = Adress.builder()
                    .city(form.getCity()).country("Brasil").district(form.getDistrict())
                    .state(form.getState()).street(form.getStreet()).zipCode(form.getZipPostal()).build();
                    adressAux = adressRespository.save(adressAux);
                }
                // Phone Tratament
                Phone phoneAux = FormConverter.phoneMounterNoContentUser(form);
                // Date Tratament
                Date formatedDate = FormConverter.StringToDate(form.getBirthDate());
                
                userResult = User.builder()
                .adress(adressAux).birthDate(formatedDate)
                .gender(Gender.intToGender(form.getGender()))
                .mail(form.getMail()).name(form.getName()).build();
                
                userResult = userRepository.save(userResult);
    
                phoneAux.setUser(userResult);
                phoneAux = phoneRepository.save(phoneAux);
    
                userResult.setPhones(new ArrayList<>());
                userResult.getPhones().add(phoneAux);
            }
    
            Contact contact = Contact.builder().msg(form.getMessage())
            .sendDate(Date.from(Instant.now())).user(userResult).status(StatusContact.OPENED).build();
            contact = contactRepository.save(contact);
    
            Calendar cal = Calendar.getInstance();
            cal.setTime(Date.from(Instant.now()));
            cal.add(Calendar.MINUTE,1);
    
            request.getSession().setAttribute("DataProxRes", cal.getTime());
    
            MailGenerator.sendMail(
                "Olá "+userResult.getName()+" recebemos a sua mensagem e em até 2 dias úteis um de nossos consultores irá realizar contato contigo.", 
                "Pousada Secreta: Mensagem Recebida Com Sucesso!", 
                "brunopbrito31@gmail.com", 
                System.getenv("Mailer_Password"), 
                userResult.getMail(), 
                "", 
                ""
            );
            model.addAttribute("form",new Form());
           
            return new ModelAndView("form-sucess");
        }
        return new ModelAndView("form-fail");
    }
    
}
