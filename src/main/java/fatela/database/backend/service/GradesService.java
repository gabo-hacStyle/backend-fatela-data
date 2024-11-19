package fatela.database.backend.service;

import fatela.database.backend.dto.response.ShowGradesDTO;
import fatela.database.backend.dto.response.ShowStudentsByCountryDTO;
import fatela.database.backend.dto.response.ShowStudentsByYearDTO;
import fatela.database.backend.models.GradesModel;
import fatela.database.backend.repository.GradesRepository;
import fatela.database.backend.specifications.GradesSpecification;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class GradesService {

    @Autowired
    private GradesRepository gradesRepository;

    public Page<ShowGradesDTO> getGrades(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<GradesModel> grades =  gradesRepository.findAll(pageable);

        return getShowGradesDTOPages(grades);

    }
    public Page<ShowGradesDTO> getGradesFiltered(
                                                 String studentCode,
                                                 String approved,
                                                 String courseCode,
                                                 String courseProgram,
                                                 Integer year,
                                                 Integer countryId,
                                                 String studentGender,
                                                 String courseCodeWithYear,
                                                 int page, int size){
        Pageable pageable = PageRequest.of(page, size);


        Page<GradesModel> grades = gradesRepository.findGradesByFilters(
                studentCode,
                approved,
                courseCode,
                courseProgram,
                year,
                countryId,
                studentGender,
                courseCodeWithYear,
                pageable
        );


        return getShowGradesDTOPages(grades);

    }

    public Map<String, Object> getInfoQuantities (
            String studentCode,
            String approved,
            String courseCode,
            String courseProgram,
            Integer year,
            Integer countryId,
            String studentGender,
            String courseCodeWithYear
    ){
        // Integer studentsQuantity = gradesRepository.countGradesByFilters(
//         studentCode,
//         approved,
//         courseCode,
//         courseProgram,
//         year,
//         countryId,
//         courseCodeWithYear
// );
        Integer femalesQuantity = 0;
        Integer malesQuantity = 0;
        Integer totalQuantity = 0;

        if ("Masculino".equalsIgnoreCase(studentGender)) {
            malesQuantity = gradesRepository.countGradesByFiltersMale(
                    studentCode,
                    approved,
                    courseCode,
                    courseProgram,
                    year,
                    countryId,
                    courseCodeWithYear
            );
            totalQuantity = malesQuantity;
        } else if ("Femenino".equalsIgnoreCase(studentGender)) {
            femalesQuantity = gradesRepository.countGradesByFiltersFemale(
                    studentCode,
                    approved,
                    courseCode,
                    courseProgram,
                    year,
                    countryId,
                    courseCodeWithYear
            );
            totalQuantity = femalesQuantity;
        } else {
            femalesQuantity = gradesRepository.countGradesByFiltersFemale(
                    studentCode,
                    approved,
                    courseCode,
                    courseProgram,
                    year,
                    countryId,
                    courseCodeWithYear
            );
            malesQuantity = gradesRepository.countGradesByFiltersMale(
                    studentCode,
                    approved,
                    courseCode,
                    courseProgram,
                    year,
                    countryId,
                    courseCodeWithYear
            );
            totalQuantity = femalesQuantity + malesQuantity;
        }
        Integer coursesQuantity = gradesRepository.coursesQuantity(courseProgram);

        List<ShowStudentsByCountryDTO> studentsByCountry = gradesRepository.studentsNumberByCountry(
                studentCode,
                approved,
                courseCode,
                courseProgram,
                year,
                countryId,
                studentGender,
                courseCodeWithYear
        );
        Map<String, Object> response = new HashMap<>();
        response.put("studentsQuantity", totalQuantity);
        response.put("femalesQuantity", femalesQuantity);
        response.put("malesQuantity", malesQuantity);
        response.put("coursesQuantity", coursesQuantity);
        response.put("studentsByCountry", studentsByCountry);
        return response;
    }
    public Integer getStudentsQuantityByCountry(Integer countryId){
        return gradesRepository.countAllByStudentCountryId(countryId);
    }

    public List<ShowStudentsByYearDTO> getStudentsByYear (Integer countryId){
        List<ShowStudentsByYearDTO> grades;
        if (countryId == null) {
            grades = gradesRepository.studentsNumberByYear();
        } else {
            grades = gradesRepository.studentsNumberByYearAndCountry(countryId);
        }
        return grades;
    }

    private Page<ShowGradesDTO> getShowGradesDTOPages(Page<GradesModel> grades) {
        return grades.map(grade -> {
            ShowGradesDTO showGradesDTO = new ShowGradesDTO();
            showGradesDTO.setStudentCode(grade.getStudentCode());
            showGradesDTO.setCourseCode(grade.getCourseCode());
            showGradesDTO.setStudentCountryId(grade.getStudentCountryId());
            showGradesDTO.setApproved(grade.getApproved());
            showGradesDTO.setGrade(grade.getGrade());
            showGradesDTO.setStatus(grade.getStatus());
            showGradesDTO.setYear(grade.getYear());
            showGradesDTO.setStudentName(grade.getStudentName());
            showGradesDTO.setStudentGender(grade.getStudentGender());
            showGradesDTO.setStudentEmail(grade.getStudentEmail());
            showGradesDTO.setStudentCountryName(grade.getStudentCountryName());
            showGradesDTO.setCourseTeacher(grade.getCourseTeacher());
            showGradesDTO.setCourseProgram(grade.getCourseProgram());
            return showGradesDTO;
        });
    }
    private List<ShowGradesDTO> getShowGradesDTOList(List<GradesModel> grades) {
        return grades.stream().map(grade -> {
            ShowGradesDTO showGradesDTO = new ShowGradesDTO();
            showGradesDTO.setStudentCode(grade.getStudentCode());
            showGradesDTO.setCourseCode(grade.getCourseCode());
            showGradesDTO.setStudentCountryId(grade.getStudentCountryId());
            showGradesDTO.setApproved(grade.getApproved());
            showGradesDTO.setGrade(grade.getGrade());
            showGradesDTO.setStatus(grade.getStatus());
            showGradesDTO.setYear(grade.getYear());
            showGradesDTO.setStudentName(grade.getStudentName());
            showGradesDTO.setStudentGender(grade.getStudentGender());
            showGradesDTO.setStudentEmail(grade.getStudentEmail());
            showGradesDTO.setStudentCountryName(grade.getStudentCountryName());
            showGradesDTO.setCourseTeacher(grade.getCourseTeacher());
            showGradesDTO.setCourseProgram(grade.getCourseProgram());
            return showGradesDTO;
        }).collect(Collectors.toList());
    }

}
