import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TareaTest {
    @Test
    public void tareaSinTitulo(){
        var tarea = new Tarea();
        String titulo = tarea.obtenerTitulo();
        assertEquals("Tarea sin nombre", titulo);

    }

    @Test
    public void tareaSinDescpcion(){
        var tarea = new Tarea();
        String descripcion = tarea.obtenerDescripcion();
        assertEquals("",descripcion);
    }

    @Test
    public void tareaNoCompletada(){
        var tarea = new Tarea();
        boolean completa = tarea.estaCompleta();
        assertFalse(completa);
    }
    @Test
    public void tareaObtenerTitulo(){
        Tarea tarea = new Tarea("Tarea", "Descripción", LocalDateTime.of(2023, 4, 17, 10, 0), LocalDateTime.of(2023, 4, 17, 18, 0));
        assertEquals("Tarea",tarea.obtenerTitulo());

    }
    @Test
    public void tareaCompletada() {
        Tarea tarea = new Tarea("Tarea", "Descripción", LocalDateTime.of(2023, 4, 17, 10, 0), LocalDateTime.of(2023, 4, 17, 18, 0));
        tarea.marcarComoCompleta();
        assertTrue(tarea.estaCompleta());
    }




}