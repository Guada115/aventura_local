
package com.example;

import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}
