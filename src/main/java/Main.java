import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {

        // Create some events
        Evento evento1 = new Evento("Evento1", "Descriocion1",
                                    LocalDateTime.of(2023, 4, 8, 12, 30),LocalDateTime.of(2023,5,7,1,15));

        Evento evento2 = new Evento("Evento2", "AAAAAAAAA",  LocalDateTime.of(2023, 7, 1, 23, 30),LocalDateTime.of(2023,2,12,3,15));


        // Create a day and add the events
        Dia dia = new Dia(LocalDate.of(2023, 4, 8));
        if (evento1.obtenerFechaInicio().toLocalDate() == dia.obtenerFecha())
        {
            dia.agregarEvento(evento1);
        }
        else
        {
            System.out.println("No hay eventos para " + dia.obtenerFecha());
        }


//
//        System.out.println("Eventos  " + dia.obtenerFecha() + ":");
//        for (Evento evento : dia.obtenerEventos()) {
//            System.out.println(evento.obtenerTitulo() + " " + evento.obtenerDescripcion() + " (" + evento.obtenerFechaInicio().toLocalTime() + "-" + evento.obtenerFechaFin().toLocalTime() + ")");
//        }
    }
}