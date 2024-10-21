package fatela.database.backend.persistence.crud;

import fatela.database.backend.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {

    List<Usuario> findByPaisUsuarioOrderByNombreUsuarioAsc(Long paisUsuario);

}