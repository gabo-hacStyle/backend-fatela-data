package fatela.database.backend.repository;

import fatela.database.backend.models.CoursesModel;
import fatela.database.backend.models.MenuCoursesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CourseRepository extends CrudRepository<CoursesModel, String> {

    @Query("SELECT c.courseName FROM CoursesModel c GROUP BY c.courseName")
    List<String> findAllGroupByCourseCode();

    @Query("SELECT DISTINCT new fatela.database.backend.models.MenuCoursesModel(c.courseCode, c.courseName, c.courseProgram, c.courseTeacher) FROM CoursesModel c")
    Page<MenuCoursesModel> findAll(Pageable pageable);

    @Query("SELECT c FROM CoursesModel c " +
            "where c.courseCode = :courseCode")
    List<CoursesModel> findCoursesOptions(@Param("courseCode") String courseCode);


    @Query("SELECT DISTINCT new fatela.database.backend.models.MenuCoursesModel(c.courseCode, c.courseName, c.courseProgram, c.courseTeacher) FROM CoursesModel c WHERE c.year = :year")
    List<MenuCoursesModel> findCoursesByYear(@Param("year") Integer year);
}
