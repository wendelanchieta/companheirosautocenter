package com.companheirosautocenter.appautocenter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.companheirosautocenter.appautocenter.services.EstadoService;

@Controller
public class DefaultResource {
	
	@Autowired
	private EstadoService estadoService; 

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
    public String cadastrarpessoa(Model model) {
    	model.addAttribute("posts", estadoService.findAllByOrderByUfAsc());
    	return "cadastrar/cadastrarpessoa";
    }
    
    @RequestMapping("/cadastrarveiculo")
    public String cadastrarveiculo() {
    	return "cadastrar/cadastrarveiculo";
    }
    
    @GetMapping("/tes")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/posttes");
		mv.addObject("posts", estadoService.findAllByOrderByUfAsc());
		
		return mv;
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

