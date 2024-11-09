package fatela.database.backend.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="dim_estudiantes")
public class StudentsModel {
    @Id
    @Column(name="cod_es")
    private String studentCode;

    @Column(name="nombre_es")
    private String studentName;

    @Column(name="genero_es")
    private String studentGender;

    @Column(name="email_es")
    private String studentEmail;

    @Column(name="fecha_modificacion")
    private Date modificationDate;


}
