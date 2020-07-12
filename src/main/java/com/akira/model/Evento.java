package com.akira.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eventoId;
	private String eventoDescricao;
	private String eventoData;
	private String eventoLocal;
	private String eventoHorario;
	
	@OneToMany
	private List<Convidado> convidado;
	
	

	public List<Convidado> getConvidado() {
		return convidado;
	}

	public void setConvidado(List<Convidado> convidado) {
		this.convidado = convidado;
	}

	public Integer getEventoId() {
		return eventoId;
	}

	public void setEventoId(Integer eventoId) {
		this.eventoId = eventoId;
	}

	public String getEventoDescricao() {
		return eventoDescricao;
	}

	public void setEventoDescricao(String eventoDescricao) {
		this.eventoDescricao = eventoDescricao;
	}

	public String getEventoData() {
		return eventoData;
	}

	public void setEventoData(String eventoData) {
		this.eventoData = eventoData;
	}

	public String getEventoLocal() {
		return eventoLocal;
	}

	public void setEventoLocal(String eventoLocal) {
		this.eventoLocal = eventoLocal;
	}

	public String getEventoHorario() {
		return eventoHorario;
	}

	public void setEventoHorario(String eventoHorario) {
		this.eventoHorario = eventoHorario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
