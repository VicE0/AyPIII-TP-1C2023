
import java.time.LocalDate;
import java.time.LocalDateTime;
public class EventoAnual extends Evento {
    public EventoAnual(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        super(titulo, descripcion, fechaInicio, fechaFin);
    }

    @Override
    public LocalDate obtenerSiguienteOcurrencia(LocalDate fecha) {
        return fecha.plusYears(1);
    }
}