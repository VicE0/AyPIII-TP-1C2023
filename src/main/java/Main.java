import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CreadorEventosDiarios creadorEventosDiarios = new CreadorEventosDiarios();
        CreadorEventosSemanales creadorEventosSemanales = new CreadorEventosSemanales();
        CreadorEventosMensuales creadorEventosMensuales = new CreadorEventosMensuales();
        CreadorEventosAnuales creadorEventosAnuales = new CreadorEventosAnuales();

        Calendario eventosDiarios = new Calendario(creadorEventosDiarios);
        Calendario eventosSemanales = new Calendario(creadorEventosSemanales);
        Calendario eventosMensuales = new Calendario(creadorEventosMensuales);
        Calendario eventosAnuales = new Calendario(creadorEventosAnuales);

        Calendario calendario = new Calendario();

        ArrayList<Evento> listaEventosDiarios   = eventosDiarios.proximosEventosDiarios("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1);
        ArrayList<Evento> listaEventosSemanales = eventosSemanales.proximosEventosSemanales("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, List.of(DayOfWeek.MONDAY));
        ArrayList<Evento> listaEventosMensuales = eventosMensuales.proximosEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 12, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2);
        ArrayList<Evento> listaEventosAnuales   = eventosAnuales.proximosEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.HASTA_FECHA_FIN, 3);


        calendario.agregarEventosACalendario(listaEventosDiarios);
        calendario.agregarEventosACalendario(listaEventosSemanales);
        calendario.agregarEventosACalendario(listaEventosMensuales);
        calendario.agregarEventosACalendario(listaEventosAnuales);

        ArrayList<Evento> listaTodosLosEventos = calendario.obtenerListaEventosTotales();
        System.out.println(listaTodosLosEventos);
//        calendario.eliminarEvento(listaTodosLosEventos.get(10));

        for(Evento eventoSemanal : listaEventosSemanales) {
            for(Evento evento : listaTodosLosEventos){
                System.out.println(evento.getClass() == eventoSemanal.getClass());
            }

        }



//        for(Evento enListac : listaTodosLosEventos){
//            System.out.println(enListac.obtenerTitulo());
//        }







    }
}