//import java.util.Date;
import java.time.LocalDateTime;

public class Evento
{
    private String titulo;
    private String descripcion;
    private LocalDateTime    fechaInicio;
    private LocalDateTime    fechaFin;

    public Evento()
    {
        this.titulo = "Evento sin titulo";
        this.descripcion = "-";
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = this.fechaInicio.plusDays(1);

        //Si no tengo titulo/descripcion, las pruebas no se pasan
        //this.fechaInicio = LocalDateTime.of(-999999999,1,1,0,0 ); //Chequear
        //this.fechaFin = LocalDateTime.of(999999999,12,31,23,59 ); //El evento puede terminar el mismo dia
    }

    public Evento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin)
    {
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
