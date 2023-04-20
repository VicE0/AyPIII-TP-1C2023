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

        String titulo = "Evento Semanal";
        String descripcion = "Evento que se repite cada martes y jueves infinitamente";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);


        int maxOcurrencias = 5; //ingreso un numero grande pero testeable
        Repeticion tipoRepeticion = Repeticion.INFINITA;

        List<DayOfWeek> diasSemana = List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY);

        var eventoSemanal = new EventoSemanal(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion ,diasSemana);

        List<LocalDateTime> proximosEventos = eventoSemanal.obtenerProximosEventos();

        System.out.println(proximosEventos);

        System.out.println(eventoSemanal.obtenerFechaFin());
    }
}