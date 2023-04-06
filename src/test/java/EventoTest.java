import org.junit.Test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
public class EventoTest
{

    @Test
    public void eventoSinTitulo()
    {
        //ARRANGE
        var evento = new Evento();
        //ACT
        String titulo = evento.obtenerTitulo();
        //ASSERT
        assertEquals("Evento sin titulo", titulo);
    }
    @Test
    public void eventoSinDescripcion()
    {
        //ARRANGE
        var evento = new Evento();
        //ACT
        String descripcion = evento.obtenerDescripcion();
        //ASSERT
        assertEquals("-", descripcion);
    }

    @Test
    public void creacionEventoNormal() //Este evento empieza un dia y termina otro
    {
        var fechaInicio = LocalDateTime.of(2023, 4, 1, 7, 0);
        var fechaFin = LocalDateTime.of(2023, 4, 8, 12, 30);

        var evento = new Evento("Examenes","Semana de examenes" ,fechaInicio, fechaFin);

        assertEquals("Examenes", evento.obtenerTitulo());
        assertEquals("Semana de examenes", evento.obtenerDescripcion());
        assertEquals(fechaInicio, evento.obtenerFechaInicio());
        assertEquals(fechaFin, evento.obtenerFechaFin());
    }

}