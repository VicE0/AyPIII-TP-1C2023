import java.time.LocalDateTime;

public class TareaConVencimiento extends Tarea{
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaVencimiento;

    public TareaConVencimiento(){
        super("Tarea sin titulo","");
        this.fechaInicio = LocalDateTime.now();
        this.fechaVencimiento = this.fechaInicio.plusDays(1);

    }
    public TareaConVencimiento(String titulo, String descripcion,LocalDateTime fechaInicio, LocalDateTime fechaVencimiento){
        super(titulo,descripcion);
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;

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