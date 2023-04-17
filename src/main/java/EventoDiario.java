import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EventoDiario extends Evento {
    private int intervalo;

    public EventoDiario(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int intervalo) {
        super(titulo, descripcion, fechaInicio, fechaFin);
        this.intervalo = intervalo;
    }

    public int obtenerIntervalo() {
        return intervalo;
    }

    public void establecerIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    @Override
    public LocalDate obtenerSiguienteOcurrencia(LocalDate fecha) {
        if (intervalo > 1) {
            fecha = fecha.plusDays(intervalo - 1);
        }
        return fecha.plusDays(1);
    }
}







//    LO DEJO POR AHI POR SI SE USA DESPUES
//    public ArrayList<LocalDateTime> obtenerProximosEventos(LocalDateTime Inicio, LocalDateTime Fin) {
//
//        ArrayList<LocalDateTime> proximosEventos = new ArrayList<>();
//
//        // Añade la primera fecha ingresada
//        proximosEventos.add(Inicio);
//
//        LocalDateTime proximaFecha = obtenerProximoInicio(Inicio);
//
//        if (proximaFecha.isBefore(Fin)) {
//            proximosEventos.add(proximaFecha);
//        }
//
//        // Añade todas las demas hata llegar a la fecha Fin
//       while(proximaFecha.isBefore(Fin)) {
//            proximaFecha = obtenerProximoInicio(proximaFecha);
//            proximosEventos.add(proximaFecha);
//        }
//
//       return proximosEventos;
//    }
//}


