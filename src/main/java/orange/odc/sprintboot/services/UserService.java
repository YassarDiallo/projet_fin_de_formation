package orange.odc.sprintboot.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import orange.odc.sprintboot.models.User;
import orange.odc.sprintboot.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> getAll(){
		return userRepository.findAll();
	}



	public User saveUser(User user) {
		String password = passwordEncoder.encode(user.getPassword());
		String passwordConf = passwordEncoder.encode(user.getPasswordConf());

		user.setPassword(password);
		user.setPasswordConf(passwordConf);
		return userRepository.save(user);
	}

	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}


	public Optional<User> findByMail(String mail) {
		return userRepository.findByEmail(mail);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return userRepository.findByEmail(username)
				.orElseThrow(() -> new RuntimeException("C'est utilisateur n'existe pas"));
	}
	
}
