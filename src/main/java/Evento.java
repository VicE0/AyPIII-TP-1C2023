//import java.util.Date;
import java.time.LocalDateTime;

public class Evento
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String repetir;
    private int   frecuencia;


    public Evento() //Constructor default
    {
        this.titulo = "Evento sin titulo";
        this.descripcion = "-";
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = this.fechaInicio.plusDays(1);
        this.repetir = "Diario";
        this.frecuencia = 1;
    }

    public Evento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, String repetir, int frecuencia)
    {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.repetir = repetir;
        this.frecuencia = frecuencia;

    }
//GETTERS
    public String obtenerTitulo() {return titulo;}

    public String obtenerDescripcion() {return descripcion;}
    public LocalDateTime obtenerFechaInicio() {return fechaInicio;}
    public LocalDateTime obtenerFechaFin() {return fechaFin;}
    public String obtenerRepetir() {return repetir;}
    public int obtenerFrecuencia(){return frecuencia;}




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
    public void establecerRepetir(String repetir) {this.repetir = repetir;}
    public void establecerFrecuencia(int frecuencia) {this.frecuencia = frecuencia;}
}


