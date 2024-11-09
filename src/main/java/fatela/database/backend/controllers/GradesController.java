package fatela.database.backend.controllers;


import fatela.database.backend.dto.response.ShowGradesDTO;
import fatela.database.backend.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
public class GradesController {

    @Autowired
    private GradesService gradesService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COORDINATOR', 'ROLE_STAFF')")
    public ResponseEntity<Page<ShowGradesDTO>> getGrades(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "100")  int size){
        return ResponseEntity.ok(gradesService.getGrades(page, size));
    }



}
