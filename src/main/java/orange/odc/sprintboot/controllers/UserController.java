package orange.odc.sprintboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import orange.odc.sprintboot.models.User;
import orange.odc.sprintboot.services.UserService;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "login/login";
	}

	@RequestMapping("/inscription")
	public String inscription(Model model){
		model.addAttribute("user", new User());
		return "user/userFrom";
	}


	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute User user) {
		if (user.getFirtName().equals("") || user.getLastName().equals("") || user.getEmail().equals("") || user.getPassword().equals("")) {
			return "redirect:/inscription";
		}
		if (user.getPassword().equals(user.getPasswordConf())){
			userService.saveUser(user);
			return "redirect:/connexion";
		}
		else {
			return "redirect:/inscription";
		}
	}

	@RequestMapping("editUser/{id}")
	public ModelAndView editUser(@PathVariable(name = "id") Long id){
		ModelAndView mav = new ModelAndView("user/userForm");
		mav.addObject("userE", userService.getUser(id));
		return mav;
	}

	public String deleteUser(@PathVariable(name = "id") Long id){
		userService.deleteUser(id);
		return "redirect:/liste";
	}
	

}
