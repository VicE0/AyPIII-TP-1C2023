import java.time.LocalDateTime;
public class Alarma {
    private LocalDateTime fechaYHora;
    private Efecto efecto;

    public Alarma(LocalDateTime fechaYHora, Efecto efecto){
        this.fechaYHora = fechaYHora;
        this.efecto = efecto;

    }

    public void activarAlarma(){
        boolean encontrada = false;
        while (encontrada == false){
            LocalDateTime fechaActual = LocalDateTime.now();
            if (fechaActual.equals(fechaYHora)){
                sonarAlarma(this.efecto);
                encontrada = true;
            }
        }
    }
    public void sonarAlarma(Efecto efecto){
        efecto.reproducirEfecto();

    }
}
