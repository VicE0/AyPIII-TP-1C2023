import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
public class EventoDiario extends Evento
{
    private final long intervaloDeDias;

    public EventoDiario(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, long intervaloDeDias)
    {
        super(titulo, descripcion,fechaInicio,fechaFin);
        this.intervaloDeDias = intervaloDeDias;
    }

    public long obtenerIntervaloDeDias() {return intervaloDeDias;}

    public LocalDateTime obtenerProximoInicio()
    {
        return obtenerFechaInicio().plus(intervaloDeDias, ChronoUnit.DAYS);
    }
}
//Podria usarse para tareas


