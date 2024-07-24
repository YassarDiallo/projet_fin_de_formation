package orange.odc.sprintboot.controllers;

import orange.odc.sprintboot.models.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/connexion")
public class loginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", new Login());
        return "login/login";

    }
}
