/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Controllers;

import Application.Entities.Entity_User;
import Application.Security.TokenJWT;
import Application.Services.Service_User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class Controller_Sign extends Controller{
    @Autowired
    private Service_User service;
    
    public Controller_Sign(){
        
    }
    @PostMapping("/sign")
    public ResponseEntity<JSONObject> Process_Info(@RequestParam("id") String param, HttpServletRequest Request, @RequestBody  Map<String, Object> body, HttpServletResponse responses){
        try{
            System.out.println(body);
            List user = this.service.Consultar((String)(body.get("IDENTIFICACION_USUARIO")));
            System.out.println(user);
            if(!user.isEmpty()){
                if(user.get(0) instanceof  Entity_User){
                    String token = this.getToken().GenerateToken((String) body.get("IDENTIFICACION_USUARIO"));
                    if(!token.isEmpty() || token.equals(null)){
                        System.out.println(token);
                        ObjectMapper mapper = new ObjectMapper();
                        JSONObject jsonObjectFinal = new JSONObject(mapper.convertValue(user.get(0), Map.class));
                        System.out.println(jsonObjectFinal);
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("URL", "/init");
                        headers.add("Token", token);
                        headers.add("Access-Control-Expose-Headers", "URL, Token");
                        ResponseEntity response = new ResponseEntity<>(jsonObjectFinal.toString(),headers,HttpStatus.ACCEPTED);
                        return response;
                    }
                    else{
                        responses.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        responses.getWriter().write("El token no fue generado.");
                        return new ResponseEntity(new JSONObject(Map.of("Error", "El token no fue generado")), HttpStatus.BAD_GATEWAY);
                    }
                }
                else{
                    return new ResponseEntity(new JSONObject(Map.of("Error", "El usuario ya se encuentra registrado")), HttpStatus.CONFLICT);
                }
            }
            else{
                                    return new ResponseEntity(new JSONObject(Map.of("Error", "El usuario ya se encuentra registrado")), HttpStatus.CONFLICT);

            }
        }
        catch(Exception e){
            System.out.println(e);
            Map<String, String> message_error = new HashMap<>();
            message_error.put("messages", e.toString());
            return new ResponseEntity<>(new JSONObject(message_error),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/log-in")
    public ResponseEntity<Map<String, Object>> Log_in(HttpServletRequest request, @RequestBody Map<String,Object> body){
        try{
            List<Entity_User> user = this.service.Consultar((String) (body.get("IDENTIFICACION_USUARIO")));
            if(user.isEmpty() || user.equals(null) || user.contains(null)){
                ObjectMapper mapper = new ObjectMapper();
                mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_SNAKE_CASE);
                Entity_User usuario = mapper.convertValue(body, Entity_User.class);
                this.service.Almacenar(usuario);
                JSONObject jsonObject = new JSONObject(mapper.convertValue(body, Map.class));
                HttpHeaders headers = new HttpHeaders();
                headers.set("URL", "/sign");
                String identificacion = jsonObject.getString("IDENTIFICACION_USUARIO");
                ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                    Map.of("IDENTIFICACION_USUARIO", identificacion),
                    headers,
                    HttpStatus.ACCEPTED
                );
                return response;
            }
            else{
                return new ResponseEntity(Map.of("Error", "El usuario ya existe"), HttpStatus.CONFLICT);
            }
        }
        catch(Exception e){
            Map<String, Object> map = Map.of("Error", e.getMessage());
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
    }
}
