import java.time.LocalDate;
import java.time.LocalDateTime;

public class TareaDiaCompleto extends Tarea {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaVencimiento;

    public TareaDiaCompleto(LocalDate fechaInicio) {
        super("Tarea sin titulo", "");
        this.fechaInicio = fechaInicio.atTime(0,0,0);
        this.fechaVencimiento = this.fechaInicio.plusDays(1);
    }

    public TareaDiaCompleto(String titulo, String descripcion, LocalDate fechaInicio){
        super(titulo,descripcion);
        this.fechaInicio = fechaInicio.atTime(0,0,0);
        this.fechaVencimiento = fechaInicio.atTime(23,59,59);
    }


    public LocalDateTime obtenerFechaInicio() {
        return fechaInicio;
    }
    public LocalDateTime obtenerFechaVencimiento(){
        return fechaVencimiento;
    }
    public void establecerFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void establecerFechaVencimiento(LocalDateTime fechaVencimiento){
        this.fechaVencimiento = fechaVencimiento;
    }

}
