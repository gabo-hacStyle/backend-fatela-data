package fatela.database.backend.persistence.entity;


import jakarta.persistence.*;

import java.sql.Date;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "usuarios")
public class Usuario {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_usuario")




    private String nombreUsuario;

    @Column(name = "pais_usuario")
    private Long paisUsuario;

    @Column(name = "pwd_usuario")
    private String userPassword;



    @Column(name = "username_usuario")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "pais_usuario", insertable = false, updatable = false)
    private Pais pais;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;



    public Date getFechaModificacion() {
        return fechaModificacion;
    }



    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = Date.valueOf(sdf.format(new Date(System.currentTimeMillis())));
    }



    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = Date.valueOf(sdf.format(new Date(System.currentTimeMillis())));
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getPaisUsuario() {
        return paisUsuario;
    }

    public void setPaisUsuario(Long paisUsuario) {
        this.paisUsuario = paisUsuario;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion= fechaCreacion;
    }
}
