package fatela.database.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "usarios")
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_usuario")
    private Long userId;

    @Column(name = "nombre")
    private String name;

    private String email;

    @Column(name="contrasena")
    private String password;

    @Column(name = "habilitado")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "fk_pais")
    private CountryModel country;


    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RolModel.class, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "pk_usuario"),
        inverseJoinColumns = @JoinColumn(name = "pk_rol")
    )
    private Set<RolModel> roles;

}
