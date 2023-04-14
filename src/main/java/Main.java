import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LocalDateTime startDate = LocalDateTime.of(2023, 4, 10, 10, 0);
        LocalDateTime endDate = startDate.plusHours(1);

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023,4,20,9,30);
        String titulo = "Evento1";
        String desc = "AAAA";
        long intervalo = 3;

        EventoDiario dailyEvent = new EventoDiario(titulo,desc,startDate, endDate,intervalo);

        System.out.println(dailyEvent.obtenerProximosEventos(fechaInicio, fechaFin));
    }
}