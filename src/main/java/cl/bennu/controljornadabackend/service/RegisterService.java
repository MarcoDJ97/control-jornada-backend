
package cl.bennu.controljornadabackend.service;

import cl.bennu.controljornadabackend.mapper.RegisterMapper;
import cl.bennu.controljornadabackend.model.Register;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class RegisterService {

    private final RegisterMapper registerMapper;

    public RegisterService(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    public List<Register> listar() {
        return registerMapper.listar();
    }

    public Register encontrarPorId(Integer id) {
        Register register = registerMapper.encontrarPorId(id);

        if (register == null) {
            throw new ResponseStatusException(
                NOT_FOUND,
                "No existe un registro con ID " + id
            );
        }

        return register;
    }

    @Transactional
    public Register crear(Register register) {
        registerMapper.crear(register);

        return registerMapper.encontrarPorId(register.getRegistersId());
    }

    @Transactional
    public Register actualizar(Integer id, Register data) {
        encontrarPorId(id);

        data.setRegistersId(id);
        registerMapper.actualizar(data);

        return registerMapper.encontrarPorId(id);
    }

    @Transactional
    public void eliminar(Integer id) {
        encontrarPorId(id);
        registerMapper.eliminar(id);
    }
}
