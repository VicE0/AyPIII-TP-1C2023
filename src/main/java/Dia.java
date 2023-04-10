
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Dia
{
    private LocalDate fecha;
    private ArrayList<Evento> eventos;


    public Dia(LocalDate fecha)
    {
        this.fecha = fecha;
        this.eventos = new ArrayList<>();
    }
    public LocalDate obtenerFecha() {return fecha;}

    public ArrayList<Evento> obtenerEventos() {return eventos;}

//    public void agregarEvento(Evento evento) {eventos.add(evento);}

    public void agregarEvento(Evento evento)
    {

        if (evento.obtenerRepetirCada() > 0)
        {
            LocalDateTime fechaInicio = evento.obtenerFechaInicio();
            LocalDateTime fechaFin = evento.obtenerFechaFin();

            LocalDateTime nuevaFechaInicio = fechaInicio.plusDays(evento.obtenerRepetirCada());
            LocalDateTime nuevaFechaFin = fechaFin.plusDays(evento.obtenerRepetirCada());

            for (int i = 0; i < evento.obtenerRepetirCada(); i++)
            {
                Evento eventoRepetido = new Evento(evento.obtenerTitulo(), evento.obtenerDescripcion(), nuevaFechaInicio, nuevaFechaFin);
                this.eventos.add(eventoRepetido);
            }

        } else
        {
            this.eventos.add(evento);
        }
    }

    public void eliminarEvento(Evento evento) {eventos.remove(evento);}

    //Para no eliminar uno por uno, puede ser temporal
    public void limpiarEventos() {eventos.clear();}
}