import org.junit.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EventoDiarioTest {
//
//    //Chequeo caso intervalo negativo
//    @Rule
//    public ExpectedException error = ExpectedException.none();
//
//    //Testeo constructor default de la clase Evento para eventoDiario
//    @Test
//    public void testEventoDiarioDefault() {
//
//        var eventoDiario = new EventoDiario();
//
//        String titulo =  "Evento sin titulo";
//        String descripcion = "-";
//
//        LocalDate fechaInicio = LocalDate.now();
//        LocalDate fechaFin = fechaInicio.plusDays(1);
//
//        int ocurrenciasRealizadas = 0;
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_FECHA_FIN;
//
//        int intervalo = 1;
//
//        assertEquals(titulo, eventoDiario.obtenerTitulo());
//        assertEquals(descripcion, eventoDiario.obtenerDescripcion());
//        assertEquals(fechaInicio, eventoDiario.obtenerFechaInicio().toLocalDate());
//        assertEquals(fechaFin, eventoDiario.obtenerFechaFin().toLocalDate());
//        assertEquals(ocurrenciasRealizadas, eventoDiario.obtenerOcurrencias());
//        assertEquals(maxOcurrencias, eventoDiario.obtenerMaxOcurrencias());
//        assertEquals(tipoRepeticion, eventoDiario.obtenerTipoRepeticion());
//        assertEquals(intervalo, eventoDiario.obtenerIntervalo());
//
//        //Para los tests de constructor Default de la clase abstracta Evento se utilizó el metodo .toLocalDate para que el test no falle por milésima de segundos.
//
//    }
//    //Creo un EventoDiario Default
//    @Test
//    public void testCreacionEventoDiarioDefault() {
//
//        var eventoDiario = new EventoDiario();
//
//        List<LocalDateTime> proximosEventos = eventoDiario.obtenerProximosEventos();
//
//        LocalDateTime primerEventoEnLista = proximosEventos.get(0);
//        LocalDateTime segundoEventoEnLista = proximosEventos.get(1);
//
//        assertEquals(primerEventoEnLista.toLocalDate(), eventoDiario.obtenerFechaInicio().toLocalDate());
//        assertEquals(segundoEventoEnLista.toLocalDate(), eventoDiario.obtenerFechaFin().toLocalDate());
//
//        assertEquals(2, proximosEventos.size());
//    }
//
//    @Test
//    public void testObtenerSiguienteOcurrenciaValoresDefault() {
//
//        var eventoDiario = new EventoDiario();
//
//        //Por default, el intervalo = 1
//        LocalDateTime FechaEsperada = eventoDiario.obtenerFechaInicio().plusDays(eventoDiario.obtenerIntervalo());
//        LocalDateTime FechaActual = eventoDiario.calcularSiguienteOcurrencia(eventoDiario.obtenerFechaInicio());
//
//        assertEquals(FechaEsperada, FechaActual);
//
//    }
//    @Test
//    public void testObtenerSiguienteOcurrencia() {
//
//        String titulo = "Evento Diario";
//        String descripcion =   "Evento que se repite todos los dias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin =    LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion);
//
//        LocalDateTime FechaEsperada = fechaInicio.plusDays(1);
//        LocalDateTime FechaActual = eventoDiario.calcularSiguienteOcurrencia(fechaInicio);
//
//        assertEquals(FechaEsperada, FechaActual);
//    }
//
//    @Test
//    public void testEventoCadaTresDias() {
//
//        String titulo = "Evento Cada 3 Dias";
//        String descripcion = "Evento que se repite cada 3 dias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//
//        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
//        int intervalo = eventoDiario.obtenerIntervalo();
//        eventoDiario.establecerIntervalo(3);
//
//
//        LocalDateTime fechaEsperada = fechaInicio.plusDays(3);
//        LocalDateTime FechaActual = eventoDiario.calcularSiguienteOcurrencia(fechaInicio);
//
//        assertEquals(fechaEsperada, FechaActual);
//        //Testeo el getter
//        assertNotEquals(intervalo, eventoDiario.obtenerIntervalo());
//    }
//
//    @Test
//    public void testIntervaloCero() {
//
//        String titulo = "Evento Diario";
//        String descripcion = "Evento que ingresa un intervalo de dias nulo";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
//        eventoDiario.establecerIntervalo(0);
//
//        LocalDateTime fechaEsperada = fechaInicio;
//        LocalDateTime FechaActual = eventoDiario.calcularSiguienteOcurrencia(fechaInicio);
//        assertEquals(fechaEsperada, FechaActual);
//    }
//
//
//    @Test
//    public void testIntervaloNegativo() {
//
//        String titulo = "Evento Cada 3 Dias";
//        String descripcion = "Evento que se repite cada 3 dias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//
//        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
//        eventoDiario.establecerIntervalo(-1);
//
//        error.expect(RuntimeException.class);
//        eventoDiario.calcularSiguienteOcurrencia(fechaInicio);
//    }
//
//
//    //Test de EventoDiario con repeticion infinita
//    @Test
//    public void testEventoDiarioInfinito() {
//
//        String titulo = "Evento diario infinito";
//        String descripcion = "Evento que se repite cada dia infinitamente";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 99; //Seteo un numero alto pero testeable
//        Repeticion tipoRepeticion = Repeticion.INFINITA;
//
//
//        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
//        eventoDiario.establecerIntervalo(1);
//
//        List<LocalDateTime> proximosEventos = eventoDiario.obtenerProximosEventos();
//
//        LocalDateTime fechaActual = fechaInicio;
//        for (LocalDateTime fecha : proximosEventos) {
//
//            assertFalse(fechaActual.isAfter(fecha));
//            fechaActual = fecha;
//        }
//
//        assertEquals(99, proximosEventos.size());
//        assertEquals(null, eventoDiario.obtenerFechaFin());
//    }
//
//
//   //Test de EventoDiario con repeticion hasta alcanzar fechaFin
//    @Test
//    public void testEventoDiarioHastaFecha() {
//
//        String titulo = "Evento diario";
//        String descripcion = "Evento que se repite hasta llegar a una fecha";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0);
//
//        int maxOcurrencias = 1;
//
//        Repeticion tipoRepeticion = Repeticion.HASTA_FECHA_FIN;
//
//        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
//        eventoDiario.establecerIntervalo(1);
//
//        List<LocalDateTime> proximosEventos = eventoDiario.obtenerProximosEventos();
//
//        LocalDateTime fechaActual = fechaInicio;
//        for (LocalDateTime fecha : proximosEventos) {
//
//            assertFalse(fechaActual.isAfter(fecha));
//            fechaActual = fecha;
//        }
//
//        assertEquals(10, proximosEventos.size());
//
//    }
//
//    //test de EventoDiario con repeticion hasta que se alcancen 20 ocurrencias
//    @Test
//    public void testEventoDiarioHastaOcurrencias() {
//
//        String titulo = "Evento diario";
//        String descripcion = "Evento que se repite hasta alcanzar 20 ocurrencias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0);
//
//        int maxOcurrencias = 20;
//
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        var eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
//        eventoDiario.establecerIntervalo(1);
//
//        List<LocalDateTime> proximosEventos = eventoDiario.obtenerProximosEventos();
//
//        LocalDateTime fechaActual = fechaInicio;
//        for (LocalDateTime fecha : proximosEventos) {
//
//            assertFalse(fechaActual.isAfter(fecha));
//            fechaActual = fecha;
//        }
//        assertEquals(20, eventoDiario.obtenerMaxOcurrencias());
//        assertEquals(eventoDiario.obtenerOcurrencias(), proximosEventos.size());
//    }

}






