package fatela.database.backend.controllers;


import fatela.database.backend.dto.response.*;

import fatela.database.backend.models.GradesModel;
import fatela.database.backend.models.StudentsModel;
import fatela.database.backend.repository.GradesRepository;
import fatela.database.backend.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/grades")
public class GradesController {

    @Autowired
    private GradesService gradesService;



    //Todas las notas en paginas
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_COORDINATOR', 'ROLE_STAFF')")
    public ResponseEntity<Page<ShowGradesDTO>> getGrades(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "100")  int size){
        return ResponseEntity.ok(gradesService.getGrades(page, size));
    }

    //Notas filtradas -> Staff
    @GetMapping
    @RequestMapping("/filteredBy")
    @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_COORDINATOR')")
    public ResponseEntity<Page<ShowGradesDTO>> getGradesFiltered(

                @RequestParam(required = false) String student,
                @RequestParam(required = false) String approved,
                @RequestParam(required = false) String course,
                @RequestParam(required = false) String program,
                @RequestParam(required = false) Integer year,
                @RequestParam(required = false) Integer countryId,
                @RequestParam(required = false) String gender,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "30") int size){
        //Integer yearInt = year == null ? null : Integer.parseInt(year);


        Page<ShowGradesDTO> grades = gradesService.getGradesFiltered(student, approved, course, program, year,
        countryId, gender, page, size);



        return ResponseEntity.ok(grades);

    }

    //Cantidad de estudiantes y notas unicamente de un pais -> Coordinator
    //modificar todos los filtros pero esta vez para un pais en especifico, where pais = countryId
    //@GetMapping
    //@RequestMapping("/country/{countryId}")
    //@PreAuthorize("hasAnyRole('ROLE_COORDINATOR')")
    //public ResponseEntity<ShowGradesForCoordinatorDTO> getStudentsByCountry(@PathVariable Integer countryId){
    //   Integer quantity = gradesService.getStudentsQuantityByCountry(countryId);
    //   List<ShowGradesDTO> grades = gradesService.getStudentsByCountry(countryId);
    //    return ResponseEntity.ok(new ShowGradesForCoordinatorDTO(quantity, grades));
    //}

    @GetMapping
    @RequestMapping("/infoQuantities")
    @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_COORDINATOR')")
public ResponseEntity<ShowInitialInfoDTO> getInfoQuantities(
            @RequestParam(required = false) String approved,
            @RequestParam(required = false) String course,
            @RequestParam(required = false) String program,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String student,
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) String gender

    ) {
    Map<String, Object> quantities = gradesService.getInfoQuantities(
            student,
            approved,
            course,
            program,
            year,
            countryId,
            gender
    );

        ShowInitialInfoDTO response = new ShowInitialInfoDTO();
        response.setTotalStudents(((Number) quantities.get("studentsQuantity")).longValue());
        response.setTotalFemales(((Number) quantities.get("femalesQuantity")).longValue());
        response.setTotalMales(((Number) quantities.get("malesQuantity")).longValue());
        response.setStudentsByCountry((List<ShowStudentsByCountryDTO>) quantities.get("studentsByCountry"));
        response.setStudentsByYear((List<ShowStudentsByYearDTO>) quantities.get("studentsByYear"));
    return ResponseEntity.ok(response);
}

}


