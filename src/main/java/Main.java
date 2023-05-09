import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import java.time.Clock;

public class Main {
    public static void main(String[] args) {

//        //Para testear todos los cambios :]
//        CreadorEventosDiarios creadorEventosDiarios = new CreadorEventosDiarios();

        String titulo = "AAAAAAAAAAAAAAAAAAAAAAA";
        String descripcion = "Evento que se repite hasta llegar a una fecha";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0, 10);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0, 10);

        int maxOcurrencias = 10;

        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        int intervalo = 2;
//        Set<DayOfWeek> diasSemana = Set.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);

////        CONSTRUCTOR ACA
//        ConstructorEventos builder = new ConstructorEventosDiaCompleto(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
//        CreadorDeEventos diaCompleto = new CreadorEventosDiaCompleto();
//
//        var calendario = new Calendario(diaCompleto);
        ConstructorEventos eventoDiarioConstruido = new ConstructorEventosDiarios(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion, intervalo);
        ConstructorEventos eventoSemanalConstruido = new ConstructorEventosSemanales();
        ConstructorEventos eventoMensualConstruido = new ConstructorEventosMensuales();
        ConstructorEventos eventoAnualConstruido = new ConstructorEventosAnuales();
        ConstructorEventos eventoDiaCompletoConstruido = new ConstructorEventosDiaCompleto();


        CreadorDeEventos eventoDiarioCreado = new CreadorEventosDiarios();
        CreadorDeEventos eventoSemanalCreado = new CreadorEventosSemanales();
        CreadorDeEventos eventoMensualCreado = new CreadorEventosMensuales();
        CreadorDeEventos eventoAnualCreado = new CreadorEventosAnuales();
        CreadorDeEventos eventoDiaCompletoCreado = new CreadorEventosDiaCompleto();


        Evento eventoDiario = eventoDiarioCreado.crearEvento(eventoDiarioConstruido);
        Evento eventoSemanal = eventoSemanalCreado.crearEvento(eventoSemanalConstruido);
        Evento eventoMensual = eventoMensualCreado.crearEvento(eventoMensualConstruido);
        Evento eventoAnual = eventoAnualCreado.crearEvento(eventoAnualConstruido);
        Evento eventoDiaCompleto = eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);

        var calendario = new Calendario();

        //Agrego eventos iguales
        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoDiario);

        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoMensual);

        calendario.agregarEvento(eventoAnual);
        calendario.agregarEvento(eventoAnual);

        calendario.agregarEvento(eventoDiaCompleto);

        ArrayList<Evento> listaEventos = calendario.obtenerListaEventosTotales();

        //Elimino la instancia de EventoDiario y todas sus repeticiones
        calendario.eliminarEvento(listaEventos.get(0));

        for (Evento evento : listaEventos) {
            System.out.println(evento.getClass());

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