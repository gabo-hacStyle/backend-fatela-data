package fatela.database.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "dim_cursos")
public class CoursesModel {

    @Id
    @Column(name="curso_real")
    private String courseCode;

    @Column(name="codigo_curso")
    private String courseName;

    @Column(name="profesor_curso")
    private String courseTeacher;

    @Column(name="anio_electivo")
    private Integer year;

    @Column(name = "maestria")
    private String courseProgram;

}
