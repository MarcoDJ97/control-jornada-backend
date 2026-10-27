package cl.bennu.controljornadabackend.service;

import cl.bennu.controljornadabackend.mapper.CommuneMapper;
import cl.bennu.controljornadabackend.model.Commune;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CommuneService {

    private final cl.bennu.controljornadabackend.mapper.CommuneMapper communeMapper;

    public CommuneService(CommuneMapper communeMapper) {
        this.communeMapper = communeMapper;
    }

    public List<Commune> listar() {
        return communeMapper.listar();
    }

    public Commune encontrarPorId(Integer id) {
        Commune commune = communeMapper.encontrarPorId(id);

        if (commune == null) {
            throw new ResponseStatusException(
                NOT_FOUND,
                "No existe una comuna con ID " + id
            );
        }

        return commune;
    }

    @Transactional
    public Commune crear(Commune commune) {
        communeMapper.crear(commune);
        return communeMapper.encontrarPorId(commune.getId());
    }

    @Transactional
    public Commune actualizar(Integer id, Commune data) {
        encontrarPorId(id);

        data.setId(id);
        communeMapper.actualizar(data);

        return communeMapper.encontrarPorId(id);
    }

    @Transactional
    public void eliminar(Integer id) {
        encontrarPorId(id);
        communeMapper.eliminar(id);
    }
}