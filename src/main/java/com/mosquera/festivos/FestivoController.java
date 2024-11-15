package com.mosquera.festivos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/festivos")
public class FestivoController {
    private final FestivoService festivoService;

    public FestivoController(FestivoService festivoService) {
        this.festivoService = festivoService;
    }

    @GetMapping
    public ResponseEntity<String> validarFestivo(@RequestParam String TextoFecha) {
        final String resultado = festivoService.validarFecha(TextoFecha);
        return ResponseEntity.ok(resultado);
    }
}