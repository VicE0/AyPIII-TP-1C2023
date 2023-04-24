import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoMensual extends Evento{
    private int cantidadMeses;

    public EventoMensual(){
        super();
        this.cantidadMeses = 1;
    }

    public EventoMensual(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int cantidadMeses) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias,tipoRepeticion);
        this.cantidadMeses = cantidadMeses;
    }
    @Override
    public String obtenerTitulo() {
        return super.obtenerTitulo();
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion();
    }

    @Override
    public LocalDateTime obtenerFechaInicio() {
        return super.obtenerFechaInicio();
    }

    @Override
    public LocalDateTime obtenerFechaFin() {
        return super.obtenerFechaFin();
    }

    @Override
    public int obtenerMaxOcurrencias() {
        return super.obtenerMaxOcurrencias();
    }

    @Override
    public Repeticion obtenerTipoRepeticion() {
        return super.obtenerTipoRepeticion();
    }

    @Override
    public int obtenerOcurrencias() {
        return super.obtenerOcurrencias();
    }

    public int obtenerCantidadMeses() {
        return cantidadMeses;
    }

    public void establecerCantidadMeses(int cantidadMeses) {
        this.cantidadMeses = cantidadMeses;
    }

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        if (cantidadMeses > 1) {
            fecha = fecha.plusMonths(cantidadMeses - 1);
        }
        else if (cantidadMeses == 0){
            return fecha;
        }

        else if (cantidadMeses < 0){
            throw new RuntimeException("Cantidad de meses negativo");
        }
        return fecha.plusMonths(1);
    }

    //Metodo que crea una nueva instancia del objeto EventoMensual según la cantidad de veces que indique el tipo de repeticion
    //Por cada fecha en la que el evento se deba repetir, se crea un nuevo objeto con dicha fecha como una nueva fecha de inicio
    protected ArrayList<Evento> switchCaseRepeticion(LocalDateTime proximaFecha,ArrayList<Evento>  proximosEventos)
    {
        switch (obtenerTipoRepeticion()) {
            case INFINITA -> {

                var eventoInfinito = new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses);
                proximosEventos.add(eventoInfinito);

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {
                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoInfinitoNuevo = new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses);

                    proximosEventos.add(eventoInfinitoNuevo);

                    sumarOcurrencias();
                }
                return proximosEventos;
            }
            case HASTA_FECHA_FIN -> {
                if (!fechaFinNula()) {

                    var eventoFechaFin = new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses);

                    proximosEventos.add(eventoFechaFin);
                }
                while (proximaFecha.isBefore(obtenerFechaFin())) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoFechaFinNuevo = new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses);
                    proximosEventos.add(eventoFechaFinNuevo);

                }
                return proximosEventos;
            }
            case HASTA_OCURRENCIAS -> {

                var eventoOcurrencias = new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses);
                proximosEventos.add(eventoOcurrencias);
                sumarOcurrencias();

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoOcurrenciasNuevo = new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses);

                    proximosEventos.add(eventoOcurrenciasNuevo);
                    sumarOcurrencias();

                }
                return proximosEventos;
            }
            default -> {
                return proximosEventos;
            }
        }
    }
}
