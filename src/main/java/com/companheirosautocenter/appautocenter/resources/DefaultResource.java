package com.companheirosautocenter.appautocenter.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.companheirosautocenter.appautocenter.domain.Cidade;
import com.companheirosautocenter.appautocenter.domain.Pedido;
import com.companheirosautocenter.appautocenter.services.CidadeService;
import com.companheirosautocenter.appautocenter.services.EstadoService;

@Controller
public class DefaultResource {
	
	@Autowired
	private EstadoService estadoService; 
	
	@Autowired
	private CidadeService cidadeService;

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
    
    @RequestMapping("/cadastraros")
    public String cadastraros() {
    	return "cadastrar/cadastraros";
    }
    
    /*@RequestMapping(value = "/getCidades", method = RequestMethod.GET)
	public @ResponseBody
	List<Cidade> getTags(@RequestParam String nomeCidade, HttpServletResponse response) {
		return cidadeService.findByNomeOrderByNomeDesc(nomeCidade);

	}*/
    
    @RequestMapping(value = "/autocompletecidades", method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> autocompletecidades() {
		return ResponseEntity.ok().body(cidadeService.findAllByOrderByNomeAsc());
	}
    
    /*@RequestMapping(value = "/getCidades", method = RequestMethod.GET)
	public @ResponseBody
	List<Cidade> getTags(HttpServletResponse response) {
		return cidadeService.findAllByOrderByNomeAsc();

	}*/
    
    
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

