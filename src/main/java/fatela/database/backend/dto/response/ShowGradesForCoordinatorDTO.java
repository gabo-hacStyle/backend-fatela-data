package fatela.database.backend.dto.response;

import fatela.database.backend.models.GradesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowGradesForCoordinatorDTO {
    public Integer quantity;
    public List<ShowGradesDTO> grades;
}
