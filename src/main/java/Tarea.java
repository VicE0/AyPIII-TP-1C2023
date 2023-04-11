import java.time.LocalDateTime;

public class Tarea
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private int repetirCada;


    public Tarea()
    {
        this.titulo = "Tarea sin titulo";
        this.descripcion = "-";
        this.fechaInicio = LocalDateTime.now();
        this.repetirCada = 0;

    }

    public Tarea(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int repetirCada)
    {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.repetirCada = repetirCada;
    }

    //GETTERS
    public String obtenerTitulo() {
        return titulo;
    }

    public String obtenerDescripcion()
    {return descripcion;
    }
    public LocalDateTime obtenerFechaInicio() {
        return fechaInicio;
    }
    public int obtenerRepetirCada(){
        return repetirCada;}






    public void establecerTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void establecerDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void establecerFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void establecerRepetirCada(int repetirCada){
        this.repetirCada = repetirCada;
    }
}

