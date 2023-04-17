import org.junit.Test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

public class EventoTest
{
//    //Testeo el constructor Principal
//    @Test
//    public void eventoSinTitulo()
//    {
//        //ARRANGE
//        var evento = new Evento();
//        //ACT
//        String titulo = evento.obtenerTitulo();
//        //ASSERT
//        assertEquals("Evento sin titulo", titulo);
//    }
//    @Test
//    public void eventoSinDescripcion()
//    {
//        //ARRANGE
//        var evento = new Evento();
//        //ACT
//        String descripcion = evento.obtenerDescripcion();
//        //ASSERT
//        assertEquals("-", descripcion);
//    }
//
//   @Test
//    public void testEventoEnBlanco() //En este caso, el evento esta en blanco y es de dia completo
//    {
//        //ARRANGE
//        var evento = new Evento();
//
//        //ACT
//        String titulo = evento.obtenerTitulo();
//        String descripcion = evento.obtenerDescripcion();
//
//
//        //ASSERT
//        assertEquals("Evento sin titulo",titulo);
//        assertEquals("-", descripcion);
//        assertEquals(LocalDateTime.now().toLocalDate(), evento.obtenerFechaInicio().toLocalDate());
//        assertEquals(LocalDateTime.now().plusDays(1).toLocalDate(), evento.obtenerFechaFin().toLocalDate());
//
//    }
//
//    //Testeo el constructor principal
//    @Test
//    public void creacionEventoNormal() //Este evento empieza un dia y termina otro
//    {
//        String titulo = "Examenes";
//        String descripcion = "Semana de examenes";
//        var fechaInicio = LocalDateTime.of(2023, 4, 1, 12, 30);
//        var fechaFin = LocalDateTime.of(2023, 4, 8, 12, 30);
//        Frecuencia frecuencia = Frecuencia.DIARIA;
//
//        var evento = new Evento(titulo, descripcion,fechaInicio, fechaFin,frecuencia, null);
//
//        assertEquals("Examenes", evento.obtenerTitulo());
//        assertEquals("Semana de examenes", evento.obtenerDescripcion());
//        assertEquals(fechaInicio, evento.obtenerFechaInicio());
//        assertEquals(fechaFin, evento.obtenerFechaFin());
//        assertEquals(frecuencia, evento.obtenerFrecuencia());
//    }
//
//    @Test
//    public void TestEstablecerFrecuencia() {
//
//        String titulo = "Test de ocurrencias";
//        String descripcion = "Ocurrencias";
//        var fechaInicio = LocalDateTime.of(2023, 4, 1, 12, 30);
//        var fechaFin = LocalDateTime.of(2023, 4, 8, 12, 30);
//        Frecuencia frecuencia = Frecuencia.DIARIA;
//
//        var evento = new Evento(titulo, descripcion,fechaInicio, fechaFin,frecuencia, null);
//
//        LocalDate fechaDiariaEsperada = LocalDate.of(2023, 4, 2);
//        LocalDate fechaDiariaActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
//        assertEquals(fechaDiariaEsperada, fechaDiariaActual);
//
//        evento.establecerFrecuencia(Frecuencia.SEMANAL);
//        LocalDate fechaSemanalEsperada = LocalDate.of(2023, 4, 8);
//        LocalDate fechaSemanalActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
//        assertEquals(fechaSemanalEsperada, fechaSemanalActual);
//
//        evento.establecerFrecuencia(Frecuencia.MENSUAL);
//        LocalDate fechaMensualEsperada = LocalDate.of(2023, 5, 1);
//        LocalDate fechaMensualActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
//        assertEquals(fechaMensualEsperada, fechaMensualActual);
//
//        evento.establecerFrecuencia(Frecuencia.ANUAL);
//        LocalDate fechaAnualEsperada = LocalDate.of(2024, 4, 1);
//        LocalDate fechaAnualActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
//        assertEquals(fechaAnualEsperada, fechaAnualActual);
//    }
//
//    @Test
//    public void TestEventoCadaTresDias() {
//
//        String titulo = "Evento Diario";
//        String descripcion = "Se repite cada 3 dias";
//        var fechaInicio = LocalDateTime.of(2023, 4, 1, 12, 30);
//        var fechaFin = LocalDateTime.of(2023, 4, 8, 12, 30);
//        Frecuencia frecuencia = Frecuencia.DIARIA;
//        int intervaloDias = 3;
//        frecuencia.establecerIntervalo(intervaloDias);
//
//        var evento = new Evento(titulo, descripcion, fechaInicio, fechaFin, frecuencia, null);
//
//        LocalDate fechaDiariaEsperada = LocalDate.of(2023, 4, 4);
//        LocalDate fechaDiariaActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
//        assertEquals(fechaDiariaEsperada, fechaDiariaActual);
//    }
//
//    @Test
//    public void TestEventoSemanal() {
//
//        String titulo = "Evento Semanal";
//        String descripcion = "Se repite todos los Lunes y Viernes";
//
//        var fechaInicio = LocalDateTime.of(2023, 4, 3, 12, 30); //Comienzo el lunes 3 / 04
//        var fechaFin = LocalDateTime.of(2023, 4, 8, 12, 30);
//
//        Frecuencia frecuencia = Frecuencia.SEMANAL;
//        List<DayOfWeek>  diasSemanaEventos = List.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
//
//        var evento = new Evento(titulo, descripcion, fechaInicio, fechaFin, frecuencia, diasSemanaEventos);
//
//        assertEquals(diasSemanaEventos.size(),2);
//
//        LocalDate fechaViernesEsperada = LocalDate.of(2023, 4, 7); //Espero que la siguiente fecha sea el viernes 7/04
//        LocalDate fechaViernesActual = evento.obtenerSiguienteOcurrencia(fechaInicio.toLocalDate());
//        assertEquals(fechaViernesEsperada, fechaViernesActual);
//
//        LocalDate fechaLunesEsperada = LocalDate.of(2023, 4, 10); // Espero que la siguiente fecha a partir del viernes 7/04 sea el lunes 10/04
//        LocalDate fechaLunesActual = evento.obtenerSiguienteOcurrencia(fechaViernesActual);
//        assertEquals(fechaLunesEsperada, fechaLunesActual);
//    }
}