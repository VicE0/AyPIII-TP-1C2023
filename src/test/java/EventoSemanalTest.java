import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.List;

import static org.junit.Assert.*;

public class EventoSemanalTest {

    //Testeo constructor default de la clase Evento para eventoSemanal
    @Test
    public void testEventoSemanalDefault() {

        var eventoSemanal = new EventoSemanal();

        String titulo =  "Evento sin titulo";
        String descripcion = "-";

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = fechaInicio.plusDays(1);

        int ocurrenciasRealizadas = 0;
        int maxOcurrencias = 1;
        Repeticion tipoRepeticion = Repeticion.HASTA_FECHA_FIN;

        assertEquals(titulo, eventoSemanal.obtenerTitulo());
        assertEquals(descripcion, eventoSemanal.obtenerDescripcion());
        assertEquals(fechaInicio, eventoSemanal.obtenerFechaInicio().toLocalDate());
        assertEquals(fechaFin, eventoSemanal.obtenerFechaFin().toLocalDate());
        assertEquals(ocurrenciasRealizadas, eventoSemanal.obtenerOcurrencias());
        assertEquals(maxOcurrencias, eventoSemanal.obtenerMaxOcurrencias());
        assertEquals(tipoRepeticion, eventoSemanal.obtenerTipoRepeticion());
        assertNull(eventoSemanal.obtenerDiasSemana());

        //Para los tests de constructor Default de la clase abstracta Evento se utilizó el metodo .toLocalDate para que el test no falle por milésima de segundos.

    }
    //Creo un EventoSemanal Default
    @Test
    public void testCreacionEventoSemanalDefault() {

        var eventoSemanal = new EventoSemanal();

        List<Evento> proximosEventos = eventoSemanal.obtenerProximosEventos(eventoSemanal);

        Evento primerEventoEnLista = proximosEventos.get(0);
        Evento segundoEventoEnLista = proximosEventos.get(1);

        assertEquals(primerEventoEnLista.obtenerFechaInicio().toLocalDate(), eventoSemanal.obtenerFechaInicio().toLocalDate());
        assertEquals(segundoEventoEnLista.obtenerFechaFin().toLocalDate(), eventoSemanal.obtenerFechaFin().toLocalDate());
        assertEquals(2, proximosEventos.size());
    }

    @Test
    public void testObtenerSiguienteOcurrenciaValoresDefault() {

        var eventoSemanal = new EventoSemanal();

        //Por default, el evento no se repite de forma semanal

        LocalDateTime FechaEsperada = eventoSemanal.obtenerFechaInicio().plusWeeks(1);
        LocalDateTime FechaActual = eventoSemanal.calcularSiguienteOcurrencia(eventoSemanal.obtenerFechaInicio());

        assertEquals(FechaEsperada, FechaActual);

    }

    //Caso 2 veces por semana
    @Test
    public void testEventoDosVecesPorSemana() {

        String titulo = "Evento Semanal";
        String descripcion =   "Evento que se repite todos los lunes y los viernes";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0); //lunes 3 de abril
        LocalDateTime fechaFin =    LocalDateTime.of(2023, 4, 24, 9, 30); //lunes 24 de abril

