import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tarea
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaVencimiento;
    private Boolean estaCompleta;
    private int id;
    private static int idSiguiente = 0;

    private ArrayList<Alarma> alarmas;


    public Tarea(){
        this.titulo = "Tarea sin nombre";
        this.descripcion = "";
        this.fechaInicio = LocalDateTime.now();
        this.fechaVencimiento = this.fechaInicio.plusDays(1);
        this.estaCompleta = false;
        this.id = idSiguiente++;
        this.alarmas = new ArrayList<Alarma>();
    }

    public Tarea(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaVencimiento)
    {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.estaCompleta = false;
        this.id = idSiguiente++;
        this.alarmas = new ArrayList<Alarma>();
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

    public int obtenerId(){
        return id;
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
    public void establecerFechaVencimiento(LocalDateTime fechaVencimiento){
        this.fechaVencimiento = fechaVencimiento;
    }
    public void establecerEstaCompleta(Boolean estaCompleta){
        this.estaCompleta = estaCompleta;
    }
    public void establecerId(int id){
        this.id = id;
    }
    public void marcarComoCompleta(){
        this.estaCompleta = true;
    }
    public void agregarAlarma(Alarma alarma){
        alarmas.add(alarma);
    }
    public void desactivarAlarma(Alarma alarma){
        alarmas.remove(alarma);
    }
}

