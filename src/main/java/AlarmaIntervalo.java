import java.time.LocalDateTime;
import java.time.LocalDate;

public class AlarmaIntervalo extends Alarma{
    private  LocalDateTime fechaYHora;
    public AlarmaIntervalo(LocalDateTime fechaYHora, int intervalo, Efecto efecto) {
        super(efecto);
        this.fechaYHora = calcularIntervalo(fechaYHora,intervalo);
    }
    public void establecerFechaYHora(LocalDateTime fechaYHora){
        this.fechaYHora = fechaYHora;
    }
    public LocalDateTime obtenerFechaYHora(){
        return fechaYHora;
    }

    public LocalDateTime calcularIntervalo(LocalDateTime fechaYHora, int intervalo){
        int anio = fechaYHora.getYear();
        int mes = fechaYHora.getMonthValue();
        int dia = fechaYHora.getDayOfMonth();
        int horas = fechaYHora.getHour();
        int minutos = fechaYHora.getMinute();

        int minutosTotales = horas*60 + minutos;
        minutosTotales -= intervalo;
        if (minutosTotales < 0) {
            minutosTotales += 24 * 60;
        }
        int horaNueva = minutosTotales/60;
        int minutosNuevos = minutosTotales % 60;

        int diaNuevo = dia;

        if (horaNueva > horas){
            diaNuevo --;
            if (diaNuevo<=0){
                mes --;
                if (mes<=0){
                    mes = 12;
                    anio --;
                }
                int cantidadDeDias = LocalDate.of(anio, mes, 1).lengthOfMonth();
                diaNuevo = cantidadDeDias + diaNuevo;
            }

        }

        return (LocalDateTime.of(anio, mes, diaNuevo, horaNueva, minutosNuevos));

    }



}
