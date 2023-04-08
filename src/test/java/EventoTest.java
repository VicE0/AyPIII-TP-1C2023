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



    //testeo el constructor default
   @Test
    public void testEventoEnBlanco() //En este caso, el evento esta en blanco y es de dia completo
    {
        // Arrange
        var evento = new Evento();

        // Act
        String titulo = evento.obtenerTitulo();
        String descripcion = evento.obtenerDescripcion();
        String repetir = evento.obtenerRepetir();
        int frecuencia = evento.obtenerFrecuencia();

        // Assert
        assertEquals("Evento sin titulo",titulo);
        assertEquals("-", descripcion);
        assertEquals("Diario", repetir);
        assertEquals(1, frecuencia);
        assertEquals(LocalDateTime.now().toLocalDate(), evento.obtenerFechaInicio().toLocalDate());
        assertEquals(LocalDateTime.now().plusDays(1).toLocalDate(), evento.obtenerFechaFin().toLocalDate());
    }

    //Testeo el constructor principal
    @Test
    public void creacionEventoNormal() //Este evento empieza un dia y termina otro
    {
        String titulo = "Examenes";
        String descripcion = "Semana de examenes";
        var fechaInicio = LocalDateTime.of(2023, 4, 1, 12, 30);
        var fechaFin = LocalDateTime.of(2023, 4, 8, 12, 30);
        String repetir = "Semanal";
        int frecuencia = 2;

        var evento = new Evento(titulo, descripcion,fechaInicio, fechaFin, repetir, frecuencia);

        assertEquals("Examenes", evento.obtenerTitulo());
        assertEquals("Semana de examenes", evento.obtenerDescripcion());
        assertEquals(fechaInicio, evento.obtenerFechaInicio());
        assertEquals(fechaFin, evento.obtenerFechaFin());
        assertEquals(repetir, evento.obtenerRepetir());
        assertEquals(frecuencia, evento.obtenerFrecuencia());
    }

}