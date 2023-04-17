import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Evento
{
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
//    private Frecuencia frecuencia;
//    private List<DayOfWeek> diasSemana;


    //Constructor default con los valores iniciales
    //Por Default, el evento será de dia completo
    public Evento(){
        this.titulo = "Evento sin titulo";
        this.descripcion = "-";
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = this.fechaInicio.plusDays(1);
//        this.frecuencia = null;
//        this.diasSemana = null;
    }

    // PRE: Pido los datos necesarios para la creación de un evento
    // POS: Inicializo los valores correctos del evento con los datos disponibles
    public Evento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
//        this.frecuencia = frecuencia;
//        this.diasSemana = diasSemana;
    }

//GETTERS
    public String obtenerTitulo() {return titulo;}
    public String obtenerDescripcion() {return descripcion;}
    public LocalDateTime obtenerFechaInicio() {return fechaInicio;}
    public LocalDateTime obtenerFechaFin() {return fechaFin;}
//    public Frecuencia obtenerFrecuencia(){return frecuencia;}


    //Método para obtener la siguiente ocurrencia del Evento segun la frecuencia que tiene asignada.
    public abstract LocalDate obtenerSiguienteOcurrencia(LocalDate fecha);
//        switch (frecuencia) {
//
//            case DIARIA:
//                if (frecuencia.obtenerIntervalo() > 1) {
//                    fecha = fecha.plusDays(frecuencia.obtenerIntervalo() - 1);
//                }
//                return fecha.plusDays(1);
//
//            case SEMANAL:
//                if (diasSemana == null || diasSemana.isEmpty()) {
//                    return fecha.plusWeeks(1); //Simplemente añade una semana
//                }
//                for (int i = 1; i <= 7; i++)
//                {
//                    LocalDate siguienteFecha = fecha.plusDays(i);
//
//                    DayOfWeek siguienteDia = siguienteFecha.getDayOfWeek();
//                    if (diasSemana.contains(siguienteDia)) {
//                        return siguienteFecha;
//                    }
//                }
//
//            case MENSUAL:
//                return fecha.plusMonths(1);
//
//            case ANUAL:
//                return fecha.plusYears(1);
//
//            default:
//                return null;
//        }
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
//    public void establecerFrecuencia(Frecuencia frecuencia) {this.frecuencia = frecuencia;}
//    public void establecerDiasSemana(List<DayOfWeek> diasSemana) {this.diasSemana = diasSemana;}
}


