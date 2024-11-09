package fatela.database.backend.service;

import fatela.database.backend.models.StudentsModel;
import fatela.database.backend.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentsRepository studentsRepository;

    public List<StudentsModel> getAllStudents() {
        return (List<StudentsModel>) studentsRepository.findAll();
    }

}
