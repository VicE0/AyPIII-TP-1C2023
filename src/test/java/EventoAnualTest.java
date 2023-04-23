//import static org.junit.Assert.*;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class EventoAnualTest {
//    @Rule
//    public ExpectedException error = ExpectedException.none();
//
//    //Testeo constructor default de la clase Evento para eventoMensual
//    @Test
//    public void testEventoMensualDefault() {
//
//        var eventoAnual = new EventoAnual();
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
//        assertEquals(titulo, eventoAnual.obtenerTitulo());
//        assertEquals(descripcion, eventoAnual.obtenerDescripcion());
//        assertEquals(fechaInicio, eventoAnual.obtenerFechaInicio().toLocalDate());
//        assertEquals(fechaFin, eventoAnual.obtenerFechaFin().toLocalDate());
//        assertEquals(ocurrenciasRealizadas, eventoAnual.obtenerOcurrencias());
//        assertEquals(maxOcurrencias, eventoAnual.obtenerMaxOcurrencias());
//        assertEquals(tipoRepeticion, eventoAnual.obtenerTipoRepeticion());
//
//        //Para los tests de constructor Default de la clase abstracta Evento se utilizó el metodo .toLocalDate para que el test no falle por milésima de segundos.
//
//    }
//    //Creo un EventoSemanal Default
//    @Test
//    public void testCreacionEventoAnualDefault() {
//
//        var eventoAnual = new EventoAnual();
//
//        List<LocalDateTime> proximosEventos = eventoAnual.obtenerProximosEventos();
//
//        LocalDateTime primerEventoEnLista = proximosEventos.get(0);
//        LocalDateTime segundoEventoEnLista = proximosEventos.get(1);
//
//        assertEquals(primerEventoEnLista.toLocalDate(), eventoAnual.obtenerFechaInicio().toLocalDate());
//        assertEquals(segundoEventoEnLista.toLocalDate(), eventoAnual.obtenerFechaInicio().toLocalDate().plusYears(1));
//
//        assertEquals(2, proximosEventos.size());
//    }
//
//    @Test
//    public void testObtenerSiguienteOcurrenciaValoresDefault() {
//
//        var eventoAnual = new EventoAnual();
//
//        LocalDateTime FechaEsperada = eventoAnual.obtenerFechaInicio().plusYears(1);
//        LocalDateTime FechaActual = eventoAnual.calcularSiguienteOcurrencia(eventoAnual.obtenerFechaInicio());
//
//        assertEquals(FechaEsperada, FechaActual);
//
//    }
//
//    @Test
//    public void testEventoAnual() {
//
//        String titulo = "Evento Anual";
//        String descripcion =   "Evento que se repite al siguiente mes";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0); //lunes 3 de abril
//        LocalDateTime fechaFin =    LocalDateTime.of(2023, 4, 24, 9, 30); //lunes 24 de abril
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//        int cantidadAnios = 1;
//
//        var eventoAnual = new EventoAnual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadAnios);
//
//        LocalDateTime FechaEsperada = fechaInicio.plusYears(cantidadAnios);
//        LocalDateTime FechaActual = eventoAnual.calcularSiguienteOcurrencia(fechaInicio);
//
//        assertEquals(FechaEsperada, FechaActual);
//    }
//
//
//    @Test
//    public void testEventoCada3Anios() {
//
//        String titulo = "Evento Anual";
//        String descripcion =   "Evento que se repite cada 3 años";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0); //lunes 3 de abril
//        LocalDateTime fechaFin =    LocalDateTime.of(2023, 4, 24, 9, 30); //lunes 24 de abril
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        int cantidadAnios = 1;
//
//        var eventoAnual = new EventoAnual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadAnios);
//        eventoAnual.establecerCantidadAnios(3);
//
//
//        LocalDateTime fechaEsperada = fechaInicio.plusYears(3);
//        LocalDateTime FechaActual = eventoAnual.calcularSiguienteOcurrencia(fechaInicio);
//
//        assertEquals(fechaEsperada, FechaActual);
//        assertNotEquals(cantidadAnios, eventoAnual.obtenerCantidadAnios());
//    }
//
//    @Test
//    public void testcantidadAniosCero() {
//
//        String titulo = "Evento Anual";
//        String descripcion = "Evento con cantidad de meses nulo";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        int cantidadAnios = 0;
//
//        var eventoAnual = new EventoAnual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadAnios);
//
//
//        LocalDateTime fechaEsperada = fechaInicio;
//        LocalDateTime FechaActual = eventoAnual.calcularSiguienteOcurrencia(fechaInicio);
//        assertEquals(fechaEsperada, FechaActual);
//    }
//
//
//    @Test
//    public void testCantAniosNegativa() {
//
//        String titulo = "Evento Anual";
//        String descripcion = "Evento que se repite cada 3 dias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 1;
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        int cantidadAnios = -1;
//
//        var eventoAnual = new EventoAnual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadAnios);
//
//        error.expect(RuntimeException.class);
//        eventoAnual.calcularSiguienteOcurrencia(fechaInicio);
//    }
//
//
//    //Test de EventoAnual con repeticion infinita
//    @Test
//    public void testEventoAnualInfinito() {
//
//        String titulo = "Evento Anual infinito";
//        String descripcion = "Evento que se repite cada mes infinitamente";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 99; //Seteo un numero alto pero testeable
//        Repeticion tipoRepeticion = Repeticion.INFINITA;
//
//        int cantidadAnios = 1;
//
//        var eventoAnual = new EventoAnual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadAnios);
//
//        List<LocalDateTime> proximosEventos = eventoAnual.obtenerProximosEventos();
//
//        LocalDateTime fechaActual = fechaInicio;
//        for (LocalDateTime fecha : proximosEventos) {
//
//            assertFalse(fechaActual.isAfter(fecha));
//            fechaActual = fecha;
//        }
//
//        assertEquals(99, proximosEventos.size());
//        assertNull(eventoAnual.obtenerFechaFin());
//    }
//
//
//    //Test de EventoAnual con repeticion hasta alcanzar fechaFin
//    @Test
//    public void testEventoAnualHastaFecha() {
//
//        String titulo = "Evento Anual";
//        String descripcion = "Evento que se repite hasta llegar a una fecha";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2029, 4, 1, 9, 0);
//
//        int maxOcurrencias = 1;
//
//        Repeticion tipoRepeticion = Repeticion.HASTA_FECHA_FIN;
//
//        int cantidadAnios = 2;
//
//        var eventoAnual = new EventoAnual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadAnios);
//
//        List<LocalDateTime> proximosEventos = eventoAnual.obtenerProximosEventos();
//
//
//        LocalDateTime fechaActual = fechaInicio;
//        for (LocalDateTime fecha : proximosEventos) {
//
//            assertFalse(fechaActual.isAfter(fecha));
//            fechaActual = fecha;
//        }
//
//        LocalDateTime fecha2023 = proximosEventos.get(0);
//        LocalDateTime fecha2025 = proximosEventos.get(1);
//        LocalDateTime fecha2027 = proximosEventos.get(2);
//        LocalDateTime fecha2029 = proximosEventos.get(3);
//
//        LocalDateTime fecha2023Esperada = fechaInicio;
//        LocalDateTime fecha2025Esperada = fecha2023Esperada.plusYears(2);
//        LocalDateTime fecha2027Esperada = fecha2025Esperada.plusYears(2);
//        LocalDateTime fecha2029Esperada = fecha2027Esperada.plusYears(2);
//
//        assertEquals(fecha2023,fecha2023Esperada );
//        assertEquals(fecha2025,fecha2025Esperada );
//        assertEquals(fecha2027,fecha2027Esperada );
//        assertEquals(fecha2029,fecha2029Esperada );
//
//        assertEquals(4, proximosEventos.size());
//
//    }
//
//    //test de EventoAnual con repeticion hasta que se alcancen 20 ocurrencias
//    @Test
//    public void testEventoAnualHastaOcurrencias() {
//
//        String titulo = "Evento Anual";
//        String descripcion = "Evento que se repite hasta alcanzar 20 ocurrencias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0);
//
//        int maxOcurrencias = 20;
//
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        int cantidadAnios = 1;
//
//        var eventoAnual = new EventoAnual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadAnios);
//
//        List<LocalDateTime> proximosEventos = eventoAnual.obtenerProximosEventos();
//
//        LocalDateTime fechaActual = fechaInicio;
//        for (LocalDateTime fecha : proximosEventos) {
//
//            assertFalse(fechaActual.isAfter(fecha));
//            fechaActual = fecha;
//        }
//        assertEquals(20, eventoAnual.obtenerMaxOcurrencias());
//        assertEquals(eventoAnual.obtenerOcurrencias(), proximosEventos.size());
//    }
//
//}