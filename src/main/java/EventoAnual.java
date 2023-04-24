import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoAnual extends Evento {
    private int cantidadAnios;

    public EventoAnual() {
        super();
        this.cantidadAnios = 1;
    }

    public EventoAnual(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int cantidadAnios) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
        this.cantidadAnios = cantidadAnios;
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

    public int obtenerCantidadAnios() {
        return cantidadAnios;
    }

    public void establecerCantidadAnios(int cantidadAnios) {
        this.cantidadAnios = cantidadAnios;
    }

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        if (cantidadAnios > 1) {
            fecha = fecha.plusYears(cantidadAnios - 1);

        } else if (cantidadAnios == 0) {
            return fecha;

        } else if (cantidadAnios < 0) {
            throw new RuntimeException("Cantidad de años negativa");
        }
        return fecha.plusYears(1);
    }

    //Metodo que crea una nueva instancia del objeto EventoAnual según la cantidad de veces que indique el tipo de repeticion
    //Por cada fecha en la que el evento se deba repetir, se crea un nuevo objeto con dicha fecha como una nueva fecha de inicio
    @Override
    protected ArrayList<Evento> switchCaseRepeticion(LocalDateTime proximaFecha, ArrayList<Evento>  proximosEventos)
    {
        switch (obtenerTipoRepeticion()) {
            case INFINITA -> {

                var eventoInfinito = new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios);
                proximosEventos.add(eventoInfinito);

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {
                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoInfinitoNuevo = new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios);

                    proximosEventos.add(eventoInfinitoNuevo);

                    sumarOcurrencias();
                }
                return proximosEventos;
            }
            case HASTA_FECHA_FIN -> {
                if (!fechaFinNula()) {

                    var eventoFechaFin = new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios);

                    proximosEventos.add(eventoFechaFin);
                }
                while (proximaFecha.isBefore(obtenerFechaFin())) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoFechaFinNuevo = new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios);
                    proximosEventos.add(eventoFechaFinNuevo);

                }
                return proximosEventos;
            }
            case HASTA_OCURRENCIAS -> {

                var eventoOcurrencias = new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios);

                proximosEventos.add(eventoOcurrencias);
                sumarOcurrencias();

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoOcurrenciasNuevo = new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios);

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