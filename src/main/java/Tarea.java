import java.time.LocalDateTime;

public class Tarea
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaVencimiento;
    private Boolean estaCompleta;



    public Tarea(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaVencimiento, Boolean tareaCompleta)
    {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.estaCompleta = tareaCompleta;
    }


    public String obtenerTitulo() {
        return titulo;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }
    public LocalDateTime obtenerFechaInicio() {
        return fechaInicio;
    }
    public LocalDateTime obtenerFechaVencimiento(){
        return fechaVencimiento;
    }
    public Boolean estaCompleta(){
        return estaCompleta;
    }


    public void establecerTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void establecerDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void establecerFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void establecerRepetirCada(Boolean estaCompleta){
        this.estaCompleta = estaCompleta;
    }
}

