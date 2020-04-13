package joao.io.projeto1.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import joao.io.projeto1.entity.User;
import joao.io.projeto1.service.UserService;

@Controller
public class UserController {

	public Integer increment = 1;
	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public ModelAndView findAll() {
		
		ModelAndView mav = new ModelAndView("/post");
		mav.addObject("users", userService.findAll());
		
		return mav;
	}

	//Vai para tela de adição de post
	@GetMapping("/add")
	public ModelAndView add(User user) {

		ModelAndView mv = new ModelAndView("/postAdd");
		mv.addObject("user", user);

		return mv;
	}

	//Vai para tela de edição de posts (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") String id) {
		Optional<User> user = userService.findById(id);
		return add(user.get());
	}

	//Exclui um post por seu ID e redireciona para a tela principal
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") String id) {

		Optional<User> user = userService.findById(id);
		userService.deleteUser(user.get());

		return findAll();
	}

	//Recebe um objeto preenchido do Thymeleaf e valida 
	//Se tudo estiver ok, salva e volta para tela principal
	//Se houver erro, retorna para tela atual exibindo as mensagens de erro
	@PostMapping("/save")
	public ModelAndView save(@Valid User user, BindingResult result) {
		
		if(user.getId().equals("")) {
			Integer cont = increment++;
			user.setId(cont.toString());
		}
		
		List<User> userCpf = userService.findAll();
		for (int i = 0; i < userCpf.size(); i++) {
			if(userCpf.get(i).getCpf().equals(user.getCpf())) {
				return add(user);
			}
		}
		
		if(result.hasErrors()) {
			return add(user);
		}

		userService.save(user);

		return findAll();
	}

}
