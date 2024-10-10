package fatela.database.backend.persistence.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "paises")
public class Pais {

    @Column(name = "id_pais")
    @Id
    private Long idPais;


    @Column(name = "pais")
    private String Pais;


    @OneToMany(mappedBy = "pais")
    private List<Usuario> usuarios;



    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public Long getIdPais() {
        return idPais;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

}
