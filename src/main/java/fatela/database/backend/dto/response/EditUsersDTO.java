package fatela.database.backend.dto.response;

import fatela.database.backend.models.CountryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUsersDTO {
    private String name;
    private String email;
    private Boolean enabled;
    private Integer countryId;
    private Set<String> roles;
}