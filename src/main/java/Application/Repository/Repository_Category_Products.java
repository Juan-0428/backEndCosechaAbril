/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Repository;

import Application.Entities.Category_Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jdavi
 */
@Repository
public interface Repository_Category_Products extends JpaRepository<Category_Product, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Category_Product c SET c.cantidadProductos = c.cantidadProductos - 1 WHERE c.identificacionCategoria = :id")
    void decrementCantidadProductoById(@Param("id") String id);
}
          
