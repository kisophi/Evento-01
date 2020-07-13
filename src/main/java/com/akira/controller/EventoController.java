package com.akira.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.akira.model.Convidado;
import com.akira.model.Evento;
import com.akira.repository.ConvidadoRepository;
import com.akira.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	
	//Pagina Incial
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	//Lista dos Evento
	@RequestMapping("/eventoLista")
	public String lista(Evento evento,Model model) {
		
		//pega todos eventos
		List<Evento> eventos = er.findAll();
		
		//Adiciona na session
		model.addAttribute("eventos", eventos);
		
		return "evento-lista";
	}
	
	//Direciona para o Form de Cadastro de eventos
	@RequestMapping(value = "/eventoCadastro",method = RequestMethod.GET)
	public String formCadastro() {
		return "evento-cadastro";
	}
	
	//Salva o Cadastro de Eventos
	@RequestMapping(value = "/eventoCadastro",method = RequestMethod.POST)
	public String SalvaCadastro(@Valid Evento evento,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : result.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // message do erro @annotations do model
			}
			
			model.addAttribute("msg", msg);
			return "evento-cadastro";
		}
		
		er.save(evento);
		
		//redireciona para lista de eventos
		return "redirect:/eventoLista";
	}
	
	//Exclui Evento
	@RequestMapping("/excluiEvento/{id}")
	public String excluiEvento(@PathVariable("id") Integer eventoId) {
		
		//Pega Evento pelo ID pela URL pathVariable e deleta
		er.deleteById(eventoId);
		
		return "redirect:/eventoLista";
	}
	
	//Direciona para pagina de edita evento
	@RequestMapping(value = "/editaEvento/{id}",method = RequestMethod.GET)
	public String formEditaEvento(@PathVariable("id") Integer eventoId,Model model) {
		
		//Pega evento pelo ID da URL pathVariable
		Optional<Evento> evento = er.findById(eventoId);
		
		//Atribui na session
		model.addAttribute("evento", evento);
		
		//direciona para pagina edita evento
		return "evento-edita";
	}
	
	//Salva o Edita Evento
	@RequestMapping(value = "/editaEvento/{id}",method = RequestMethod.POST)
	public String salvaEditaEvento(@PathVariable("id") Integer eventoId,Evento evento) {
		
		//salva o Evento editado
		er.save(evento);
		
		return "redirect:/eventoLista";
	}
	
	
	//pega o evento e preenche na pagina direcionada
	@RequestMapping(value = "/convidadoCadastro/{id}",method = RequestMethod.GET)
	public String formConvidado(@PathVariable("id")Integer eventoId, Model model,Convidado convidado) {
		//pega o Evento pelo Id
		Evento evento = er.findByEventoId(eventoId);
		
		//atribui 
		model.addAttribute("evento", evento);
		
		//Pega a lista de Convidados do Evento Buscado
		List<Convidado> convidados = cr.findByEvento(evento);
		
		//atribui
		model.addAttribute("convidados", convidados);
		
		return "convidado-cadastro";
	}
	
	//salva o Convidado
	@RequestMapping(value = "/convidadoCadastro/{id}",method = RequestMethod.POST)
	public String salvaformConvidado(@PathVariable("id")Integer eventoId,Convidado convidado) {
		//pega o evento
		Evento evento = er.findByEventoId(eventoId);
		
		//Seta no o evento no convidado
		convidado.setEvento(evento);
		
		//salva o convidado
		cr.save(convidado);
		
		//Redireciona para propria pagina
		return "redirect:/convidadoCadastro/{id}";
	}
	
	
	//Exclui os convidado do Evento
	@RequestMapping("/excluiConvidado/{id}")
	public String excluiConvidado(@PathVariable("id")Integer convidadoId) {

		//Pega convidado pelo Id e deleta como obj
		Convidado c = cr.findByConvidadoId(convidadoId);
		cr.delete(c);

		//pego o id do Evento para passsar na Url do redirect
		Evento evento = c.getEvento();
		Integer eventoId = evento.getEventoId();

		return "redirect:/convidadoCadastro/"+eventoId;
	}
	
	//Busca eventos por Descricao
	@RequestMapping("/buscaEvento")
	public String buscaEvento(String eventoDescricao,Model model) {
		
		//busca todos por nome(Descricao)
		List<Evento> eventos = er.findByEventoDescricao(eventoDescricao);
		
		model.addAttribute("eventos", eventos);
		
		return "evento-busca";
	}
	
	
	
	
}
