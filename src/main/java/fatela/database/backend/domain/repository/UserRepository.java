package fatela.database.backend.domain.repository;

import fatela.database.backend.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();
    Optional<List<User>>getByCountry(Long idCountry);
    Optional<User> getUser(Long idUser);
    User save(User user);
    void delete(Long idUser);
}
