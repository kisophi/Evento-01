package com.akira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.akira.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer>{

	public Evento findByEventoId(Integer eventoId);
	
	@Query("select e from Evento e where eventoDescricao like %?1%")
	public List<Evento> findByEventoDescricao(String eventoDescricao);
}
