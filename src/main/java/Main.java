import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//        //Para testear todos los cambios :]
//        CreadorEventosDiarios creadorEventosDiarios = new CreadorEventosDiarios();
//
        String titulo = "AAAAAAAAAAAAAAAAAAAAAAA";
        String descripcion = "Evento que se repite hasta llegar a una fecha";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0);

        int maxOcurrencias = 11;

        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        int intervalo = 1;
//
//        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion, intervalo);

//        ArrayList<Evento> listita = creadorEventosDiarios.proximosEventos(eventoDiario);
//
//        for (Evento evento : listita){
//            System.out.println(evento.obtenerFechaInicio());
//
//        }

        ConstructorEventos builder = new ConstructorEventosDiarios(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion, intervalo);
        CreadorDeEventos diario = new CreadorEventosDiarios();

        var calendario = new Calendario(diario);

        ArrayList<Evento> listaEventoss = calendario.proximosEventos(builder);
        calendario.agregarEventosACalendario(listaEventoss);


        ArrayList<Evento>eventito= calendario.obtenerListaEventosTotales();


        for (Evento evento : eventito){
            System.out.println(evento.obtenerTitulo());
        }


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