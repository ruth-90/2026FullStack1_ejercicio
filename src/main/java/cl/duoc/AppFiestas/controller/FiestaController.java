package cl.duoc.AppFiestas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Fiesta> obtenerFiestas(){
        return fiestaService.obtenerFiestas();
    } 
    //Buscar fiesta por id
    @GetMapping("/{id}")
    public Fiesta buscarFiestaPorId(@PathVariable int id){
        return fiestaService.buscarFiestaPorId(id);
    }
    //Guardar un fiesta
    @PostMapping 
    public Fiesta guardarFiesta(@RequestBody Fiesta fiesta){
        return fiestaService.guardarFiesta(fiesta);
    }
    //Actualizar una fiesta
    @PutMapping
    public Fiesta actualizarFiesta(@RequestBody Fiesta fiesta){
        return fiestaService.actualizarFiesta(fiesta);
    }
    // Eliminar uan fiesta por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFiesta(@PathVariable int id) {
        fiestaService.eliminarFiesta(id);
        return ResponseEntity.ok("Fiesta eliminada correctamente");
    }

}
