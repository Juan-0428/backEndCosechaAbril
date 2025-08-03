/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author jdavi
 */
@Component
public class Filter_Jwt extends OncePerRequestFilter {
   
    @Autowired
    private TokenJWT Token;
    private List<String> private_routes = List.of("/api/init/**",
    "/api/secure/**");
    private List<String> public_routes = new ArrayList<>();
    AntPathMatcher matcher = new AntPathMatcher();
    private int bandera = 0;
    
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        for(String route: this.private_routes){
            if(this.matcher.match(route, request.getRequestURI())){
                this.bandera =1;
                 try{
                    Enumeration<String> headers = request.getHeaderNames();
                    Boolean validation_header = false;
                    while(headers.hasMoreElements()){
                        String header = headers.nextElement();
                        if (request.getHeader(header).startsWith("Bearer ")){
                            validation_header = true;
                        }
                    }
                    if(validation_header){
                        String token = request.getHeader("Authorization");
                        if(token!= null){
                                token = token.substring(7);
                                if(Token.VerifyToken(token) && request.getParameter("ID").equals(Token.GetUsername(token))){
                                    filterChain.doFilter(request, response);
                                }
                                else{
                                    response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                                    response.getWriter().write("El token es inválido");
                                    return;
                                }
                        }
                        else{
                                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                                    response.getWriter().write("El token no existe");
                                    return;
                        }
                    }
                    else{
                        response.setStatus(HttpServletResponse.SC_CONFLICT);
                        response.getWriter().write("No se encuentra el encabezado authorization");
                        return;

                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    response.getWriter().write(e.toString());
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
        
    }
        
    }
    
