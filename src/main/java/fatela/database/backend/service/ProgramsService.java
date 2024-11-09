package fatela.database.backend.service;

import fatela.database.backend.models.CoursesModel;
import fatela.database.backend.models.ProgramModel;
import fatela.database.backend.repository.CourseRepository;
import fatela.database.backend.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramsService {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<ProgramModel> getAllPrograms() {
        return (List<ProgramModel>) programRepository.findAll();
    }
    //Find program by name
    public ProgramModel getProgramByCode(String code){
        return programRepository.findByProgramCode(code);
    }

    //Find all courses
    public List<CoursesModel> getAllCourses(){
        return (List<CoursesModel>) courseRepository.findAll();
    }
}
