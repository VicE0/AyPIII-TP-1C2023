import java.time.LocalDateTime;
import java.util.List;
public class CreadorEventosDiarios implements CreadorDeEventos{

    public Evento crearEventoDefault() {
        return new EventoDiario();
    }

    public Evento crearEvento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int intervalo)
    {
        return new EventoDiario(titulo,descripcion,fechaInicio,fechaFin,maxOcurrencias, tipoRepeticion,intervalo);
    }

//    public List<LocalDateTime> proximosEventosDiarios(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion) {
//        Evento evento = new EventoDiario(titulo,descripcion,fechaInicio,fechaFin,maxOcurrencias, tipoRepeticion);
//        return evento.obtenerProximosEventos();
//    }

}
