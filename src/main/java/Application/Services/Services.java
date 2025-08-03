/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Services;

import Application.Repository.Repositoriez;
import org.springframework.stereotype.Service;

/**
 *
 * @author jdavi
 */

public interface Services<T>  {
    public void Almacenar(T Entity);
    public Object Consultar(String input);
}
