import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoAnual extends Evento {
    private int cantidadAnios;

    public EventoAnual() {
        super();
        this.cantidadAnios = 1;
    }

    public EventoAnual(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int cantidadAnios) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
        this.cantidadAnios = cantidadAnios;
    }


    public int obtenerCantidadAnios() {
        return cantidadAnios;
    }

    public void establecerCantidadAnios(int cantidadAnios) {
        this.cantidadAnios = cantidadAnios;
    }


    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        if (cantidadAnios > 1) {
            fecha = fecha.plusYears(cantidadAnios - 1);

        } else if (cantidadAnios == 0) {
            return fecha;

        } else if (cantidadAnios < 0) {
            throw new RuntimeException("Cantidad de años negativa");
        }
        return fecha.plusYears(1);
    }

}