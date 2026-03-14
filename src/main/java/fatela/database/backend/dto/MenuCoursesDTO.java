package fatela.database.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuCoursesDTO {

    private String courseCode;
    private String courseName;
    private String courseProgram;
    private String courseTeacher;
}
