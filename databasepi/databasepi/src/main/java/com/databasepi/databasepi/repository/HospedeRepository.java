package com.databasepi.databasepi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.databasepi.databasepi.model.Hospede;
import com.databasepi.databasepi.model.Vaga;

public interface HospedeRepository extends CrudRepository<Hospede, String> {

	Iterable<Hospede> findByVaga(Vaga vaga);

	Hospede findByCpf(String cpf);

	// para poder deletar hospede pelo id
	Hospede findById(long id);

//	List<Hospede> findBynomeHospede(String nomeHospede);

	@Query(value = "select u from Hospede u where u.nomeHospede like %?1%")
	List<Hospede> findByNomesHospedes(String nomeHospede);

}
