package fatela.database.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramCoursesDTO {
    private String program;
    private List<CourseDTO> courses;
}
