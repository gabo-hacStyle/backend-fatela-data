package fatela.database.backend.persistence;


import fatela.database.backend.domain.User;
import fatela.database.backend.domain.repository.UserRepository;
import fatela.database.backend.persistence.crud.UsuarioCrudRepository;
import fatela.database.backend.persistence.entity.Usuario;
import fatela.database.backend.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {
    @Autowired
    private UsuarioCrudRepository userCrudRepository;
    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getAll(){
        List<Usuario> usuarios = (List<Usuario>) userCrudRepository.findAll();
        return mapper.toUsers(usuarios);
    }

    @Override
    public Optional<List<User>> getByCountry(Long idCountry){
        List<Usuario> usuarios = userCrudRepository.findByPaisUsuarioOrderByNombreUsuarioAsc(idCountry);
        return Optional.of(mapper.toUsers(usuarios));
    }

    public Optional<User> getUser(Long idUser){
        Optional<Usuario> usuario = userCrudRepository.findById(idUser);
        return usuario.map(user -> mapper.toUser(user));
    }

    @Override
    public User save(User user) {
        return mapper.toUser(userCrudRepository.save(mapper.toUsuario(user)));
    }

    @Override
    public void delete(Long idUser){
        userCrudRepository.deleteById(idUser);
    }

}
