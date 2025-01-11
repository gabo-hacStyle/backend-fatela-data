package fatela.database.backend.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowGradesDTO {

    private String studentCode;
    private String courseCode;
    private Integer studentCountryId;
    private String courseName;
    private String approved;
    private Float grade;
    private String status;
    private Integer year;
    private String studentName;
    private String studentGender;
    private String studentEmail;
    private String studentCountryName;
    private String courseTeacher;
    private String courseProgram;
}
