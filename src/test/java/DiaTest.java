import org.junit.Test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class DiaTest
{

    @Test
    public void testAgregarEvento()
    {
        var dia = new Dia(LocalDate.of(2023, 4, 8));

        var evento1 = new Evento("Evento 1", "Descripción evento 1",
                LocalDateTime.of(2023, 4, 8, 10, 0),
                LocalDateTime.of(2023, 4, 8, 12, 0));

        var evento2 = new Evento("Evento 2", "Descripción evento 2",
                LocalDateTime.of(2023, 4, 8, 14, 0),
                LocalDateTime.of(2023, 4, 8, 16, 0));

        dia.agregarEvento(evento1);
        assertEquals(1, dia.obtenerEventos().size());

        dia.agregarEvento(evento2);
        assertEquals(2, dia.obtenerEventos().size());
    }
    @Test
    public void testAgregarEventoSinFirma()
    {
        var dia = new Dia(LocalDate.of(2023, 4, 8));

        var evento1 = new Evento();

        var evento2 = new Evento("Evento 2", "Descripción evento 2",
                LocalDateTime.of(2023, 4, 8, 14, 0),
                LocalDateTime.of(2023, 4, 8, 16, 0));

        dia.agregarEvento(evento1);
        assertEquals(1, dia.obtenerEventos().size());

        dia.agregarEvento(evento2);
        assertEquals(2, dia.obtenerEventos().size());
    }

    //un evento no tine ni desc ni titulo
    @Test
    public void testEliminarEvento()
    {
        var dia = new Dia(LocalDate.of(2023, 4, 8));

        var evento1 = new Evento(null, null,
                LocalDateTime.of(2023, 4, 8, 10, 0),
                LocalDateTime.of(2023, 4, 8, 12, 0));

        var evento2 = new Evento("Evento 2", "Descripción evento 2",
                LocalDateTime.of(2023, 4, 8, 14, 0),
                LocalDateTime.of(2023, 4, 8, 16, 0));


        dia.agregarEvento(evento1);
        dia.agregarEvento(evento2);
        dia.eliminarEvento(evento1);

        assertEquals(1, dia.obtenerEventos().size());
        assertFalse(dia.obtenerEventos().contains(evento1));
        assertTrue(dia.obtenerEventos().contains(evento2));
    }

    @Test
    public void testLimpiarEventos()
    {
        var dia = new Dia(LocalDate.of(2023, 4, 8));
        var evento1 = new Evento("Evento 1", "Descripción evento 1",
                LocalDateTime.of(2023, 4, 8, 10, 0),
                LocalDateTime.of(2023, 4, 8, 12, 0));

        var evento2 = new Evento("Evento 2", "Descripción evento 2",
                LocalDateTime.of(2023, 4, 8, 14, 0),
                LocalDateTime.of(2023, 4, 8, 16, 0));

        dia.agregarEvento(evento1);
        dia.agregarEvento(evento2);

        dia.limpiarEventos();
        assertEquals(0, dia.obtenerEventos().size());
    }
}