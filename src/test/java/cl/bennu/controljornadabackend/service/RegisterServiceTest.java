
package cl.bennu.controljornadabackend.service;

import cl.bennu.controljornadabackend.mapper.RegisterMapper;
import cl.bennu.controljornadabackend.model.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Mock
    private RegisterMapper registerMapper;

    @InjectMocks
    private RegisterService registerService;

    private Register register;

    @BeforeEach
    void setUp() {
        register = new Register();
        register.setRegistersId(1);
        register.setEmployeeId(10);
        register.setStartOfTheDay(LocalTime.of(8, 0));
        register.setEntry(LocalTime.of(8, 30));
        register.setExit(LocalTime.of(17, 0));
        register.setBreakTime(LocalTime.of(12, 0));
        register.setBackToWork(LocalTime.of(13, 0));
        register.setDay(LocalDate.of(2026, 10, 27));
        register.setJustificationId(null);
    }

    @Test
    void listarDebeDevolverTodosLosRegistros() {
        when(registerMapper.listar()).thenReturn(List.of(register));

        List<Register> resultado = registerService.listar();

        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getRegistersId());
        verify(registerMapper).listar();
    }

    @Test
    void encontrarPorIdDebeDevolverElRegistroExistente() {
        when(registerMapper.encontrarPorId(1)).thenReturn(register);

        Register resultado = registerService.encontrarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getRegistersId());
        assertEquals(10, resultado.getEmployeeId());
        verify(registerMapper).encontrarPorId(1);
    }

    
        @Test
        void crearDebeGuardarElRegistro() {
            registerService.crear(register);

            verify(registerMapper).crear(register);
        }

    @Test
    void actualizarDebeActualizarElRegistroExistente() {
        when(registerMapper.encontrarPorId(1)).thenReturn(register);

        Register resultado = registerService.actualizar(1, register);

        assertNotNull(resultado);
        assertEquals(1, resultado.getRegistersId());

        verify(registerMapper, times(2)).encontrarPorId(1);
        verify(registerMapper).actualizar(register);
    }

        @Test
        void eliminarDebeEliminarElRegistroExistente() {
            when(registerMapper.encontrarPorId(1)).thenReturn(register);

            registerService.eliminar(1);

            verify(registerMapper).encontrarPorId(1);
            verify(registerMapper).eliminar(1);
        }


    @Test
    void actualizarDebeFallarSiElRegistroNoExiste() {
        when(registerMapper.encontrarPorId(99)).thenReturn(null);

        assertThrows(
                ResponseStatusException.class,
                () -> registerService.actualizar(99, register)
        );

        verify(registerMapper, never()).actualizar(any(Register.class));
    }

    @Test
    void eliminarDebeFallarSiElRegistroNoExiste() {
        when(registerMapper.encontrarPorId(99)).thenReturn(null);

        assertThrows(
                ResponseStatusException.class,
                () -> registerService.eliminar(99)
        );

        verify(registerMapper, never()).eliminar(99);
    }
}
