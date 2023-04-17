import java.time.LocalDate;
import java.time.LocalDateTime;
public class EventoMensual extends Evento{
    public EventoMensual(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        super(titulo, descripcion, fechaInicio, fechaFin);
    }

    @Override
    public LocalDate obtenerSiguienteOcurrencia(LocalDate fecha) {
        return fecha.plusMonths(1);
    }
}
