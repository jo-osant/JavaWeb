package com.JavaWebController.app3.Contoller;

import com.JavaWebController.app3.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String mostrarTelaLogin(){
        return "login.html";
    }

    @PostMapping("/validar")
    public String validarCredenciaisLogin(@ModelAttribute User usr, Model modelo, HttpSession session){
        if (usr.getLogin().equalsIgnoreCase("joao") && usr.getSenha().equalsIgnoreCase("123")){
        session.setAttribute("UsuarioLogado", usr);
            return "redirect:/home";
        }else{
            modelo.addAttribute("msg", "Acesso invalido");
            return "login.html";
        }
    }

    @GetMapping("/home")
    public String mostrarTelaInicial(HttpSession session, Model modelo){
        User usr = (User) session.getAttribute("UsuarioLogado");
        if(usr == null){
            modelo.addAttribute("msg", "Sessão do usuario expirada");
            return "redirect:/login";
        }else{
            modelo.addAttribute("nomeUsuario", usr.getLogin());
            return "home.html";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
