import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.*;

public class CalendarioTest {

    //Creadores de los eventos, son la clase "Director" que utilizan el Builder
    CreadorDeEventos eventoDiarioCreado      = new CreadorEventosDiarios();
    CreadorDeEventos eventoSemanalCreado     = new CreadorEventosSemanales();
    CreadorDeEventos eventoMensualCreado     = new CreadorEventosMensuales();
    CreadorDeEventos eventoAnualCreado       = new CreadorEventosAnuales();
    CreadorDeEventos eventoDiaCompletoCreado = new CreadorEventosDiaCompleto();


    @Test
    public void testCrearTarea(){

        var calendario = new Calendario();
        var tarea = new TareaConVencimiento();

        calendario.agregarTarea(tarea);

        assertEquals(1,calendario.obtenerTareas().size());
        assertTrue(calendario.obtenerTareas().contains(tarea));
    }

    @Test
    public void testTareaSeCreaConDatosCorrectos(){

        var calendario = new Calendario();
        var tarea = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));

        calendario.agregarTarea(tarea);

        assertEquals("Tarea",calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Hacer la tarea",calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,22,23,59,59),calendario.obtenerTareas().get(0).obtenerFechaVencimiento());

    }
    @Test
    public void testCrearVariasTareas(){

        var calendario = new Calendario();

        var tareaUno = new TareaConVencimiento();
        var tareaDos = new TareaDiaCompleto();
        var tareaTres = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));

        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);
        calendario.agregarTarea(tareaTres);

        assertEquals(3,calendario.obtenerTareas().size());
        assertTrue(calendario.obtenerTareas().contains(tareaUno));
        assertTrue(calendario.obtenerTareas().contains(tareaDos));
        assertTrue(calendario.obtenerTareas().contains(tareaTres));
    }
    @Test
    public void testSePuedeCrearTareasIguales(){

        var calendario = new Calendario();
        var tareaUno = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));

        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);

        assertEquals(2,calendario.obtenerTareas().size());
        assertTrue(calendario.obtenerTareas().contains(tareaUno));
        assertTrue(calendario.obtenerTareas().contains(tareaDos));
        assertNotEquals(calendario.obtenerTareas().get(0).obtenerId(),calendario.obtenerTareas().get(1).obtenerId());

    }
    @Test
    public void testEliminarTarea(){

        var calendario = new Calendario();

        var tareaUno = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,11,22));

        int idParaBorrar = tareaUno.obtenerId();

        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);

        assertEquals(2,calendario.obtenerTareas().size());

        calendario.eliminarTarea(idParaBorrar);
        assertEquals(1,calendario.obtenerTareas().size());
        assertFalse(calendario.obtenerTareas().contains(tareaUno));

    }
    @Test
    public void testEliminarVariasTareas(){

        var calendario = new Calendario();

        var tareaUno = new TareaConVencimiento();
        int idUno = tareaUno.obtenerId();

        var tareaDos = new TareaDiaCompleto();
        int idDos = tareaDos.obtenerId();

        var tareaTres = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        int idTres = tareaTres.obtenerId();

        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);
        calendario.agregarTarea(tareaTres);

        assertEquals(3,calendario.obtenerTareas().size());
        calendario.eliminarTarea(idUno);
        calendario.eliminarTarea(idDos);
        calendario.eliminarTarea(idTres);

        assertEquals(0,calendario.obtenerTareas().size());
        assertFalse(calendario.obtenerTareas().contains(tareaUno));
        assertFalse(calendario.obtenerTareas().contains(tareaDos));
        assertFalse(calendario.obtenerTareas().contains(tareaTres));

    }
    @Test
    public void testSiHayTareasIgualesBorraLaCorrecta(){

        var calendario = new Calendario();

        var tareaUno = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));

        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));

        int idUno = tareaUno.obtenerId();

        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);
        assertEquals(2,calendario.obtenerTareas().size());

        calendario.eliminarTarea(idUno);
        assertEquals(1,calendario.obtenerTareas().size());
        assertFalse(calendario.obtenerTareas().contains(tareaUno));
        assertTrue(calendario.obtenerTareas().contains(tareaDos));

    }
    @Test
    public void testSiNoEncuentraIdNoEliminaNada(){

        var calendario = new Calendario();
        var tareaUno = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));

        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);
        assertEquals(2,calendario.obtenerTareas().size());

        calendario.eliminarTarea(9393);
        assertEquals(2,calendario.obtenerTareas().size());
    }
    @Test
    public void testModificarTarea(){

        var calendario = new Calendario();
        var tarea = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        calendario.agregarTarea(tarea);
        int idUno = tarea.obtenerId();

        assertEquals("Tarea",calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Hacer la tarea",calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,22,23,59,59),calendario.obtenerTareas().get(0).obtenerFechaVencimiento());

        calendario.modificarTarea(idUno,"Nuevo Titulo","Nueva Descripcion",LocalDateTime.of(2023,12,24,0,0),LocalDateTime.of(2023,12,24,18,20),null,null);

        assertEquals("Nuevo Titulo",calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Nueva Descripcion",calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,24,0,0),calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,24,18,20),calendario.obtenerTareas().get(0).obtenerFechaVencimiento());

    }
    @Test
    public void testConDosTareasIgualesModificaCorrecta(){
        var calendario = new Calendario();
        var tareaUno = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        int idUno = tareaUno.obtenerId();

        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);
        calendario.modificarTarea(idUno,"Nuevo Titulo","Nueva Descripcion",LocalDateTime.of(2023,12,24,0,0),LocalDateTime.of(2023,12,24,18,20), null, null);

        assertEquals("Nuevo Titulo",calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Nueva Descripcion",calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,24,0,0),calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,24,18,20),calendario.obtenerTareas().get(0).obtenerFechaVencimiento());
        assertEquals("Tarea",calendario.obtenerTareas().get(1).obtenerTitulo());
        assertEquals("Hacer la tarea",calendario.obtenerTareas().get(1).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(1).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,22,23,59,59),calendario.obtenerTareas().get(1).obtenerFechaVencimiento());

    }

    @Test
    public void testModificarAlarmaDeTarea() {

        var calendario = new Calendario();
        var tarea = new TareaDiaCompleto("Tarea", "Hacer la tarea", LocalDate.of(2023, 12, 22));
        var notificacion = new Notificacion();
        var alarma = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 12, 22, 0, 0), notificacion);
        tarea.agregarAlarma(alarma);
        calendario.agregarTarea(tarea);
        int idUno = tarea.obtenerId();

        assertEquals("Tarea", calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Hacer la tarea", calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023, 12, 22, 0, 0), calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023, 12, 22, 23, 59, 59), calendario.obtenerTareas().get(0).obtenerFechaVencimiento());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(0).obtenerAlarmas().get(0).obtenerFechaYHora());

        calendario.modificarTarea(idUno, null, null, null,null, calendario.obtenerTareas().get(0).obtenerAlarmas().get(0),LocalDateTime.of(2023, 12, 21, 23, 30));
        assertEquals("Tarea", calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Hacer la tarea", calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023, 12, 22, 0, 0), calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023, 12, 22, 23, 59, 59), calendario.obtenerTareas().get(0).obtenerFechaVencimiento());
        assertEquals(LocalDateTime.of(2023, 12, 21, 23, 30),calendario.obtenerTareas().get(0).obtenerAlarmas().get(0).obtenerFechaYHora());

    }
    @Test
    public void testSiNoEncuentraIdNoModificaNada(){
        var calendario = new Calendario();
        var tareaUno = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));

        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);
        calendario.modificarTarea(9999,"Nuevo Titulo","Nueva Descripcion",LocalDateTime.of(2023,12,24,0,0),LocalDateTime.of(2023,12,24,18,20),null,null);

        assertEquals("Tarea",calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Hacer la tarea",calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,22,23,59,59),calendario.obtenerTareas().get(0).obtenerFechaVencimiento());
        assertEquals("Tarea",calendario.obtenerTareas().get(1).obtenerTitulo());
        assertEquals("Hacer la tarea",calendario.obtenerTareas().get(1).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(1).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,22,23,59,59),calendario.obtenerTareas().get(1).obtenerFechaVencimiento());
    }

    @Test
    public void testCreoAlarmasYVeoCualEsLaProximaEnSonar(){
        var calendario = new Calendario();
        var tareaUno = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        var notificacion = new Notificacion();
        var alarma = new AlarmaIntervalo( LocalDateTime.of(2023, 5,2 , 0, 0),60,notificacion);
        var alarmaUno = new AlarmaFechaAbsoluta( LocalDateTime.of(2023, 5, 1, 0, 0),notificacion);
        var alarmaDos = new AlarmaFechaAbsoluta( LocalDateTime.of(2023, 4, 29, 0, 0),notificacion);
        var alarmaTres = new AlarmaFechaAbsoluta( LocalDateTime.of(2023, 5, 5, 0, 0),notificacion);

        tareaUno.agregarAlarma(alarma);
        tareaUno.agregarAlarma(alarmaUno);
        tareaDos.agregarAlarma(alarmaDos);
        tareaDos.agregarAlarma(alarmaTres);
        calendario.agregarTarea(tareaUno);
        calendario.agregarTarea(tareaDos);
//        assertEquals(alarmaDos,calendario.proximaAlarma(LocalDateTime.now()));
    }
