import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "Tipo de alarma")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AlarmaIntervalo.class, name = "alarmaIntervalo"),
        @JsonSubTypes.Type(value = AlarmaFechaAbsoluta.class, name = "alarmaFechaAbsoluta")
})
public abstract class Alarma {
    @JsonProperty("efecto")
    private Efecto efecto;
    public abstract void establecerFechaYHora(LocalDateTime fechaYHora);

    public abstract LocalDateTime obtenerFechaYHora();

    public Alarma(){

    }
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
