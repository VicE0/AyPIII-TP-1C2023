import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import java.time.Clock;

public class Main {
    public static void main(String[] args) {

//        //Para testear todos los cambios :]
//        CreadorEventosDiarios creadorEventosDiarios = new CreadorEventosDiarios();
//
        String titulo = "AAAAAAAAAAAAAAAAAAAAAAA";
        String descripcion = "Evento que se repite hasta llegar a una fecha";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0,10);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0,10);

        int maxOcurrencias = 10;

        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

//        int intervalo = 2;
        Set<DayOfWeek> diasSemana = Set.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);

//        CONSTRUCTOR ACA
        ConstructorEventos builder = new ConstructorEventosDiaCompleto(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
        CreadorDeEventos diaCompleto = new CreadorEventosDiaCompleto();

        var calendario = new Calendario(diaCompleto);

        ArrayList<Evento> listaEventoss = calendario.proximosEventos(builder);
        calendario.agregarEventosACalendario(listaEventoss);


        ArrayList<Evento>eventito= calendario.obtenerListaEventosTotales();


        for (Evento evento : eventito){
            System.out.println(evento.obtenerFechaInicio() + " " + evento.obtenerFechaFin());
        }
//
//        Clock clock = Clock.systemUTC().withZone(java.time.ZoneId.of("UTC+1")) ;
//
//        LocalDateTime now = LocalDateTime.now(clock);
//
//        System.out.println(clock.getZone());


    }
}
//class Director is
//        // The director works with any builder instance that the
//        // client code passes to it. This way, the client code may
//        // alter the final type of the newly assembled product.
//        // The director can construct several product variations
//        // using the same building steps.

//        method constructSportsCar(builder: Builder) is
//        builder.reset()
//        builder.setSeats(2)
//        builder.setEngine(new SportEngine())
//        builder.setTripComputer(true)
//        builder.setGPS(true)
//
//        method constructSUV(builder: Builder) is