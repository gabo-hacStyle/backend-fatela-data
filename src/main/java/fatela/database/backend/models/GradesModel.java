package fatela.database.backend.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "fct_notas")
@Builder
public class GradesModel {

    @Id
    @Column(name = "md5")
    private String hash;

    @Column(name = "fk_estudiante")
    private String studentCode;

    @Column(name = "fk_curso")
    private String courseCodeWithYear;

    @Column(name = "fk_pais")
    private Integer studentCountryId;

    @Column(name = "codigo_curso")
    private String courseCode;

    @Column(name = "aprobado")
    private String approved;

    @Column(name = "nota")
    private Float grade;

    private String status;

    @Column(name = "anio_electivo")
    private Integer year;

    @Column(name="nombre_es")
    private String studentName;

    @Column(name="genero_es")
    private String studentGender;

    @Column(name="email_es")
    private String studentEmail;

    @Column(name= "pais")
    private String studentCountryName;

    @Column(name = "profesor_curso")
    private String courseTeacher;

    @Column(name="maestria")
    private String courseProgram;
}
