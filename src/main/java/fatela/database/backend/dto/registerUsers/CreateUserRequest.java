package fatela.database.backend.dto.registerUsers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private Integer countryId;
    private Set<String> roles;
}
