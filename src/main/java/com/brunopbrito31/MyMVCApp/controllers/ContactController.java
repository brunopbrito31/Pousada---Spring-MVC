package com.brunopbrito31.MyMVCApp.controllers;

import java.text.SimpleDateFormat;
import java.util.Optional;

import com.brunopbrito31.MyMVCApp.models.auxiliar.MailGenerator;
import com.brunopbrito31.MyMVCApp.models.entities.Contact;
import com.brunopbrito31.MyMVCApp.models.entities.enums.StatusContact;
import com.brunopbrito31.MyMVCApp.models.repositories.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id){
        Optional<Contact> searchedContact = contactRepository.findById(id);
        return !searchedContact.isPresent() ? 
            ResponseEntity.notFound().build(): 
            ResponseEntity.ok().body(searchedContact.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable Long id){

        System.out.println("id passado pro back= "+id);
       
        Optional<Contact> searchedContact = contactRepository.findById(id);
        if(!searchedContact.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Contact confirmedContact = searchedContact.get();
        confirmedContact.setStatus(StatusContact.DELETED);
        contactRepository.save(confirmedContact);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/response")
    public ResponseEntity sendResponse(
        @RequestParam("idcontact") Long idContact, 
        @RequestBody String msg
    ){

        Optional<Contact> searchedContact = contactRepository.findById(idContact);
        if(!searchedContact.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        Contact confirmedContact = searchedContact.get();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try{
            MailGenerator.sendMail(
                "Olá "+confirmedContact.getUser().getName()+", "+msg, 
                "Pousada Secreta: Retorno do Contato realizado no dia: "+sdf.format(confirmedContact.getSendDate()), 
                "brunopbrito31@gmail.com", 
                System.getenv("Mailer_Password"), 
                confirmedContact.getUser().getMail(), 
                "", 
                ""
            );
            confirmedContact.setStatus(StatusContact.CLOSED);
            contactRepository.save(confirmedContact);
    
            return ResponseEntity.ok().build();

        }catch(RuntimeException e){
            ResponseEntity.internalServerError();
        }
        return null;
    }
    
}
