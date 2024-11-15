package fatela.database.backend.repository;

import fatela.database.backend.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String username);
    Optional<UserModel> findByUserId(Long userId);

}