//
//    @Test
//    public void testCrearEventosYTareas() {
//        var creadorEventosDiarios = new CreadorEventosDiarios();
//        var eventosDiarios = new Calendario(creadorEventosDiarios);
//        var creadorEventosSemanales = new CreadorEventosSemanales();
//        var eventosSemanales = new Calendario(creadorEventosSemanales);
//        var creadorEventosAnuales = new CreadorEventosAnuales();
//        var eventosAnuales = new Calendario(creadorEventosAnuales);
//        var calendario = new Calendario();
//        var tarea = new TareaConVencimiento();
//        var tareaDos = new TareaDiaCompleto();
//        calendario.agregarTarea(tarea);
//        calendario.agregarTarea(tareaDos);
//        eventosDiarios.crearEvento("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1, null);
//        eventosSemanales.crearEvento("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, 0, Set.of(DayOfWeek.MONDAY));
//        eventosAnuales.crearEvento("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.HASTA_FECHA_FIN, 3, null);
//        calendario.agregarEventosACalendario(eventosAnuales.obtenerEventosCreados());
//        calendario.agregarEventosACalendario(eventosDiarios.obtenerEventosCreados());
//        calendario.agregarEventosACalendario(eventosSemanales.obtenerEventosCreados());
//        assertEquals(3,calendario.obtenerListaEventosTotales().size());
//        assertEquals(2, calendario.obtenerTareas().size());
//        assertTrue(calendario.obtenerTareas().contains(tarea));
//    }

    @Test
    public void testCrearEventoDefault(){

        //Construyo los objetos con constructor default
        ConstructorEventos   eventoDiarioConstruido       =   new ConstructorEventosDiarios();
        ConstructorEventos   eventoSemanalConstruido      =   new ConstructorEventosSemanales();
        ConstructorEventos   eventoMensualConstruido      =   new ConstructorEventosMensuales();
        ConstructorEventos   eventoAnualConstruido        =   new ConstructorEventosAnuales();
        ConstructorEventos   eventoDiaCompletoConstruido  =   new ConstructorEventosDiaCompleto();

        Evento eventoDiario      = eventoDiarioCreado.crearEvento(eventoDiarioConstruido);
        Evento eventoSemanal     = eventoSemanalCreado.crearEvento(eventoSemanalConstruido);
        Evento eventoMensual     = eventoMensualCreado.crearEvento(eventoMensualConstruido);
        Evento eventoAnual       = eventoAnualCreado.crearEvento(eventoAnualConstruido);
        Evento eventoDiaCompleto = eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);

        var calendario = new Calendario();

        //Agrego eventos iguales
        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoDiario);

        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoMensual);

        calendario.agregarEvento(eventoAnual);
        calendario.agregarEvento(eventoAnual);

        calendario.agregarEvento(eventoDiaCompleto);

        ArrayList<Evento> listaEventos = calendario.obtenerListaEventosTotales();
        int i = 0;
        for(Evento evento : listaEventos){
            assertEquals(evento, calendario.obtenerListaEventosTotales().get(i));
            i++;
        }

        //Verifico que esten los duplicados como instancias unicas
        assertEquals(listaEventos.get(0).getClass(), eventoDiario.getClass());
        assertEquals(listaEventos.get(1).getClass(), eventoDiario.getClass());

        assertEquals(listaEventos.get(2).getClass(), eventoSemanal.getClass());
        assertEquals(listaEventos.get(3).getClass(), eventoMensual.getClass());

        assertEquals(listaEventos.get(4).getClass(), eventoAnual.getClass());
        assertEquals(listaEventos.get(5).getClass(), eventoAnual.getClass());

        assertEquals(listaEventos.get(6).getClass(), eventoDiaCompleto.getClass());



        //Cada evento fue agregado correctamente, incluso duplicados
        assertEquals(7, calendario.obtenerListaEventosTotales().size());

    }

    @Test
    public void testEliminarEventoDefault(){

        //Construyo los objetos con constructor default
        ConstructorEventos   eventoDiarioConstruido       =   new ConstructorEventosDiarios();
        ConstructorEventos   eventoSemanalConstruido      =   new ConstructorEventosSemanales();
        ConstructorEventos   eventoMensualConstruido      =   new ConstructorEventosMensuales();
        ConstructorEventos   eventoAnualConstruido        =   new ConstructorEventosAnuales();
        ConstructorEventos   eventoDiaCompletoConstruido  =   new ConstructorEventosDiaCompleto();

        Evento eventoDiario      = eventoDiarioCreado.crearEvento(eventoDiarioConstruido);
        Evento eventoSemanal     = eventoSemanalCreado.crearEvento(eventoSemanalConstruido);
        Evento eventoMensual     = eventoMensualCreado.crearEvento(eventoMensualConstruido);
        Evento eventoAnual       = eventoAnualCreado.crearEvento(eventoAnualConstruido);
        Evento eventoDiaCompleto = eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);

        var calendario = new Calendario();

        //Agrego eventos iguales
        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoDiario);

        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoMensual);

        calendario.agregarEvento(eventoAnual);
        calendario.agregarEvento(eventoAnual);

        calendario.agregarEvento(eventoDiaCompleto);

        ArrayList<Evento> listaEventos = calendario.obtenerListaEventosTotales();

        //Elimino la instancia de EventoDiario y todas sus repeticiones
        calendario.eliminarEvento(listaEventos.get(0));

       for(Evento evento : listaEventos){
           assertNotEquals(evento.getClass() ,eventoDiario.getClass());

       }

       assertEquals(5, listaEventos.size() );

    }

