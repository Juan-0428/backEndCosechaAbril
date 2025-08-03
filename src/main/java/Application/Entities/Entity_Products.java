/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name= "PRODUCTO")
public class Entity_Products {

    /**
     * @return the categoriaProducto
     */

    /**
     * @return the usuario
     */
    public Entity_User getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Entity_User usuario) {
        this.usuario = usuario;
    }
    @Id
    @Column(name= "IDENTIFICACION_PRODUCTO", nullable = false, length= 10)
    private String identificacionProducto;
    
    @Column(name= "NOMBRE_PRODUCTO", nullable = false, length= 30)
    private String nombreProducto;
    
    @Column(name="DESCRIPCION_PRODUCTO", nullable = false, length= 30)
    private String descripcionProducto;
    
    @Column(name= "PRECIO_PRODUCTO", nullable= false)
    private float precioProducto;
    
    @Column(name= "MONEDA_PRODUCTO", nullable= false, length= 10)
    private String monedaProducto;
            
    @Column(name= "GEOLOCALIZACION", nullable= false, length= 50)
    private String geolocalizacion;
    
    @Column(name= "FECHA_CREACION", nullable = false)
    private Date fechaCreacion;
    
    @Column(name= "FECHA_CADUCIDAD", nullable = false)
    private Date fechaCaducidad;
    
    @Column(name= "DIRECCION_PRODUTO", nullable = false, length= 50)
    private String direccionProducto;
    
    @Column(name= "CODIGO_POSTAL", nullable = false)
    private int codigoPostal;
    
    @Column(name= "DETALLES", nullable = false, length= 50)
    private String detalles;
    
    @Column(name= "CANTIDAD", nullable = false)
    private int cantidad;
    
    @ManyToOne
    @JoinColumn(name= "CATEGORIA_PRODUCTO", referencedColumnName = "IDENTIFICACION_CATEGORIA")
    @JsonProperty("CATEGORIA_PRODUCTO")
    private Category_Product categoriaProducto;
    
    @ManyToOne
    @JoinColumn(name= "IDENTIFICACION_PROPIETARIO", referencedColumnName= "IDENTIFICACION_USUARIO")
    @JsonProperty("IDENTIFICACION_PROPIETARIO")
    private Entity_User usuario;
    /**
     * @return the identificacionProducto
     */
    public String getIdentificacionProducto() {
        return identificacionProducto;
    }

    /**
     * @param identificacionProducto the identificacionProducto to set
     */
    public void setIdentificacionProducto(String identificacionProducto) {
        this.identificacionProducto = identificacionProducto;
    }

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * @return the descripcionProducto
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * @param descripcionProducto the descripcionProducto to set
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     * @return the precioProducto
     */
    public float getPrecioProducto() {
        return precioProducto;
    }

    /**
     * @param precioProducto the precioProducto to set
     */
    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * @return the monedaProducto
     */
    public String getMonedaProducto() {
        return monedaProducto;
    }

    /**
     * @param monedaProducto the monedaProducto to set
     */
    public void setMonedaProducto(String monedaProducto) {
        this.monedaProducto = monedaProducto;
    }

    /**
     * @return the geolocalizacion
     */
    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    /**
     * @param geolocalizacion the geolocalizacion to set
     */
    public void setGeolocalizacion(String geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaCaducidad
     */
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * @param fechaCaducidad the fechaCaducidad to set
     */
    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * @return the direccionProducto
     */
    public String getDireccionProducto() {
        return direccionProducto;
    }

    /**
     * @param direccionProducto the direccionProducto to set
     */
    public void setDireccionProducto(String direccionProducto) {
        this.direccionProducto = direccionProducto;
    }

    /**
     * @return the codigoPostal
     */
    public int getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the detalles
     */
    public String getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    } 
     public Category_Product getCategoriaProducto() {
        return categoriaProducto;
    }

    /**
     * @param categoriaProducto the categoriaProducto to set
     */
    public void setCategoriaProducto(Category_Product categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }
    
}
