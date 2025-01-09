package fatela.database.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowInitialInfoDTO {
    private Long totalStudents;
    private Long totalFemales;
    private Long totalMales;
    private List<ShowStudentsByCountryDTO> studentsByCountry;
    private List<ShowStudentsByYearDTO> studentsByYear;



}
