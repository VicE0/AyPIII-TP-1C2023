
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public void agregarEvento(Evento evento) {eventos.add(evento);}

    public void eliminarEvento(Evento evento) {eventos.remove(evento);}

    //Para no eliminar uno por uno, puede ser temporal
    public void limpiarEventos() {eventos.clear();}




}