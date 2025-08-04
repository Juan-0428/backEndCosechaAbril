/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Repository;

import Application.Entities.Entity_Products;
import Application.Entities.Entity_User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jdavi
 */
@Repository
public interface Repository_Products extends JpaRepository<Entity_Products, Long>{
     @Query("""
        SELECT p
        FROM Entity_Products p
        JOIN p.categoriaProducto c
        WHERE c.identificacionCategoria = :type
    """)
    List<Entity_Products> findBycategoriaProducto(@Param("type") String nombreCategoria);
    List<Entity_Products> findByidentificacionProducto(String identificacion);
    @Query("""
    SELECT p.usuario
    FROM Entity_Products p
    WHERE p.usuario.identificacionUsuario = :id
    """)
    Entity_User findByIdentificacionPropietario(@Param("id") String identificacion);
    
    @Query("""
           SELECT p.cantidadProducto
           FROM Entity_Products p
           """)
    int findByIdentificacionProducto(@Param("id") String identificacion);
}
