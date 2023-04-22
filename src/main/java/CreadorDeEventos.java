import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public interface CreadorDeEventos {
    Evento crearEventoDefault();
    Evento crearEvento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int intervalo);
}
