import java.time.LocalDateTime;

public class EventoMensual extends Evento{
    private int cantidadMeses;

    public EventoMensual(LocalDateTime fechaInicio){
        super(fechaInicio);
        this.cantidadMeses = 1;
    }

    public EventoMensual(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int cantidadMeses) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias,tipoRepeticion);
        this.cantidadMeses = cantidadMeses;
    }


    public int obtenerCantidadMeses() {
        return cantidadMeses;
    }

    public void establecerCantidadMeses(int cantidadMeses) {
        this.cantidadMeses = cantidadMeses;
    }

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        if (cantidadMeses > 1) {
            fecha = fecha.plusMonths(cantidadMeses - 1);
        }
        else if (cantidadMeses == 0){
            return fecha;
        }

        else if (cantidadMeses < 0){
            throw new RuntimeException("Cantidad de meses negativo");
        }
        return fecha.plusMonths(1);
    }
}
