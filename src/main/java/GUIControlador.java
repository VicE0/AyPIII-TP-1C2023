import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.util.ArrayList;

public class GUIControlador {

    private Calendario calendario;

    private Tarea tareaDiaCompleto;
    private Tarea tareaConVencimiento;

    private Evento eventoDiario;
    private Evento eventoSemanal;
    private Evento eventoMensual;
    private Evento eventoAnual;
    private Evento eventoDiaCompleto;


    private GUIVista vista;
    private ListView<Tarea> listaTareas;

    public GUIControlador(Calendario calendario, GUIVista vista) {
        this.calendario = calendario;
        this.vista = vista;
        this.vista.setControlador(this);
        this.listaTareas = new ListView<>();
        this.tareaDiaCompleto = new TareaDiaCompleto();
        this.tareaConVencimiento = new TareaConVencimiento();
    }

    public ListView<Tarea> obtenerListaTareas(){
        return this.listaTareas;
    }

    public Tarea obtenerObjetoTareaDiaCompleto(){
        return this.tareaDiaCompleto;
    }

    public Tarea obtenerObjetoTareaConVencimiento(){
        return this.tareaConVencimiento;
    }

    public Evento obtenerObjetoEventoAnual(){ return this.eventoAnual; }


    public void agregarTarea(Tarea tarea) {
        calendario.agregarTarea(tarea);
        vista.mostarListaTareas(calendario.obtenerTareas());
//
//        vista.gridlayout(listaTareas);
//        vista.limpiarCampos();
    }
    public void agregarEvento(Evento evento){
        calendario.agregarEvento(evento);
    }


}