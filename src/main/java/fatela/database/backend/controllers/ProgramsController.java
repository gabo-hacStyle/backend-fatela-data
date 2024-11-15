package fatela.database.backend.controllers;

import fatela.database.backend.models.CoursesModel;
import fatela.database.backend.models.ProgramModel;
import fatela.database.backend.service.ProgramsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramsController {

    @Autowired
    private ProgramsService programsService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COORDINATOR', 'ROLE_STAFF')")
    public List<ProgramModel> getAllPrograms() {
        return programsService.getAllPrograms();
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COORDINATOR', 'ROLE_STAFF')")
    public ProgramModel getProgramByName(@PathVariable String code) {
        return programsService.getProgramByCode(code);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COORDINATOR', 'ROLE_STAFF')")
    @RequestMapping("/courses")
    public List<String> getCourses() {
        return programsService.getAllCourses();
    }
}
