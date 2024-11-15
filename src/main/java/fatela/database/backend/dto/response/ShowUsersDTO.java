package fatela.database.backend.dto.response;

import fatela.database.backend.models.CountryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowUsersDTO {
    private Long userId;
    private String name;
    private String email;
    private boolean enabled;
    private CountryModel country;
    private Set<String> roles;


}
