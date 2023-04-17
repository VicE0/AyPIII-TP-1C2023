import org.junit.Test;

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





}