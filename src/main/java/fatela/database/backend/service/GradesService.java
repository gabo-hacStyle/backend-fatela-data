package fatela.database.backend.service;

import fatela.database.backend.dto.response.ShowGradesDTO;
import fatela.database.backend.models.GradesModel;
import fatela.database.backend.repository.GradesRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GradesService {

    @Autowired
    private GradesRepository gradesRepository;

    public Page<ShowGradesDTO> getGrades(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<GradesModel> grades =  gradesRepository.findAll(pageable);

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

}
