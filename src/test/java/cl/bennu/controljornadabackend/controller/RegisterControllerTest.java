
package cl.bennu.controljornadabackend.controller;

import cl.bennu.controljornadabackend.model.Register;
import cl.bennu.controljornadabackend.service.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegisterController.class)
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
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
    void listarDebeRetornarLosRegistros() throws Exception {
        when(registerService.listar()).thenReturn(List.of(register));

        mockMvc.perform(get("/api/registers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].registersId").value(1))
                .andExpect(jsonPath("$[0].employeeId").value(10));

        verify(registerService).listar();
    }

    @Test
    void encontrarPorIdDebeRetornarElRegistro() throws Exception {
        when(registerService.encontrarPorId(1)).thenReturn(register);

        mockMvc.perform(get("/api/registers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registersId").value(1))
                .andExpect(jsonPath("$.employeeId").value(10));

        verify(registerService).encontrarPorId(1);
    }

    @Test
    void crearDebeRetornarElRegistroCreado() throws Exception {
        when(registerService.crear(any(Register.class)))
                .thenReturn(register);

        mockMvc.perform(post("/api/registers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "employeeId": 10,
                        "startOfTheDay": "08:00:00",
                        "entry": "08:30:00",
                        "exit": "17:00:00",
                        "breakTime": "12:00:00",
                        "backToWork": "13:00:00",
                        "day": "2026-10-27",
                        "justificationId": null
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registersId").value(1))
                .andExpect(jsonPath("$.employeeId").value(10));

        verify(registerService).crear(any(Register.class));
    }

    @Test
    void actualizarDebeRetornarElRegistroActualizado() throws Exception {
        when(registerService.actualizar(eq(1), any(Register.class)))
                .thenReturn(register);

        mockMvc.perform(put("/api/registers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "employeeId": 10,
                        "startOfTheDay": "08:00:00",
                        "entry": "08:30:00",
                        "exit": "17:00:00",
                        "breakTime": "12:00:00",
                        "backToWork": "13:00:00",
                        "day": "2026-10-27",
                        "justificationId": null
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registersId").value(1))
                .andExpect(jsonPath("$.employeeId").value(10));

        verify(registerService).actualizar(eq(1), any(Register.class));
    }

    @Test
    void eliminarDebeRetornarRespuestaExitosa() throws Exception {
        doNothing().when(registerService).eliminar(1);

        mockMvc.perform(delete("/api/registers/1"))
                .andExpect(status().isOk());

        verify(registerService).eliminar(1);
    }
}
