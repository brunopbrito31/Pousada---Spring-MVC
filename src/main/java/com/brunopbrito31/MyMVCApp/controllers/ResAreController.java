package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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


    // Página Inicial da Área Restrita
    @GetMapping("/dashboard")
    public ModelAndView getDash(
        HttpSession session,
        Model model
    ) throws IOException
    {
        if(session.getAttribute("user") != null){
            model.addAttribute("idUsu",session.getAttribute("user").toString());
            model.addAttribute("cards", cardMenResAreRepository.findAll());
            model.addAttribute("userOn", session.getAttribute("user"));
        }
        return new ModelAndView("/area-restrita/dashboard");
    }

    // Página de Listagem de contatos
    @GetMapping("/contacts")
    public ModelAndView getContactsList(
        Model model, 
        HttpSession session,
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue="") String content
    ) throws IOException{  

        // Trecho para evitar erro quando um usuário não logado tentar acessar, mocka um valor fake na sessão para permitir o redirecionamento para a tela de login sem quebrar
        Integer aut = isAutSessionEmpty(session);
        
        Long totalItems = 0l;

        // Verifica o nível de Permissão do Usuário  para determinar o total de itens com base nos filtros de cada nível de usuário e pesquisa
        if( aut == 0){
            if(content.equals("")){
                totalItems = contactRepository.persOpenCount(); 

            }else{
                totalItems = contactRepository.findAllActiveOpenContactsWithFilterByNameOrMailCount(content);
                System.out.println("total de itens= "+totalItems);
            }
        }else if( aut == 1){
            if(content.equals("")){
                totalItems = contactRepository.persCount(); 
            }else{
                totalItems = contactRepository.findAllActiveContactsWithFilterNameOrMailCount(content);
                System.out.println("total de itens= "+totalItems);
            }
        }

        Paginator paginator = new Paginator(
            pageNo,
            pageSize,
            totalItems
        );

        // Verifica o nível de permissão do usuário para trazer a listagem correta do banco
        List<Contact> contacts = null; 
        if( aut == 0 ){
            if(content.equals("")){
                contacts = contactRepository
                .findAllActiveOpenContacts(
                    paginator.getStartLimit(),
                    pageSize
                );
            }else{
                contacts = contactRepository
                .findAllActiveOpenContactsWithFilterByNameOrMail(
                    paginator.getStartLimit(),
                    pageSize,
                    content
                );
            }

        }else if( aut == 1 ){
            if(content.equals("")){
                contacts = contactRepository
                .findAllActiveContacts(
                    paginator.getStartLimit(),
                    pageSize
                );
            }else{
                contacts = contactRepository
                .findAllActiveContactsWithFilterNameOrMail(
                    paginator.getStartLimit(),
                    pageSize,
                    content
                );
            }
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        model.addAttribute("content",content);
        model.addAttribute("dateFormater", sdf);
        model.addAttribute("aut",session.getAttribute("aut"));
        model.addAttribute("contacts",contacts);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("qtPages",paginator.getQtPages());
        return new ModelAndView("/area-restrita/list-contacts");
    }

    // Página de Usuários
    @GetMapping("/users")
    public ModelAndView getUsers(
        Model model,
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize
    ) throws IOException{

        // Objeto de paginação
        Paginator paginator = new Paginator(
            pageNo,
            pageSize,
            userRepository.countActiveUsers()
        );

        // Passa para view o valor da pagina atual, a quantidade de páginas e a lista de usuários a serem exibidos na pagina
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

    // Página de Configurações de texto da pagina inicial do site
    @GetMapping("/configurations")
    public ModelAndView getAdm(
        Model model
    ) throws IOException{
        model.addAttribute("config",initialPageRepository.findById(1l).get());
        return new ModelAndView("/area-restrita/adm");
    }

    // Página de Galeria de Imagens
    @GetMapping("/galery")
    public ModelAndView addImgToGalery(
        Model model
    ) throws IOException{
        return new ModelAndView("/area-restrita/galery-page");
    }

    // Logoff da Area Restrita
    @GetMapping("/logoff")
    public void getLogoff(
        HttpSession session,
        HttpServletResponse response
    ) throws IOException{
        session.invalidate();
        response.sendRedirect("/users/login");
    }

    // Condição para garantir o redirecionamento para a tela de login em algumas páginas caso o usuário esteja deslogado
    private Integer isAutSessionEmpty(HttpSession session){
        return session.getAttribute("aut") == null ? 0
        : Integer.parseInt(session.getAttribute("aut").toString());
    }
    
}
