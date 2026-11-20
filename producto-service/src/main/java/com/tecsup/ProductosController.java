package com.tecsup;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    @GetMapping
    public List<Map<String, Object>> listar() {
        return List.of(Map.of("id", 1, "nombre", "Agua mineral", "precio", 2.50, "categoriaId", 1), Map.of("id", 2, "nombre", "Arroz", "precio", 4.20, "categoriaId", 2));
    }
}
