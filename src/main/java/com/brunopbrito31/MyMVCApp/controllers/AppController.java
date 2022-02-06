package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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
import com.brunopbrito31.MyMVCApp.models.repositories.InitialPageRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.PhoneRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.ProductRepository;
import com.brunopbrito31.MyMVCApp.models.repositories.UseTermRepository;
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

    @Autowired
    private InitialPageRepository initialPageRepository;

    @Autowired
    private UseTermRepository useTermRepository;

    // Página Inicial
    @GetMapping
    public ModelAndView loadInitialPage(ModelMap model){
        // Carrega as confirgurações de texto, imagens
        model.addAttribute("form",new Form());
        model.addAttribute("quartos", productRepository.findAll());
        model.addAttribute("config", initialPageRepository.findById(1l).get());
        model.addAttribute("term", useTermRepository.findById(1l).get());
        return new ModelAndView("index");
    }

    // Processamento do envio do formulário de contato
    @PostMapping
    public ModelAndView processForm(
        @ModelAttribute("form") Form form,
        Model model,
        HttpSession session
    ) throws ParseException, IOException{

        // Proteção contra robô, cadastramento repetitivo e automatizado

        Date opdate = (Date)session.getAttribute("DataProxRes");

        // Primeiro envio, seta a data e horário do próximo envio como o atual
        if(opdate == null){
            session.setAttribute("DataProxRes",Date.from(Instant.now()));
            opdate = (Date)session.getAttribute("DataProxRes");
        }

        Long now = Date.from(Instant.now()).getTime();
        Long proRes = opdate.getTime(); 

        // Verifica se já está habilitado para realizar outro envio
        if(proRes <= now ){
            User userResult;
            Optional<User> searchedUser = userRepository.findByMail(form.getMail());
    
            // Verifica pelo email se o usuário já existe na base, em caso afirmativo recupera os dados do mesmo para associar ao contato
            if(searchedUser.isPresent()){
                userResult = searchedUser.get();
    
            }else{
                // Criação de Usuário

                // Tratamento do endereço
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
                // Tratamento do telefone
                Phone phoneAux = FormConverter.phoneMounterNoContentUser(form);
            
                Date formatedDate = FormConverter.StringToDate(form.getBirthDate());
            
                // Finaliza a criação do usuário
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
    
            // Criação do Contato
            Contact contact = Contact.builder().msg(form.getMessage())
            .sendDate(Date.from(Instant.now())).user(userResult).status(StatusContact.OPENED).build();
            contact = contactRepository.save(contact);
    
            // Definição da data e hora de permissão de próximo cadastro pelo usuário (1 Min após a conclusão do cadastro atual)
            Calendar cal = Calendar.getInstance();
            cal.setTime(Date.from(Instant.now()));
            cal.add(Calendar.MINUTE,1);
    
            session.setAttribute("DataProxRes", cal.getTime());

            // Envia email confirmando o contato
            MailGenerator.sendMail(
                "Olá "+userResult.getName()+" recebemos a sua mensagem e em até 2 dias úteis um de nossos consultores irá realizar contato contigo.", 
                "Pousada Secreta: Mensagem Recebida Com Sucesso!", 
                "brunopbrito31@gmail.com", 
                System.getenv("Mailer_Password"), 
                userResult.getMail(), 
                "", 
                ""
            );

            model.addAttribute("config",initialPageRepository.findById(1l).get());
            return new ModelAndView("form-sucess");
        }

        // Caso haja uma tentativa de reenvio em menos de 1 min
        model.addAttribute("config",initialPageRepository.findById(1l).get());
        return new ModelAndView("form-fail");
    }
    
}
