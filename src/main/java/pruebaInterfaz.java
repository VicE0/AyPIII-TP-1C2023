import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


//LINEA 46 EN POM.XML: CAMBIAR LA CLASE QUE EJECUTA "MAINCLASS" POR LA DEFINITIVA, ESTA SOLO ES DE PRUEBA
public class pruebaInterfaz extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var label = new Label("aHI Hola mundo!");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();

    }


}
