import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EventoMensualTest {
    @Rule
    public ExpectedException error = ExpectedException.none();

    //Testeo constructor default de la clase Evento para eventoMensual
    @Test
    public void testEventoMensualDefault() {

        var eventoMensual = new EventoMensual();

        String titulo =  "Evento sin titulo";
        String descripcion = "-";

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = fechaInicio.plusDays(1);

        int ocurrenciasRealizadas = 0;
        int maxOcurrencias = 1;
        Repeticion tipoRepeticion = Repeticion.HASTA_FECHA_FIN;

        assertEquals(titulo, eventoMensual.obtenerTitulo());
        assertEquals(descripcion, eventoMensual.obtenerDescripcion());
        assertEquals(fechaInicio, eventoMensual.obtenerFechaInicio().toLocalDate());
        assertEquals(fechaFin, eventoMensual.obtenerFechaFin().toLocalDate());
        assertEquals(ocurrenciasRealizadas, eventoMensual.obtenerOcurrencias());
        assertEquals(maxOcurrencias, eventoMensual.obtenerMaxOcurrencias());
        assertEquals(tipoRepeticion, eventoMensual.obtenerTipoRepeticion());

        //Para los tests de constructor Default de la clase abstracta Evento se utilizó el metodo .toLocalDate para que el test no falle por milésima de segundos.

    }
    //Creo un EventoSemanal Default
    @Test
    public void testCreacionEventoMensualDefault() {

        var eventoMensual = new EventoMensual();

        ArrayList<Evento> proximosEventos = eventoMensual.obtenerProximosEventos(eventoMensual);

        Evento primerEventoEnLista = proximosEventos.get(0);
        Evento segundoEventoEnLista = proximosEventos.get(1);

        assertEquals(primerEventoEnLista.obtenerFechaInicio().toLocalDate(), eventoMensual.obtenerFechaInicio().toLocalDate());
        assertEquals(segundoEventoEnLista.obtenerFechaFin().toLocalDate(), eventoMensual.obtenerFechaFin().toLocalDate());

        assertEquals(2, proximosEventos.size());
    }

    @Test
    public void testObtenerSiguienteOcurrenciaValoresDefault() {

        var eventoMensual = new EventoMensual();

        LocalDateTime FechaEsperada = eventoMensual.obtenerFechaInicio().plusMonths(1);
        LocalDateTime FechaActual = eventoMensual.calcularSiguienteOcurrencia(eventoMensual.obtenerFechaInicio());

        assertEquals(FechaEsperada, FechaActual);

    }

    @Test
    public void testEventoMensual() {

        String titulo = "Evento Mensual";
        String descripcion =   "Evento que se repite al siguiente mes";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0); //lunes 3 de abril
        LocalDateTime fechaFin =    LocalDateTime.of(2023, 4, 24, 9, 30); //lunes 24 de abril

        //Estos dos atributos son testeados correctamento en los siguientes test
        int maxOcurrencias = 1;
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
        int cantidadMeses = 1;

        var eventoMensual = new EventoMensual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadMeses);

        LocalDateTime FechaEsperada = fechaInicio.plusMonths(cantidadMeses);
        LocalDateTime FechaActual = eventoMensual.calcularSiguienteOcurrencia(fechaInicio);

        assertEquals(FechaEsperada, FechaActual);
    }


    @Test
    public void testEventoCada3Meses() {

        String titulo = "Evento Mensual";
        String descripcion =   "Evento que se repite cada 3 meses";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0); //lunes 3 de abril
        LocalDateTime fechaFin =    LocalDateTime.of(2023, 4, 24, 9, 30); //lunes 24 de abril

        //Estos dos atributos son testeados correctamento en los siguientes test
        int maxOcurrencias = 1;
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        int cantidadMeses = 1;

        var eventoMensual = new EventoMensual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadMeses);
        eventoMensual.establecerCantidadMeses(3);


        LocalDateTime fechaEsperada = fechaInicio.plusMonths(3);
        LocalDateTime FechaActual = eventoMensual.calcularSiguienteOcurrencia(fechaInicio);

        assertEquals(fechaEsperada, FechaActual);
        assertNotEquals(cantidadMeses, eventoMensual.obtenerCantidadMeses());
    }

    @Test
    public void testCantidadMesesCero() {

        String titulo = "Evento Mensual";
        String descripcion = "Evento con cantidad de meses nulo";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);

        //Estos dos atributos son testeados correctamento en los siguientes test
        int maxOcurrencias = 1;
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        int cantidadMeses = 0;

        var eventoMensual = new EventoMensual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadMeses);


        LocalDateTime fechaEsperada = fechaInicio;
        LocalDateTime FechaActual = eventoMensual.calcularSiguienteOcurrencia(fechaInicio);
        assertEquals(fechaEsperada, FechaActual);
    }


    @Test
    public void testCantMesesNegativa() {

        String titulo = "Evento Mensual";
        String descripcion = "Evento que se repite cada 3 dias";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);

        //Estos dos atributos son testeados correctamento en los siguientes test
        int maxOcurrencias = 1;
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        int cantidadMeses = -1;

        var eventoMensual = new EventoMensual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadMeses);

        error.expect(RuntimeException.class);
        eventoMensual.calcularSiguienteOcurrencia(fechaInicio);
    }


    //Test de EventoMensual con repeticion infinita
    @Test
    public void testEventoMensualInfinito() {

        String titulo = "Evento Mensual infinito";
        String descripcion = "Evento que se repite cada mes infinitamente";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);

        //Estos dos atributos son testeados correctamento en los siguientes test
        int maxOcurrencias = 99; //Seteo un numero alto pero testeable
        Repeticion tipoRepeticion = Repeticion.INFINITA;

        int cantidadMeses = 2;

        var eventoMensual = new EventoMensual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadMeses);

        ArrayList<Evento> proximosEventos = eventoMensual.obtenerProximosEventos(eventoMensual);

        LocalDateTime fechaActual = eventoMensual.obtenerFechaInicio();

        for (Evento eventoEnLista : proximosEventos){

            LocalDateTime fecha = eventoEnLista.obtenerFechaInicio();
            assertFalse(fechaActual.isAfter(fecha));
            fechaActual = fecha;

        }
        assertEquals(100, proximosEventos.size()); //ya que se añade el dia inicial

        LocalDateTime fechaAbril = proximosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fechaJunio = proximosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fechaAgosto = proximosEventos.get(2).obtenerFechaInicio();

        LocalDateTime fechaAbrilEsperada = fechaInicio;
        LocalDateTime fechaJunioEsperada = fechaAbrilEsperada.plusMonths(2);
        LocalDateTime fechaAgostoEsperada = fechaJunioEsperada.plusMonths(2);

        assertEquals(fechaAbrilEsperada,fechaAbril );
        assertEquals(fechaJunioEsperada,fechaJunio );
        assertEquals(fechaAgostoEsperada,fechaAgosto );

    }



    //Test de EventoSemanal con repeticion hasta alcanzar fechaFin
    @Test
    public void testEventoSemanalHastaFecha() {

        String titulo = "Evento Semanal";
        String descripcion = "Evento que se repite hasta llegar a una fecha";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 8, 1, 9, 0);

        int maxOcurrencias = 1;

        Repeticion tipoRepeticion = Repeticion.HASTA_FECHA_FIN;

        int cantidadMeses = 2;

        var eventoMensual = new EventoMensual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadMeses);

        ArrayList<Evento> proximosEventos = eventoMensual.obtenerProximosEventos(eventoMensual);

        LocalDateTime fechaActual = eventoMensual.obtenerFechaInicio();

        for (Evento eventoEnLista : proximosEventos){

            LocalDateTime fecha = eventoEnLista.obtenerFechaInicio();
            assertFalse(fechaActual.isAfter(fecha));
            fechaActual = fecha;

        }

        LocalDateTime fechaAbril = proximosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fechaJunio = proximosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fechaAgosto = proximosEventos.get(2).obtenerFechaInicio();

        LocalDateTime fechaAbrilEsperada = fechaInicio;
        LocalDateTime fechaJunioEsperada = fechaAbrilEsperada.plusMonths(2);
        LocalDateTime fechaAgostoEsperada = fechaJunioEsperada.plusMonths(2);

        assertEquals(fechaAbrilEsperada,fechaAbril );
        assertEquals(fechaJunioEsperada,fechaJunio );
        assertEquals(fechaAgostoEsperada,fechaAgosto );

        assertEquals(3, proximosEventos.size()); // Lapso de 2 meses: Abril - Junio - Agosto

    }

    //test de EventoDiario con repeticion hasta que se alcancen 20 ocurrencias
    @Test
    public void testEventoSemanalHastaOcurrencias() {

        String titulo = "Evento Mensual";
        String descripcion = "Evento que se repite hasta alcanzar 20 ocurrencias";

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 1, 9, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 10, 9, 0);

        int maxOcurrencias = 20;

        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;

        int cantidadMeses = 1;

        var eventoMensual = new EventoMensual(titulo, descripcion, fechaInicio, fechaFin,maxOcurrencias, tipoRepeticion, cantidadMeses);

        ArrayList<Evento> proximosEventos = eventoMensual.obtenerProximosEventos(eventoMensual);

        LocalDateTime fechaActual = eventoMensual.obtenerFechaInicio();

        for (Evento eventoEnLista : proximosEventos){

            LocalDateTime fecha = eventoEnLista.obtenerFechaInicio();
            assertFalse(fechaActual.isAfter(fecha));
            fechaActual = fecha;

        }

        assertEquals(20, eventoMensual.obtenerMaxOcurrencias());
        assertEquals(eventoMensual.obtenerOcurrencias(), proximosEventos.size());
    }

}