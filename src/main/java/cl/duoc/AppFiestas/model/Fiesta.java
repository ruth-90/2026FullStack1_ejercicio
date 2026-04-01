package cl.duoc.AppFiestas.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Fiesta {
    private int id;
    private String nombre;
    private String tipo;
    private LocalDate fecha;
    private String ubicacion;
    private int capacidad;
    

}
