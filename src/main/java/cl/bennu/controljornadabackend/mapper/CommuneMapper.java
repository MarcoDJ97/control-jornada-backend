package cl.bennu.controljornadabackend.mapper;

import cl.bennu.controljornadabackend.model.Commune;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuneMapper {

    List<Commune> listar();

    Commune encontrarPorId(Integer id);

    int crear(Commune commune);

    int actualizar(Commune commune);

    int eliminar(Integer id);
}