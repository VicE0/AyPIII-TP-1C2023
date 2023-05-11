import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Calendario {

//    private CreadorDeEventos creadorDeEventos;
    private  final ArrayList<Evento> eventos;
    private final ArrayList<Tarea> tareas;

    public  Calendario(){
        this.tareas = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }


    public void agregarEvento(Evento evento){

        this.eventos.add(evento);
    }

    //PRE: Recibe una lista de objetos Evento
    //POS: Agrega toda la lista de los eventos a la clase calendario
    public void agregarEventosACalendario(ArrayList<Evento> listaEventos){

        this.eventos.addAll(listaEventos);
    }


    public ArrayList<Evento> proximosEventos(ConstructorEventos constructor){

        ArrayList<Evento> OcurrenciasEventos = new ArrayList<>();

        // Añade la primera fecha ingresada

        OcurrenciasEventos.add(constructor.obtenerEventoCreado());
        constructor.obtenerEventoCreado().sumarOcurrencias();

        LocalDateTime proximaFecha = constructor.obtenerEventoCreado().calcularSiguienteOcurrencia(constructor.obtenerEventoCreado().obtenerFechaInicio());

        return constructor.repeticionEvento(proximaFecha, OcurrenciasEventos);
    }

    //PRE: -
    //POS: Devuelve la lista de objetos evento creada en el calendario
    public ArrayList<Evento> obtenerListaEventosTotales(){
        return this.eventos;
    }




    //PRE: Recibe una el objeto Evento a eliminar
    //POS: Itera por toda la lista de los eventos totales almacenados en la clase calendario. Si las clases del elemento a eliminar y el
    // evento encontrado en la lista coinciden, entonces lo elimina. Esto asegura que se eliminen todas las ocurrencias de dicho objeto

    public void eliminarEvento(Evento eventoAEliminar) {

        Iterator<Evento> iterador = obtenerListaEventosTotales().iterator();

        while (iterador.hasNext()) {
            Evento evento = iterador.next();

            if (evento.getClass().equals(eventoAEliminar.getClass()) && evento.obtenerTitulo().equals(eventoAEliminar.obtenerTitulo())) {
                iterador.remove();
            }
        }
    }


    public void modificarEvento(Evento eventoOriginal, Evento eventoModificado, Alarma alarma, LocalDateTime nuevaFechaAlarma) {

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
            nuevoEvento.establecerMaxOcurrencias(eventoModificado.obtenerMaxOcurrencias());
            nuevoEvento.establecerTipoRepeticion(eventoModificado.obtenerTipoRepeticion());

            if (eventoOriginal.obtenerAlarmasEvento() != null){
                nuevoEvento.modificarAlarmaEvento(alarma, nuevaFechaAlarma);
            }
        }
    }


    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void modificarTarea(int id, String nuevoTitulo, String nuevaDescripcion, LocalDateTime nuevaFechaInicio, LocalDateTime nuevaFechaVencimiento, Alarma alarma, LocalDateTime nuevaFechaAlarma) {

        boolean encontrada = false;
        for (int i=0;i< tareas.size() && !encontrada ; i++){
            Tarea tarea = tareas.get(i);
            if (tarea.obtenerId() == id) {
                tarea.establecerTitulo(nuevoTitulo != null ? nuevoTitulo : tarea.obtenerTitulo());
                tarea.establecerDescripcion(nuevaDescripcion != null ? nuevaDescripcion : tarea.obtenerDescripcion());
                tarea.establecerFechaInicio(nuevaFechaInicio != null ? nuevaFechaInicio : tarea.obtenerFechaInicio());
                tarea.establecerFechaVencimiento(nuevaFechaVencimiento != null ? nuevaFechaVencimiento : tarea.obtenerFechaVencimiento());
                if (alarma != null){
                    tarea.modificarAlarma(alarma,nuevaFechaAlarma);
                }
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

    Alarma proximaAlarma(LocalDateTime fechaReferencia) {
        ArrayList<Alarma> alarmas = new ArrayList<>();
        for (Evento evento : eventos) {
            alarmas.addAll(evento.obtenerAlarmasEvento());
        }
        for (Tarea tarea : tareas) {
            alarmas.addAll(tarea.obtenerAlarmas());
        }
        Alarma recorrerLista = null;
        for (Alarma alarma : alarmas){
            if (recorrerLista == null || alarma.obtenerFechaYHora().isBefore(recorrerLista.obtenerFechaYHora())) {
                // la alarma actual es la próxima en sonar
                if (fechaReferencia.isBefore(alarma.obtenerFechaYHora())) {
                    // solo si la alarma es futura
                    recorrerLista = alarma;
                }
            }
        }
        return recorrerLista;
    }


//ESTE METODO NO ES PEDIDO POR EL ENUNCIADO DE LA ETAPA 1, PERO A RECOMENDACIÓN DE ESSAYA FUE IMPLEMENTADO
// Método para obtener los eventos correspondientes a una fecha


    public ArrayList<Evento> obtenerEventosEntreFechas(LocalDate fechaA, LocalDate fechaB){
        ArrayList<Evento> eventosEnRango = new ArrayList<>();
        for (Evento evento : this.eventos){
            LocalDate fechaEvento = evento.obtenerFechaInicio().toLocalDate();
            if (fechaEvento.isAfter(fechaA) || fechaEvento.isBefore(fechaB)){
                eventosEnRango.add(evento);
            }
        }
        return eventosEnRango;

    }
}