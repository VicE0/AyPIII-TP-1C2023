import java.time.LocalDateTime;

public class Evento
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int repetirCada;


    public Evento() //Constructor default
    {
        this.titulo = "Evento sin titulo";
        this.descripcion = "-";
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = this.fechaInicio.plusDays(1);
        this.repetirCada = 0;

    }

    public Evento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin)
    {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        //RepetirCada deberia ir aca, estoy probando aun y lo voy a agregar al final. Por ahora solo lo seteo en main/tests :)
    }

//GETTERS
    public String obtenerTitulo() {return titulo;}

    public String obtenerDescripcion() {return descripcion;}
    public LocalDateTime obtenerFechaInicio() {return fechaInicio;}
    public LocalDateTime obtenerFechaFin() {return fechaFin;}
    public int obtenerRepetirCada(){return repetirCada;}





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
    public void establecerRepetirCada(int repetirCada){this.repetirCada = repetirCada;}
}


