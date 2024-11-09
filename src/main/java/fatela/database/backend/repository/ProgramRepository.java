package fatela.database.backend.repository;

import fatela.database.backend.models.ProgramModel;
import org.springframework.data.repository.CrudRepository;

public interface ProgramRepository extends CrudRepository<ProgramModel, String> {

    ProgramModel findByProgramCode(String name);

}
