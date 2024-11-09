package fatela.database.backend.dto.registerUsers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminRrequest {


    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private Integer countryId;


}
