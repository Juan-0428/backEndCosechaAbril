/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Controllers;

import Application.Entities.Entity_Products;
import Application.Entities.Entity_User;
import Application.Repository.Repository_Products;
import Application.Services.Service_Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.function.ServerRequest.Headers;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jdavi
 */
@RestController()
@RequestMapping("/api/product")
public class Controller_product extends Controller {

    @Autowired
    private Service_Product service;

    @Autowired
    private ObjectMapper mapper;

    private final String apiPayU = "https://sandbox.api.payulatam.com/payments-api/4.0/service.cgi";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public ResponseEntity<JSONObject> POSTProduct(@RequestParam("id") String identificacion, @RequestBody JSONObject body, HttpServletRequest Request) {
        try {
            List<Entity_Products> product = this.service.Consultar(identificacion);
            if (!product.isEmpty()) {
                Entity_Products producto = product.get(0);
                Map<String, Object> map = mapper.convertValue(producto, Map.class);
                Set<String> keys = map.keySet();
                Iterator<String> keyIterator = keys.iterator();
                while (keyIterator.hasNext()) {
                    if (keyIterator.next() == "identificacionPropietario" && keyIterator.next() != null) {
                        Entity_User usuario_info = this.service.Consultar_Propietario(keyIterator.next().toString());
                        if (!usuario_info.equals(null)) {
                            map.put("NOMBRE_USUARIO", usuario_info.getNombreUsuario().toString());
                            map.put("IDENTIFICACION_USUARIO", usuario_info.getIdentificacionUsuario().toString());
                        }
                    }
                }
                JSONObject json_response = new JSONObject(map);
                return new ResponseEntity(json_response.toString(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(new JSONObject(Map.of("Message", "Error!")), HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new JSONObject(Map.of("Message", "Error!")), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public Object Comprar_Producto(@RequestParam("idTransaccion") String id, @RequestBody List<Map<String, Object>> body, HttpServletRequest request) {
        try {
            URL url = new URL(request.getRequestURL().toString());
            float precioTotal = 0;
            List<String> list_products = new ArrayList<>();
            if (!url.equals(null) && url instanceof URL) {
                for (Map<String, Object> map : body) {
                    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_SNAKE_CASE);
                    Entity_Products producto = mapper.convertValue(map, Entity_Products.class);
                    list_products.add(producto.getNombreProducto());
                    precioTotal += producto.getPrecioProducto();
                    this.service.Delete_Registro(producto);
                }
                HttpHeaders header = new HttpHeaders();
                Map<String, Object> headers_map = Map.of("Content-Type", "application/json", "language", "es");
                headers_map.forEach((key, value) -> {
                    header.add(key, headers_map.get(key).toString());
                });
                /*Map<String, Object> request_body = 
                            Map.of("command", "SUBMIT TRANSACTION", 
                                    "test(JSON)", true, 
                                    "merchant",*/
                Map<String, Object> map_request; // Declare outside

                try (InputStream inputStream = new ClassPathResource("Controllers/resources_back/configuration_payU.json").getInputStream()) {
                    map_request = this.mapper.readValue(inputStream, Map.class);
                    // Now map_request contains the JSON as a Map
                }
                HttpEntity<Map<String, Object>> request_composition = new HttpEntity<>(map_request, header);
                ResponseEntity<Map> response = restTemplate.postForEntity(this.apiPayU, request_composition, Map.class);
                System.out.println(response.getBody());
                return new ResponseEntity(new JSONObject(response.getBody()), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(new JSONObject(Map.of("Error", "Error")), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new JSONObject(Map.of("Response", e)), HttpStatus.ALREADY_REPORTED);
        }

    }

    public ResponseEntity<JSONObject> Confirmar_Compra(@RequestParam("idTransaccion") String identificacion, @RequestBody JSONObject body, HttpServletRequest request) {
        try {
            //PayuClient payuClient = PayuClient.init("YOUR_KEY", "YOUR_SALT");
            return new ResponseEntity(new JSONObject(Map.of("Error", "Hola")), HttpStatus.ALREADY_REPORTED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new JSONObject(Map.of("Error", e)), HttpStatus.ALREADY_REPORTED);
        }
    }

}
