import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDate;


public class Calendario {
    private final ArrayList<Evento> eventos;
    private final ArrayList<Tarea> tareas;

    public Calendario()  {
        this.eventos = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public void crearEvento(Evento evento) {
        this.eventos.add(evento);
    }


    public void eliminarEvento(Evento eventoOriginal) {
        ArrayList<Evento> eventosAEliminar = new ArrayList<>();

        for (Evento nuevoEvento : this.eventos) {
            if (nuevoEvento.getClass().equals(eventoOriginal.getClass()) && nuevoEvento.obtenerTitulo().equals(eventoOriginal.obtenerTitulo())) {
                eventosAEliminar.add(nuevoEvento);
            }
        }

        for (Evento evento : eventosAEliminar) {
            eventosAEliminar.remove(evento);
        }

    }

    public void modificarEvento(Evento eventoOriginal, Evento eventoModificado) {
        ArrayList<Evento> eventosAModificar = new ArrayList<>();

        for (Evento nuevoEvento : this.eventos) {
            if (nuevoEvento.getClass().equals(eventoOriginal.getClass()) && nuevoEvento.obtenerTitulo().equals(eventoOriginal.obtenerTitulo())) {
                eventosAModificar.add(nuevoEvento);
            }
        }
        for (Evento nuevoEvento : eventosAModificar) {
            nuevoEvento.establecerTitulo(eventoModificado.obtenerTitulo());
            nuevoEvento.establecerDescripcion(eventoModificado.obtenerDescripcion());
            nuevoEvento.establecerFechaInicio(eventoModificado.obtenerFechaInicio());
            nuevoEvento.establecerFechaFin(eventoModificado.obtenerFechaFin());
        }
    }

    // Método para obtener los eventos correspondientes a una fecha
    public ArrayList<Evento> obtenerEventosSegunFecha(LocalDate fecha) {

        ArrayList<Evento> eventosEnFecha = new ArrayList<>();

        for (Evento evento : this.eventos) {
            if (evento.obtenerFechaInicio().toLocalDate().equals(fecha) || evento.obtenerFechaFin().toLocalDate().equals(fecha)) {
                eventosEnFecha.add(evento);
            }
        }
        return eventosEnFecha;
    }

    // Método para obtener todos los eventos del calendario
    public ArrayList<Evento> obtenerTodosLosEventos() {
        return this.eventos;
    }


    public void crearTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void modificarTarea(int id, String nuevoTitulo, String nuevaDescripcion, LocalDateTime nuevaFechaInicio, LocalDateTime nuevaFechaVencimiento) {

        boolean encontrada = false;
        for (int i=0;i< tareas.size() && !encontrada ; i++){
            Tarea tarea = tareas.get(i);
            if (tarea.obtenerId() == id) {
                tarea.establecerTitulo(nuevoTitulo);
                tarea.establecerDescripcion(nuevaDescripcion);
                tarea.establecerFechaInicio(nuevaFechaInicio);
                tarea.establecerFechaVencimiento(nuevaFechaVencimiento);
                encontrada = true;
            }
        }
    }

    public void eliminarTarea(int id) {

        boolean encontrada = false;
        for (int i=0;i< tareas.size() && !encontrada ; i++){

            Tarea tarea = tareas.get(i);

            if (tarea.obtenerId() == id){
                tareas.remove(i);
                encontrada = true;

                }
            }
    }
    public ArrayList<Tarea> obtenerTareas(){
        return tareas;
    }
}
