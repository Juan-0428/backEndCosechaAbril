/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Services;

import Application.Entities.Entity_Products;
import Application.Entities.Entity_User;
import Application.Repository.Repository_Category_Products;
import Application.Repository.Repository_Products;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jdavi
 */
@Service
public class Service_Product implements Services<Entity_Products>{
    
    @Autowired
    private Repository_Products repository;
    
    @Autowired
    private Repository_Category_Products categoria;
    @Override
    public void Almacenar(Entity_Products Entity) {
    }
    @Override
    public List Consultar(String id) {
        List<Entity_Products> producto = this.repository.findByidentificacionProducto(id);
        return producto;
        
    }
   public Entity_User Consultar_Propietario(String id){
       Entity_User usuario = this.repository.findByIdentificacionPropietario(id);
       return usuario;
   }
   public List Consultar_type(String type){
       List<Entity_Products> resultado = this.repository.findBycategoriaProducto(type);
       return resultado;
   }
   public void Delete_Registro(Entity_Products producto){
       this.repository.delete(producto);
       this.categoria.decrementCantidadProductoById(producto.getCategoriaProducto().getIdentificacionCategoria());
   }
}
