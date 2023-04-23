import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


//        CreadorEventosDiarios   creadorEventosDiarios     =   new CreadorEventosDiarios();
//        CreadorEventosSemanales creadorEventosSemanales   =   new CreadorEventosSemanales();
//        CreadorEventosMensuales creadorEventosMensuales   =   new CreadorEventosMensuales();
//        CreadorEventosAnuales   creadorEventosAnuales     =   new CreadorEventosAnuales();
//
//        Calendario eventosDiarios   = new Calendario(creadorEventosDiarios);
//        Calendario eventosSemanales = new Calendario(creadorEventosSemanales);
//        Calendario eventosMensuales = new Calendario(creadorEventosMensuales);
//        Calendario eventosAnuales   = new Calendario(creadorEventosAnuales);
//
//        Calendario calendario = new Calendario();
//
//        //Creo multiples eventos iguales
//        eventosDiarios.crearEventoDefault();
//        eventosDiarios.crearEventoDefault();


//
//        eventosSemanales.crearEventoDefault();
//        eventosSemanales.crearEventoDefault();
//        eventosSemanales.crearEventoDefault();
//
//        eventosMensuales.crearEventoDefault();
//        eventosAnuales.crearEventoDefault();
//
//        calendario.agregarEventoACalendario(eventosDiarios.obtenerEventosCreados());
//        calendario.agregarEventoACalendario(eventosSemanales.obtenerEventosCreados());
//        calendario.agregarEventoACalendario(eventosMensuales.obtenerEventosCreados());
//        calendario.agregarEventoACalendario(eventosAnuales.obtenerEventosCreados());
//
//        ArrayList<Evento> aaaaa = calendario.obtenerListaEventosTotales();
//
//        calendario.eliminarEvento(calendario.obtenerListaEventosTotales().get(2));
//        System.out.println(aaaaa);

        String titulo = "Evento Cada 3 Dias";
        String descripcion = "Evento que se repite cada 3 dias";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);

        //Estos dos atributos son testeados correctamento en los siguientes test
        int maxOcurrencias = 3;
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        int intervalo = 1;

        Evento eventoDiario = new EventoDiario(titulo,descripcion,fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervalo);


        List<Evento> proximosEventos = eventoDiario.obtenerProximosEventos(eventoDiario);
//
        // print the details of each occurrence
        for (Evento proximoEvento : proximosEventos) {
            System.out.println(proximoEvento.obtenerFechaInicio());
        }




    }
}