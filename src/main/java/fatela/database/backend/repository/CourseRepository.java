package fatela.database.backend.repository;

import fatela.database.backend.models.CoursesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<CoursesModel, String> {

    @Query("SELECT c.courseName FROM CoursesModel c GROUP BY c.courseName")
    List<String> findAllGroupByCourseCode();

    //CoursesModel findByCourseCode(String courseCode);

    //CoursesModel findByCourseName(String courseName);

    //CoursesModel findByCourseTeacher(String courseTeacher);

    //CoursesModel findByYear(Integer year);

    //CoursesModel findByCourseProgram(String courseProgram);
}
