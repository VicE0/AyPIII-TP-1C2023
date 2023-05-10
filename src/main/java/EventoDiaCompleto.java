import java.time.LocalDateTime;
import java.time.LocalDate;

public class EventoDiaCompleto extends Evento {
    private LocalDateTime fechaInicioDia;
    private LocalDateTime fechaFinalDia;


    public EventoDiaCompleto(LocalDate fechaInicio) {
        super(fechaInicio.atTime(0,0,0));
        this.fechaFinalDia = fechaInicio.atTime(23,59,59);
    }

    public EventoDiaCompleto(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
    }

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        return fecha.plusDays(1);
    }
}
