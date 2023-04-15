import java.time.LocalDateTime;

public class Evento
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;


    //Constructor default con los valores iniciales
    //Por Default, el evento será de dia completo
    public Evento(){
        this.titulo = "Evento sin titulo";
        this.descripcion = "-";
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = this.fechaInicio.plusDays(1);
    }

    // PRE: Pido los datos necesarios para la creación de un evento
    // POS: Inicializo los valores correctos del evento con los datos disponibles
    public Evento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;

    }

//GETTERS
    public String obtenerTitulo() {return titulo;}
    public String obtenerDescripcion() {return descripcion;}
    public LocalDateTime obtenerFechaInicio() {return fechaInicio;}
    public LocalDateTime obtenerFechaFin() {return fechaFin;}



//SETTERS
    public void establecerTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void establecerDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void establecerFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void establecerFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}


