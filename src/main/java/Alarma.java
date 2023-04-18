import java.time.LocalDateTime;
public class Alarma {
    private LocalDateTime fechaYHora;
    private Efecto efecto;

    public Alarma(LocalDateTime fechaYHora, String efecto){
        this.fechaYHora = fechaYHora;
        this.efecto = efecto;

    }
    public void sonarAlarma(Efecto efecto){
        efecto.reproducir();

    }
}
