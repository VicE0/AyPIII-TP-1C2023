import java.time.LocalDateTime;
public abstract class Alarma {
    private final Efecto efecto;
    public abstract LocalDateTime obtenerFechaYHora();

    public Alarma(Efecto efecto){
        this.efecto = efecto;

    }

    public void activarAlarma(){
        boolean encontrada = false;
        while (!encontrada){
            LocalDateTime fechaActual = LocalDateTime.now();
            if (fechaActual.equals(obtenerFechaYHora())){
                sonarAlarma(this.efecto);
                encontrada = true;
            }
        }
    }
    public void sonarAlarma(Efecto efecto){
        efecto.reproducirEfecto();

    }
    public Efecto obtenerEfecto(){
        return efecto;
    }
}
