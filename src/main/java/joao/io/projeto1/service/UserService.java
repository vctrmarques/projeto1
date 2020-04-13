package joao.io.projeto1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import joao.io.projeto1.entity.User;

@Service
public interface UserService {
	
	public List<User> findAll();
	
	public Optional<User> findById(String id);
	
	public User findByCpf(String cpf);

	public void save(User user);
	
	public void deleteUser(User user);
	
	public void saveOrUpdateUser(User user);

}
