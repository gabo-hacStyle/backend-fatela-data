package fatela.database.backend.models;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "maestrias")
public class ProgramModel {

    @Id
    @Column(name="nombre_programa")
    private String name;

    @Column(name="codigo_programa")
    private String programCode;

    @Column(name="estado_programa")
    private String status;

    @OneToMany
    @JoinColumn(name = "maestria")
    private List<CoursesModel> courses;
}
