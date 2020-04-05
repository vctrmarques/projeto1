package joao.io.projeto1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import joao.io.projeto1.entity.User;
import joao.io.projeto1.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public String getUser(Model model) {
		model.addAttribute("usersList", this.userService.findAll());
		return "user";
	}

	@PostMapping()
	public String save(@ModelAttribute("userForm") User user) {
		userService.saveOrUpdateUser(user);
		return "result";
	}
	
	@PostMapping(value="/edit{cpf}")
	public String updateUser(@ModelAttribute("userForm") User newUser) {
		
		User oldUser = userService.findByCpf(newUser.getCpf());
        
		oldUser.setName(newUser.getName());
		oldUser.setEmail(newUser.getEmail());
		oldUser.setSexo(newUser.getSexo());
		oldUser.setNacionalidade(newUser.getNacionalidade());
		oldUser.setNaturalidade(newUser.getNaturalidade());
		oldUser.setDataNascimento(newUser.getDataNascimento());
		
		userService.saveOrUpdateUser(oldUser);
		return "result";
	}

	@DeleteMapping(value = "/delete{cpf}")
	public void deleteUser(@PathVariable String cpf) {
		userService.deleteUser(userService.findByCpf(cpf));
	}


}
