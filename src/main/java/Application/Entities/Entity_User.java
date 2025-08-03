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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

/**
 *
 * @author jdavi
 */
@Entity
@Table(name = "USUARIO")
public class Entity_User {
    @Id
    @Column(name = "IDENTIFICACION_USUARIO", length = 10)
    private String identificacionUsuario;   
    @Column(name = "NOMBRE_USUARIO", length = 20, nullable = false)
    private String nombreUsuario;
    @Column(name= "APELLIDO_USUARIO", length= 20, nullable= false)
    private String apellidoUsuario; 
    @Column(name= "EDAD_USUARIO", nullable = false)
    private int edadUsuario;
    @Column(name="DIRECCION_USUARIO", length = 20, nullable= false)
    private String direccionUSUARIO;
    @Column(name="GENERO_USUARIO", length = 3, nullable= false)
    private String generoUsuario;
    @Column(name= "FECHA_NACIMIENTO", nullable= false) 
    private Date fechaNacimiento; 
    @Column(name="CONTRASENA_USUARIO", length = 20, nullable= false)
    private String contrasenaUsuario;
    
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Entity_User(String identificacionUsuario){
        this.identificacionUsuario = identificacionUsuario;   
    }
    public Entity_User(){}
    

    @ManyToOne
    @JoinColumn(name= "CATEGORIA_USUARIO", referencedColumnName = "IDENTIFICACION_CATEGORIA")
    @JsonProperty("CATEGORIA_USUARIO")
    private Category categoriaUsuario;
       
    /**
     * @return the identificacionUsuario
     */
    


    @JsonValue
    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    /**
     * @param identificacionUsuario the identificacionUsuario to set
     */
    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the apellidoUsuario
     */
    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    /**
     * @param apellidoUsuario the apellidoUsuario to set
     */
    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    /**
     * @return the edadUsuario
     */
    public int getEdadUsuario() {
        return edadUsuario;
    }

    /**
     * @param edadUsuario the edadUsuario to set
     */
    public void setEdadUsuario(int edadUsuario) {
        this.edadUsuario = edadUsuario;
    }

    /**
     * @return the direccionUSUARIO
     */
    public String getDireccionUSUARIO() {
        return direccionUSUARIO;
    }

    /**
     * @param direccionUSUARIO the direccionUSUARIO to set
     */
    public void setDireccionUSUARIO(String direccionUSUARIO) {
        this.direccionUSUARIO = direccionUSUARIO;
    }

    /**
     * @return the generoUsuario
     */
    public String getGeneroUsuario() {
        return generoUsuario;
    }

    /**
     * @param generoUsuario the generoUsuario to set
     */
    public void setGeneroUsuario(String generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the contraseñaUsuario
     */
    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    /**
     * @param contrasenaUsuario the contraseñaUsuario to set
     */
    public void setContrasenaUsuario(String contraseñaUsuario) {
        this.contrasenaUsuario = contraseñaUsuario;
    }
    
    public Category getCategoriaUsuario() {
           return categoriaUsuario;
        }

        /**
         * @param categoriaUsuario the categoriaUsuario to set
         */
        public void setCategoriaUsuario(Category categoriaUsuario) {
            this.categoriaUsuario = categoriaUsuario;
}
    /**
     * @return the categoriaUsuario
     */

}

