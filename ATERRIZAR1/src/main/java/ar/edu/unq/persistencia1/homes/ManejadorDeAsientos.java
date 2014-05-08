package ar.edu.unq.persistencia1.homes;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.exceptions.AsientoYaReservado;

import java.util.List;

public class ManejadorDeAsientos {

    public void reservarAsiento(Usuario usuario, Asiento asiento, Tramo tramo) throws AsientoYaReservado {
        if(tramo.estaReservado(asiento)){
            throw new AsientoYaReservado();
        }
        tramo.reservar(asiento, usuario);
    }

    public void reservarAsientos(Usuario usuario, List<Asiento> asientos, Tramo tramo) throws AsientoYaReservado {
        if(tramo.alMenosUnoReservados(asientos)){
            throw new AsientoYaReservado();
        }
        tramo.reservar(asientos, usuario);
    }

    public List<Asiento> asientosDisponibles(Tramo tramo){
        return tramo.getAsientosDisponibles();
    }

}