//    @Test
//    public void testCrearEvento(){
//
//
//        ConstructorEventos eventoDiarioConstruido      = new ConstructorEventosDiarios("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1);
//        ConstructorEventos eventoSemanalConstruido     = new ConstructorEventosSemanales("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, Set.of(DayOfWeek.MONDAY));
//        ConstructorEventos eventoMensualConstruido     = new ConstructorEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 12, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2);
//        ConstructorEventos eventoAnualConstruido       = new ConstructorEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_FECHA_FIN, 3);
//        ConstructorEventos eventoDiaCompletoConstruido = new ConstructorEventosDiaCompleto("Evento Dia Completo", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS );
//
//
//        Evento eventoDiario      = eventoDiarioCreado.crearEvento(eventoDiarioConstruido);
//        Evento eventoSemanal     = eventoSemanalCreado.crearEvento(eventoSemanalConstruido);
//        Evento eventoMensual     = eventoMensualCreado.crearEvento(eventoMensualConstruido);
//        Evento eventoAnual       = eventoAnualCreado.crearEvento(eventoAnualConstruido);
//        Evento eventoDiaCompleto = eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);
//
//        var calendario = new Calendario();
//
//        calendario.agregarEvento(eventoDiario);
//        calendario.agregarEvento(eventoSemanal);
//        calendario.agregarEvento(eventoMensual);
//        calendario.agregarEvento(eventoAnual);
//        calendario.agregarEvento(eventoDiaCompleto);
//
//        int i = 0;
//        for(Evento evento : calendario.obtenerListaEventosTotales()){
//
//            //Pruebo que estan los duplicados
//            assertEquals(evento.obtenerTitulo(), calendario.obtenerListaEventosTotales().get(i).obtenerTitulo());
//            i++;
//        }
//        //Cada evento fue agregado correctamente, incluso duplicados
//        assertEquals(6, calendario.obtenerListaEventosTotales().size());
//    }

