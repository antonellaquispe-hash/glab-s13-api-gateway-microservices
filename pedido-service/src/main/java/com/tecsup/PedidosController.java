package com.tecsup;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {
    @GetMapping
    public List<Map<String, Object>> listar() {
        return List.of(Map.of("id", 1, "clienteId", 1, "estado", "REGISTRADO", "total", 6.70), Map.of("id", 2, "clienteId", 2, "estado", "EN_PREPARACION", "total", 12.40));
    }
}
