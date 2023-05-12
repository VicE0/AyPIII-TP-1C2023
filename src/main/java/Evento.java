import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Evento
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int maxOcurrencias;
    private int ocurrenciasRealizadas;
    private Repeticion tipoRepeticion;
    private final ArrayList<Alarma> alarmasEvento;


    //Constructor default con los valores iniciales
    //Por Default, el evento será de dia completo
    public Evento(LocalDateTime fechaInicio){

        this.titulo = "Evento sin titulo";
        this.descripcion = "-";
        this.fechaInicio = fechaInicio;
        this.fechaFin = this.fechaInicio.plusDays(1);
        this.maxOcurrencias = 1;
        this.ocurrenciasRealizadas = 0;
        this.tipoRepeticion = Repeticion.HASTA_FECHA_FIN;
        this.alarmasEvento = new ArrayList<>();

    }

    // PRE: Pido los datos necesarios para la creación de un evento
    // POS: Inicializo los valores correctos del evento con los datos disponibles
    public Evento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.maxOcurrencias = maxOcurrencias;
        this.ocurrenciasRealizadas = 0;
        this.tipoRepeticion = tipoRepeticion;
        this.alarmasEvento = new ArrayList<>();


    }

//GETTERS
    public String obtenerTitulo() {return titulo;}
    public String obtenerDescripcion() {return descripcion;}
    public LocalDateTime obtenerFechaInicio() {return fechaInicio;}
    public LocalDateTime obtenerFechaFin() {return fechaFin;}
    public int obtenerMaxOcurrencias() {return maxOcurrencias;}
    public int obtenerOcurrencias() {return ocurrenciasRealizadas;}
    public Repeticion obtenerTipoRepeticion() {return tipoRepeticion;}
    public ArrayList<Alarma> obtenerAlarmasEvento(){
        return alarmasEvento;
    }


//    public ArrayList<Evento> obtenerProximosEventos(Evento evento)
//    {
//        ArrayList<Evento> proximosEventos = new ArrayList<>();
//
//        // Añade la primera fecha ingresada
//        proximosEventos.add(evento);
//        sumarOcurrencias();
//
//        LocalDateTime proximaFecha = calcularSiguienteOcurrencia(fechaInicio);
//
//        return switchCaseRepeticion(proximaFecha, proximosEventos);
//    }


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
    public void establecerMaxOcurrencias(int maxOcurrencias) {
        this.maxOcurrencias = maxOcurrencias;
    }
    public void establecerTipoRepeticion(Repeticion tipoRepeticion ) {
        this.tipoRepeticion = tipoRepeticion;
    }



    //Ests métodos lo heredan las subclases EventoDiario, EventoSemanal, EventoMensual y EventoAnual

    //Método para obtener la siguiente ocurrencia del Evento segun la frecuencia que tiene asignada.
    protected abstract LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha);

    //Método para crear una nueva instancia del objeto evento segun las repeticiones que le corresponden al evento.
//    protected abstract ArrayList<Evento> switchCaseRepeticion(LocalDateTime proximaFecha,ArrayList<Evento>  proximosEventos);


    public void agregarAlarmaEvento(Alarma alarma){
        alarmasEvento.add(alarma);
    }

    public void desactivarAlarmaEvento(Alarma alarma){
        alarmasEvento.remove(alarma);
    }

    public void modificarAlarmaEvento(Alarma alarma, LocalDateTime fechayHora){
        alarma.establecerFechaYHora(fechayHora);
    }
    public boolean fechaFinNula() {
       return fechaFin == null;
   }

    public void sumarOcurrencias(){
        ocurrenciasRealizadas++;
    }

}


