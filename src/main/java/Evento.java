import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public  class Evento
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int maxOcurrencias;
    private int ocurrenciasRealizadas;
    private Repeticion tipoRepeticion;
    private  ArrayList<Alarma> alarmasEvento;



    //Constructor default con los valores iniciales
    //Por Default, el evento será de dia completo
    public Evento(){
        this.titulo = "Evento sin titulo";
        this.descripcion = "-";
        this.fechaInicio = LocalDateTime.now();
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

    public void agregarAlarmaEvento(Alarma alarma){
        alarmasEvento.add(alarma);
    }

    public void desactivarAlarmaEvento(Alarma alarma){
        alarmasEvento.remove(alarma);
    }


    public void establecerMaxOcurrencias(int maxOcurrencias) {
        this.maxOcurrencias = maxOcurrencias;
    }
    public void establecerTipoRepeticion(Repeticion tipoRepeticion ) {
        this.tipoRepeticion = tipoRepeticion;
    }

    //Método para obtener la siguiente ocurrencia del Evento segun la frecuencia que tiene asignada.
    //Este método lo heredan las subclases EventoDiario, EventoSemanal, EventoMensual y EventoAnual
    protected  LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha){
        return fecha;
    }


    public List<Evento> obtenerProximosEventos(Evento evento)
    {
       List<Evento> proximosEventos = new ArrayList<>();

        // Añade la primera fecha ingresada
        proximosEventos.add(evento);
        sumarOcurrencias();

        LocalDateTime proximaFecha = calcularSiguienteOcurrencia(fechaInicio);

        return switchCaseRepeticion(proximaFecha, proximosEventos, evento);
    }

    protected List<Evento> switchCaseRepeticion(LocalDateTime proximaFecha,List<Evento>  proximosEventos, Evento evento)
    {
        switch (tipoRepeticion) {

            case INFINITA:

                //Un evento infinito no tiene fecha final.
                fechaFin = null;

                //Por temas de testeo, el while se deja funcionando a base de las ocurrencias
                while (fechaFinNula() && ocurrenciasRealizadas < maxOcurrencias)
                {
                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
                    var eventoNuevo = new Evento(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion());
                    proximosEventos.add(eventoNuevo);
                    sumarOcurrencias();
                }
                return proximosEventos;

            case HASTA_FECHA_FIN:
                if ( !fechaFinNula() ) {
                    var eventoSiguiente = new Evento(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion());
                    proximosEventos.add(eventoSiguiente);
                }

                while(proximaFecha.isBefore(fechaFin)) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
                    var eventoNuevo = new Evento(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion());
                    proximosEventos.add(eventoNuevo);

                }
                return proximosEventos;

            case HASTA_OCURRENCIAS:

                var eventoSiguiente = new Evento(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion());
                proximosEventos.add(eventoSiguiente);
                sumarOcurrencias();

                while (ocurrenciasRealizadas < maxOcurrencias)
                {
                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoNuevo = new Evento(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion());
                    proximosEventos.add(eventoNuevo);
                    sumarOcurrencias();

                }
                return proximosEventos;

            default:
                return proximosEventos;
        }
    }

   public boolean fechaFinNula()
   {
       if (fechaFin != null) {
           return false;
       }
       return true;
   }

    public void sumarOcurrencias(){
        ocurrenciasRealizadas++;
    }


}


