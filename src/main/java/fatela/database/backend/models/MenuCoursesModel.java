package fatela.database.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cursos")
public class MenuCoursesModel {
    @Id
    @Column(name = "codigo_curso")
    private String courseCode;

     @Column(name = "nombre_curso")
    private String courseName;
}
