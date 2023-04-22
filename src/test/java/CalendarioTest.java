import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CalendarioTest {
    @Test
    public void testCrearTarea(){
        var calendario = new Calendario();
        var tarea = new TareaConVencimiento();
        calendario.crearTarea(tarea);
        assertEquals(1,calendario.obtenerTareas().size());
        assertTrue(calendario.obtenerTareas().contains(tarea));
    }
    @Test
    public void testTareaSeCreaConDatosCorrectos(){
        var calendario = new Calendario();
        var tarea = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        calendario.crearTarea(tarea);
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
        calendario.crearTarea(tareaUno);
        calendario.crearTarea(tareaDos);
        calendario.crearTarea(tareaTres);
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
        calendario.crearTarea(tareaUno);
        calendario.crearTarea(tareaDos);
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
        calendario.crearTarea(tareaUno);
        calendario.crearTarea(tareaDos);
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
        calendario.crearTarea(tareaUno);
        calendario.crearTarea(tareaDos);
        calendario.crearTarea(tareaTres);

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
        calendario.crearTarea(tareaUno);
        calendario.crearTarea(tareaDos);
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
        calendario.crearTarea(tareaUno);
        calendario.crearTarea(tareaDos);
        assertEquals(2,calendario.obtenerTareas().size());
        calendario.eliminarTarea(9393);
        assertEquals(2,calendario.obtenerTareas().size());
    }
    @Test
    public void testModificarTarea(){
        var calendario = new Calendario();
        var tarea = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        calendario.crearTarea(tarea);
        int idUno = tarea.obtenerId();
        assertEquals("Tarea",calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Hacer la tarea",calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,22,23,59,59),calendario.obtenerTareas().get(0).obtenerFechaVencimiento());


        calendario.modificarTarea(idUno,"Nuevo Titulo","Nueva Descripcion",LocalDateTime.of(2023,12,24,0,0),LocalDateTime.of(2023,12,24,18,20));
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
        calendario.crearTarea(tareaUno);
        calendario.crearTarea(tareaDos);
        calendario.modificarTarea(idUno,"Nuevo Titulo","Nueva Descripcion",LocalDateTime.of(2023,12,24,0,0),LocalDateTime.of(2023,12,24,18,20));
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
    public void testSiNoEncuentraIdNoModificaNada(){
        var calendario = new Calendario();
        var tareaUno = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        var tareaDos = new TareaDiaCompleto("Tarea","Hacer la tarea", LocalDate.of(2023,12,22));
        calendario.crearTarea(tareaUno);
        calendario.crearTarea(tareaDos);
        calendario.modificarTarea(9999,"Nuevo Titulo","Nueva Descripcion",LocalDateTime.of(2023,12,24,0,0),LocalDateTime.of(2023,12,24,18,20));
        assertEquals("Tarea",calendario.obtenerTareas().get(0).obtenerTitulo());
        assertEquals("Hacer la tarea",calendario.obtenerTareas().get(0).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(0).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,22,23,59,59),calendario.obtenerTareas().get(0).obtenerFechaVencimiento());
        assertEquals("Tarea",calendario.obtenerTareas().get(1).obtenerTitulo());
        assertEquals("Hacer la tarea",calendario.obtenerTareas().get(1).obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023,12,22,0,0),calendario.obtenerTareas().get(1).obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023,12,22,23,59,59),calendario.obtenerTareas().get(1).obtenerFechaVencimiento());
    }
}