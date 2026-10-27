
package cl.bennu.controljornadabackend.mapper;

import cl.bennu.controljornadabackend.model.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegisterMapper {

    List<Register> listar();

    Register encontrarPorId(@Param("id") Integer id);

    void crear(Register register);

    void actualizar(Register register);

    void eliminar(@Param("id") Integer id);
}
