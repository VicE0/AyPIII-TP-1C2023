import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
public class CreadorEventosDiarios implements CreadorDeEventos{

    public Evento crearEventoDefault() {
        return new EventoDiario();
    }

    //El parámetro "intervalo" puede ser usado por EventoDiario, EventoMensual y EventoAnual, ya que todos usan el tipo int y la misma lógica pero distinta aplicación.
    public Evento crearEvento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int intervalo,List<DayOfWeek> diasSemana ) {
        return new EventoDiario(titulo,descripcion,fechaInicio,fechaFin,maxOcurrencias, tipoRepeticion,intervalo);
    }
    public Evento obtenerEventos(){
        return new EventoDiario();

    }

}
