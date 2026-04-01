package cl.duoc.AppFiestas.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cl.duoc.AppFiestas.model.Fiesta;

@Repository
public class FiestaRepository {


    //Arreglo que guardará las fiestas
    private List<Fiesta> listaFiestas = new ArrayList<>();


    //Metodo que retorna todas las fiestas
    public List<Fiesta> obtenerFiestas(){
        return listaFiestas;
    }

    // busca una fiesta por id
    public Fiesta buscarFiestaPorId(int id){
        for (Fiesta fiesta : listaFiestas){
            if(fiesta.getId() == id){
                return fiesta;
            }
        }
        return null;
    }

    //Guardar fiesta en la lista
    public Fiesta guardarFiesta(Fiesta fiesta){
        listaFiestas.add(fiesta);
        return fiesta;
    }

    //Actualizar una fiesta
    public Fiesta actualizarFiesta(Fiesta fiesta){
        for (int i = 0; i < listaFiestas.size(); i++ ){
            if(listaFiestas.get(i).getId() == fiesta.getId()){
                listaFiestas.set(i, fiesta);  // Reemplazar la fiesta antigua por la nueva
                return fiesta;
            }
        }
        return null;
    }
    
    //Eliminar una fiesta por id
    public boolean eliminarFiesta(int id){
        Fiesta fiesta = buscarFiestaPorId(id);
        if (fiesta != null){
            listaFiestas.remove(fiesta);
            return true; // Se eliminó correctamente

        }
        return false; // No exite una fiesta con ese id
    }

    //Filtra por tipo de fiesta
    public List<Fiesta> buscarPorTipo(String tipo){
        List<Fiesta> resultado = new ArrayList<>();

        for(Fiesta f : listaFiestas){
            if(f.getTipo().equalsIgnoreCase(tipo)){
                resultado.add(f);
            }
        }
        return resultado;
    }

    //Filtra por fecha
    public List<Fiesta> buscarPorFecha(LocalDate fecha){
        List<Fiesta> resultado = new ArrayList<>();
        
        for(Fiesta f : listaFiestas){
            if(f.getFecha().equals(fecha)){
                resultado.add(f);
            }
        }
        return resultado;
    }
}
