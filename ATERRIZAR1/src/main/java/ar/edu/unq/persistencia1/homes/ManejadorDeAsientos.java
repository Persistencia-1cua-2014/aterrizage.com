package ar.edu.unq.persistencia1.homes;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.exceptions.AsientoYaReservado;

public class ManejadorDeAsientos {

    public void reservarAsiento(Usuario usuario, Asiento asiento, Tramo tramo) throws AsientoYaReservado {
        if(asiento.estaReservado()){
            throw new AsientoYaReservado();
        }
        tramo.reservar(asiento, usuario);
    }

}
