import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GUI extends Application {
    private YearMonth mesAnioActual;
    private Label mesAnioActualLabel;

    private List<Event> eventos;

    @Override
    public void start(Stage primaryStage) {
        mesAnioActual = YearMonth.now();
        this.eventos = new ArrayList<>();

        GridPane calendarGrid = new GridPane();
        calendarGrid.setHgap(10);
        calendarGrid.setVgap(10);

        createSampleEvents();

        createCalendar(calendarGrid);

        HBox headerBox = new HBox(10);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(10));

        Button mesAnteriorBoton = new Button("<<");
        mesAnteriorBoton.setOnAction(e -> mostrarMesAnterior(calendarGrid));

        Button mesSiguienteBoton = new Button(">>");
        mesSiguienteBoton.setOnAction(e -> mostrarMesSiguiente(calendarGrid));

        mesAnioActualLabel = new Label();
        mesAnioActualLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        actualizarLabel();

        headerBox.getChildren().addAll(mesAnteriorBoton, mesAnioActualLabel, mesSiguienteBoton);

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);
        root.add(headerBox, 0, 0);
        root.add(calendarGrid, 0, 1);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calendario");
        primaryStage.show();

    }

    private void createCalendar(GridPane calendarGrid) {
        calendarGrid.getChildren().clear();

        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        for (int columna = 0; columna < 7; columna++) {

            Label diaDeSemanaLabel = new Label(diasSemana[columna]);
            diaDeSemanaLabel.setStyle("-fx-font-weight: bold;");

            calendarGrid.add(diaDeSemanaLabel, columna, 0);
            GridPane.setHalignment(diaDeSemanaLabel, HPos.CENTER);
        }

        // Obtiene el primer día del mes
        LocalDate primerDiaMes = mesAnioActual.atDay(1);
        int comiezoDiaSemana = primerDiaMes.getDayOfWeek().getValue();

        // Llena el calendario con los días del mes
        int diaMes = 1;
        int fila = 1;

        for (; diaMes <= mesAnioActual.lengthOfMonth(); diaMes++) {
            int columna = (comiezoDiaSemana + diaMes - 2) % 7;

            if (columna < 0) {
                columna += 7;
            }

            Label diaLabel = new Label(String.valueOf(diaMes));

            calendarGrid.add(diaLabel, columna, fila);
            GridPane.setHalignment(diaLabel, HPos.CENTER);


            int finalDiaMes = diaMes;
            LocalDate date = mesAnioActual.atDay(finalDiaMes);

            if (hasEventsOnDate(date)) {
                diaLabel.setStyle("-fx-font-weight: bold; -fx-background-color: yellow;");
            }

            diaLabel.setOnMouseClicked(e -> mostrarEventosDelDia(finalDiaMes));

            if (columna == 6) {
                fila++;
            }

        }
    }

    private boolean hasEventsOnDate(LocalDate date) {
        for (Event event : eventos) {
            if (event.getDate().isEqual(date)) {
                return true;
            }
        }
        return false;
    }

    private void mostrarMesAnterior(GridPane calendarGrid) {
        mesAnioActual = mesAnioActual.minusMonths(1);
        createCalendar(calendarGrid);
        actualizarLabel();
    }

    private void mostrarMesSiguiente(GridPane calendarGrid) {
        mesAnioActual = mesAnioActual.plusMonths(1);
        createCalendar(calendarGrid);
        actualizarLabel();
    }

    private void actualizarLabel() {
        String mesAnioMensaje = mesAnioActual.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + mesAnioActual.getYear();
        mesAnioActualLabel.setText(mesAnioMensaje);
    }
    private void mostrarEventosDelDia(int diaMes) {

        LocalDate selectedDate = mesAnioActual.atDay(diaMes);

        List<Event> eventsForDay = getEventsForDate(selectedDate);

        if (eventsForDay.isEmpty()) {
            showAlert("No events", "There are no events for this day.");
        } else {
            StringBuilder eventText = new StringBuilder();
            for (Event event : eventsForDay) {
                eventText.append(event.getTitle()).append("\n");
            }
            showAlert("Events for Day", eventText.toString());
        }
    }


    private List<Event> getEventsForDate(LocalDate date) {
        List<Event> eventsForDay = new ArrayList<>();
        for (Event event : eventos) {
            if (event.getDate().equals(date)) {
                eventsForDay.add(event);
            }
        }
        return eventsForDay;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void createSampleEvents() {
        eventos.add(new Event(LocalDate.of(2023, 6, 1), "Event 1"));
        eventos.add(new Event(LocalDate.of(2023, 6, 5), "Event 2"));
        eventos.add(new Event(LocalDate.of(2023, 6, 10), "Event 3"));
        eventos.add(new Event(LocalDate.of(2023, 6, 15), "Event 4"));
        eventos.add(new Event(LocalDate.of(2023, 6, 20), "Event 5"));

    }
}

