package joao.io.projeto1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import joao.io.projeto1.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	User findByName(String nome);
	
	User findByEmail(String email);
	
	User findByCpf(String cpf);
	
	User findBySexo(String sexo);
	
	User findByNaturalidade(String naturalidade);
	
	User findByNacionalidade(String nacionalidade);
	
	User findByDataNascimento(String dataNascimento);
	
}
