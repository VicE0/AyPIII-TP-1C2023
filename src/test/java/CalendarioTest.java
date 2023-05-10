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
        var tarea = new TareaConVencimiento(LocalDateTime.of(2023,5,10,22,0));

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

        var tareaUno = new TareaConVencimiento(LocalDateTime.of(2023,5,18,22,0));
        var tareaDos = new TareaDiaCompleto(LocalDate.of(2023,4,18));
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

        var tareaUno = new TareaConVencimiento(LocalDateTime.of(2023,5,10,22,30));
        int idUno = tareaUno.obtenerId();

        var tareaDos = new TareaDiaCompleto(LocalDate.of(2023,5,10));
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
        assertEquals(alarmaDos,calendario.proximaAlarma(LocalDateTime.of(2023,1,1,0,0)));
    }

    @Test
    public void testCrearEventosYTareas() {

        ConstructorEventos   eventoDiarioConstruido       =   new ConstructorEventosDiarios(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoSemanalConstruido      =   new ConstructorEventosSemanales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoMensualConstruido      =   new ConstructorEventosMensuales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoAnualConstruido        =   new ConstructorEventosAnuales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoDiaCompletoConstruido  =   new ConstructorEventosDiaCompleto(LocalDate.of(2023,5,10));

        Evento eventoDiario      = eventoDiarioCreado.crearEvento(eventoDiarioConstruido);
        Evento eventoSemanal     = eventoSemanalCreado.crearEvento(eventoSemanalConstruido);
        Evento eventoMensual     = eventoMensualCreado.crearEvento(eventoMensualConstruido);
        Evento eventoAnual       = eventoAnualCreado.crearEvento(eventoAnualConstruido);
        Evento eventoDiaCompleto = eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);


        var tarea = new TareaConVencimiento(LocalDateTime.of(2023,5,10,22,30));
        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));

        var calendario = new Calendario();

        calendario.agregarTarea(tarea);
        calendario.agregarTarea(tareaDos);
        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoMensual);
        calendario.agregarEvento(eventoAnual);
        calendario.agregarEvento(eventoDiaCompleto);

        assertEquals(5,calendario.obtenerListaEventosTotales().size());
        assertEquals(2, calendario.obtenerTareas().size());
        assertTrue(calendario.obtenerTareas().contains(tarea));
    }

    @Test
    public void testCrearEventoDefault(){

        //Construyo los objetos con constructor default
        ConstructorEventos   eventoDiarioConstruido       =   new ConstructorEventosDiarios(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoSemanalConstruido      =   new ConstructorEventosSemanales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoMensualConstruido      =   new ConstructorEventosMensuales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoAnualConstruido        =   new ConstructorEventosAnuales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoDiaCompletoConstruido  =   new ConstructorEventosDiaCompleto(LocalDate.of(2023,5,10));

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
        ConstructorEventos   eventoDiarioConstruido       =   new ConstructorEventosDiarios(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoSemanalConstruido      =   new ConstructorEventosSemanales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoMensualConstruido      =   new ConstructorEventosMensuales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoAnualConstruido        =   new ConstructorEventosAnuales(LocalDateTime.of(2023,5,10,20,30));
        ConstructorEventos   eventoDiaCompletoConstruido  =   new ConstructorEventosDiaCompleto(LocalDate.of(2023,5,10));

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

    @Test
    public void testCrearEvento(){

        ConstructorEventos eventoDiarioConstruido      = new ConstructorEventosDiarios("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1);
        ConstructorEventos eventoSemanalConstruido     = new ConstructorEventosSemanales("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, Set.of(DayOfWeek.MONDAY));
        ConstructorEventos eventoMensualConstruido     = new ConstructorEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 12, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2);
        ConstructorEventos eventoAnualConstruido       = new ConstructorEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_FECHA_FIN, 3);
        ConstructorEventos eventoDiaCompletoConstruido = new ConstructorEventosDiaCompleto("Evento Dia Completo", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS );


        Evento eventoDiario      = eventoDiarioCreado.crearEvento(eventoDiarioConstruido);
        Evento eventoSemanal     = eventoSemanalCreado.crearEvento(eventoSemanalConstruido);
        Evento eventoMensual     = eventoMensualCreado.crearEvento(eventoMensualConstruido);
        Evento eventoAnual       = eventoAnualCreado.crearEvento(eventoAnualConstruido);
        Evento eventoDiaCompleto = eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);

        var calendario = new Calendario();

        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoMensual);
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
        assertEquals(listaEventos.get(3).getClass(), eventoSemanal.getClass());

        assertEquals(listaEventos.get(4).getClass(), eventoMensual.getClass());
        assertEquals(listaEventos.get(5).getClass(), eventoAnual.getClass());

        assertEquals(listaEventos.get(6).getClass(), eventoDiaCompleto.getClass());

        //Cada evento fue agregado correctamente, incluso duplicados
        assertEquals(7, calendario.obtenerListaEventosTotales().size());
    }

    @Test
    public void testEliminarEvento() {
        ConstructorEventos eventoDiarioConstruido      = new ConstructorEventosDiarios("Evento Diario", "Evento Repetido", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS, 1);
        ConstructorEventos eventoSemanalConstruido     = new ConstructorEventosSemanales("Evento Semanal", "Evento Repetido", LocalDateTime.of(2023, 4, 11, 9, 0), LocalDateTime.of(2023, 4, 21, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, Set.of(DayOfWeek.MONDAY));
        ConstructorEventos eventoMensualConstruido     = new ConstructorEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 12, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2);
        ConstructorEventos eventoAnualConstruido       = new ConstructorEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_FECHA_FIN, 3);
        ConstructorEventos eventoDiaCompletoConstruido = new ConstructorEventosDiaCompleto("Evento Dia Completo", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 10, Repeticion.HASTA_OCURRENCIAS );

        Evento eventoDiario      = eventoDiarioCreado.crearEvento(eventoDiarioConstruido);
        Evento eventoSemanal     = eventoSemanalCreado.crearEvento(eventoSemanalConstruido);
        Evento eventoMensual     = eventoMensualCreado.crearEvento(eventoMensualConstruido);
        Evento eventoAnual       = eventoAnualCreado.crearEvento(eventoAnualConstruido);
        Evento eventoDiaCompleto = eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);

        var calendario = new Calendario();

        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoDiario);
        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoSemanal);
        calendario.agregarEvento(eventoMensual);
        calendario.agregarEvento(eventoAnual);
        calendario.agregarEvento(eventoDiaCompleto);

        ArrayList<Evento> listaEventos = calendario.obtenerListaEventosTotales();

        //Elimino la instancia de EventoDiario y todas sus repeticiones
        calendario.eliminarEvento(listaEventos.get(0));

        //Elimino la instancia de EventoSemanal y todas sus repeticiones
        calendario.eliminarEvento(listaEventos.get(2));

        for(Evento evento : listaEventos){
            assertNotEquals(evento.getClass() ,eventoDiario.getClass());
            assertNotEquals(evento.getClass() ,eventoSemanal.getClass());
        }

        assertEquals(3, listaEventos.size() );
    }

    //Test con tipo de repeticion HastaOcurrencias
    @Test
    public void testObtenerProximosEventosDiarios(){

        ConstructorEventos eventoDiarioConstruido = new ConstructorEventosDiarios("Evento Diario", "Test para proximas ocurrencias", LocalDateTime.of(2023, 4, 10, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 3, Repeticion.HASTA_OCURRENCIAS, 2);
        Evento eventoDiario      =   eventoDiarioCreado.crearEvento(eventoDiarioConstruido);

        assertTrue(eventoDiario.obtenerAlarmasEvento().isEmpty());

        //Verifico que puedo acceder a alarmas
        var Notificacion = new Notificacion();
        var alarma       = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),Notificacion);

        eventoDiario.agregarAlarmaEvento(alarma);
        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoDiarioConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        LocalDateTime fecha1 = todosLosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fecha2 = todosLosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fecha3 = todosLosEventos.get(2).obtenerFechaInicio();

        LocalDateTime fecha2Esperada = LocalDateTime.of(2023, 4, 10, 9, 0).plusDays(2);
        LocalDateTime fecha3Esperada = fecha2Esperada.plusDays(2);

        assertEquals(fecha1, LocalDateTime.of(2023, 4, 10, 9, 0));
        assertEquals(fecha2,fecha2Esperada );
        assertEquals(fecha3,fecha3Esperada );

        assertTrue(eventoDiario.obtenerAlarmasEvento().contains(alarma));
        assertEquals(3, todosLosEventos.size());

    }

    //Test con tipo de repeticion HastaFechaFin
    @Test
    public void testObtenerProximosEventosDiariosFechaFin(){

        ConstructorEventos eventoDiarioConstruido = new ConstructorEventosDiarios("Evento Diario", "Test para proximas ocurrencias", LocalDateTime.of(2023, 4, 10, 9, 30), LocalDateTime.of(2023, 4, 17, 9, 30), 3, Repeticion.HASTA_FECHA_FIN, 1);
        Evento eventoDiario      =   eventoDiarioCreado.crearEvento(eventoDiarioConstruido);

        assertTrue(eventoDiario.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoDiarioConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();


        //En este caso, el intervalo de dias es 1, entonces deberia ser un array de 7 elementos (Dia 10 al dia 7).
        //Por temas de claridad visual, se agrega tambien el primer dia, entonces el array pasa a tener 8 elementos
        assertEquals(8, todosLosEventos.size());
    }

    //Test con tipo de repeticion Infinita
    @Test
    public void testObtenerProximosEventosDiariosInfinito(){

        ConstructorEventos eventoDiarioConstruido = new ConstructorEventosDiarios("Evento Diario", "Test para proximas ocurrencias", LocalDateTime.of(2023, 4, 10, 9, 30), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 1);
        //Por temas de testeo, se coloca un numero alto pero manejable

        Evento eventoDiario      =   eventoDiarioCreado.crearEvento(eventoDiarioConstruido);

        assertTrue(eventoDiario.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoDiarioConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();


        //Por temas de claridad visual, se agrega tambien el primer dia, entonces el array pasa a tener 100 elementos en vez de 99
        assertEquals(100, todosLosEventos.size());
    }

    @Test
    public void testEliminarTodosLosProximosEventosDiarios(){

        String titulo      = "Evento Diario";
        String descripcion = "Evento que se repite hasta ocurrencias";
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
        LocalDateTime fechaFin    = LocalDateTime.of(2023, 4, 17, 9, 30);
        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
        int maxOcurrencias = 10;
        int intervalo = 1;

        ConstructorEventos eventoDiarioConstruido = new ConstructorEventosDiarios(titulo,descripcion,fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervalo);
        ConstructorEventos eventoMensualConstruido     = new ConstructorEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 12, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2);

        Evento eventoDiario      =   eventoDiarioCreado.crearEvento(eventoDiarioConstruido);
        Evento eventoSemanal     =   eventoSemanalCreado.crearEvento(eventoMensualConstruido);


        var Notificacion = new Notificacion();
        var alarma       = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),Notificacion);

        eventoDiario.agregarAlarmaEvento(alarma);

        var calendario = new Calendario();

        calendario.agregarEvento(eventoSemanal);

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoDiarioConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        calendario.eliminarEvento(todosLosEventos.get(1));

        //Me aseguro que lo unico que quede en la lista de eventos sea el unico eventoSemanal agregado
       assertEquals(1, todosLosEventos.size());
       assertEquals(todosLosEventos.get(0).getClass(), eventoSemanal.getClass());

    }

    //Test eventosSemanales Hasta FechaFin
    @Test
    public void testObtenerProximosEventosSemanal(){

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 30);

        ConstructorEventos eventoSemanalConstruido     = new ConstructorEventosSemanales("Evento Semanal", "Test de ocurrencias", fechaInicio, LocalDateTime.of(2023, 4, 10, 9, 30), 1, Repeticion.HASTA_FECHA_FIN, Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY));
        Evento eventoSemanal      =   eventoSemanalCreado.crearEvento(eventoSemanalConstruido);

        assertTrue(eventoSemanal.obtenerAlarmasEvento().isEmpty());

        //Verifico que puedo acceder a alarmas
        var Notificacion = new Notificacion();

        var alarma       = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),Notificacion);

        eventoSemanal.agregarAlarmaEvento(alarma);
        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoSemanalConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        LocalDateTime fechaLunes = todosLosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fechaMiercoles = todosLosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fechaLunes2 = todosLosEventos.get(2).obtenerFechaInicio();

        LocalDateTime fechaMiercolesEsperada = fechaInicio.plusDays(2); //Miercoles 5 de abril
        LocalDateTime fechaLunes2Esperada = fechaMiercolesEsperada.plusDays(5); //Lunes 10 de abril

        assertEquals(fechaLunes, fechaInicio);
        assertEquals(fechaMiercoles,fechaMiercolesEsperada );
        assertEquals(fechaLunes2,fechaLunes2Esperada );

        assertTrue(eventoSemanal.obtenerAlarmasEvento().contains(alarma));

        assertEquals(3, todosLosEventos.size());
    }

    //Test eventosSemanales Hasta MaxOcurrencias
    @Test
    public void testObtenerProximosEventosSemanalHastaOcurrencias(){

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 30);

        ConstructorEventos eventoSemanalConstruido     = new ConstructorEventosSemanales("Evento Semanal", "Test de ocurrencias", fechaInicio, LocalDateTime.of(2023, 4, 10, 9, 30), 5, Repeticion.HASTA_OCURRENCIAS, Set.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY));
        Evento eventoSemanal      =   eventoSemanalCreado.crearEvento(eventoSemanalConstruido);

        assertTrue(eventoSemanal.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoSemanalConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        LocalDateTime fechaLunes    = todosLosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fechaViernes  = todosLosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fechaLunes2   = todosLosEventos.get(2).obtenerFechaInicio();
        LocalDateTime fechaViernes2 = todosLosEventos.get(3).obtenerFechaInicio();
        LocalDateTime fechaLunes3   = todosLosEventos.get(4).obtenerFechaInicio();

        LocalDateTime fechaViernesEsperada  = fechaInicio.plusDays(4); //Viernes 4 de abril
        LocalDateTime fechaLunes2Esperada   = fechaViernesEsperada.plusDays(3); //Lunes 10 de abril
        LocalDateTime fechaViernes2Esperada = fechaLunes2.plusDays(4); //Viernes 14 de abril
        LocalDateTime fechaLunes3Esperada   = fechaViernes2Esperada.plusDays(3); //Lunes 17 de abril


        assertEquals(fechaLunes, fechaInicio); //Lunes 3 de abril
        assertEquals(fechaViernes, fechaViernesEsperada);
        assertEquals(fechaLunes2, fechaLunes2Esperada);
        assertEquals(fechaViernes2, fechaViernes2Esperada);
        assertEquals(fechaLunes3, fechaLunes3Esperada);

        assertEquals(5, todosLosEventos.size());
    }

    @Test
    public void testObtenerProximosEventosSemanalInfinito(){

        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 3, 9, 30);

        ConstructorEventos eventoSemanalConstruido     = new ConstructorEventosSemanales("Evento Semanal", "Test de ocurrencias", fechaInicio, LocalDateTime.of(2023, 4, 10, 9, 30), 99, Repeticion.INFINITA, Set.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY));
        Evento eventoSemanal      =   eventoSemanalCreado.crearEvento(eventoSemanalConstruido);

        assertTrue(eventoSemanal.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoSemanalConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        //Por temas de claridad visual, se agrega tambien el primer dia, entonces el array pasa a tener 100 elementos en vez de 99
        assertEquals(100, todosLosEventos.size());
    }

    //Test con tipo de repeticion HastaOcurrencias
    @Test
    public void testObtenerProximosEventosMensuales(){

        ConstructorEventos eventoMensualConstruido     = new ConstructorEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 10, 9, 30), LocalDateTime.of(2023, 4, 17, 9, 30), 3, Repeticion.HASTA_OCURRENCIAS, 2);
        Evento eventoMensual      =   eventoMensualCreado.crearEvento(eventoMensualConstruido);

        assertTrue(eventoMensual.obtenerAlarmasEvento().isEmpty());

        //Verifico que puedo acceder a alarmas
        var Notificacion = new Notificacion();
        var alarma       = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),Notificacion);

        eventoMensual.agregarAlarmaEvento(alarma);
        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoMensualConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        LocalDateTime fecha1 = todosLosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fecha2 = todosLosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fecha3 = todosLosEventos.get(2).obtenerFechaInicio();

        LocalDateTime fecha2Esperada = LocalDateTime.of(2023, 4, 10, 9, 30).plusMonths(2);
        LocalDateTime fecha3Esperada = fecha2Esperada.plusMonths(2);

        assertEquals(fecha1, LocalDateTime.of(2023, 4, 10, 9, 30));
        assertEquals(fecha2,fecha2Esperada );
        assertEquals(fecha3,fecha3Esperada );

        assertTrue(eventoMensual.obtenerAlarmasEvento().contains(alarma));
        assertEquals(3, todosLosEventos.size());

    }

    //Test con tipo de repeticion HastaFechaFin
    @Test
    public void testObtenerProximosEventosMensualesFechaFin(){

        ConstructorEventos eventoMensualConstruido     = new ConstructorEventosMensuales("Evento Mensual", "Evento Unico", LocalDateTime.of(2023, 4, 10, 9, 30), LocalDateTime.of(2023, 4, 17, 9, 30), 3, Repeticion.HASTA_OCURRENCIAS, 2);
        Evento eventoMensual      =   eventoMensualCreado.crearEvento(eventoMensualConstruido);

        assertTrue(eventoMensual.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoMensualConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        LocalDateTime fecha1 = todosLosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fecha2 = todosLosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fecha3 = todosLosEventos.get(2).obtenerFechaInicio();

        assertEquals(fecha1,  LocalDateTime.of(2023, 4, 10, 9, 30));
        assertEquals(fecha2, fecha1.plusMonths(2));
        assertEquals(fecha3, fecha2.plusMonths(2));

        //En este caso, el intervalo de meses es 1, entonces deberia ser un array de 2 elementos (Mes 4 -> Mes 6).
        //Por temas de claridad visual, se agrega tambien el primer mes, entonces el array pasa a tener 3 elementos
        assertEquals(3, todosLosEventos.size());
    }

    //Test con tipo de repeticion Infinita
    @Test
    public void testObtenerProximosEventosMensualesInfinito(){

        ConstructorEventos eventoMensualConstruido = new ConstructorEventosDiarios("Evento Mensual", "Test para proximas ocurrencias", LocalDateTime.of(2023, 4, 10, 9, 30), LocalDateTime.of(2023, 6, 15, 9, 30), 99, Repeticion.INFINITA, 1);
        Evento eventoMensual     =   eventoMensualCreado.crearEvento(eventoMensualConstruido);

        assertTrue(eventoMensual.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();


        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoMensualConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();


        //Por temas de claridad visual, se agrega tambien el primer dia, entonces el array pasa a tener 100 elementos en vez de 99
        assertEquals(100, todosLosEventos.size());
    }


//Test con tipo de repeticion HastaOcurrencias
@Test
public void testObtenerProximosEventosAnuales(){

    ConstructorEventos eventoAnualConstruido     = new ConstructorEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 10, 9, 30), LocalDateTime.of(2023, 4, 17, 9, 30), 3, Repeticion.HASTA_OCURRENCIAS, 2);
    Evento eventoAnual      =   eventoAnualCreado.crearEvento(eventoAnualConstruido);

    assertTrue(eventoAnual.obtenerAlarmasEvento().isEmpty());

    var calendario = new Calendario();

    ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoAnualConstruido);
    calendario.agregarEventosACalendario(proximosEventos);

    ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

    LocalDateTime fecha1 = todosLosEventos.get(0).obtenerFechaInicio();
    LocalDateTime fecha2 = todosLosEventos.get(1).obtenerFechaInicio();
    LocalDateTime fecha3 = todosLosEventos.get(2).obtenerFechaInicio();

    LocalDateTime fecha2Esperada = LocalDateTime.of(2023, 4, 10, 9, 30).plusYears(2);
    LocalDateTime fecha3Esperada = fecha2Esperada.plusYears(2);

    assertEquals(fecha1, LocalDateTime.of(2023, 4, 10, 9, 30));
    assertEquals(fecha2,fecha2Esperada );
    assertEquals(fecha3,fecha3Esperada );


    assertEquals(3, todosLosEventos.size());

}

    //Test con tipo de repeticion HastaFechaFin
    @Test
    public void testObtenerProximosEventosAnualesFechaFin(){

        ConstructorEventos eventoAnualConstruido     = new ConstructorEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 10, 9, 30), LocalDateTime.of(2027, 4, 10, 9, 30), 3, Repeticion.HASTA_FECHA_FIN, 1);
        Evento eventoAnual      =   eventoAnualCreado.crearEvento(eventoAnualConstruido);

        assertTrue(eventoAnual.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoAnualConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        LocalDateTime fecha1 = todosLosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fecha2 = todosLosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fecha3 = todosLosEventos.get(2).obtenerFechaInicio();

        assertEquals(fecha1,  LocalDateTime.of(2023, 4, 10, 9, 30));
        assertEquals(fecha2, fecha1.plusYears(1));
        assertEquals(fecha3, fecha2.plusYears(1));

        //En este caso, el intervalo de Anual es 1, entonces deberia ser un array de 4 elementos (Año 2023 -> Año 2027).
        //Por temas de claridad visual, se agrega tambien el primer mes, entonces el array pasa a tener 5 elementos
        assertEquals(5, todosLosEventos.size());
    }

    //Test con tipo de repeticion Infinita
    @Test
    public void testObtenerProximosEventosAnualesInfinito(){

        ConstructorEventos eventoAnualConstruido     = new ConstructorEventosAnuales("Evento Anual", "Evento Unico", LocalDateTime.of(2023, 4, 10, 9, 30), LocalDateTime.of(2027, 4, 17, 9, 30), 99, Repeticion.INFINITA, 2);
        Evento eventoAnual      =   eventoAnualCreado.crearEvento(eventoAnualConstruido);

        assertTrue(eventoAnual.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();


        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoAnualConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();


        //Por temas de claridad visual, se agrega tambien el primer dia, entonces el array pasa a tener 100 elementos en vez de 99
        assertEquals(100, todosLosEventos.size());
    }

///////////////////////////////////////////////////////
//Test con tipo de repeticion HastaOcurrencias
@Test
public void testObtenerProximosEventosDiaCompleto(){

    ConstructorEventos eventoDiaCompletoConstruido = new ConstructorEventosDiaCompleto("Evento Dia Completo", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 17, 9, 30), 3, Repeticion.HASTA_OCURRENCIAS );
    Evento eventoDiaCompleto      =   eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);

    assertTrue(eventoDiaCompleto.obtenerAlarmasEvento().isEmpty());

    //Verifico que puedo acceder a alarmas
    var Notificacion = new Notificacion();
    var alarma       = new AlarmaFechaAbsoluta(LocalDateTime.of(2023, 4, 17, 10, 0),Notificacion);

    eventoDiaCompleto.agregarAlarmaEvento(alarma);
    var calendario = new Calendario();

    ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoDiaCompletoConstruido);
    calendario.agregarEventosACalendario(proximosEventos);

    ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

    LocalDateTime fecha1 = todosLosEventos.get(0).obtenerFechaInicio();
    LocalDateTime fecha2 = todosLosEventos.get(1).obtenerFechaInicio();
    LocalDateTime fecha3 = todosLosEventos.get(2).obtenerFechaInicio();

    LocalDateTime fecha2Esperada = LocalDateTime.of(2023, 4, 13, 9, 0).plusDays(1);
    LocalDateTime fecha3Esperada = fecha2Esperada.plusDays(1);

    assertEquals(fecha1, LocalDateTime.of(2023, 4, 13, 9, 0));
    assertEquals(fecha2,fecha2Esperada );
    assertEquals(fecha3,fecha3Esperada );

    assertTrue(eventoDiaCompleto.obtenerAlarmasEvento().contains(alarma));
    assertEquals(3, todosLosEventos.size());

}

    //Test con tipo de repeticion HastaFechaFin
    @Test
    public void testObtenerProximosEventosDiaCompletoFechaFin(){

        ConstructorEventos eventoDiaCompletoConstruido = new ConstructorEventosDiaCompleto("Evento Dia Completo", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 16, 9, 30), 3, Repeticion.HASTA_FECHA_FIN );
        Evento eventoDiaCompleto      =   eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);

        assertTrue(eventoDiaCompleto.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();

        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoDiaCompletoConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();

        LocalDateTime fecha1 = todosLosEventos.get(0).obtenerFechaInicio();
        LocalDateTime fecha2 = todosLosEventos.get(1).obtenerFechaInicio();
        LocalDateTime fecha3 = todosLosEventos.get(2).obtenerFechaInicio();

        assertEquals(fecha1,  LocalDateTime.of(2023, 4, 13, 9, 0));
        assertEquals(fecha2, fecha1.plusDays(1));
        assertEquals(fecha3, fecha2.plusDays(1));

        //En este caso, el intervalo de meses es 1, entonces deberia ser un array de 2 elementos (Mes 4 -> Mes 6).
        //Por temas de claridad visual, se agrega tambien el primer mes, entonces el array pasa a tener 3 elementos
        assertEquals(5, todosLosEventos.size());
    }

    //Test con tipo de repeticion Infinita
    @Test
    public void testObtenerProximosEventosDiaCompletoInfinito(){

        ConstructorEventos eventoDiaCompletoConstruido = new ConstructorEventosDiaCompleto("Evento Dia Completo", "Evento Unico", LocalDateTime.of(2023, 4, 13, 9, 0), LocalDateTime.of(2023, 4, 16, 9, 30), 99, Repeticion.INFINITA );
        Evento eventoDiaCompleto      =   eventoDiaCompletoCreado.crearEvento(eventoDiaCompletoConstruido);

        assertTrue(eventoDiaCompleto.obtenerAlarmasEvento().isEmpty());

        var calendario = new Calendario();


        ArrayList<Evento> proximosEventos = calendario.proximosEventos(eventoDiaCompletoConstruido);
        calendario.agregarEventosACalendario(proximosEventos);

        ArrayList<Evento> todosLosEventos = calendario.obtenerListaEventosTotales();


        //Por temas de claridad visual, se agrega tambien el primer dia, entonces el array pasa a tener 100 elementos en vez de 99
        assertEquals(100, todosLosEventos.size());
    }


///////ULTIMOS TESTS A CORREGIR//////

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