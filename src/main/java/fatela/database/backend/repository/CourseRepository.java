package fatela.database.backend.repository;

import fatela.database.backend.models.CoursesModel;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<CoursesModel, String> {

    //CoursesModel findByCourseCode(String courseCode);

    //CoursesModel findByCourseName(String courseName);

    //CoursesModel findByCourseTeacher(String courseTeacher);

    //CoursesModel findByYear(Integer year);

    //CoursesModel findByCourseProgram(String courseProgram);
}
