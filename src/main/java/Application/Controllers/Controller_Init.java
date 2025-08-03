/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Controllers;

import Application.Entities.Entity_Products;
import Application.Entities.Entity_User;
import Application.Security.TokenJWT;
import Application.Services.Service_Product;
import Application.Services.Service_User;
import ch.qos.logback.core.subst.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jdavi
 */
@RestController
@RequestMapping("/api/init")
public class Controller_Init extends Controller {
    
    @Autowired
    private Service_User service;
    
    @Autowired
    private Service_Product service_product;
    
    @Autowired
    private ObjectMapper mapper;
    @GetMapping("")
    public ResponseEntity<JSONObject> Main_Screem(@RequestParam("ID") String param, HttpServletRequest request){
        try{
           String header_authorization = request.getHeader("Authorization");
           if(header_authorization.startsWith("Bearer")){
               String token = header_authorization.substring(7);
               String username = this.getToken().GetUsername(token);
               System.out.println(username);
               List user = this.service.Consultar(username);
               if(!user.isEmpty()){
                    if(user.get(0) instanceof Entity_User){
                        JSONObject json = new JSONObject(mapper.convertValue(user.get(0), Map.class));
                        return new ResponseEntity(json.toString(), HttpStatus.ACCEPTED);
                    }
                    else{
                        return new ResponseEntity(new JSONObject(Map.of("Error", "El usuario no se encuentra registrado.")), HttpStatus.FAILED_DEPENDENCY);
                    }
               }
               else{
                   return new ResponseEntity(new JSONObject(Map.of("Error", "error el usuario no existe!")), HttpStatus.CONFLICT);
               }
               
           }
           else{
               return new ResponseEntity(new JSONObject(Map.of("Error", "El encaebazado header está incorrecto")), HttpStatus.CONFLICT);
           }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(new JSONObject(Map.of("Error", e)), HttpStatus.FORBIDDEN);
        }
    }
    
    @GetMapping("/producto")
    public ResponseEntity<JSONObject> GetProducto(HttpServletRequest request, @RequestParam("type") String param){
        try{
            String type_product = param;
            Object service = this.service_product.Consultar_type(param);
            if(service instanceof Entity_Products || !service.equals(null)){
                return new ResponseEntity(new JSONObject(new ObjectMapper().convertValue(service, Map.class)), HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity(new JSONObject(Map.of("Error", "No se encontró el tipo de producto")), HttpStatus.BAD_REQUEST);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(new JSONObject(Map.of("ERROR", e)), HttpStatus.BAD_GATEWAY);
        }
    }
    
}
