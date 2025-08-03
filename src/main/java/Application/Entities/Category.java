/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author jdavi
 */
@Entity
@Table(name="CATEGORIA_USUARIO")
public class Category {
    @Id
    @Column(name= "IDENTIFICACION_CATEGORIA", length= 10, nullable= false)
    @JsonProperty("IDENTIFICACION_CATEGORIA")
    private String identificacionCategoria;
    @Column(name= "NOMBRE_CATEGORIA", length= 20, nullable= false)
    private String nombreCategoria;
    
    @Column(name="CANTIDAD_USUARIO")
    private int cantidadUsuario;
    /**
     * @return the identificacionCategoria
     */
    
    @JsonCreator
    public Category(String identificacionCategoria) {
        this.identificacionCategoria = identificacionCategoria;
    }
    public Category() {
        // Constructor vacío requerido por JPA y Jackson
    }
    
    @JsonValue
    public String getIdentificacionCategoria() {
        return identificacionCategoria;
    }

    /**
     * @param identificacionCategoria the identificacionCategoria to set
     */
    public void setIdentificacionCategoria(String identificacionCategoria) {
        this.identificacionCategoria = identificacionCategoria;
    }

    /**
     * @return the nombreCategoria
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * @param nombreCategoria the nombreCategoria to set
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * @return the cantidadUsuario
     */
    public int getCantidadUsuario() {
        return cantidadUsuario;
    }

    /**
     * @param cantidadUsuario the cantidadUsuario to set
     */
    public void setCantidadUsuario(int cantidadUsuario) {
        this.cantidadUsuario = cantidadUsuario;
    }
    
}
