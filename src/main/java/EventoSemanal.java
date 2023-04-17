import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.List;

public class EventoSemanal extends Evento {
    private List<DayOfWeek> diasSemana;

    public EventoSemanal(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, List<DayOfWeek> diasSemana) {
        super(titulo, descripcion, fechaInicio, fechaFin);
        this.diasSemana = diasSemana;
    }

    public List<DayOfWeek> obtenerDiasSemana() {
        return diasSemana;
    }

    public void establecerDiasSemana(List<DayOfWeek> diasSemana) {
        this.diasSemana = diasSemana;
    }

    @Override
    public LocalDate obtenerSiguienteOcurrencia(LocalDate fecha) {
        if (diasSemana == null || diasSemana.isEmpty()) {
            return fecha.plusWeeks(1);
        }
        for (int i = 1; i <= 7; i++) {
            LocalDate siguienteFecha = fecha.plusDays(i);
            DayOfWeek siguienteDia = siguienteFecha.getDayOfWeek();
            if (diasSemana.contains(siguienteDia)) {
                return siguienteFecha;
            }
        }
        return null;
    }
}
