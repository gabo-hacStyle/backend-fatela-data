package fatela.database.backend.controllers;

import fatela.database.backend.dto.CourseDTO;
import fatela.database.backend.dto.ProgramCoursesDTO;
import fatela.database.backend.models.CoursesModel;
import fatela.database.backend.models.MenuCoursesModel;
import fatela.database.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CoursesController {



    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_COORDINATOR', 'ROLE_STAFF')")
    public ResponseEntity<Page<MenuCoursesModel>> getCoursesList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ){
        Page<MenuCoursesModel> coursesPage = courseRepository.findAll(PageRequest.of(page, size));
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

    @GetMapping
    @RequestMapping("/year/{year}")
    @PreAuthorize("hasAnyRole('ROLE_STAFF')")
    public ResponseEntity<List<ProgramCoursesDTO>> getCoursesByYear(
            @PathVariable int year
    )
    {
        List<MenuCoursesModel> courses = courseRepository.findCoursesByYear(year);

        // Agrupar cursos por maestría (programa)
        Map<String, List<MenuCoursesModel>> groupedByProgram = courses.stream()
                .collect(Collectors.groupingBy(MenuCoursesModel::getCourseProgram));

        // Convertir a DTO
        List<ProgramCoursesDTO> result = groupedByProgram.entrySet().stream()
                .map(entry -> {
                    String program = entry.getKey();
                    List<CourseDTO> courseDTOs = entry.getValue().stream()
                            .map(course -> new CourseDTO(
                                    course.getCourseCode(),
                                    course.getCourseName(),
                                    course.getCourseTeacher()
                            ))
                            .collect(Collectors.toList());
                    return new ProgramCoursesDTO(program, courseDTOs);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
