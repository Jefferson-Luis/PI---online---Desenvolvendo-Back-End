package com.databasepi.databasepi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.databasepi.databasepi.model.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, String> {

	Funcionario findById(long id);

	// criado para a busca
	Funcionario findByNome(String nome);

	//Busca para vÃ¡rios nomes FuncionÃ¡rios
	@Query(value="select u from Funcionario u where u.nome like %?1%")	  
	List<Funcionario> findByNomes(String nome);
}
