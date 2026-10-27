package cl.bennu.controljornadabackend.controller;

import cl.bennu.controljornadabackend.model.Commune;
import cl.bennu.controljornadabackend.service.CommuneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communes")
public class CommuneController {

    private final cl.bennu.controljornadabackend.service.CommuneService communeService;

    public CommuneController(CommuneService communeService) {
        this.communeService = communeService;
    }

    @GetMapping
    public ResponseEntity<List<Commune>> listar() {
        return ResponseEntity.ok(communeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commune> encontrarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(communeService.encontrarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Commune> crear(@RequestBody Commune commune) {
        Commune creado = communeService.crear(commune);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commune> actualizar(
            @PathVariable Integer id,
            @RequestBody Commune commune) {

        return ResponseEntity.ok(
            communeService.actualizar(id, commune)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        communeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}