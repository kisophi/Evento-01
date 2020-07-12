package com.akira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akira.model.Convidado;
import com.akira.model.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidado, Integer>{
	
	public Convidado findByConvidadoId(Integer convidadoId);
	public List<Convidado> findByEvento(Evento evento);
	
}
