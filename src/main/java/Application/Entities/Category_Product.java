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
import jakarta.persistence.Table;

/**
 *
 * @author jdavi
 */
@Entity
@Table(name= "CATEGORIA_PRODUCTOS")
public class Category_Product {
    @Id
    @Column(name= "IDENTIFICACION_CATEGORIA", length = 10, nullable= false)
    @JsonProperty("IDENTIFICACION_CATEGORIA")
    private String identificacionCategoria;
    
    @Column(name= "NOMBRE_CATEGORIA", length = 30, nullable= false)
    private String nombreCategoria;
    
    @Column(name= "CANTIDAD_PRODUCTOS", nullable= false)
    private int cantidadProductos;
    /**
     * @return the identificacionCategoria
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Category_Product(String identificacionCategoria) {
        this.identificacionCategoria = identificacionCategoria;
    }
    
    public Category_Product(){}
    
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
     * @return the cantidad_productos
     */
    public int getCantidad_productos() {
        return cantidadProductos;
    }

    /**
     * @param cantidad_productos the cantidad_productos to set
     */
    public void setCantidad_productos(int cantidad_productos) {
        this.cantidadProductos = cantidad_productos;
    }
    

}
