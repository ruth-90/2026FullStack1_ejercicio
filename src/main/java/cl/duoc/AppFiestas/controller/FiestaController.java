package cl.duoc.AppFiestas.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.AppFiestas.model.Fiesta;
import cl.duoc.AppFiestas.service.FiestaService; 


@RestController
@RequestMapping("/api/v1/fiestas")

public class FiestaController {

    @Autowired
    private FiestaService fiestaService;

    //obtener todas las fiestas/listar
    @GetMapping
    public ResponseEntity<List<Fiesta>> obtenerFiestas(){
        List<Fiesta> fiestas = fiestaService.obtenerFiestas();
        return ResponseEntity.ok(fiestas); // 200 ok
    } 

    //Buscar fiesta por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarFiestaPorId(@PathVariable int id){
        try{
            Fiesta fiesta = fiestaService.buscarFiestaPorId(id);
            return ResponseEntity.ok(fiesta);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 not found
        }
    }

    //Guardar una fiesta/CREAR
    @PostMapping 
    public ResponseEntity<?> guardarFiesta(@RequestBody Fiesta fiesta){
        try{
            Fiesta nuevaFiesta = fiestaService.guardarFiesta(fiesta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFiesta); //201 created
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Actualizar una fiesta
    @PutMapping
    public ResponseEntity<?> actualizarFiesta(@RequestBody Fiesta fiesta){
        try{
            Fiesta fiestaActualizada = fiestaService.actualizarFiesta(fiesta);
            return ResponseEntity.ok(fiestaActualizada); // 200 ok
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 not found
        }
    }

    // Eliminar una fiesta por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFiesta(@PathVariable int id) {
        try{
            fiestaService.eliminarFiesta(id);
            return ResponseEntity.ok("Fiesta eliminada correctamente");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 not found
        }  
    }

    //Buscar fiestas por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> buscarPorTipo(@PathVariable String tipo){
       try{
            List<Fiesta> fiestas = fiestaService.buscarPorTipo(tipo);
            return ResponseEntity.ok(fiestas); // 200 ok
       }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 400
       }
    }

    //Buscar fiestas por fecha
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> buscarPorFecha(@PathVariable String fecha){
        try{
            LocalDate fechaConvertida = LocalDate.parse(fecha);
            List<Fiesta> fiestas = fiestaService.buscarPorFecha(fechaConvertida);
            return ResponseEntity.ok(fiestas); // 200 ok
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de fecha inválido");// 400
        }
    }
}
