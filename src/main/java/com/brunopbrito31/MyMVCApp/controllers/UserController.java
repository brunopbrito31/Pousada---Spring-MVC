package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brunopbrito31.MyMVCApp.models.entities.FormUser;
import com.brunopbrito31.MyMVCApp.models.entities.User;
import com.brunopbrito31.MyMVCApp.models.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> allUsers = userRepository.findAll();
        return allUsers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/search-by-id")
    public ResponseEntity<User> findUserById(@RequestParam("id") Long id){
        Optional<User> searchedUser = userRepository.findById(id);
        return searchedUser.isPresent() ? 
            ResponseEntity.ok().build() : 
            ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<User> findUserByMail(@RequestParam("mail") String mail){
        Optional<User> searchedUser = userRepository.findByMail(mail);
        return searchedUser.isPresent() ? 
            ResponseEntity.ok().build() : 
            ResponseEntity.noContent().build();
    }

    @GetMapping("/login")
    public ModelAndView getLogin(
        @ModelAttribute("form") FormUser formUser,
        Model model,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        // // Validation with condition
        // if(request.getSession().getAttribute("user") == null){
        //     System.out.println("Passou por aqui");
        //     response.sendRedirect("/");
        // }
        if(request.getSession().getAttribute("user") == null){
            model.addAttribute("formUser",formUser);
            model.addAttribute("error",false);
            return new ModelAndView("/area-restrita/login-pag");
        }

        response.sendRedirect("/restrict-area/dashboard");
        return null;
    }

    @PostMapping("/login")
    public ModelAndView validateLogin(
        @ModelAttribute("form") FormUser formUser,
        Model model,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException{
        String user = formUser.getUser();
        String password = formUser.getPassword();

        Optional<User> userSearched = userRepository.findByMail(user);
        if(userSearched.isPresent()){
            if(userSearched.get().getPassword().equals(password) && password != null && password.length() > 0){
                request.getSession().setAttribute("user", userSearched.get().getMail());
                request.getSession().setMaxInactiveInterval(600);
                response.sendRedirect("/restrict-area/dashboard");
            }
        }
        model.addAttribute("error",true);
        model.addAttribute("formUser",formUser);
        return new ModelAndView("/area-restrita/login-pag");
    }
}
