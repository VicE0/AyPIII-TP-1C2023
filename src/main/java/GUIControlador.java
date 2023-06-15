
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class GUIControlador {

    private Calendario calendario;
    private Tarea tareaDiaCompleto;
    private Tarea tareaConVencimiento;
    private CreadorDeEventos eventoDiarioCreado;

    private Evento eventoDiario;
    private GUIVista vista;
    private ListView<Tarea> listaTareas;
    private ListView<Evento> listaEventos;

    private  List<Evento> eventosMesActual ;
    private  List<Tarea> tareasMesActual;

    private YearMonth mesAnioActual;
    private Label mesAnioActualLabel;

    private GridPane calendarioGrid;

    public GUIControlador(Calendario calendario, GUIVista vista, GridPane calendarioGrid, Label mesAnioActualLabel) {
        this.calendario = calendario;
        this.vista = vista;
        this.vista.setControlador(this);

        this.listaTareas = new ListView<>();
        this.listaEventos = new ListView<>();
        this.eventosMesActual = new ArrayList<>();
        this.tareasMesActual = new ArrayList<>();

        this.tareaDiaCompleto = new TareaDiaCompleto();
        this.tareaConVencimiento = new TareaConVencimiento();
        this.eventoDiario = new EventoDiario();

        this.mesAnioActualLabel = mesAnioActualLabel;
        this.calendarioGrid = calendarioGrid;
        this.eventoDiarioCreado = new CreadorEventosDiarios();
        this.mesAnioActual = YearMonth.now();

    }

    public ListView<Tarea> obtenerListaTareas() {
        this.listaTareas.setItems(FXCollections.observableArrayList(calendario.obtenerTareas()));
        return this.listaTareas;
    }

    public ListView<Evento> obtenerListaEventos() {
        this.listaEventos.setItems(FXCollections.observableArrayList(calendario.obtenerListaEventosTotales()));
        return this.listaEventos;
    }

    public Tarea obtenerObjetoTareaDiaCompleto() {
        return this.tareaDiaCompleto;
    }

    public Tarea obtenerObjetoTareaConVencimiento() {
        return this.tareaConVencimiento;
    }

    public Evento obtenerObjetoEventoDiario() {
        return this.eventoDiario;
    }


    public void agregarTarea(Tarea tarea) {
        calendario.agregarTarea(tarea);
        vista.mostarListaTareas(calendario.obtenerTareas());
        Calendario.guardarCalendarioEnArchivo(calendario, "calendario.json");
//
//        vista.gridlayout(listaTareas);
//        vista.limpiarCampos();
    }


    public void agregarEvento(ConstructorEventos constructorEventos) {
        Evento eventoDiarioFin = eventoDiarioCreado.crearEvento(constructorEventos);
        ArrayList<Evento> proximosEventos = calendario.proximosEventos(constructorEventos);
        calendario.agregarEventosACalendario(proximosEventos);
        listaEventos.getItems().addAll(calendario.obtenerListaEventosTotales());
        vista.mostarListaEventos(calendario.obtenerListaEventosTotales());
        Calendario.guardarCalendarioEnArchivo(calendario,"calendario.json");
    }

    public void tareaAgregarAlarma(Tarea tarea, Alarma alarma) {
        tarea.agregarAlarma(alarma);

    }

    public void eventoAgregarAlarma(Evento evento, Alarma alarma) {
        evento.agregarAlarmaEvento(alarma);
    }

    public void mostrarMesAnterior() {
        mesAnioActual = mesAnioActual.minusMonths(1);
        crearCalendario();
        actualizarLabel();
    }

    public void mostrarMesSiguiente() {
        mesAnioActual = mesAnioActual.plusMonths(1);
        crearCalendario();
        actualizarLabel();
    }


    public void crearCalendario() {

        calendarioGrid.getChildren().clear();

        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        IntStream.range(0, 7)
                .forEach(columna -> {
                    Label diaDeSemanaLabel = new Label(diasSemana[columna]);
                    diaDeSemanaLabel.setStyle("-fx-font-weight: bold;");

                    calendarioGrid.add(diaDeSemanaLabel, columna, 0);
                    GridPane.setHalignment(diaDeSemanaLabel, HPos.CENTER);
                });

        LocalDate primerDiaMes = mesAnioActual.atDay(1);
        int comienzoDiaSemana = primerDiaMes.getDayOfWeek().getValue();

        int diaMes = 1;
        int fila = 1;

        while (diaMes <= mesAnioActual.lengthOfMonth()) { //Mientras el dia del mes actual sea menor o igual al numero total de días en el mes actual

            int columna = (comienzoDiaSemana + diaMes - 2) % 7; //Formatea la columna donde se colocara el dia

            columna = (columna < 0) ? columna + 7 : columna; //Si la operacion anterior dio negativa => diaMes actual está antes del primer día de la semana

            Label diaLabel = new Label(String.valueOf(diaMes));
            calendarioGrid.add(diaLabel, columna, fila);
            GridPane.setHalignment(diaLabel, HPos.CENTER);

            LocalDate fechaDeseada = mesAnioActual.atDay(diaMes);

            if (tieneEventosEnFecha(fechaDeseada)) {
                diaLabel.setStyle("-fx-font-weight: bold; -fx-background-color: lightsalmon;");
            }

            if (tieneTareasEnFecha(fechaDeseada)) {
                diaLabel.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
            }

            if (tieneEventosYTareasEnFecha(fechaDeseada)) {
                diaLabel.setStyle("-fx-font-weight: bold; -fx-background-color: greenyellow;");
            }


            int finalDiaMes = diaMes;
            diaLabel.setOnMouseClicked(e -> {
                mostrarEventosDelDia(finalDiaMes);
                mostrarTareasDelDia(finalDiaMes);
            });

            if (columna == 6) {
                fila++;
            }

            diaMes++;
            LocalDate ultimoDia = mesAnioActual.atDay(mesAnioActual.lengthOfMonth());
            crearListaEventosYTareasEnFecha(primerDiaMes,ultimoDia);

        }
    }


    public void crearListaEventosYTareasEnFecha( LocalDate primerDia,LocalDate ultimoDia ){
        actualizarEventosYTareasMesActual();

        eventosMesActual.addAll(calendario.obtenerEventosEntreFechas(primerDia, ultimoDia));
        tareasMesActual.addAll(calendario.obtenerTareasEntreFechas(primerDia, ultimoDia));
    }

    public  List<Evento> obtenerEventosMesActual(){
        return this.eventosMesActual;
    }

    public  List<Tarea> obtenerTareasMesActual(){
        return this.tareasMesActual;
    }

    public void actualizarEventosYTareasMesActual() {
        eventosMesActual.clear();
        eventosMesActual.addAll(obtenerEventosMesActual());

        tareasMesActual.clear();
        tareasMesActual.addAll(obtenerTareasMesActual());
    }

    private boolean tieneEventosEnFecha(LocalDate fechaDeseada) {

        for (Evento evento : listaEventos.getItems()) {
            if (evento.obtenerFechaInicio().toLocalDate().isEqual(fechaDeseada)) {
                return true;
            }
        }
        return false;
    }


    private boolean tieneTareasEnFecha(LocalDate fechaDeseada) {

        for (Tarea tarea : listaTareas.getItems()) {
            if (tarea.obtenerFechaInicio().toLocalDate().isEqual(fechaDeseada)) {
                return true;
            }
        }
        return false;
    }


    private boolean tieneEventosYTareasEnFecha(LocalDate fechaDeseada) {
        boolean tieneEventos = false;
        boolean tieneTareas = false;

        for (Evento evento : listaEventos.getItems()) {
            if (evento.obtenerFechaInicio().toLocalDate().isEqual(fechaDeseada)) {
                tieneEventos = true;
                break;
            }
        }

        for (Tarea tarea : listaTareas.getItems()) {
            if (tarea.obtenerFechaInicio().toLocalDate().isEqual(fechaDeseada)) {
                tieneTareas = true;
                break;
            }
        }

        return tieneEventos && tieneTareas;
    }


    public void actualizarLabel() {

        String mesAnioMensaje = mesAnioActual.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + mesAnioActual.getYear();
        mesAnioActualLabel.setText(mesAnioMensaje);
    }

    public void mostrarEventosDelDia(int diaMes) {

        LocalDate diaSeleccionado = mesAnioActual.atDay(diaMes);
        List<Evento> eventosDelDia = obtenerEventosDelDia(diaSeleccionado);

        if (eventosDelDia.isEmpty()) {
            mostrarMensaje("No hay eventos", "No hay eventos asignados a este dia.");

        } else {
            StringBuilder tituloPopUp = new StringBuilder();

            for (Evento evento : eventosDelDia) {
                tituloPopUp.append(evento.obtenerTitulo()).append("\n");
                tituloPopUp.append(evento.obtenerDescripcion()).append("\n");
                tituloPopUp.append(("Inicio: ") + evento.obtenerFechaInicio()).append("\n");
                tituloPopUp.append(("Fin: ") + evento.obtenerFechaFin()).append("\n");
            }
            mostrarMensaje("Eventos ", tituloPopUp.toString());

        }
    }

    public void mostrarTareasDelDia(int diaMes) {

        LocalDate diaSeleccionado = mesAnioActual.atDay(diaMes);
        List<Tarea> tareasDelDia = obtenerTareasDelDia(diaSeleccionado);

        if (tareasDelDia.isEmpty()) {
            mostrarMensaje("No hay tareas", "No hay tareas asignadas a este dia.");

        } else {
            StringBuilder tituloPopUp = new StringBuilder();

            for (Tarea tarea : tareasDelDia) {
                tituloPopUp.append(tarea.obtenerTitulo()).append("\n");
                tituloPopUp.append(tarea.obtenerDescripcion()).append("\n");
                tituloPopUp.append(("Inicio: ") + tarea.obtenerFechaInicio()).append("\n");
                tituloPopUp.append(("Vencimiento: ") + tarea.obtenerFechaVencimiento()).append("\n");

                if (tarea.estaCompleta()) {
                    tituloPopUp.append("Tarea completada \n");
                } else {
                    tituloPopUp.append("Tarea no completada \n");
                }

            }
            mostrarMensaje("Tarea del dia ", tituloPopUp.toString());
        }
    }

    private List<Evento> obtenerEventosDelDia(LocalDate fecha) {
        List<Evento> eventosDia = new ArrayList<>();

        for (Evento evento : listaEventos.getItems()) {
            if (evento.obtenerFechaInicio().toLocalDate().equals(fecha)) {
                eventosDia.add(evento);
            }
        }
        return eventosDia;
    }

    private List<Tarea> obtenerTareasDelDia(LocalDate fecha) {
        List<Tarea> TareasDia = new ArrayList<>();

        for (Tarea tarea : listaTareas.getItems()) {

            if (tarea.obtenerFechaInicio().toLocalDate().equals(fecha)) {
                TareasDia.add(tarea);
            }
        }
        return TareasDia;
    }


    private void mostrarMensaje(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    public void iniciarAplicacion() {
        calendario = Calendario.cargarCalendarioDesdeArchivo("calendario.json");
        if (calendario == null) {
            // Si no se pudo cargar desde el archivo, crea un nuevo calendario
            calendario = new Calendario();
        }
    }

    public void cerrarAplicacion() {
        Calendario.guardarCalendarioEnArchivo(calendario, "calendario.json");
    }

}