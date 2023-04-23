import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        CreadorEventosDiarios   creadorEventosDiarios     =   new CreadorEventosDiarios();
        CreadorEventosSemanales creadorEventosSemanales   =   new CreadorEventosSemanales();
        CreadorEventosMensuales creadorEventosMensuales   =   new CreadorEventosMensuales();
        CreadorEventosAnuales   creadorEventosAnuales     =   new CreadorEventosAnuales();

        Calendario eventosDiarios   = new Calendario(creadorEventosDiarios);
        Calendario eventosSemanales = new Calendario(creadorEventosSemanales);
        Calendario eventosMensuales = new Calendario(creadorEventosMensuales);
        Calendario eventosAnuales   = new Calendario(creadorEventosAnuales);

        Calendario calendario = new Calendario();

        //Creo multiples eventos iguales
        eventosDiarios.crearEventoDefault();
        eventosDiarios.crearEventoDefault();

        eventosSemanales.crearEventoDefault();
        eventosSemanales.crearEventoDefault();
        eventosSemanales.crearEventoDefault();

        eventosMensuales.crearEventoDefault();
        eventosAnuales.crearEventoDefault();

        calendario.agregarEventoACalendario(eventosDiarios.obtenerEventosCreados());
        calendario.agregarEventoACalendario(eventosSemanales.obtenerEventosCreados());
        calendario.agregarEventoACalendario(eventosMensuales.obtenerEventosCreados());
        calendario.agregarEventoACalendario(eventosAnuales.obtenerEventosCreados());

        ArrayList<Evento> aaaaa = calendario.obtenerListaEventosTotales();

        calendario.eliminarEvento(calendario.obtenerListaEventosTotales().get(6));




        System.out.println(aaaaa);


    }
}