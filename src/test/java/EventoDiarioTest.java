import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class EventoDiarioTest
{
    @Test
    public void EventoDiarioLit()
    {
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023,4,17,9,30);
        String titulo = "Evento1";
        String desc = "AAAA";
        long intervalo = 1;

        var eventoDiario = new EventoDiario(titulo,desc,fechaInicio, fechaFin, intervalo);

        LocalDateTime nuevaFechaEsperada = fechaInicio.plusDays(intervalo);
        LocalDateTime nuevaFechaActual = eventoDiario.obtenerProximoInicio();

        assertEquals(nuevaFechaEsperada, nuevaFechaActual);
    }

}