//    @Test
//    public void testEliminarEvento() {
//
//        CreadorEventosDiarios creadorEventosDiarios = new CreadorEventosDiarios();
//        CreadorEventosSemanales creadorEventosSemanales = new CreadorEventosSemanales();
//        CreadorEventosMensuales creadorEventosMensuales = new CreadorEventosMensuales();
//        CreadorEventosAnuales creadorEventosAnuales = new CreadorEventosAnuales();
//
//        Calendario eventosDiarios = new Calendario(creadorEventosDiarios);
//        Calendario eventosSemanales = new Calendario(creadorEventosSemanales);
//        Calendario eventosMensuales = new Calendario(creadorEventosMensuales);
//        Calendario eventosAnuales = new Calendario(creadorEventosAnuales);
//
//        Calendario calendario = new Calendario();
//
//        //Creo multiples eventos iguales
//        eventosDiarios.crearEvento("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1, null);
//        eventosDiarios.crearEvento("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1, null);
//
//        eventosSemanales.crearEvento("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, 0, Set.of(DayOfWeek.MONDAY));
//        eventosSemanales.crearEvento("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, 0, Set.of(DayOfWeek.MONDAY));
//
//
//        eventosMensuales.crearEvento("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 12, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2, null);
//
//        eventosAnuales.crearEvento("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.HASTA_FECHA_FIN, 3, null);
//
//        calendario.agregarEventosACalendario(eventosDiarios.obtenerEventosCreados());
//        calendario.agregarEventosACalendario(eventosSemanales.obtenerEventosCreados());
//        calendario.agregarEventosACalendario(eventosMensuales.obtenerEventosCreados());
//        calendario.agregarEventosACalendario(eventosAnuales.obtenerEventosCreados());
//
//        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();
//
//        //Elimino la instancia de EventoSemanal y todas sus repeticiones
//        calendario.eliminarEvento(todosLosEventos.get(2));
//
//        for(Evento eventoSemanal : eventosSemanales.obtenerEventosCreados()) {
//            for(Evento evento : todosLosEventos){
//                assertNotEquals(evento.getClass() ,eventoSemanal.getClass());
//            }
//        }
//
//        assertEquals(4, todosLosEventos.size());
//    }
//
//    @Test
//    public void testObtenerProximosEventosDiarios(){
//
//        CreadorEventosDiarios creadorDeEventosDiarios = new CreadorEventosDiarios();
//        Calendario eventosDiariosLista = new Calendario(creadorDeEventosDiarios);
//
//        String titulo      = "Evento Diario";
//        String descripcion = "Evento que se repite hasta ocurrencias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin    = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        int maxOcurrencias = 3;
//
//        //Pruebo un tipo por clase de Evento
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        var eventoDiario = new EventoDiario();
//        eventoDiario.establecerIntervalo(2);
//        int intervalo = eventoDiario.obtenerIntervalo();
//
//        assertTrue(eventoDiario.obtenerAlarmasEvento().isEmpty());
//
//        //Verifico que puedo acceder a alarmas
//        var Notificacion = new Notificacion();
//        var alarma       = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),Notificacion);
//        eventoDiario.agregarAlarmaEvento(alarma);
//
//        //Creo el evento con la alarma
//        eventosDiariosLista.crearEvento(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervalo, null);
//
//        ArrayList<Evento> lista = eventosDiariosLista.proximosEventosDiarios(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion, intervalo);
//
//
//        LocalDateTime fecha1 = lista.get(0).obtenerFechaInicio();
//        LocalDateTime fecha2 = lista.get(1).obtenerFechaInicio();
//        LocalDateTime fecha3 = lista.get(2).obtenerFechaInicio();
//
//        LocalDateTime fecha2Esperada = fechaInicio.plusDays(2);
//        LocalDateTime fecha3Esperada = fecha2Esperada.plusDays(2);
//
//        assertEquals(fecha1, fechaInicio);
//        assertEquals(fecha2,fecha2Esperada );
//        assertEquals(fecha3,fecha3Esperada );
//
//        assertTrue(eventoDiario.obtenerAlarmasEvento().contains(alarma));
//        assertEquals(3, lista.size());
//
//    }
//
//    @Test
//    public void testEliminarTodosLosProximosEventosDiarios(){
//
//        CreadorEventosDiarios creadorDeEventosDiarios = new CreadorEventosDiarios();
//        Calendario eventosDiariosLista = new Calendario(creadorDeEventosDiarios);
//
//        String titulo      = "Evento Diario";
//        String descripcion = "Evento que se repite hasta ocurrencias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin    = LocalDateTime.of(2023, 4, 17, 9, 30);
//
//        int maxOcurrencias = 3;
//
//        //Pruebo un tipo por clase de Evento
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        var eventoDiario = new EventoDiario();
//        eventoDiario.establecerIntervalo(2);
//        int intervalo = eventoDiario.obtenerIntervalo();
//
//        assertTrue(eventoDiario.obtenerAlarmasEvento().isEmpty());
//
//        //Verifico que puedo acceder a alarmas
//        var Notificacion = new Notificacion();
//        var alarma       = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),Notificacion);
//        eventoDiario.agregarAlarmaEvento(alarma);
//
//        ArrayList<Evento> lista = eventosDiariosLista.proximosEventosDiarios(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervalo);
//
//        eventosDiariosLista.agregarEventosACalendario(lista);
//
//        ArrayList<Evento> listaTodosLosEventos = eventosDiariosLista.obtenerListaEventosTotales();
//
//        eventosDiariosLista.eliminarEvento(listaTodosLosEventos.get(0));
//
//        assertTrue(listaTodosLosEventos.isEmpty());
//
//    }
//
//
//    //Este test verifica que eliminar evento afecta a todas las instancias del objeto Evento
//    @Test
//    public void testEliminarSoloEventosSemanales(){
//
//        CreadorEventosDiarios creadorEventosDiarios = new CreadorEventosDiarios();
//        CreadorEventosSemanales creadorEventosSemanales = new CreadorEventosSemanales();
//        CreadorEventosMensuales creadorEventosMensuales = new CreadorEventosMensuales();
//        CreadorEventosAnuales creadorEventosAnuales = new CreadorEventosAnuales();
//
//        Calendario eventosDiarios = new Calendario(creadorEventosDiarios);
//        Calendario eventosSemanales = new Calendario(creadorEventosSemanales);
//        Calendario eventosMensuales = new Calendario(creadorEventosMensuales);
//        Calendario eventosAnuales = new Calendario(creadorEventosAnuales);
//
//        Calendario calendario = new Calendario();
//
//        ArrayList<Evento> listaEventosDiarios   = eventosDiarios.proximosEventosDiarios("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1);
//        ArrayList<Evento> listaEventosSemanales = eventosSemanales.proximosEventosSemanales("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, Set.of(DayOfWeek.MONDAY));
//        ArrayList<Evento> listaEventosMensuales = eventosMensuales.proximosEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 12, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2);
//        ArrayList<Evento> listaEventosAnuales   = eventosAnuales.proximosEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.HASTA_FECHA_FIN, 3);
//
//
//        calendario.agregarEventosACalendario(listaEventosDiarios);
//        calendario.agregarEventosACalendario(listaEventosSemanales);
//        calendario.agregarEventosACalendario(listaEventosMensuales);
//        calendario.agregarEventosACalendario(listaEventosAnuales);
//
//
//        ArrayList<Evento> listaTodosLosEventos = calendario.obtenerListaEventosTotales();
//
//        //Elimino los eventos semanales
//        calendario.eliminarEvento(listaTodosLosEventos.get(10));
//
//        for(Evento eventoSemanal : listaEventosSemanales) {
//            for(Evento evento : listaTodosLosEventos){
//                assertNotEquals(evento.getClass() ,eventoSemanal.getClass());
//            }
//        }
//
//    }
//
//    @Test
//    public void testObtenerProximosEventosSemanal(){
//
//        CreadorEventosSemanales creadorEventosSemanales = new CreadorEventosSemanales();
//        Calendario eventosSemanalesLista = new Calendario(creadorEventosSemanales);
//
//        String titulo      = "Evento Semanal";
//        String descripcion = "Evento que se repite hasta FechaFin";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0);
//        LocalDateTime fechaFin    = LocalDateTime.of(2023, 4, 10, 9, 0);
//
//        int maxOcurrencias = 3;
//
//        //Pruebo un tipo por clase de Evento
//        Repeticion tipoRepeticion = Repeticion.HASTA_FECHA_FIN;
//
//        var eventoSemanal = new EventoSemanal();
//
//        eventoSemanal.establecerDiasSemana(Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY));
//        Set<DayOfWeek> diasSemana = eventoSemanal.obtenerDiasSemana();
//
//        assertTrue(eventoSemanal.obtenerAlarmasEvento().isEmpty());
//
//        //Verifico que puedo acceder a alarmas
//        var sonido       = new Sonido();
//        var alarmaSonido = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),sonido);
//        eventoSemanal.agregarAlarmaEvento(alarmaSonido);
//
//        //Creo el evento con la alarma
//        eventosSemanalesLista.crearEvento(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,0 ,diasSemana);
//
//        ArrayList<Evento> lista = eventosSemanalesLista.proximosEventosSemanales(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,diasSemana);
//
//        LocalDateTime fechaLunes = lista.get(0).obtenerFechaInicio();
//        LocalDateTime fechaMiercoles = lista.get(1).obtenerFechaInicio();
//        LocalDateTime fechaLunes2 = lista.get(2).obtenerFechaInicio();
//
//        LocalDateTime fechaMiercolesEsperada = fechaInicio.plusDays(2); //Miercoles 5 de abril
//        LocalDateTime fechaLunes2Esperada = fechaMiercolesEsperada.plusDays(5); //Lunes 10 de abril
//
//        assertEquals(fechaLunes, fechaInicio);
//        assertEquals(fechaMiercoles,fechaMiercolesEsperada );
//        assertEquals(fechaLunes2,fechaLunes2Esperada );
//        assertTrue(eventoSemanal.obtenerAlarmasEvento().contains(alarmaSonido));
//        assertEquals(3, lista.size());
//
//    }
//
//    @Test
//    public void testObtenerProximosEventosMensuales(){
//
//        CreadorEventosMensuales creadorEventosMensuales = new CreadorEventosMensuales();
//        Calendario eventosMensualesLista = new Calendario(creadorEventosMensuales);
//
//        String titulo       = "Evento Mensual";
//        String descripcion  = "Evento que se repite Infinitamente";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0);
//        LocalDateTime fechaFin    = LocalDateTime.of(2023, 4, 10, 9, 0);
//
//        int maxOcurrencias = 99; //numero alto pero testeable
//
//        Repeticion tipoRepeticion = Repeticion.INFINITA;
//
//        var eventoMensual = new EventoMensual();
//
//        eventoMensual.establecerCantidadMeses(2);
//        int intervaloMeses = eventoMensual.obtenerCantidadMeses();
//
//        assertTrue(eventoMensual.obtenerAlarmasEvento().isEmpty());
//
//        //Verifico que puedo acceder a alarmas
//        var mail        = new Email();
//        var alarmaMail  = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),mail);
//        eventoMensual.agregarAlarmaEvento(alarmaMail);
//
//
//        //Creo el evento con la alarma
//        eventosMensualesLista.crearEvento(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervaloMeses ,null);
//
//        ArrayList<Evento> listaMensual = eventosMensualesLista.proximosEventosMensuales(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervaloMeses);
//
//        LocalDateTime fechaAbril  = listaMensual.get(0).obtenerFechaInicio();
//        LocalDateTime fechaJunio  = listaMensual.get(1).obtenerFechaInicio();
//        LocalDateTime fechaAgosto = listaMensual.get(2).obtenerFechaInicio();
//
//        LocalDateTime fechaJunioEsperada  = fechaInicio.plusMonths(2);
//        LocalDateTime fechaAgostoEsperada = fechaJunioEsperada.plusMonths(2);
//
//        assertEquals(fechaAbril, fechaInicio);
//        assertEquals(fechaJunio,fechaJunioEsperada );
//        assertEquals(fechaAgosto,fechaAgostoEsperada );
//
//        assertTrue(eventoMensual.obtenerAlarmasEvento().contains(alarmaMail));
//        assertEquals(100, listaMensual.size());
//
//    }
//
//    @Test
//    public void testObtenerProximosEventosAnuales(){
//
//        CreadorEventosAnuales creadorEventosAnuales = new CreadorEventosAnuales();
//        Calendario eventosAnualesLista = new Calendario(creadorEventosAnuales);
//
//        String titulo      =  "Evento Anual";
//        String descripcion =  "Evento que se repite hasta ocurrencias";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 0);
//        LocalDateTime fechaFin    = LocalDateTime.of(2023, 4, 10, 9, 0);
//
//        int maxOcurrencias = 3;
//
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        var eventoAnual = new EventoAnual();
//        eventoAnual.establecerCantidadAnios(2);
//        int intervaloAnios = eventoAnual.obtenerCantidadAnios();
//
//        eventosAnualesLista.crearEvento(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervaloAnios ,null);
//
//        ArrayList<Evento> listaAnios = eventosAnualesLista.proximosEventosAnuales(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervaloAnios);
//
//        LocalDateTime fecha2023 = listaAnios.get(0).obtenerFechaInicio();
//        LocalDateTime fecha2025 = listaAnios.get(1).obtenerFechaInicio();
//        LocalDateTime fecha2027 = listaAnios.get(2).obtenerFechaInicio();
//
//        LocalDateTime fecha2025Esperada = fechaInicio.plusYears(2);
//        LocalDateTime fecha2027Esperada = fecha2025Esperada.plusYears(2);
//
//        assertEquals(fecha2023, fechaInicio);
//        assertEquals(fecha2025,fecha2025Esperada );
//        assertEquals(fecha2027,fecha2027Esperada );
//
//        assertTrue(eventoAnual.obtenerAlarmasEvento().isEmpty());
//
//        assertEquals(3, listaAnios.size());
//
//    }
//
//    //Modifico un evento default a uno con valores por parametro
//    @Test
//    public void testModificarEvento(){
//
//        //Testeo con eventos anuales en este caso
//        CreadorEventosAnuales creadorEventosAnuales = new CreadorEventosAnuales();
//        Calendario eventosAnualesLista = new Calendario(creadorEventosAnuales);
//
//        var eventoOriginal = new EventoAnual("Evento Original", "Evento que quiero modificar", LocalDateTime.of(2023, 4, 3, 9, 0),LocalDateTime.of(2023, 4, 10, 9, 0),3,Repeticion.HASTA_OCURRENCIAS,1 );
//
//        var mail        = new Email();
//        var alarmaMail  = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),mail);
//
//        eventoOriginal.agregarAlarmaEvento(alarmaMail);
//
//        eventosAnualesLista.crearEvento(eventoOriginal.obtenerTitulo(), eventoOriginal.obtenerDescripcion(), eventoOriginal.obtenerFechaInicio(),eventoOriginal.obtenerFechaFin(),eventoOriginal.obtenerMaxOcurrencias(),eventoOriginal.obtenerTipoRepeticion(),eventoOriginal.obtenerCantidadAnios(), null);
//
//        var eventoModificado = new EventoAnual("Evento Modificado", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.HASTA_FECHA_FIN, 3);
//
//        LocalDateTime nuevaFechaYHoraAlarma = LocalDateTime.of(2023, 4, 15, 10, 0);
//
//        eventosAnualesLista.modificarEvento(eventoOriginal, eventoModificado, alarmaMail,nuevaFechaYHoraAlarma );
//
//
//        for(Evento evento :eventosAnualesLista.obtenerListaEventosTotales() ){
//
//            assertEquals(evento.obtenerTitulo(), eventoModificado.obtenerTitulo());
//            assertEquals(evento.obtenerDescripcion(), eventoModificado.obtenerDescripcion());
//            assertEquals(evento.obtenerFechaInicio(), eventoModificado.obtenerFechaInicio());
//            assertEquals(evento.obtenerFechaFin(), eventoModificado.obtenerFechaFin());
//            assertEquals(evento.obtenerMaxOcurrencias(), eventoModificado.obtenerMaxOcurrencias());
//            assertEquals(evento.obtenerTipoRepeticion(), eventoModificado.obtenerTipoRepeticion());
//            assertEquals(evento.obtenerAlarmasEvento(), eventoModificado.obtenerAlarmasEvento());
//
//        }
//
//        assertNotEquals(eventoOriginal.obtenerCantidadAnios(), eventoModificado.obtenerCantidadAnios());
//
//    }
//
//    //Tests para verificar que modificarEvento modifique todas las repeticiones del evento
//    @Test
//    public void testModificarTodasLasOcurrenciasDeEvento(){
//
//        //En este caso, pruebo con eventos diarios
//        CreadorEventosDiarios creadorEventosDiarios = new CreadorEventosDiarios();
//        CreadorEventosSemanales creadorEventosSemanales = new CreadorEventosSemanales();
//        CreadorEventosMensuales creadorEventosMensuales = new CreadorEventosMensuales();
//        CreadorEventosAnuales creadorEventosAnuales = new CreadorEventosAnuales();
//
//        Calendario eventosDiarios = new Calendario(creadorEventosDiarios);
//        Calendario eventosSemanales = new Calendario(creadorEventosSemanales);
//        Calendario eventosMensuales = new Calendario(creadorEventosMensuales);
//        Calendario eventosAnuales = new Calendario(creadorEventosAnuales);
//
//        Calendario calendario = new Calendario();
//
//        ArrayList<Evento> listaEventosDiarios   = eventosDiarios.proximosEventosDiarios("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 2, Repeticion.HASTA_OCURRENCIAS, 1);
//
//        //Le agrego alarmas a eventos diarios
//        for (Evento eventoDiario : listaEventosDiarios){
//
//            var mail        = new Email();
//            var alarmaMail  = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),mail);
//            eventoDiario.agregarAlarmaEvento(alarmaMail);
//        }
//
//        ArrayList<Evento> listaEventosSemanales = eventosSemanales.proximosEventosSemanales("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 3, Repeticion.HASTA_OCURRENCIAS, Set.of(DayOfWeek.MONDAY));
//        ArrayList<Evento> listaEventosMensuales = eventosMensuales.proximosEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 12, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 4, Repeticion.INFINITA, 2);
//
//        ArrayList<Evento> listaEventosAnuales   = eventosAnuales.proximosEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, 3);
//        //Le agrego alarmas a eventos Anuales
//        for (Evento eventoAnual : listaEventosAnuales){
//
//            var Sonido        = new Sonido();
//            var alarmaSonido  = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),Sonido);
//
//            eventoAnual.agregarAlarmaEvento(alarmaSonido);
//        }
//
//        calendario.agregarEventosACalendario(listaEventosDiarios);
//        calendario.agregarEventosACalendario(listaEventosSemanales);
//        calendario.agregarEventosACalendario(listaEventosMensuales);
//        calendario.agregarEventosACalendario(listaEventosAnuales);
//
//
//        ArrayList<Evento> listaTodosLosEventos = calendario.obtenerListaEventosTotales();
//
//        var eventoModificado = new EventoDiario("Evento Modificado", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.HASTA_FECHA_FIN, 3);
//
//        LocalDateTime nuevaFechaYHoraAlarma = LocalDateTime.of(2023, 4, 12, 10, 0);
//
//        var mail        = new Email();
//        var alarmaMail  = new AlarmaFechaAbsoluta(nuevaFechaYHoraAlarma,mail);
//        eventoModificado.agregarAlarmaEvento(alarmaMail);
//
//
//
//        for (Evento evento : listaTodosLosEventos) {
//
//            if (evento.getClass().equals(eventoModificado.getClass())){
//                calendario.modificarEvento(evento, eventoModificado, evento.obtenerAlarmasEvento().get(0),nuevaFechaYHoraAlarma );
//            }
//        }
//
//        Evento primerEventoDiario = calendario.obtenerListaEventosTotales().get(0);
//        Evento segundoEventoDiario = calendario.obtenerListaEventosTotales().get(1);
//
//        //Dentro de la lista de eventos totales del calendario, hay 3 eventos semanales. Agarro uno al azar con respecto a su indice y pruebo que no ha sido modificado
//        Evento eventoSemanalRandom = calendario.obtenerListaEventosTotales().get(2);
//
//        assertEquals(primerEventoDiario.obtenerTitulo(), eventoModificado.obtenerTitulo());
//        assertEquals(primerEventoDiario.obtenerDescripcion(), eventoModificado.obtenerDescripcion());
//        assertEquals(primerEventoDiario.obtenerFechaInicio(), eventoModificado.obtenerFechaInicio());
//        assertEquals(primerEventoDiario.obtenerFechaFin(), eventoModificado.obtenerFechaFin());
//        assertEquals(primerEventoDiario.obtenerMaxOcurrencias(), eventoModificado.obtenerMaxOcurrencias());
//        assertEquals(primerEventoDiario.obtenerTipoRepeticion(), eventoModificado.obtenerTipoRepeticion());
//        assertEquals(primerEventoDiario.obtenerAlarmasEvento().get(0).obtenerFechaYHora(), eventoModificado.obtenerAlarmasEvento().get(0).obtenerFechaYHora());
//
//        assertEquals(segundoEventoDiario.obtenerTitulo(), eventoModificado.obtenerTitulo());
//        assertEquals(segundoEventoDiario.obtenerDescripcion(), eventoModificado.obtenerDescripcion());
//        assertEquals(segundoEventoDiario.obtenerFechaInicio(), eventoModificado.obtenerFechaInicio());
//        assertEquals(segundoEventoDiario.obtenerFechaFin(), eventoModificado.obtenerFechaFin());
//        assertEquals(segundoEventoDiario.obtenerMaxOcurrencias(), eventoModificado.obtenerMaxOcurrencias());
//        assertEquals(segundoEventoDiario.obtenerTipoRepeticion(), eventoModificado.obtenerTipoRepeticion());
//        assertEquals(segundoEventoDiario.obtenerAlarmasEvento().get(0).obtenerFechaYHora(), eventoModificado.obtenerAlarmasEvento().get(0).obtenerFechaYHora());
//
//        //Verifico que no ha sido modificado
//        assertNotEquals(eventoSemanalRandom.obtenerTitulo(), eventoModificado.obtenerTitulo());
//        assertNotEquals(eventoSemanalRandom.obtenerDescripcion(), eventoModificado.obtenerDescripcion());
//        assertNotEquals(eventoSemanalRandom.obtenerFechaInicio(), eventoModificado.obtenerFechaInicio());
//        assertNotEquals(eventoSemanalRandom.obtenerFechaFin(), eventoModificado.obtenerFechaFin());
//        assertNotEquals(eventoSemanalRandom.obtenerMaxOcurrencias(), eventoModificado.obtenerMaxOcurrencias());
//        assertNotEquals(eventoSemanalRandom.obtenerTipoRepeticion(), eventoModificado.obtenerTipoRepeticion());
//
//       //Como para evento semanal no cree una alarma, me aseguro que no se haya creado  uno despues de la modificacion
//        assertTrue(eventoSemanalRandom.obtenerAlarmasEvento().isEmpty());
//    }
//
//    @Test
//    public void testObtenerEventosSegunFecha(){
//
//        CreadorEventosDiarios creadorEventosDiarios = new CreadorEventosDiarios();
//        CreadorEventosSemanales creadorEventosSemanales = new CreadorEventosSemanales();
//        CreadorEventosMensuales creadorEventosMensuales = new CreadorEventosMensuales();
//        CreadorEventosAnuales creadorEventosAnuales = new CreadorEventosAnuales();
//
//        Calendario eventosDiarios = new Calendario(creadorEventosDiarios);
//        Calendario eventosSemanales = new Calendario(creadorEventosSemanales);
//        Calendario eventosMensuales = new Calendario(creadorEventosMensuales);
//        Calendario eventosAnuales = new Calendario(creadorEventosAnuales);
//
//        Calendario calendario = new Calendario();
//
//        ArrayList<Evento> listaEventosDiarios = eventosDiarios.proximosEventosDiarios("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1);
//        ArrayList<Evento> listaEventosSemanales = eventosSemanales.proximosEventosSemanales("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, Set.of(DayOfWeek.MONDAY));
//        ArrayList<Evento> listaEventosMensuales = eventosMensuales.proximosEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2);
//        ArrayList<Evento> listaEventosAnuales = eventosAnuales.proximosEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.HASTA_FECHA_FIN, 3);
//
//
//        calendario.agregarEventosACalendario(listaEventosDiarios);
//        calendario.agregarEventosACalendario(listaEventosSemanales);
//        calendario.agregarEventosACalendario(listaEventosMensuales);
//        calendario.agregarEventosACalendario(listaEventosAnuales);
//
//        ArrayList<Evento>eventosEnFecha = calendario.obtenerEventosSegunFecha(LocalDate.of(2023, 4, 10));
//
//        Evento evento1 = eventosEnFecha.get(0);
//        Evento evento2 = eventosEnFecha.get(1);
//
//        assertEquals(2, eventosEnFecha.size());
//
//        //Me aseguro que sea el mismo objeto
//        assertEquals(evento1.getClass(), listaEventosDiarios.get(0).getClass());
//        assertEquals(evento2.getClass(), listaEventosMensuales.get(0).getClass());
//
//        assertEquals(evento1.obtenerTitulo(), listaEventosDiarios.get(0).obtenerTitulo());
//        assertEquals(evento2.obtenerTitulo(), listaEventosMensuales.get(0).obtenerTitulo());
//
//
//    }
}