package fatela.database.backend.dto.request;

import fatela.database.backend.dto.response.EditUsersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long userId;
    private EditUsersDTO userDto;
}
