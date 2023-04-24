import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoDiario extends Evento {
    private int intervalo;

    public EventoDiario() {
        super();
        this.intervalo = 1;
    }

    public EventoDiario(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias,Repeticion tipoRepeticion,int intervalo) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);
        this.intervalo = intervalo;
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

    public int obtenerIntervalo() {
        return intervalo;
    }

    public void establecerIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        if (intervalo > 1) {
            fecha = fecha.plusDays(intervalo - 1);
        }
        else if (intervalo == 0){
            return fecha;
        }

        else if (intervalo < 0){
            throw new RuntimeException("Intervalo de dias negativo");
        }
        return fecha.plusDays(1);
    }

    //Metodo que crea una nueva instancia del objeto EventoDiario según la cantidad de veces que indique el tipo de repeticion
    //Por cada fecha en la que el evento se deba repetir, se crea un nuevo objeto con dicha fecha como una nueva fecha de inicio
    @Override
    protected ArrayList<Evento> switchCaseRepeticion(LocalDateTime proximaFecha,ArrayList<Evento>  proximosEventos)
    {
        switch (obtenerTipoRepeticion()) {
            case INFINITA -> {

                var eventoInfinito = new EventoDiario(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), intervalo);
                proximosEventos.add(eventoInfinito);

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoInfinitoNuevo = new EventoDiario(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), intervalo);

                    proximosEventos.add(eventoInfinitoNuevo);

                    sumarOcurrencias();
                }
                return proximosEventos;
            }
            case HASTA_FECHA_FIN -> {

                if (!fechaFinNula()) {

                    var eventoFechaFin = new EventoDiario(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), intervalo);

                    proximosEventos.add(eventoFechaFin);
                }

                while (proximaFecha.isBefore(obtenerFechaFin())) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoFechaFinNuevo = new EventoDiario(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), intervalo);
                    proximosEventos.add(eventoFechaFinNuevo);

                }
                return proximosEventos;
            }
            case HASTA_OCURRENCIAS -> {

                var eventoOcurrencias = new EventoDiario(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), intervalo);
                proximosEventos.add(eventoOcurrencias);
                sumarOcurrencias();

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoOcurrenciasNuevo = new EventoDiario(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), intervalo);

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


