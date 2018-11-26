package com.companheirosautocenter.appautocenter.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultResource {

	@Value("${spring.application.name}")
    String appName;
 
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    
    @RequestMapping("/logon")
    public String logon() {
    	return "logon";
    }
    
    @RequestMapping("/testes")
    public String testes() {
    	return "cadastrar/testes";
    }
    
    @RequestMapping("/cadastrarpessoa")
    public String cadastrarpessoa() {
    	return "cadastrar/cadastrarpessoa";
    }
    
    @RequestMapping("/cadastrarveiculo")
    public String cadastrarveiculo() {
    	return "cadastrar/cadastrarveiculo";
    }
    
    /**
     * @see {@link http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/}
     * 
     * @return
     */
    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}

