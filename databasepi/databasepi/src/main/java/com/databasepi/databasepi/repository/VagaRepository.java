package com.databasepi.databasepi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.databasepi.databasepi.model.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, String> {
	Vaga findByCodigo(long codigo);

	List<Vaga> findByQuarto(String nome);

	@Query(value = "select u from Vaga u where u.quarto like %?1%")
	List<Vaga> findByQuartos(String nome);
}