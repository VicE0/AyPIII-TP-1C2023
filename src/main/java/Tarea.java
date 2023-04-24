import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Tarea
{
    private String titulo;
    private String descripcion;
    public abstract LocalDateTime obtenerFechaInicio();
    public abstract LocalDateTime obtenerFechaVencimiento();

    public abstract void establecerFechaInicio(LocalDateTime fechaInicio);
    public abstract void establecerFechaVencimiento(LocalDateTime fechaVencimiento);
    private Boolean estaCompleta;
    private final int id;
    private static int idSiguiente = 0;

    private final ArrayList<Alarma> alarmas;


    public Tarea(String titulo, String descripcion)
    {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estaCompleta = false;
        this.id = idSiguiente++;
        this.alarmas = new ArrayList<>();
    }


    public String obtenerTitulo() {
        return titulo;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public Boolean estaCompleta(){
        return estaCompleta;
    }

    public int obtenerId(){
        return id;
    }
    public ArrayList<Alarma> obtenerAlarmas(){
        return alarmas;
    }
    public void establecerTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void establecerDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void marcarComoCompleta(){
        this.estaCompleta = true;
    }
    public void agregarAlarma(Alarma alarma){
        alarmas.add(alarma);
    }
    public void modificarAlarma(Alarma alarma, LocalDateTime fechayHora){
        alarma.establecerFechaYHora(fechayHora);
    }
    public void desactivarAlarma(Alarma alarma){
        alarmas.remove(alarma);
    }
}

