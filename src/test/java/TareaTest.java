import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TareaTest {
    @Test
    public void testTareaSinTitulo(){
        var tarea = new TareaConVencimiento();
        String titulo = tarea.obtenerTitulo();
        assertEquals("Tarea sin titulo", titulo);

    }

    @Test
    public void testTareaSinDescripcion(){
        var tarea = new TareaConVencimiento();
        String descripcion = tarea.obtenerDescripcion();
        assertEquals("",descripcion);
    }
    @Test
    public void testTareaDeDiaCompleto(){
        var tarea = new TareaDiaCompleto("Tarea","Descripcion",LocalDate.of(2023,4,18));
        LocalDateTime fechaInicio = tarea.obtenerFechaInicio();
        LocalDateTime fechaVencimiento = tarea.obtenerFechaVencimiento();
        assertEquals(LocalDateTime.of(2023,4,18,0,0,0),fechaInicio);
        assertEquals(LocalDateTime.of(2023,4,18,23,59,59),fechaVencimiento);

    }
    @Test
    public void testTareaNoCompletada(){
        var tarea = new TareaConVencimiento();
        boolean completa = tarea.estaCompleta();
        assertFalse(completa);
    }
    @Test
    public void testTareaObtenerAtributos(){
        var tarea = new TareaConVencimiento();
        tarea.establecerTitulo("Tarea");
        tarea.establecerDescripcion("Descripcion");
        tarea.establecerFechaInicio(LocalDateTime.of(2023, 4, 17, 10, 0));
        tarea.establecerFechaVencimiento(LocalDateTime.of(2023, 4, 17, 18, 0));
        assertEquals("Tarea",tarea.obtenerTitulo());
        assertEquals("Descripcion",tarea.obtenerDescripcion());
        assertEquals(LocalDateTime.of(2023, 4, 17, 10, 0),tarea.obtenerFechaInicio());
        assertEquals(LocalDateTime.of(2023, 4, 17, 18, 0),tarea.obtenerFechaVencimiento());

    }

    @Test
    public void testTareasTienenDistintoId(){
        var tarea_uno = new TareaConVencimiento("Tarea", "Descripcion", LocalDateTime.of(2023, 4, 17, 10, 0), LocalDateTime.of(2023, 4, 17, 18, 0));
        var tarea_dos = new TareaDiaCompleto("Tarea", "Descripcion", LocalDate.of(2023,4,18));
        var tarea_tres = new TareaConVencimiento("Tarea", "Descripcion", LocalDateTime.of(2023, 4, 18, 10, 0), LocalDateTime.of(2023, 4, 18, 18, 0));
        assertNotEquals(tarea_uno.obtenerId(),tarea_dos.obtenerId());
        assertNotEquals(tarea_uno.obtenerId(),tarea_tres.obtenerId());
        assertNotEquals(tarea_dos.obtenerId(),tarea_tres.obtenerId());
    }

    @Test
    public void testMarcarTareaCompletada() {
        var tarea = new TareaConVencimiento("Tarea", "Descripcion", LocalDateTime.of(2023, 4, 17, 10, 0), LocalDateTime.of(2023, 4, 17, 18, 0));
        tarea.marcarComoCompleta();
        assertTrue(tarea.estaCompleta());
    }

    @Test
    public void testActivarAlarma(){
        var tarea = new TareaConVencimiento( "Tarea", "Descripcion", LocalDateTime.of(2023, 4, 17, 10, 0), LocalDateTime.of(2023, 4, 17, 18, 0));
        var Notificacion = new Notificacion();
        var alarma = new Alarma( LocalDateTime.of(2023, 4, 17, 10, 0),Notificacion);
        tarea.agregarAlarma(alarma);
        ArrayList<Alarma> listaAlarmas = tarea.obtenerAlarmas();
        assertTrue(listaAlarmas.contains(alarma));

    }
    @Test
    public void testBorrarAlarma(){
        var tarea = new TareaConVencimiento("Tarea", "Descripcion", LocalDateTime.of(2023, 4, 17, 10, 0), LocalDateTime.of(2023, 4, 17, 18, 0));
        var notificacion = new Notificacion();
        var sonido = new Sonido();
        var alarma = new Alarma(tarea.obtenerFechaInicio(),notificacion);
        var alarma_uno = new Alarma(tarea.obtenerFechaInicio(), sonido);
        tarea.agregarAlarma(alarma);
        tarea.agregarAlarma(alarma_uno);
        tarea.desactivarAlarma(alarma);
        ArrayList<Alarma> listaAlarmas = tarea.obtenerAlarmas();
        assertFalse(listaAlarmas.contains(alarma));
        assertEquals(1,listaAlarmas.size());


    }
    @Test
    public void testAgregarMuchasAlarmas(){
        var tarea = new TareaConVencimiento("Tarea", "Descripcion", LocalDateTime.of(2023, 4, 17, 10, 0), LocalDateTime.of(2023, 4, 17, 18, 0));
        var notificacion = new Notificacion();
        var sonido = new Sonido();
        var mail = new Email();
        var alarma = new Alarma(tarea.obtenerFechaInicio(),notificacion);
        var alarma_uno = new Alarma(tarea.obtenerFechaInicio(),sonido);
        var alarma_dos = new Alarma(tarea.obtenerFechaInicio(),mail);

        tarea.agregarAlarma(alarma);
        tarea.agregarAlarma(alarma_uno);
        tarea.agregarAlarma((alarma_dos));
        ArrayList<Alarma> listaAlarmas = tarea.obtenerAlarmas();
        assertEquals(3,listaAlarmas.size());

    }

}