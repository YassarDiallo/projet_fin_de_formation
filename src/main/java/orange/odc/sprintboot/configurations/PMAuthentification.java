package orange.odc.sprintboot.configurations;

import orange.odc.sprintboot.models.User;
import orange.odc.sprintboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PMAuthentification implements AuthenticationManager{

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        User user = (User) userService.loadUserByUsername(username);
        User user1 =userService.findByMail(authentication.getPrincipal().toString()).orElse(null);

        System.out.print("**************************Utilisateur**********************");



        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Password incorrect");
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return authenticated;
    }

}
