
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Iterator;

public class Calendario {

    private CreadorDeEventos creadorDeEventos;
    private ArrayList<Evento> eventos;
    private ArrayList<Tarea> tareas;
    private ArrayList<Alarma> alarmas;

    public  Calendario(){
        this.tareas = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }

    public Calendario(CreadorDeEventos creadorDeEventos) {
        this.creadorDeEventos = creadorDeEventos;
        this.eventos = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }

    public void crearEvento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int intervalo, List<DayOfWeek> diasSemana) {
        this.eventos.add(creadorDeEventos.crearEvento(titulo, descripcion, fechaInicio,fechaFin, maxOcurrencias, tipoRepeticion, intervalo,diasSemana));
    }

    public void crearEventoDefault(){
        this.eventos.add(creadorDeEventos.crearEventoDefault());
    }

    public void agregarEventosACalendario(ArrayList<Evento> listaEventos){

        for(Evento eventoEnLista : listaEventos) {
            this.eventos.add(eventoEnLista);
        }
    }


    public ArrayList<Evento> obtenerListaEventosTotales(){
        return this.eventos;
    }


//    public void eliminarEvento(Evento eventoAEliminar) {
//        ArrayList<Evento> eventosAEliminar = new ArrayList<>();
//
//        for (Evento nuevoEvento : this.eventos) {
//            if (nuevoEvento.getClass().equals(eventoAEliminar.getClass()) && nuevoEvento.obtenerTitulo().equals(eventoAEliminar.obtenerTitulo())) {
//                eventosAEliminar.add(nuevoEvento);
//            }
//        }
//
//        for (Evento evento : eventosAEliminar) {
//            eventosAEliminar.remove(evento);
//        }
//    }


    public void eliminarEvento(Evento eventoAEliminar) {
        Iterator<Evento> it = obtenerListaEventosTotales().iterator();
        while (it.hasNext()) {
            Evento evento = it.next();
            if (evento.getClass().equals(eventoAEliminar.getClass()) && evento.obtenerTitulo().equals(eventoAEliminar.obtenerTitulo())) {
                it.remove();
            }
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


    public ArrayList<Evento> obtenerEventosCreados() {

        ArrayList<Evento> eventosCreados = new ArrayList<>();

        for (Evento evento : this.eventos) {
            eventosCreados.add(creadorDeEventos.obtenerEventos());
        }
        return eventosCreados;
    }


    public ArrayList<Evento> proximosEventosDiarios(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int intervalo) {
        Evento eventoDiario = new EventoDiario( titulo,  descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion,intervalo );
        return eventoDiario.obtenerProximosEventos(eventoDiario);
    }

    public ArrayList<Evento> proximosEventosSemanales(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, List<DayOfWeek> diasSemana) {
        Evento eventoSemanal = new EventoSemanal( titulo,  descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion,diasSemana );
        return eventoSemanal.obtenerProximosEventos(eventoSemanal);
    }

    public ArrayList<Evento> proximosEventosMensuales(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int intervaloMeses) {
        Evento eventoMensual = new EventoMensual( titulo,  descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion,intervaloMeses );
        return eventoMensual.obtenerProximosEventos(eventoMensual);
    }
    public ArrayList<Evento> proximosEventosAnuales(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int intervaloAnios) {
        Evento eventoAnual = new EventoAnual( titulo,  descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion,intervaloAnios );
        return eventoAnual.obtenerProximosEventos(eventoAnual);
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

    //    // Método para obtener los eventos correspondientes a una fecha
//    public ArrayList<Evento> obtenerEventosSegunFecha(LocalDate fecha) {
//
//        ArrayList<Evento> eventosEnFecha = new ArrayList<>();
//
//        for (Evento evento : this.eventos) {
//            if (evento.obtenerFechaInicio().toLocalDate().equals(fecha) || evento.obtenerFechaFin().toLocalDate().equals(fecha)) {
//                eventosEnFecha.add(evento);
//            }
//        }
//        return eventosEnFecha;
//    }
//

    Alarma proximaAlarma() {
        alarmas = new ArrayList<>();
        for (Evento evento : eventos) {
            alarmas.addAll(evento.obtenerAlarmasEvento());
        }
        for (Tarea tarea : tareas) {
            alarmas.addAll(tarea.obtenerAlarmas());
        }
        Alarma recorrerLista = null;
        for (Alarma alarma : alarmas){
            if (recorrerLista == null || alarma.obtenerFechaYHora().compareTo(recorrerLista.obtenerFechaYHora())<0) {
                // la alarma actual es la próxima en sonar
                if (alarma.obtenerFechaYHora().isAfter(LocalDateTime.now())) {
                    // solo si la alarma es futura
                    recorrerLista = alarma;
                }
            }
        }
        return recorrerLista;
    }

}