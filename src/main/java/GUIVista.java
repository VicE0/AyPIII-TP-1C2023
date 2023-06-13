import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUIVista {
    private GUIControlador controlador;

    private CreadorDeEventos eventoDiarioCreado;
    private CreadorDeEventos eventoSemanalCreado;
    private CreadorDeEventos eventoMensualCreado;
    private CreadorDeEventos eventoAnualCreado;
    private CreadorDeEventos eventoDiaCompletoCreado;


    private GridPane calendarioGrid;
    private Label mesAnioActualLabel;
    private Stage primaryStage;

    private Label tituloLabel;
    private Label descripcionLabel;
    private Label fechaInicioLabel;
    private Label horarioInicioLabel;
    private Label fechaVencimientoLabel;
    private Label fechaFinLabel;
    private Label horarioFinLabel;
    private Label horarioVencimientoLabel;
    private Label maxOcurrenciasPermitidasLabel;
    private Label intervaloLabel;

    private Label tipoRepeticionLabel;

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
    private TextField maxOcurrenciasPermitidasTextField;
    private TextField intervaloTextField;
    private TextField diasSemanaRepeticionTextField;

    private DatePicker fechaInicioPicker;
    private DatePicker fechaVencimientoSinHoraPicker;
    private DatePicker fechaFinSinHoraPicker;

    private ChoiceBox<Repeticion> tipoRepeticionChoiceBox;



    public GUIVista(Stage primaryStage, GridPane calendarioGrid, Label mesAnioActualLabel) {

        this.primaryStage = primaryStage;
        this.calendarioGrid = calendarioGrid;
        this.mesAnioActualLabel = mesAnioActualLabel;

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

        this.maxOcurrenciasPermitidasLabel = new Label("Maximas ocurrencias del evento");
        maxOcurrenciasPermitidasTextField = new TextField();

        this.intervaloLabel = new Label("Intervalo ");
        intervaloTextField = new TextField();

        this.tipoRepeticionLabel = new Label("Tipo de repetición:");
        this.tipoRepeticionChoiceBox = new ChoiceBox<>();
        tipoRepeticionChoiceBox.getItems().addAll(Repeticion.values());

        this.agregarTareaButton = new Button("Agregar Tarea");
        this.agregarEventoButton = new Button("Agregar Evento");
        this.agregarTareaConVencimientoButton = new Button(("Agregar Tarea Con Vencimiento"));
        this.agregarTareaDiaCompletoButton = new Button("Agregar Tarea Día Completo");
        this.agregarEventoTerminadoButton = new Button("Agregar");


        this.eventoDiarioCreado      = new CreadorEventosDiarios();
        this.eventoSemanalCreado     = new CreadorEventosSemanales();
        this.eventoMensualCreado     = new CreadorEventosMensuales();
        this.eventoAnualCreado       = new CreadorEventosAnuales();
        this.eventoDiaCompletoCreado = new CreadorEventosDiaCompleto();

    }

    public void setControlador(GUIControlador controlador) {
        this.controlador = controlador;
    }


    public void mostrarCalendarioCompleto(Stage primaryStage) {

        HBox headerBox = new HBox(10);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(10));

        Button mesAnteriorBoton = new Button("<<");
        mesAnteriorBoton.setOnAction(e -> controlador.mostrarMesAnterior());

        Button mesSiguienteBoton = new Button(">>");
        mesSiguienteBoton.setOnAction(e -> controlador.mostrarMesSiguiente());

        mesAnioActualLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        controlador.actualizarLabel();

        headerBox.getChildren().addAll(mesAnteriorBoton, mesAnioActualLabel, mesSiguienteBoton);

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);
        root.add(headerBox, 0, 0);
        root.add(calendarioGrid, 0, 1);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calendario");
        primaryStage.show();
    }


    public Scene Escena() {
        agregarTareaButton.setOnAction(e -> mostrarVentanaAgregarTarea());
        agregarEventoButton.setOnAction(e -> mostrarVentanaAgregarEvento());
        ingresarTareaConVencimiento();
        ingresarTareaDiaCompleto();
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
                fechaFinLabel, fechaFinSinHoraPicker, horarioFinLabel, horarioFinTextField, minutosFinTextField, maxOcurrenciasPermitidasLabel,
                maxOcurrenciasPermitidasTextField,intervaloLabel, intervaloTextField,
                tipoRepeticionLabel, tipoRepeticionChoiceBox,agregarEventoTerminadoButton);

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
    public void ingresarEventoDiario(){
        agregarEventoTerminadoButton.setOnAction(e ->{
            String titulo = tituloField.getText();
            String descripcion = descripcionField.getText();
//            LocalDate fechaInicio = fechaInicioPicker.getValue();
//            LocalDate fechaFin = fechaFinSinHoraPicker.getValue();
//
//            ConstructorEventos eventoDiarioConstruido = new ConstructorEventosDiarios(titulo,descripcion, fechaInicio, fechaFin, );
//            Evento eventoDiario = eventoDiarioCreado.crearEvento(eventoDiarioConstruido);

//En los tests se encuentra la logica para agregar los eventos, el problema esta que la fecha de inicio picker y la fecha fin picker funcionan con LocalDate
// No me hago una idea de como agregar correctamente el paramtero :[
//Se deberia hacer como las tareas que se puede agregar manualmente el evento diario / semanal /mensual etc, segun la cantidad de clases de eventos que hay
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