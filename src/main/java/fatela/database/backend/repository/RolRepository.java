package fatela.database.backend.repository;

import fatela.database.backend.models.RolModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolRepository extends CrudRepository<RolModel, Integer> {

    Optional<RolModel> findByName(String name);
}
