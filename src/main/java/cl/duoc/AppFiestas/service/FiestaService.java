package cl.duoc.AppFiestas.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.AppFiestas.model.Fiesta;
import cl.duoc.AppFiestas.repository.FiestaRepository;

@Service
public class FiestaService {

@Autowired
private FiestaRepository fiestaRepository;

    //obtener todas las fiestas/listar
public List<Fiesta> obtenerFiestas(){
    return fiestaRepository.obtenerFiestas();
}

    //buscar una fiesta por id
public Fiesta buscarFiestaPorId(int id){

    Fiesta fiesta = fiestaRepository.buscarFiestaPorId(id);
    if ( fiesta == null){
        throw new IllegalArgumentException("No existe la fiesta con id:" + id);
    }
    return fiesta;
}

    //Guardar una nueva fiesta
public Fiesta guardarFiesta(Fiesta fiesta){

        // Regla 1: el id debe ser mayor a 0
    if (fiesta.getId() <= 0) {
        throw new IllegalArgumentException("El id debe ser mayor que cero");
    }
        // Regla 2: no se permite id repetido
    if(fiestaRepository.buscarFiestaPorId(fiesta.getId()) != null) {
        throw new IllegalArgumentException("Ya existe una fiesta con el mismo id");
     }
        //Regla 3: el nombre de la fiesta es obligatorio
    if (fiesta.getNombre() == null || fiesta.getNombre().trim().isEmpty()){
        throw new IllegalArgumentException("El nombre de la fiesta es obligatorio");
    }
        //Regla 4: el tipo de fiesta es obligatorio
    if (fiesta.getTipo() == null || fiesta.getTipo().trim().isEmpty()){
        throw new IllegalArgumentException("El tipo de fiesta es obligatorio");
    }
        //Regla 5: la fecha de la fiesta es obligatoria
    if (fiesta.getFecha() == null){
        throw new IllegalArgumentException("La fecha de la fiesta es obligatoria");
    }
        //Regla 6: la ubicacion de la fiesta es obligatoria
    if (fiesta.getUbicacion() == null || fiesta.getUbicacion().trim().isEmpty()){
        throw new IllegalArgumentException("La ubicacion de la fiesta es obligatoria");
    }
        //Regla 7: la capacidad de la fiesta debe ser mayor a cero
    if (fiesta.getCapacidad() <= 0){
        throw new IllegalArgumentException("La capacidad de la fiesta debe ser mayor a cero");
    }
    return fiestaRepository.guardarFiesta(fiesta);
}

    //Actualizar una fiesta existente
public Fiesta actualizarFiesta(Fiesta fiesta){

    Fiesta existente = fiestaRepository.buscarFiestaPorId(fiesta.getId());
    if (existente == null){
        throw new IllegalArgumentException("No exite una fiesta con id:" + fiesta.getId());
    }
    if (fiesta.getNombre() == null ||fiesta.getNombre().trim().isEmpty()){
        throw new IllegalArgumentException("El nombre de la fiesta es obligatorio");
    }
    if (fiesta.getTipo() == null || fiesta.getTipo().trim().isEmpty()){
        throw new IllegalArgumentException("El tipo de fiesta es obligatorio");
    }
    if(fiesta.getFecha() == null){
        throw new IllegalArgumentException("La fecha de la fiesta es obligatoria");
    }
    if (fiesta.getUbicacion() == null || fiesta.getUbicacion().trim().isEmpty()){
        throw new IllegalArgumentException("La ubicacion de la fiesta es obligatoria");
    }
    if (fiesta.getCapacidad() <= 0){
        throw new IllegalArgumentException("La capacidad de la fiesta debe ser mayor a cero");
    }
    return fiestaRepository.actualizarFiesta(fiesta);
}

    //Eliminar una fiesta por id
public void eliminarFiesta(int id){

    boolean eliminada = fiestaRepository.eliminarFiesta(id);
    if (!eliminada){
        throw new IllegalArgumentException("No existe una fiesta con id:" + id);
    }
}

    //Buscar fiesta por tipo
    public List<Fiesta> buscarPorTipoFiesta(String tipo){

        if(tipo == null || tipo.trim().isEmpty()){
            throw new IllegalArgumentException("El tipo de fiesta es obligatorio");
        }
        return fiestaRepository.buscarPorTipo(tipo);
    }

    //Buscar fiesta por fecha
    public List<Fiesta> buscarPorFecha(LocalDate fecha){

        if (fecha == null){
            throw new IllegalArgumentException("La fecha de la fiesta es obligatoria");
        }
        return fiestaRepository.buscarPorFecha(fecha);
    }
    
}