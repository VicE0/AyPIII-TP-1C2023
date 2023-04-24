import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class EventoSemanal extends Evento {
    private List<DayOfWeek> diasSemana;

    public EventoSemanal() {
        super();
        this.diasSemana = null;
    }

    public EventoSemanal(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,int maxOcurrencias,Repeticion tipoRepeticion ,List<DayOfWeek> diasSemana) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias,tipoRepeticion);;
        this.diasSemana = diasSemana;
    }

    public List<DayOfWeek> obtenerDiasSemana() {
        return diasSemana;
    }

    public void establecerDiasSemana(List<DayOfWeek> diasSemana) {
        this.diasSemana = diasSemana;
    }

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        if (diasSemana == null || diasSemana.isEmpty()) {

            this.establecerFechaFin(fecha.plusWeeks(1));
            return fecha.plusWeeks(1);
        }
        for (int i = 1; i <= 7; i++) {
            LocalDateTime siguienteFecha = fecha.plusDays(i);
            DayOfWeek siguienteDia = siguienteFecha.getDayOfWeek();
            if (diasSemana.contains(siguienteDia)) {
                return siguienteFecha;
            }
        }
        return null;
    }
}
