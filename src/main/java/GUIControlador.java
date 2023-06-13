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

    private Evento eventoDiario;
    private Evento eventoSemanal;
    private Evento eventoMensual;
    private Evento eventoAnual;
    private Evento eventoDiaCompleto;


    private GUIVista vista;
    private ListView<Tarea> listaTareas;

    private YearMonth mesAnioActual;
    private Label mesAnioActualLabel;
    private List<Event> eventos;
    private GridPane calendarioGrid;

    public GUIControlador(Calendario calendario, GUIVista vista, GridPane calendarioGrid, Label mesAnioActualLabel) {
        this.calendario = calendario;
        this.vista = vista;
        this.vista.setControlador(this);
        this.listaTareas = new ListView<>();
        this.tareaDiaCompleto = new TareaDiaCompleto();
        this.tareaConVencimiento = new TareaConVencimiento();
        this.mesAnioActualLabel = mesAnioActualLabel;
        this.mesAnioActual = YearMonth.now();
        this.calendarioGrid = calendarioGrid;

//SON SUSTITOS PARA LA VERDADERA LISTA DE EVENTOS, SOLO QUIERO PROBAR FUNCIONABILIDAD
        this.eventos = new ArrayList<>();

        eventosEjemplo();
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
                diaLabel.setStyle("-fx-font-weight: bold; -fx-background-color: yellow;");
            }

            int finalDiaMes = diaMes;
            diaLabel.setOnMouseClicked(e -> mostrarEventosDelDia(finalDiaMes)); //Muestra los eventos del dia al hacer click

            if (columna == 6){
                fila++;
            }

            diaMes++;
        }
    }

    private boolean tieneEventosEnFecha(LocalDate fechaDeseada) {

        for (Event event : eventos) {
            if (event.getFecha().isEqual(fechaDeseada)) {
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
        List<Event> eventosDelDia = obtenerEventosDelDia(diaSeleccionado);

        if (eventosDelDia.isEmpty()) {
            mostrarMensaje("No hay eventos", "No hay eventos asignados a este dia.");

        } else {
            StringBuilder tituloPopUp = new StringBuilder();

            for (Event event : eventosDelDia) {
                tituloPopUp.append(event.getTitulo()).append("\n");
            }
            mostrarMensaje("Eventos: ", tituloPopUp.toString());
        }
    }

    private List<Event> obtenerEventosDelDia(LocalDate date) {
        List<Event> eventosDia = new ArrayList<>();

        for (Event event : eventos) {

            if (event.getFecha().equals(date)) {

                eventosDia.add(event);
            }
        }
        return eventosDia;
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


    ///SOLO ESTAN PARA PROBAR QUE SE CARGUE BIEN LA INFO
    private void eventosEjemplo() {
        eventos.add(new Event(LocalDate.of(2023, 6, 1), "Evento 1"));
        eventos.add(new Event(LocalDate.of(2023, 6, 5), "Evento 2"));
        eventos.add(new Event(LocalDate.of(2023, 6, 10), "Evento 3"));
        eventos.add(new Event(LocalDate.of(2023, 6, 15), "Evento 4"));
        eventos.add(new Event(LocalDate.of(2023, 6, 20), "Evento 5"));

    }
}