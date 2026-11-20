package com.tecsup;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/clientes")
public class ClientesController {
    @GetMapping
    public List<Map<String, Object>> listar() {
        return List.of(Map.of("id", 1, "nombres", "Ana", "apellidos", "Perez", "correo", "ana@example.com"), Map.of("id", 2, "nombres", "Luis", "apellidos", "Quispe", "correo", "luis@example.com"));
    }
}
