import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) {

        GridPane calendarioGrid = new GridPane();
        calendarioGrid.setHgap(50);
        calendarioGrid.setVgap(40);

        Label mesAnioActualLabel = new Label();

        Calendario calendario = new Calendario();

        GUIVista vista = new GUIVista(calendarioGrid, mesAnioActualLabel);
        GUIControlador controlador = new GUIControlador(calendario, vista, calendarioGrid, mesAnioActualLabel);


        controlador.registrarObservador(vista);

        //Lee desde el archivo
        controlador.iniciarAplicacion();

        vista.setControlador(controlador);

        Stage interfazCalendario= new Stage();
        controlador.crearCalendario();

        vista.mostrarCalendarioCompleto(interfazCalendario, controlador.obtenerListaTareas(), controlador.obtenerListaEventos());

        //Cuando se cierra, guarda todo en el archivo
        primaryStage.setOnCloseRequest(event -> controlador.cerrarAplicacion());


    }


    //Logica implementada en el calendario completo, funciona, pero queda algo desprolijo
//        Stage interfazIngresoDatos = new Stage();
//        interfazIngresoDatos.setTitle("Ventana de ingreso de datos");
//        interfazIngresoDatos.setScene(vista.Escena());
//        interfazIngresoDatos.show();
}