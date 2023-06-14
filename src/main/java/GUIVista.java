import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GUIVista {
    private GUIControlador controlador;


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

    private Button agregarAlarmaButton;
    private Label intervaloAlarmaLabel;
    private TextField intervaloAlarmaTextField;
    private Label fechaAlarmaLabel;
    private DatePicker fechaAlarma;
    private Label horasYMinutosLabel;
    private TextField horaInicioAlarmaTextField;
    private TextField minutosInicioAlarmaTextField;
    private Label tipoAlarmaLabel;
    private ChoiceBox <String> tipoAlarmaChoiceBox;



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

    private Label tipoEventoLabel;

    private ChoiceBox<String> tipoEventoChoiceBox;
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
        this.tipoEventoLabel = new Label("Tipo de evento");
        tipoEventoChoiceBox = new ChoiceBox<>();
        tipoEventoChoiceBox.getItems().addAll("Evento dia completo","Evento Diario", "Evento Semanal", "Evento Mensual", "Evento Anual");



        this.intervaloAlarmaLabel = new Label("Intervalo:");
        this.intervaloAlarmaTextField = new TextField();

        this.fechaAlarmaLabel = new Label("Fecha de alarma:");
        this.fechaAlarma = new DatePicker();

        this.tipoAlarmaLabel = new Label(("Tipo de efecto"));
        this.tipoAlarmaChoiceBox = new ChoiceBox<>();
        tipoAlarmaChoiceBox.getItems().addAll("Notificacion","Luz","Sonido");

        this.horasYMinutosLabel = new Label("Hora de alarma:");
        this.horaInicioAlarmaTextField = new TextField();
        this.minutosInicioAlarmaTextField = new TextField();


        this.agregarTareaButton = new Button("Agregar Tarea");
        this.agregarEventoButton = new Button("Agregar Evento");
        this.agregarTareaConVencimientoButton = new Button(("Agregar Tarea Con Vencimiento"));
        this.agregarTareaDiaCompletoButton = new Button("Agregar Tarea Día Completo");
        this.agregarEventoTerminadoButton = new Button("Agregar");
        this.agregarAlarmaButton = new Button("Agregar Alarma");


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
        ingresarEvento();
        return gridlayout(controlador.obtenerListaTareas(), controlador.obtenerListaEventos());
    }

    private void mostrarVentanaAgregarTarea() {
        Stage ventanaAgregarTarea = new Stage();
        ventanaAgregarTarea.setTitle("Agregar Tarea");
        VBox layout = new VBox(5);
        layout.setPadding(new Insets(5));

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
        VBox layout = new VBox(5);
        layout.setPadding(new Insets(5));


        layout.getChildren().addAll(tipoEventoLabel,tipoEventoChoiceBox,tituloLabel, tituloField, descripcionLabel, descripcionField, fechaInicioLabel,
                fechaInicioPicker, horarioInicioLabel, horarioInicioTextField, minutosInicioTextField,
                fechaFinLabel, fechaFinSinHoraPicker, horarioFinLabel, horarioFinTextField, minutosFinTextField, maxOcurrenciasPermitidasLabel,
                maxOcurrenciasPermitidasTextField,intervaloLabel, intervaloTextField,
                tipoRepeticionLabel, tipoRepeticionChoiceBox,agregarEventoTerminadoButton);

        Scene scene = new Scene(layout);
        ventanaAgregarEvento.setScene(scene);
        ventanaAgregarEvento.show();
    }
    public void mostrarVentanaAgregarAlarma(Tarea tarea,Evento evento){
        Stage ventanaAgregarEvento= new Stage();
        ventanaAgregarEvento.setTitle("Agregar Alarma");
        VBox layout = new VBox(5);
        layout.setPadding(new Insets(5));
        Button conIntervalo = new Button("Horario absoluto");
        conIntervalo.setOnAction(e-> {
            mostrarVentanaAgregarAlarmaFechaAbsouluta(tarea, null);
                });
        Button conHoraAbsoluto = new Button("Con Intervalo");
        conHoraAbsoluto.setOnAction(e->{
            mostrarVentanaAgregarAlarmaIntervalo(tarea,null);
        });
        layout.getChildren().addAll(conIntervalo,conHoraAbsoluto);
        Scene scene = new Scene(layout);
        ventanaAgregarEvento.setScene(scene);
        ventanaAgregarEvento.show();


    }


    public void mostrarVentanaAgregarAlarmaFechaAbsouluta(Tarea tarea, Evento evento){
        Stage ventanaAgregarEvento= new Stage();
        ventanaAgregarEvento.setTitle("Agregar Alarma");
        VBox layout = new VBox(5);
        layout.setPadding(new Insets(5));
        Button agregar = new Button("Agregar");
        Efecto efecto;
        if (tipoAlarmaChoiceBox.getValue() == "Notificacion"){
            efecto = new Notificacion();
        } else {
            efecto = null;
        }
        agregar.setOnAction(e -> {
            Alarma alarma = new AlarmaFechaAbsoluta(tarea.obtenerFechaInicio(), efecto);
            controlador.tareaAgregarAlarma(tarea,alarma);
        });
        layout.getChildren().addAll(tipoAlarmaLabel,tipoAlarmaChoiceBox,fechaAlarmaLabel,fechaAlarma,horasYMinutosLabel,horaInicioAlarmaTextField,minutosInicioAlarmaTextField,agregar);
        Scene scene = new Scene(layout);
        ventanaAgregarEvento.setScene(scene);
        ventanaAgregarEvento.show();
    }

    public void mostrarVentanaAgregarAlarmaIntervalo(Tarea tarea, Evento evento){
        Stage ventanaAgregarEvento= new Stage();
        ventanaAgregarEvento.setTitle("Agregar Alarma");
        VBox layout = new VBox(5);
        layout.setPadding(new Insets(5));
        Button agregar = new Button("Agregar");
        int intervalo = 0;
        try {
            intervalo = Integer.parseInt(intervaloTextField.getText());
        }catch (NumberFormatException e){
            intervaloAlarmaTextField.clear();
        }
        Efecto efecto;
        if (tipoAlarmaChoiceBox.getValue() == "Notificacion"){
            efecto = new Notificacion();
        } else {
            efecto = null;
        }
        int finalIntervalo = intervalo;
        agregar.setOnAction(e -> {
            Alarma alarma = new AlarmaIntervalo(tarea.obtenerFechaInicio(), finalIntervalo,efecto);
            controlador.tareaAgregarAlarma(tarea,alarma);
        });

        layout.getChildren().addAll(tipoAlarmaLabel,tipoAlarmaChoiceBox,intervaloAlarmaLabel,intervaloAlarmaTextField,agregar);
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
        Evento eventoDiario = controlador.obtenerObjetoEventoDiario();
        agregarEventoTerminadoButton.setOnAction(e ->{
            String titulo = tituloField.getText();
            if (titulo.isEmpty()){
                titulo = eventoDiario.obtenerTitulo();
            }

            String descripcion = descripcionField.getText();
            if (descripcion.isEmpty()){
                descripcion = eventoDiario.obtenerDescripcion();
            }
            String horarioInicioText = horarioInicioTextField.getText();
//            if (horarioInicioText.isEmpty()){
//                horarioInicioText = Integer.toString(tareaConVencimeitno.obtenerFechaInicio().getHour());
//            }

            String minutosInicioText = minutosInicioTextField.getText();
//            if (minutosInicioText.isEmpty()){
//                minutosInicioText = Integer.toString(tareaConVencimeitno.obtenerFechaInicio().getMinute());
//            }

            String horarioFinText = horarioFinTextField.getText();
//            if (horarioVencimientoText.isEmpty()){
//                horarioVencimientoText = Integer.toString(tareaConVencimeitno.obtenerFechaVencimiento().getHour());
//            }

            String minutosFinText = minutosFinTextField.getText();
//            if (minutosVencimientoText.isEmpty()){
//                minutosVencimientoText = Integer.toString(tareaConVencimeitno.obtenerFechaVencimiento().getMinute());
//            }

            int horaInicio;
            int minutosInicio;
            int horaFin;
            int minutosFin;

            try {
                horaInicio = Integer.parseInt(horarioInicioText);
                minutosInicio = Integer.parseInt(minutosInicioText);
                horaFin = Integer.parseInt(horarioFinText);
                minutosFin = Integer.parseInt(minutosFinText);

            } catch (NumberFormatException ex) {
                horarioInicioTextField.clear();
                minutosInicioTextField.clear();
                horarioVencimientoTextField.clear();
                minutosVencimientoTextField.clear();
                return;
            }
            int ocurrencias = Integer.parseInt(maxOcurrenciasPermitidasTextField.getText());
            Repeticion repeticiones = tipoRepeticionChoiceBox.getValue();
            int intervalo = Integer.parseInt(intervaloTextField.getText());
            String tipo = tipoEventoChoiceBox.getValue();
            if (tipo == "Evento dia completo"){
                ConstructorEventos eventoDiaCompletoConstruido = new ConstructorEventosDiaCompleto(titulo, descripcion, fechaInicioPicker.getValue().atTime(horaInicio, minutosInicio), fechaFinSinHoraPicker.getValue().atTime(horaFin, minutosFin), intervalo,repeticiones);
                controlador.agregarEvento(eventoDiaCompletoConstruido);
            }
            if (tipo == "Evento Diario") {
                ConstructorEventos eventoDiarioConstruido = new ConstructorEventosDiarios(titulo, descripcion, fechaInicioPicker.getValue().atTime(horaInicio, minutosInicio), fechaFinSinHoraPicker.getValue().atTime(horaFin, minutosFin), ocurrencias, repeticiones, intervalo);

                controlador.agregarEvento(eventoDiarioConstruido);
            }
            if (tipo == "Evento Semanal"){
                ConstructorEventos eventoSemanalConstruido = new ConstructorEventosSemanales(titulo,descripcion,fechaInicioPicker.getValue().atTime(horaInicio,minutosInicio),fechaFinSinHoraPicker.getValue().atTime(horaFin,minutosFin),ocurrencias,repeticiones, Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY));
                controlador.agregarEvento(eventoSemanalConstruido);
            }
            if (tipo == "Evento Mensual"){
                ConstructorEventos eventoMensualConstruido = new ConstructorEventosMensuales(titulo, descripcion, fechaInicioPicker.getValue().atTime(horaInicio, minutosInicio), fechaFinSinHoraPicker.getValue().atTime(horaFin, minutosFin), ocurrencias, repeticiones, intervalo);
                controlador.agregarEvento(eventoMensualConstruido);
            }
            if (tipo == "Evento Anual"){
                ConstructorEventos eventoAnualConstruido = new ConstructorEventosAnuales(titulo, descripcion, fechaInicioPicker.getValue().atTime(horaInicio, minutosInicio), fechaFinSinHoraPicker.getValue().atTime(horaFin, minutosFin), ocurrencias, repeticiones, intervalo);
                controlador.agregarEvento(eventoAnualConstruido);
            }
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
                            "Es de dia completo: " + (tarea.getClass()) + "\n" +
                            "Alamas:" + tarea.obtenerAlarmas() + "\n" ) ;
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
    public Scene gridlayout(ListView<Tarea> listaTareas, ListView<Evento> listEventos) {

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        layout.getChildren().addAll(agregarTareaButton,agregarEventoButton,listaTareas, listEventos);


        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
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
        Label alarmas = new Label("Alarmas" + tarea.obtenerAlarmas());


        estaCompletaCheckBox.setOnAction(e -> tarea.marcarComoCompleta());
        agregarAlarmaButton.setOnAction(e ->{
            mostrarVentanaAgregarAlarma(tarea,null);
        });

        layout.getChildren().addAll(tituloLabel, descripcionLabel, fechaInicioLabel, fechaVencimientoLabel,
                estaCompletaCheckBox,alarmas,agregarAlarmaButton);

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