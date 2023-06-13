import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUIVista {
    private GUIControlador controlador;

    private Stage primaryStage;
    private Label tituloLabel;
    private Label descripcionLabel;
    private Label fechaInicioLabel;
    private Label horarioInicioLabel;

    private Label fechaVencimientoLabel;

    private Label fechaFinLabel;
    private Label horarioFinLabel;
    private Label horarioVencimientoLabel;
    private Button agregarTareaButton;
    private Button agregarTareaConVencimientoButton;
    private Button agregarEventoButton;

    private Button agregarEventoTerminadoButton;
    private Button agregarTareaDiaCompletoButton;

    private TextField tituloField;
    private TextField descripcionField;
    private TextField horarioInicioTextField;
    private TextField minutosInicioTextField;
    private TextField horarioVencimientoTextField;

    private TextField minutosVencimientoTextField;
    private TextField horarioFinTextField;
    private TextField minutosFinTextField;

    private DatePicker fechaInicioPicker;
    private DatePicker fechaVencimientoSinHoraPicker;
    private DatePicker fechaFinSinHoraPicker;


    public GUIVista(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.tituloLabel = new Label("Título:");
        tituloField = new TextField();

        this.descripcionLabel = new Label("Descripción:");
        descripcionField = new TextField();

        this.fechaInicioLabel = new Label("Fecha de Inicio:");
        fechaInicioPicker = new DatePicker();

        this.horarioInicioLabel = new Label("Horario (Incluye horas y minutos):");
        horarioInicioTextField = new TextField();
        minutosInicioTextField = new TextField();

        this.fechaFinLabel = new Label("Fecha de finalizacion del evento:");
        fechaFinSinHoraPicker = new DatePicker();

        this.horarioFinLabel = new Label("Horario (Incluye horas y minutos):");
        horarioFinTextField = new TextField();
        minutosFinTextField = new TextField();

        this.fechaVencimientoLabel = new Label("Fecha de Vencimiento:");
        fechaVencimientoSinHoraPicker = new DatePicker();

        this.horarioVencimientoLabel = new Label("Horario (Incluye horas y minutos):");
        horarioVencimientoTextField = new TextField();
        minutosVencimientoTextField = new TextField();

        this.agregarTareaButton = new Button("Agregar Tarea");
        this.agregarEventoButton = new Button("Agregar Evento");
        this.agregarTareaConVencimientoButton = new Button(("Agregar Tarea Con Vencimiento"));
        this.agregarTareaDiaCompletoButton = new Button("Agregar Tarea Día Completo");
        this.agregarEventoTerminadoButton = new Button("Agregar");


    }

    public void setControlador(GUIControlador controlador) {
        this.controlador = controlador;
    }




    public Scene Escena() {
        agregarTareaButton.setOnAction(e -> mostrarVentanaAgregarTarea());
        agregarEventoButton.setOnAction(e -> mostrarVentanaAgregarEvento());
        ingresarTareaConVencimiento();
        ingresarTareaDiaCompleto();
        ingresarEvento();
        return gridlayout(controlador.obtenerListaTareas());
    }

    private void mostrarVentanaAgregarTarea() {
        Stage ventanaAgregarTarea = new Stage();
        ventanaAgregarTarea.setTitle("Agregar Tarea");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        layout.getChildren().addAll(tituloLabel, tituloField, descripcionLabel, descripcionField, fechaInicioLabel,
                fechaInicioPicker, horarioInicioLabel, horarioInicioTextField, minutosInicioTextField,
                fechaVencimientoLabel, fechaVencimientoSinHoraPicker, horarioVencimientoLabel,
                horarioVencimientoTextField, minutosVencimientoTextField, agregarTareaConVencimientoButton,agregarTareaDiaCompletoButton);

        Scene scene = new Scene(layout);
        ventanaAgregarTarea.setScene(scene);
        ventanaAgregarTarea.show();
    }

    public void mostrarVentanaAgregarEvento(){
        Stage ventanaAgregarEvento= new Stage();
        ventanaAgregarEvento.setTitle("Agregar Evento");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        layout.getChildren().addAll(tituloLabel, tituloField, descripcionLabel, descripcionField, fechaInicioLabel,
                fechaInicioPicker, horarioInicioLabel, horarioInicioTextField, minutosInicioTextField,
                fechaFinLabel, fechaFinSinHoraPicker, horarioFinLabel,
                horarioFinTextField, minutosFinTextField, agregarEventoTerminadoButton);

        Scene scene = new Scene(layout);
        ventanaAgregarEvento.setScene(scene);
        ventanaAgregarEvento.show();
    }



    public void ingresarTareaConVencimiento() {

        Tarea tareaConVencimeitno = controlador.obtenerObjetoTareaConVencimiento();

        agregarTareaConVencimientoButton.setOnAction(e -> {

            String titulo = tituloField.getText();
            if (titulo.isEmpty()) {
                titulo = tareaConVencimeitno.obtenerTitulo();
            }

            String descripcion = descripcionField.getText();
            if (descripcion.isEmpty()) {
                descripcion = tareaConVencimeitno.obtenerDescripcion();
            }


            String horarioInicioText = horarioInicioTextField.getText();
//            if (horarioInicioText.isEmpty()){
//                horarioInicioText = Integer.toString(tareaConVencimeitno.obtenerFechaInicio().getHour());
//            }

            String minutosInicioText = minutosInicioTextField.getText();
//            if (minutosInicioText.isEmpty()){
//                minutosInicioText = Integer.toString(tareaConVencimeitno.obtenerFechaInicio().getMinute());
//            }

            String horarioVencimientoText = horarioVencimientoTextField.getText();
//            if (horarioVencimientoText.isEmpty()){
//                horarioVencimientoText = Integer.toString(tareaConVencimeitno.obtenerFechaVencimiento().getHour());
//            }

            String minutosVencimientoText = minutosVencimientoTextField.getText();
//            if (minutosVencimientoText.isEmpty()){
//                minutosVencimientoText = Integer.toString(tareaConVencimeitno.obtenerFechaVencimiento().getMinute());
//            }

            int horaInicio;
            int minutosInicio;
            int horaVencimiento;
            int minutosVencimiento;

            try {
                horaInicio = Integer.parseInt(horarioInicioText);
                minutosInicio = Integer.parseInt(minutosInicioText);
                horaVencimiento = Integer.parseInt(horarioVencimientoText);
                minutosVencimiento = Integer.parseInt(minutosVencimientoText);

            } catch (NumberFormatException ex) {
                horarioInicioTextField.clear();
                minutosInicioTextField.clear();
                horarioVencimientoTextField.clear();
                minutosVencimientoTextField.clear();
                return;
            }

            controlador.agregarTarea(new TareaConVencimiento(titulo, descripcion, fechaInicioPicker.getValue().atTime(horaInicio, minutosInicio), fechaVencimientoSinHoraPicker.getValue().atTime(horaVencimiento, minutosVencimiento)));
            limpiarCampos();
        });

    }

    public void ingresarTareaDiaCompleto() {

        Tarea tareaDiaCompleto = controlador.obtenerObjetoTareaDiaCompleto();

        agregarTareaDiaCompletoButton.setOnAction(e -> {

                    String titulo = tituloField.getText();
                    if (titulo.isEmpty()) {
                        titulo = tareaDiaCompleto.obtenerTitulo();
                    }

                    String descripcion = descripcionField.getText();
                    if (descripcion.isEmpty()) {
                        descripcion = tareaDiaCompleto.obtenerDescripcion();
                    }

                    LocalDate fechaInicio = fechaInicioPicker.getValue();
                    controlador.agregarTarea(new TareaDiaCompleto(titulo, descripcion, fechaInicio));
                limpiarCampos();
                });

    }
    public void ingresarEvento(){
        agregarEventoTerminadoButton.setOnAction(e ->{
            String titulo = tituloField.getText();
            String descripcion = descripcionField.getText();

        });
    }

    //GRID
    public void mostarListaTareas(ArrayList<Tarea> tareasCalendario) {

        ListView<Tarea> listaTareas = controlador.obtenerListaTareas();

        listaTareas.setItems(FXCollections.observableArrayList(tareasCalendario));

        listaTareas.setCellFactory(parametro -> new ListCell<>() {
            @Override
            protected void updateItem(Tarea tarea, boolean empty) {
                super.updateItem(tarea, empty);
                if (empty || tarea == null) {
                    setText(null);
                } else {
                    setText("Tarea:" + "\n" + "Título: " + tarea.obtenerTitulo() + "\n" +
                            "Descripción: " + tarea.obtenerDescripcion() + "\n" +
                            "Fecha de Inicio: " + tarea.obtenerFechaInicio() + "\n" +
                            "Fecha de Vencimiento: " + tarea.obtenerFechaVencimiento() + "\n" +
                            "Esta completa:" + tarea.estaCompleta() + "\n" +
                            "Es de dia completo: " + (tarea.getClass()));
                }
            }
        });

        listaTareas.setOnMouseClicked(event -> {
            Tarea tareaSeleccionada = listaTareas.getSelectionModel().getSelectedItem();
            if (tareaSeleccionada != null) {
                mostrarDetallesTarea(tareaSeleccionada);
            }

        });
    }


    //GRID / LISTA
    public Scene gridlayout(ListView<Tarea> listaTareas) {

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        layout.getChildren().addAll(agregarTareaButton,agregarEventoButton,listaTareas);


        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }


    public Button agregarAlarma() {

        Button agregarAlarmaButton = new Button("Agregar alarma");
        agregarAlarmaButton.setOnAction(e -> {

            //Agregar funcionabilidad
        });
        return agregarAlarmaButton;
    }


    //POP-UP
    public void mostrarDetallesTarea(Tarea tarea) {
        Stage stage = new Stage();
        stage.setTitle("Detalles de la Tarea");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label tituloLabel = new Label("Título: " + tarea.obtenerTitulo());
        Label descripcionLabel = new Label("Descripción: " + tarea.obtenerDescripcion());
        Label fechaInicioLabel = new Label("Fecha de Inicio: " + tarea.obtenerFechaInicio());
        Label fechaVencimientoLabel = new Label("Fecha de vencimiento: " + tarea.obtenerFechaVencimiento());
        CheckBox estaCompletaCheckBox = new CheckBox("Completada");
        estaCompletaCheckBox.setSelected(tarea.estaCompleta());

        estaCompletaCheckBox.setOnAction(e -> tarea.marcarComoCompleta());


        layout.getChildren().addAll(tituloLabel, descripcionLabel, fechaInicioLabel, fechaVencimientoLabel,
                estaCompletaCheckBox, agregarAlarma());

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }


    public void limpiarCampos() {
        List<TextField> camposTexto = Arrays.asList(tituloField, descripcionField, horarioInicioTextField, minutosInicioTextField, horarioVencimientoTextField, minutosVencimientoTextField);
        camposTexto.forEach(TextField::clear);

        if (fechaInicioPicker != null) {
            fechaInicioPicker.setValue(null);
        }

        if (fechaVencimientoSinHoraPicker != null) {
            fechaVencimientoSinHoraPicker.setValue(null);
        }
    }
}