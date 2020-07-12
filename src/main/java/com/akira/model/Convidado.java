package com.akira.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Convidado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer convidadoId;
	private String convidadoNome;
	private String convidadoCpf;

	@ManyToOne
	private Evento evento;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Integer getConvidadoId() {
		return convidadoId;
	}

	public void setConvidadoId(Integer convidadoId) {
		this.convidadoId = convidadoId;
	}

	public String getConvidadoNome() {
		return convidadoNome;
	}

	public void setConvidadoNome(String convidadoNome) {
		this.convidadoNome = convidadoNome;
	}

	public String getConvidadoCpf() {
		return convidadoCpf;
	}

	public void setConvidadoCpf(String convidadoCpf) {
		this.convidadoCpf = convidadoCpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
