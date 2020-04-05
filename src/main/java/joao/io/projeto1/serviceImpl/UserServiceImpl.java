package joao.io.projeto1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joao.io.projeto1.entity.User;
import joao.io.projeto1.repository.UserRepository;
import joao.io.projeto1.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}
	
	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	@Override
	public void saveOrUpdateUser(User user) {
		userRepository.save(user);
    }

	@Override
    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }
	
}
