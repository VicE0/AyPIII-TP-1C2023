//import java.lang.reflect.Array;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//
//
//
//
//        CreadorEventosDiarios creadorDeEventosDiarios = new CreadorEventosDiarios();
//        Calendario eventosDiariosLista = new Calendario(creadorDeEventosDiarios);
//
//        String titulo = "Evento Diario";
//        String descripcion = "Evento que ingresa un intervalo de dias nulo";
//
//        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 10, 9, 0);
//        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 17, 9, 30);
//        //Estos dos atributos son testeados correctamento en los siguientes test
//        int maxOcurrencias = 10;
//
//        Repeticion tipoRepeticion = Repeticion.HASTA_OCURRENCIAS;
//
//        var eventoDiatio = new EventoDiario();
//        eventoDiatio.establecerIntervalo(2);
//        int intervalo = eventoDiatio.obtenerIntervalo();
//
//        eventosDiariosLista.crearEvento(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion,intervalo);
//
//
//        List<LocalDateTime> lista = eventosDiariosLista.proximosEventosDiarios(titulo, descripcion, fechaInicio,fechaFin,maxOcurrencias,tipoRepeticion, intervalo);
//
//
//        for (LocalDateTime A : lista){
//            System.out.println(A.toLocalDate());
//
//        }
//
//    }
//}