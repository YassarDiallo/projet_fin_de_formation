package orange.odc.sprintboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orange.odc.sprintboot.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);


}
