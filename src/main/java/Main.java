import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Evento Diario que se repite cada tres dias de forma infinita. God bless my pc
//        var dailyEvent = new EventoDiario();
//
//
//        System.out.println(dailyEvent.obtenerProximosEventos());



        //Evento Diario que se repite cada tres dias de forma infinita. God bless my pc
        String titulo = "Evento diario infinito";
        String descripcion = "Evento que se repite cada dia infinitamente";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0);

        int maxOcurrencias = 20; //Seteo un numero alto pero testeable

        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        int intervalo =1;

        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion, intervalo);

        List<LocalDateTime> proximosEventos = eventoDiario.obtenerProximosEventos();


        System.out.println(eventoDiario.obtenerTitulo());
        System.out.println(proximosEventos);



        System.out.println(eventoDiario.obtenerOcurrencias());
//        System.out.println(dailyEvent.obtenerFechaFin());
    }
}