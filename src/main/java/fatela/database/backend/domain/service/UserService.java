package fatela.database.backend.domain.service;

import fatela.database.backend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fatela.database.backend.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.getAll();
    }
    public Optional<List<User>> getByCountry(Long idCountry){
        return userRepository.getByCountry(idCountry);
    }
    public Optional<User> getUser(Long idUser){
        return userRepository.getUser(idUser);
    }
    public User save(User user){
        return userRepository.save(user);
    }
    public boolean delete(Long idUser){
        return getUser(idUser).map(user -> {
            userRepository.delete(idUser);
            return true;
        }).orElse(false);
    }
}
