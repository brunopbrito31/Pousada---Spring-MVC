package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brunopbrito31.MyMVCApp.models.auxiliar.LivroTemp;
import com.brunopbrito31.MyMVCApp.models.entities.FormUser;
import com.brunopbrito31.MyMVCApp.models.entities.User;
import com.brunopbrito31.MyMVCApp.models.entities.enums.StatusUser;
import com.brunopbrito31.MyMVCApp.models.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate httpClient;

    // REST EndPoint
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> allUsers = userRepository.findAll();
        return allUsers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(allUsers);
    }

    // REST EndPoint for searches in index
    @GetMapping("/search-by-id")
    public ResponseEntity<User> findUserById(@RequestParam("id") Long id){
        Optional<User> searchedUser = userRepository.findById(id);
        return searchedUser.isPresent() ? 
            ResponseEntity.ok().build() : 
            ResponseEntity.noContent().build();
    }

    // REST EndPoint for searches in index
    @GetMapping("/search")
    public ResponseEntity<User> findUserByMail(@RequestParam("mail") String mail){
        Optional<User> searchedUser = userRepository.findByMail(mail);
        return searchedUser.isPresent() ? 
            ResponseEntity.ok().build() : 
            ResponseEntity.noContent().build();
    }

    // Page of login
    @GetMapping("/login")
    public ModelAndView getLogin(
        @ModelAttribute("form") FormUser formUser,
        Model model,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        if(request.getSession().getAttribute("user") == null){
            model.addAttribute("formUser",formUser);
            model.addAttribute("error",false);
            return new ModelAndView("/area-restrita/login-pag");
        }

        response.sendRedirect("/restrict-area/dashboard");
        return null;
    }

    // Login attempts
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
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(password,userSearched.get().getPassword())  && password != null && password.length() > 0){
                RequestContextHolder.getRequestAttributes().setAttribute("user", userSearched.get().getMail(), RequestAttributes.SCOPE_SESSION);
                RequestContextHolder.getRequestAttributes().setAttribute("aut", userSearched.get().getAutho().ordinal(), RequestAttributes.SCOPE_SESSION);
                request.getSession().setMaxInactiveInterval(600);
                response.sendRedirect("/restrict-area/dashboard");
            }
        }
        model.addAttribute("error",true);
        model.addAttribute("formUser",formUser);
        return new ModelAndView("/area-restrita/login-pag");
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        System.out.println(user.getPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser (@PathVariable Long id){
        try{
            Optional<User> searchedUser = userRepository.findById(id);
            if(!searchedUser.isPresent()){
                return ResponseEntity.badRequest().build();
            }
            
            User confirmedUser = searchedUser.get();
            confirmedUser.setStatusUser(StatusUser.INACTIVE);
            userRepository.save(confirmedUser);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    // Testando o consumo de uma API Feita em Node
    @GetMapping("/testes")
    public ResponseEntity<List<LivroTemp>> getLivros(){
        List<LivroTemp> livros = (List<LivroTemp>) httpClient.getForObject("http://localhost:5700/consultar", List.class);
        System.out.println(livros);
        return ResponseEntity.ok().body(livros);
    }
}
