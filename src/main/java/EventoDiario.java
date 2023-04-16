import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EventoDiario extends Evento
{
    private long intervaloDeDias;

    public EventoDiario(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,Frecuencia frecuencia, long intervaloDeDias) {

        super(titulo, descripcion,fechaInicio,fechaFin, frecuencia, null);
        this.intervaloDeDias = intervaloDeDias;
    }

    public LocalDateTime obtenerProximoInicio(LocalDateTime fecha) {
        return fecha.plus(intervaloDeDias, ChronoUnit.DAYS);
    }

    public long obtenerIntervaloDeDias() {return intervaloDeDias;}


    //En el caso que el evento se repita y tenga una fecha final
    public ArrayList<LocalDateTime> obtenerProximosEventos(LocalDateTime Inicio, LocalDateTime Fin) {

        ArrayList<LocalDateTime> proximosEventos = new ArrayList<>();

        // Añade la primera fecha ingresada
        proximosEventos.add(Inicio);

        LocalDateTime proximaFecha = obtenerProximoInicio(Inicio);

        if (proximaFecha.isBefore(Fin)) {
            proximosEventos.add(proximaFecha);
        }

        // Añade todas las demas hata llegar a la fecha Fin
       while(proximaFecha.isBefore(Fin)) {
            proximaFecha = obtenerProximoInicio(proximaFecha);
            proximosEventos.add(proximaFecha);
        }

       return proximosEventos;
    }
}


