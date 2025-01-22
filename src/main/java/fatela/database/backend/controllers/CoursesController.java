package fatela.database.backend.controllers;

import fatela.database.backend.models.CoursesModel;
import fatela.database.backend.models.MenuCoursesModel;
import fatela.database.backend.repository.CourseRepository;
import fatela.database.backend.repository.MenuCoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    private MenuCoursesRepository menuCoursesRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_COORDINATOR', 'ROLE_STAFF')")
    public ResponseEntity<Page<MenuCoursesModel>> getCoursesList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ){
        Page<MenuCoursesModel> coursesPage = menuCoursesRepository.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(coursesPage);
    }

    @GetMapping
    @RequestMapping("/options/{courseCode}")
    @PreAuthorize("hasAnyRole('ROLE_COORDINATOR', 'ROLE_STAFF')")
    public ResponseEntity<List<CoursesModel>> getCourseOptions(
            @PathVariable String courseCode
    )
    {
        List<CoursesModel> courses = courseRepository.findCoursesOptions(courseCode);
        return ResponseEntity.ok(courses);
    }
}
