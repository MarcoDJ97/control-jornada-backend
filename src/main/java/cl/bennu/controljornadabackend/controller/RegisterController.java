
package cl.bennu.controljornadabackend.controller;

import cl.bennu.controljornadabackend.model.Register;
import cl.bennu.controljornadabackend.service.RegisterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registers")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public List<Register> listar() {
        return registerService.listar();
    }

    @GetMapping("/{id}")
    public Register encontrarPorId(@PathVariable Integer id) {
        return registerService.encontrarPorId(id);
    }

    @PostMapping
    public Register crear(@RequestBody Register register) {
        return registerService.crear(register);
    }

    @PutMapping("/{id}")
    public Register actualizar(
            @PathVariable Integer id,
            @RequestBody Register register) {
        return registerService.actualizar(id, register);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        registerService.eliminar(id);
    }
}
