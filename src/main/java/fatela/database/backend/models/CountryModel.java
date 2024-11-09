package fatela.database.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "dim_paises")
public class CountryModel {
    @Id
    @Column(name = "pk_pais")
    private Integer countryId;

    @Column(name="pais")
    private String countryName;

    //@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<UserModel> users;
}
