
package cl.bennu.controljornadabackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data 
public class Register {

    private Integer registersId;
    private Integer employeeId;
    private LocalTime startOfTheDay;
    private LocalTime entry;
    private LocalTime exit;
    private LocalTime breakTime;
    private LocalTime backToWork;
    private LocalDate day;
    private Integer justificationId;
}
