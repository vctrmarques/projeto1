package joao.io.projeto1.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import joao.io.projeto1.entity.User;
import joao.io.projeto1.repository.UserRepository;

@Component
public class DataInicializr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		List<User> users = userRepository.findAll();
		
		try {
			if(users.isEmpty()) {
				creatUsers("André Marques","masculino","andmarques@gmailcom",formato.parse("05/05/2015"),"Recife","Brasileiro","424.471.110-45");
				creatUsers("masculino","masculino", "testando@gmailcom",formato.parse("05/05/2018"),"Olinda","Brasileiro","644.992.980-40");
				creatUsers("masculino","masculino", "teste@gmailcom",formato.parse("07/09/1983"),"Paulista","Brasileiro","921.321.100-77");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	private void creatUsers(String name, String sexo, String email, Date dataNascimento, String naturalidade,
			String nacionalidade, String cpf) {
		User user = new User(name, sexo, email, dataNascimento, naturalidade, nacionalidade, cpf);
		userRepository.save(user);
	}
	
}
