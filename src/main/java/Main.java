import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {

        LocalDate fecha = LocalDate.of(2023, 4, 9);
        Dia dia = new Dia(fecha);


        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 9, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 9, 10, 0);
        Evento evento1 = new Evento("Evento Repetido", "AAAAAAAA", fechaInicio, fechaFin);
        evento1.establecerRepetirCada(3);

        LocalDateTime fechaInicio2 = LocalDateTime.of(2023, 4, 9, 13, 0);
        LocalDateTime fechaFin2 = LocalDateTime.of(2023, 4, 9, 14, 0);
        Evento evento2 = new Evento("Evento Unico", "BBBBBB", fechaInicio2, fechaFin2);
        evento2.establecerRepetirCada(0);

        dia.agregarEvento(evento1);
        dia.agregarEvento(evento2);

        ArrayList<Evento> eventosDelDia = dia.obtenerEventos();

        System.out.println(eventosDelDia.size());
        System.out.println(evento1.obtenerRepetirCada());

        for (Evento evento : eventosDelDia)
        {
            System.out.println(evento.obtenerTitulo() + " - " + evento.obtenerDescripcion() + " - " +
                    evento.obtenerFechaInicio() + " - " + evento.obtenerFechaFin());
        }
    }

    }

//    public void agregarEvento(Evento evento)
//    {
//        if (evento.obtenerRepetirCada() > 0) {

//            LocalDateTime fechaInicio = evento.obtenerFechaInicio();
//            LocalDateTime fechaFin = evento.obtenerFechaFin();

//            while (fechaInicio.toLocalDate().isBefore(fecha.plusDays(1)))
//            {
//                if (!fechaInicio.toLocalDate().isBefore(fechaInicio.toLocalDate()))
//                {
//                    LocalDateTime nuevaFechaInicio = LocalDateTime.of(fecha, fechaInicio.toLocalTime());
//                    LocalDateTime nuevaFechaFin = LocalDateTime.of(fecha, fechaFin.toLocalTime());
//                    Evento eventoRepetido = new Evento(evento.obtenerTitulo(), evento.obtenerDescripcion(), nuevaFechaInicio, nuevaFechaFin);
//                    this.eventos.add(eventoRepetido);
//                }
//                fechaInicio = fechaInicio.plusDays(evento.obtenerRepetirCada());
//                fechaFin = fechaFin.plusDays(evento.obtenerRepetirCada());
//            }
//        } else {
//            this.eventos.add(evento);
//        }
//    }
