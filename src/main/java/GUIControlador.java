import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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

    private GUIVista vista;
    private ListView<Tarea> listaTareas;
    private ListView<Evento> listaEventos;

    private YearMonth mesAnioActual;
    private Label mesAnioActualLabel;

    private GridPane calendarioGrid;

    public GUIControlador(Calendario calendario, GUIVista vista, GridPane calendarioGrid, Label mesAnioActualLabel) {
        this.calendario = calendario;
        this.vista = vista;
        this.vista.setControlador(this);

        this.listaTareas = new ListView<>();
        this.listaEventos = new ListView<>();

        this.tareaDiaCompleto = new TareaDiaCompleto();
        this.tareaConVencimiento = new TareaConVencimiento();

        this.mesAnioActualLabel = mesAnioActualLabel;
        this.calendarioGrid = calendarioGrid;

        this.mesAnioActual = YearMonth.now();
;

    }

    public ListView<Tarea> obtenerListaTareas(){
        return this.listaTareas;
    }

    public ListView<Evento> obtenerListaEventos(){
        return this.listaEventos;
    }

    public Tarea obtenerObjetoTareaDiaCompleto(){
        return this.tareaDiaCompleto;
    }

    public Tarea obtenerObjetoTareaConVencimiento(){
        return this.tareaConVencimiento;
    }


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

            if (tieneTareasEnFecha(fechaDeseada)){
                diaLabel.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
            }

            //Sí tiene tareas y eventos → color = 	GREENYELLOW

            int finalDiaMes = diaMes;
            diaLabel.setOnMouseClicked(e -> mostrarEventosDelDia(finalDiaMes)); //Muestra los eventos del dia al hacer click
            diaLabel.setOnMouseClicked(e -> mostrarTareasDelDia(finalDiaMes));

            if (columna == 6){
                fila++;
            }

            diaMes++;
        }
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

                if(tarea.estaCompleta()){
                    tituloPopUp.append("Tarea completada \n");
                }
                else{
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


//    ///SOLO ESTAN PARA PROBAR QUE SE CARGUE BIEN LA INFO
//    private void eventosEjemplo() {
//        eventos.add(new Event(LocalDate.of(2023, 6, 1), "Evento 1"));
//        eventos.add(new Event(LocalDate.of(2023, 7, 5), "Evento 2"));
//        eventos.add(new Event(LocalDate.of(2023, 6, 10), "Evento 3"));
//        eventos.add(new Event(LocalDate.of(2023, 6, 15), "Evento 4"));
//        eventos.add(new Event(LocalDate.of(2023, 6, 20), "Evento 5"));
//
//    }
}