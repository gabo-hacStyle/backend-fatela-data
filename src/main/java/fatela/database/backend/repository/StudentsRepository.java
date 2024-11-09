package fatela.database.backend.repository;

import fatela.database.backend.models.StudentsModel;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<StudentsModel, String> {

    StudentsModel findByStudentCode(String studentCode);

    StudentsModel findByStudentName(String studentName);

}
