
package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class Controlador_Rest
{
    @GetMapping("/")
    public String comienzo()
   {  
     log.info("Estoy ejecutando el controlador NYC");
     
     return "index";
    }
    @GetMapping("/registrar")
    public String Registro()
    {
        return "Registrar";
    }
    @GetMapping("/login")
    public String Login()
    {
        return "indice";
    }
     @GetMapping("/Welcome")
    public String welcome()
    {
        return "Welcome";
    }
    @GetMapping("/restaurantes")
    public  String restaurantes(){return "restaurantes";}
    @GetMapping("/aventuras")
    public String aventuras(){return "aventuras";}

}
