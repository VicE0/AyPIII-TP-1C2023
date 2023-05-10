import java.time.LocalDateTime;

public class EventoDiario extends Evento{
    private int intervalo;

    public EventoDiario(LocalDateTime fechaInicio) {
        super(fechaInicio);
        this.intervalo = 1;
    }

    public EventoDiario(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias,Repeticion tipoRepeticion,int intervalo) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
        this.intervalo = intervalo;
    }

    public int obtenerIntervalo() {
        return intervalo;
    }

    public void establecerIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        if (intervalo > 1) {
            fecha = fecha.plusDays(intervalo - 1);
        }
        else if (intervalo == 0){
            return fecha;
        }

        else if (intervalo < 0){
            throw new RuntimeException("Intervalo de dias negativo");
        }
        return fecha.plusDays(1);
    }
}


