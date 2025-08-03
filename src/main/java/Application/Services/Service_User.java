/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Services;

import Application.Entities.Entity_User;
import Application.Repository.Repositoriez;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

/**
 *
 * @author jdavi
 */
@Service
public class Service_User implements Services<Entity_User> {
    @Autowired
    private Repositoriez Repo;
    
    @Override
    public void Almacenar(Entity_User Usuario) {
        this.Repo.save(Usuario);
    }
    
    @Override
    public List Consultar(String ID){
        List<Entity_User> resultado = this.Repo.findByIdentificacionUsuario(ID);
        return resultado;
    }
}
