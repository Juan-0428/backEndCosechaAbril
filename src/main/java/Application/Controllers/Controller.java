/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Application.Controllers;

import Application.Security.TokenJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    /**
     * @return the restTemplate
     */


    /**
     * @return the Token
     */
    @Autowired
    private TokenJWT Token;
    
    public TokenJWT getToken() {
        return Token;
    }

    /**
     * @param Token the Token to set
     */
    public void setToken(TokenJWT Token) {
        this.Token = Token;
    }
    

    
}
