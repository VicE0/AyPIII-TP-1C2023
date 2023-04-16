import org.junit.Test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
public class EventoTest
{
    //Testeo el constructor Principal
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
    public void testEventoEnBlanco() //En este caso, el evento esta en blanco y es de dia completo
    {
        //ARRANGE
        var evento = new Evento();

        //ACT
        String titulo = evento.obtenerTitulo();
        String descripcion = evento.obtenerDescripcion();


        //ASSERT
        assertEquals("Evento sin titulo",titulo);
        assertEquals("-", descripcion);
        assertEquals(LocalDateTime.now().toLocalDate(), evento.obtenerFechaInicio().toLocalDate());
        assertEquals(LocalDateTime.now().plusDays(1).toLocalDate(), evento.obtenerFechaFin().toLocalDate());
        assertNull(evento.obtenerFrecuencia());

    }

    //Testeo el constructor principal
    @Test
    public void creacionEventoNormal() //Este evento empieza un dia y termina otro
    {
        String titulo = "Examenes";
        String descripcion = "Semana de examenes";
        var fechaInicio = LocalDateTime.of(2023, 4, 1, 12, 30);
        var fechaFin = LocalDateTime.of(2023, 4, 8, 12, 30);
        Frecuencia frecuencia = Frecuencia.DIARIA;

        var evento = new Evento(titulo, descripcion,fechaInicio, fechaFin,frecuencia);

        assertEquals("Examenes", evento.obtenerTitulo());
        assertEquals("Semana de examenes", evento.obtenerDescripcion());
        assertEquals(fechaInicio, evento.obtenerFechaInicio());
        assertEquals(fechaFin, evento.obtenerFechaFin());
        assertEquals(frecuencia, evento.obtenerFrecuencia());
    }

    @Test
    public void obtenerSiguienteOcurrencia() {

        String titulo = "Test de ocurrencias";
        String descripcion = "Ocurrencias";
        var fechaInicio = LocalDateTime.of(2023, 4, 1, 12, 30);
        var fechaFin = LocalDateTime.of(2023, 4, 8, 12, 30);
        Frecuencia frecuencia = Frecuencia.DIARIA;

        var evento = new Evento(titulo, descripcion,fechaInicio, fechaFin,frecuencia);

        LocalDate fechaDiariaEsperada = LocalDate.of(2023, 4, 2);
        LocalDate fechaDiariaActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
        assertEquals(fechaDiariaEsperada, fechaDiariaActual);

        evento.establecerFrecuencia(Frecuencia.SEMANAL);
        LocalDate fechaSemanalEsperada = LocalDate.of(2023, 4, 8);
        LocalDate fechaSemanalActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
        assertEquals(fechaSemanalEsperada, fechaSemanalActual);

        evento.establecerFrecuencia(Frecuencia.MENSUAL);
        LocalDate fechaMensualEsperada = LocalDate.of(2023, 5, 1);
        LocalDate fechaMensualActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
        assertEquals(fechaMensualEsperada, fechaMensualActual);

        evento.establecerFrecuencia(Frecuencia.ANUAL);
        LocalDate fechaAnualEsperada = LocalDate.of(2024, 4, 1);
        LocalDate fechaAnualActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
        assertEquals(fechaAnualEsperada, fechaAnualActual);
    }

}