        //Estos dos atributos son testeados correctamento en los siguientes test
        int maxOcurrencias = 1;
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        List<DayOfWeek> diasSemana = List.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);


        var eventoSemanal = new EventoSemanal(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion ,diasSemana);

        LocalDateTime FechaEsperada = fechaInicio.plusDays(4); //Se espera que la proxima ocurrencia del evento sea el viernes 7 de abril
        LocalDateTime FechaActual = eventoSemanal.calcularSiguienteOcurrencia(fechaInicio);

        assertEquals(FechaEsperada, FechaActual);
        assertEquals(diasSemana, eventoSemanal.obtenerDiasSemana());
    }


    //Caso 3 veces por semana
    @Test
    public void testEventoTresVecesPorSemana() {

        String titulo = "Evento Semanal";
        String descripcion =   "Evento que se repite todos los lunes, miercoles y viernes";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0); //lunes 3 de abril
        LocalDateTime fechaFin =    LocalDateTime.of(2023, 4, 24, 9, 30); //lunes 24 de abril

        //Estos dos atributos son testeados correctamento en los siguientes test
        int maxOcurrencias = 1;
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        List<DayOfWeek> diasSemana = List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY ,DayOfWeek.FRIDAY);


        var eventoSemanal = new EventoSemanal(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion ,diasSemana);

        LocalDateTime FechaMiercolesEsperada = fechaInicio.plusDays(2); //Se espera que la proxima ocurrencia del evento sea el miercoles 5 de abril
        LocalDateTime FechaMiercolesActual = eventoSemanal.calcularSiguienteOcurrencia(fechaInicio);

        assertEquals(FechaMiercolesEsperada, FechaMiercolesActual);

        LocalDateTime FechaViernesEsperada = FechaMiercolesEsperada.plusDays(2); //Se espera que la proxima ocurrencia del evento sea el viernes 7 de abril
        LocalDateTime FechaViernesActual = eventoSemanal.calcularSiguienteOcurrencia(FechaMiercolesActual);

        assertEquals(FechaViernesEsperada, FechaViernesActual);
        assertEquals(diasSemana, eventoSemanal.obtenerDiasSemana());
    }

    //Test de EventoSemanal con repeticion infinita
    @Test
    public void testEventoCadaMartesYJueves() {

        String titulo = "Evento Semanal infinito";
        String descripcion = "Evento que se repite cada martes y jueves infinitamente";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);


        int maxOcurrencias = 99; //ingreso un numero grande pero testeable
        Repeticion tipoRepeticion = Repeticion.INFINITA;

        List<DayOfWeek> diasSemana = List.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
        List<DayOfWeek> diasSemanaActualizado = List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY);

        var eventoSemanal = new EventoSemanal(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion ,diasSemana);

        //Testeo que el setter funcione
        eventoSemanal.establecerDiasSemana(List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY));

        List<Evento> proximosEventos = eventoSemanal.obtenerProximosEventos(eventoSemanal);

        LocalDateTime fechaActual = eventoSemanal.obtenerFechaInicio();

        for (Evento eventoEnLista : proximosEventos){

            LocalDateTime fecha = eventoEnLista.obtenerFechaInicio();
            assertFalse(fechaActual.isAfter(fecha));
            fechaActual = fecha;

        }

        assertEquals(100, proximosEventos.size());
        assertEquals(diasSemanaActualizado, eventoSemanal.obtenerDiasSemana());
        assertNotEquals(diasSemana, diasSemanaActualizado);

    }

  //Test de EventoSemanal con repeticion hasta alcanzar fechaFin
    @Test
    public void testEventoSemanalHastaFecha() {

        String titulo = "Evento diario infinito";
        String descripcion = "Evento que se repite cada lunes y miercoles hasta llegar a una fecha";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0); //Lunes 3 de abril
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0);//Lunes 10 de abril

        int maxOcurrencias = 1;

        Repeticion tipoRepeticion = Repeticion.HASTA_FECHA_FIN;


        List<DayOfWeek> diasSemana = List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);

        var eventoSemanal = new EventoSemanal(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion ,diasSemana);

        List<Evento> proximosEventos = eventoSemanal.obtenerProximosEventos(eventoSemanal);

        LocalDateTime fechaActual = eventoSemanal.obtenerFechaInicio();

        for (Evento eventoEnLista : proximosEventos){

            LocalDateTime fecha = eventoEnLista.obtenerFechaInicio();
            assertFalse(fechaActual.isAfter(fecha));
            fechaActual = fecha;

        }
        assertEquals(3, proximosEventos.size());

        LocalDateTime fecha1 = proximosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fecha2 = proximosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fecha3 = proximosEventos.get(2).obtenerFechaInicio();

        LocalDateTime siguienteMiercoes = fechaInicio.plusDays(2); //Se espera que la siguiente ocurrencia despues del 3 de abril sea el miercoles 5 de abril
        LocalDateTime siguienteLunes = siguienteMiercoes.plusDays(5); //Se espera que la siguiente ocurrencia sea el lunes 10 de abril

        assertEquals(fecha1, fechaInicio);
        assertEquals(fecha2, siguienteMiercoes);
        assertEquals(fecha3, siguienteLunes);

    }

    //test de EventoSemanal con repeticion hasta que se alcancen 20 ocurrencias
    @Test
    public void EventoSemanalHastaOcurrencias() {

        String titulo = "Evento Semanal";
        String descripcion = "Evento que se repite hasta llegar a 20 ocurrencias";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0);

        int maxOcurrencias = 20;

        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;


        List<DayOfWeek> diasSemana = List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);

        var eventoSemanal = new EventoSemanal(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion ,diasSemana);

        List<Evento> proximosEventos = eventoSemanal.obtenerProximosEventos(eventoSemanal);

        LocalDateTime fechaActual = eventoSemanal.obtenerFechaInicio();

        for (Evento eventoEnLista : proximosEventos){

            LocalDateTime fecha = eventoEnLista.obtenerFechaInicio();
            assertFalse(fechaActual.isAfter(fecha));
            fechaActual = fecha;

        }
        assertEquals(20, eventoSemanal.obtenerMaxOcurrencias());
        assertEquals(eventoSemanal.obtenerOcurrencias(), proximosEventos.size());
    }

}





