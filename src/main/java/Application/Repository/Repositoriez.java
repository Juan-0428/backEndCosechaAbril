/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Repository;

import Application.Entities.Entity_User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Repositoriez extends JpaRepository<Entity_User, Long> {
   List<Entity_User> findByIdentificacionUsuario(String id);
}

