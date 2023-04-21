import java.time.LocalDateTime;
import java.util.List;
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

    }


//GETTERS
    public String obtenerTitulo() {return titulo;}
    public String obtenerDescripcion() {return descripcion;}
    public LocalDateTime obtenerFechaInicio() {return fechaInicio;}
    public LocalDateTime obtenerFechaFin() {return fechaFin;}

    public int obtenerMaxOcurrencias() {return maxOcurrencias;}
    public int obtenerOcurrencias() {return ocurrenciasRealizadas;}
    public Repeticion obtenerTipoRepeticion() {return tipoRepeticion;}


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

    //Método para obtener la siguiente ocurrencia del Evento segun la frecuencia que tiene asignada.
    //Este método lo heredan las subclases EventoDiario, EventoSemanal, EventoMensual y EventoAnual
    protected abstract LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha);


    public List<LocalDateTime> obtenerProximosEventos()
    {
       List<LocalDateTime> proximosEventos = new ArrayList<>();

        // Añade la primera fecha ingresada
        proximosEventos.add(fechaInicio);
        sumarOcurrencias();

        LocalDateTime proximaFecha = calcularSiguienteOcurrencia(fechaInicio);

        return switchCaseRepeticion(proximaFecha, proximosEventos);
    }

    protected   List<LocalDateTime> switchCaseRepeticion(LocalDateTime proximaFecha,List<LocalDateTime>  proximosEventos)
    {
        switch (tipoRepeticion) {

            case INFINITA:

                //Un evento infinito no tiene fecha final.
                fechaFin = null;

                //Por temas de testeo, el while se deja funcionando a base de las ocurrencias
                while (fechaFinNula() && ocurrenciasRealizadas < maxOcurrencias)
                {
                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
                    proximosEventos.add(proximaFecha);
                    sumarOcurrencias();
                }
                return proximosEventos;

            case HASTA_FECHA_FIN:
                if ( !fechaFinNula() ) {
                    proximosEventos.add(proximaFecha);
                }

                while(proximaFecha.isBefore(fechaFin)) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
                    proximosEventos.add(proximaFecha);

                }
                return proximosEventos;

            case HASTA_OCURRENCIAS:

                proximosEventos.add(proximaFecha);
                sumarOcurrencias();

                while (ocurrenciasRealizadas < maxOcurrencias)
                {
                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
                    proximosEventos.add(proximaFecha);
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