//    @Override
//    public void start(Stage primaryStage) {
//        Calendario calendario = new Calendario();
//        GUIVista vista = new GUIVista(primaryStage);
//        GUIControlador controlador = new GUIControlador(calendario, vista);
//
//        vista.setControlador(controlador)
//        primaryStage.setTitle("Calendario");

//        primaryStage.setTitle("Calendario");
//        primaryStage.setScene(vista.Escena());
//        primaryStage.show();
//    }










//CODIGO BASE
//public class pruebaInterfaz extends Application {
//    private Calendario calendario;
//
//    private TextField tituloField;
//    private TextField descripcionField;
//    private DatePicker fechaInicioPicker;
//    private TextField horarioInicioTextField;
//    private TextField minutosInicioTextField;
//    private DatePicker fechaVencimientoSinHoraPicker;
//    private TextField horarioVencimientoTextField;
//    private TextField minutosVencimientoTextField;
//    private ListView<Tarea> listaTareas;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public void start(Stage primaryStage) {
//        calendario = new Calendario();
//
//        primaryStage.setTitle("Calendario");
//
//        Label tituloLabel = new Label("Título:");
//        tituloField = new TextField();
//
//        Label descripcionLabel = new Label("Descripción:");
//        descripcionField = new TextField();
//
//        Label fechaInicioLabel = new Label("Fecha de Inicio:");
//        fechaInicioPicker = new DatePicker();
//
//        Label horarioInicioLabel = new Label("Horario (Incluye horas y minutos):");
//        horarioInicioTextField = new TextField();
//        minutosInicioTextField = new TextField();
//
//        Label fechaVencimientoLabel = new Label("Fecha de Vencimiento:");
//        fechaVencimientoSinHoraPicker = new DatePicker();
//
//        Label horarioVencimientoLabel = new Label("Horario (Incluye horas y minutos):");
//        horarioVencimientoTextField = new TextField();
//        minutosVencimientoTextField = new TextField();
//
//        Button agregarTareaButton = new Button("Agregar Tarea");
//        Button agregarEventoButton = new Button("Agregar Evento");
//        Button agregarTareaDiaCompletoButton = new Button("Agregar Tarea Día Completo");
//
//        agregarTareaButton.setOnAction(e -> {
//            String horarioInicioText = horarioInicioTextField.getText();
//            String minutosInicioText = minutosInicioTextField.getText();
//            String horarioVencimientoText = horarioVencimientoTextField.getText();
//            String minutosVencimientoText = minutosVencimientoTextField.getText();
//            int horaInicio;
//            int minutosInicio;
//            int horaVencimiento;
//            int minutosVencimiento;
//
//            try {
//                horaInicio = Integer.parseInt(horarioInicioText);
//                minutosInicio = Integer.parseInt(minutosInicioText);
//                horaVencimiento = Integer.parseInt(horarioVencimientoText);
//                minutosVencimiento = Integer.parseInt(minutosVencimientoText);
//
//            } catch (NumberFormatException ex) {
//                horarioInicioTextField.clear();
//                minutosInicioTextField.clear();
//                horarioVencimientoTextField.clear();
//                minutosVencimientoTextField.clear();
//                return;
//            }
//
//            agregarTarea(new TareaConVencimiento(tituloField.getText(), descripcionField.getText(), fechaInicioPicker.getValue().atTime(horaInicio, minutosInicio), fechaVencimientoSinHoraPicker.getValue().atTime(horaVencimiento, minutosVencimiento)));
//        });
//
//        agregarEventoButton.setOnAction(e -> {
//
//        });
//
//        agregarTareaDiaCompletoButton.setOnAction(e -> {
//            String titulo = tituloField.getText();
//            String descripcion = descripcionField.getText();
//            LocalDate fechaInicio = fechaInicioPicker.getValue();
//            agregarTarea(new TareaDiaCompleto(titulo, descripcion, fechaInicio));
//        });
//
//        listaTareas = new ListView<>();
//        mostarListaTareas(listaTareas);
//
//
//        VBox layout = new VBox(10);
//        layout.setPadding(new Insets(10));
//        layout.getChildren().addAll(tituloLabel, tituloField, descripcionLabel, descripcionField, fechaInicioLabel, fechaInicioPicker, horarioInicioLabel, horarioInicioTextField, minutosInicioTextField, fechaVencimientoLabel, fechaVencimientoSinHoraPicker, horarioVencimientoLabel, horarioVencimientoTextField, minutosVencimientoTextField, agregarTareaButton, agregarEventoButton, agregarTareaDiaCompletoButton, listaTareas);
//
//        Scene scene = new Scene(layout, 300, 400);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void mostarListaTareas(ListView<Tarea> listaTareas){
//        listaTareas.setCellFactory(parametro -> new ListCell<>() {
//            @Override
//            protected void updateItem(Tarea tarea, boolean empty) {
//                super.updateItem(tarea, empty);
//                if (empty || tarea == null) {
//                    setText(null);
//                } else {
//                    setText("Tarea:" + "\n" + "Título: " + tarea.obtenerTitulo() + "\n" + "Descripción: " + tarea.obtenerDescripcion() + "\n" + "Fecha de Inicio: " + tarea.obtenerFechaInicio() + "\n" + "Fecha de Vencimiento: " + tarea.obtenerFechaVencimiento() + "\n" + "Esta completa:" + tarea.estaCompleta() + "\n" + "Es de dia completo: " + (tarea instanceof TareaDiaCompleto));
//                }
//            }
//        });
//
//        listaTareas.setOnMouseClicked(event -> {
//            Tarea tareaSeleccionada = listaTareas.getSelectionModel().getSelectedItem();
//            if (tareaSeleccionada != null) {
//                mostrarDetallesTarea(tareaSeleccionada);
//            }
//        });
//
//    }
//    private void agregarTarea(Tarea tarea) {
//        calendario.agregarTarea(tarea);
//        listaTareas.getItems().add(tarea);
//        limpiarCampos();
//    }
//
//
//    private void limpiarCampos() {
//
//        if (tituloField != null) {
//            tituloField.clear();
//        }
//        if (descripcionField != null) {
//            descripcionField.clear();
//        }
//        if (fechaInicioPicker != null) {
//            fechaInicioPicker.setValue(null);
//        }
//        horarioInicioTextField.clear();
//        minutosInicioTextField.clear();
//
//        if(fechaVencimientoSinHoraPicker != null){
//            fechaVencimientoSinHoraPicker = null;
//        }
//        horarioVencimientoTextField.clear();
//        minutosVencimientoTextField.clear();
//
//    }
//    private void mostrarDetallesTarea(Tarea tarea) {
//
//        Stage stage = new Stage();
//        stage.setTitle("Detalles de la Tarea");
//
//        VBox layout = new VBox(10);
//        layout.setPadding(new Insets(10));
//
//        Label tituloLabel = new Label("Título: " + tarea.obtenerTitulo());
//        Label descripcionLabel = new Label("Descripción: " + tarea.obtenerDescripcion());
//        Label fechaInicioLabel = new Label("Fecha de Inicio: " + tarea.obtenerFechaInicio());
//        Label fechaVencimientoLabel = new Label("Fecha de vencimiento: " + tarea.obtenerFechaVencimiento());
//        CheckBox estaCompletaCheckBox = new CheckBox("Completada");
//        estaCompletaCheckBox.setSelected(tarea.estaCompleta());
//        estaCompletaCheckBox.setOnAction(e -> tarea.marcarComoCompleta());
//        Button agregarAlarmaButton = new Button("Agregar alarma");
//        agregarAlarmaButton.setOnAction(e -> {
//
//        });
//        layout.getChildren().addAll(tituloLabel, descripcionLabel, fechaInicioLabel, fechaVencimientoLabel, estaCompletaCheckBox, agregarAlarmaButton);        Scene scene = new Scene(layout);
//        stage.setScene(scene);
//        stage.show();
//    }
//}