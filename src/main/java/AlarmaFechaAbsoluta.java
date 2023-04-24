import java.time.LocalDateTime;

public class AlarmaFechaAbsoluta extends Alarma{
    private  LocalDateTime fechaYHora;
   public AlarmaFechaAbsoluta(LocalDateTime fechaYHora, Efecto efecto) {
       super(efecto);

       this.fechaYHora = fechaYHora;
   }
   public void establecerFechaYHora(LocalDateTime fechaYHora){
       this.fechaYHora = fechaYHora;
   }
   public LocalDateTime obtenerFechaYHora(){
       return fechaYHora;
   }


}
