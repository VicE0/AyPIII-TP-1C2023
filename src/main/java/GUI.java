import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) {

        GridPane calendarioGrid = new GridPane();
        calendarioGrid.setHgap(10);
        calendarioGrid.setVgap(10);

        Label mesAnioActualLabel = new Label();

        Calendario calendario = new Calendario();
        GUIVista vista = new GUIVista(primaryStage, calendarioGrid, mesAnioActualLabel);
        GUIControlador controlador = new GUIControlador(calendario, vista, calendarioGrid, mesAnioActualLabel);

        vista.setControlador(controlador);

        controlador.crearCalendario();
        vista.mostrarCalendarioCompleto(primaryStage);

//        primaryStage.setScene(vista.Escena());
//        primaryStage.show();

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