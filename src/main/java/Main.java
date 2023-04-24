import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        CreadorEventosDiarios creadorDeEventosDiarios = new CreadorEventosDiarios();
        Calendario eventosDiariosLista = new Calendario(creadorDeEventosDiarios);


        String titulo = "Evento Diario";
        String descripcion = "Evento que se repite hasta ocurrencias";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin    = LocalDateTime.of(2023, 4, 17, 9, 30);

        int maxOcurrencias = 3;

        //Pruebo un tipo por clase de Evento
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        var eventoDiario = new EventoDiario();
        eventoDiario.establecerIntervalo(1);
        int intervalo = eventoDiario.obtenerIntervalo();


        ArrayList<Evento> lista = eventosDiariosLista.proximosEventosDiarios(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervalo);

        eventosDiariosLista.agregarEventosACalendario(lista);
        ArrayList<Evento> todosloseventos = eventosDiariosLista.obtenerListaEventosTotales();

        eventosDiariosLista.eliminarEvento(todosloseventos.get(0));

        System.out.println(todosloseventos.isEmpty());
        for(Evento enListac : todosloseventos){
            System.out.println(enListac.obtenerFechaInicio());
        }







    }
}