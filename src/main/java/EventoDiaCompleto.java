import java.time.LocalDateTime;
import java.util.ArrayList;


public class EventoDiaCompleto extends Evento {


    public EventoDiaCompleto() {
        super();
    }

    public EventoDiaCompleto(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion);

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

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {

        return fecha.plusDays(1);
    }

    //Metodo que crea una nueva instancia del objeto EventoDiaCompleto según la cantidad de veces que indique el tipo de repeticion
    //Por cada fecha en la que el evento se deba repetir, se crea un nuevo objeto con dicha fecha como una nueva fecha de inicio

    //PRE: Recibe una fecha y una lista de objetos evento
    //POS: Segun la repeticion que le corresponda al objeto, crea una nueva instancia de EventoDiario con la proxima fecha de ocurrencia como nueva fecha de inicio del evento
    //Agrega esta nueva instancia del objeto a la array list y la devuelve
    @Override
    protected ArrayList<Evento> switchCaseRepeticion(LocalDateTime proximaFecha, ArrayList<Evento>  proximosEventos)
    {
        switch (obtenerTipoRepeticion()) {
            case INFINITA -> {

                var eventoInfinito = new EventoDiaCompleto(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion());
                proximosEventos.add(eventoInfinito);

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoInfinitoNuevo = new EventoDiaCompleto(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion());

                    proximosEventos.add(eventoInfinitoNuevo);

                    sumarOcurrencias();
                }
                return proximosEventos;
            }
            case HASTA_FECHA_FIN -> {

                if (!fechaFinNula()) {

                    var eventoFechaFin = new EventoDiaCompleto(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion());

                    proximosEventos.add(eventoFechaFin);
                }

                while (proximaFecha.isBefore(obtenerFechaFin())) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoFechaFinNuevo = new EventoDiaCompleto(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion());
                    proximosEventos.add(eventoFechaFinNuevo);

                }
                return proximosEventos;
            }
            case HASTA_OCURRENCIAS -> {

                var eventoOcurrencias = new EventoDiaCompleto(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion());
                proximosEventos.add(eventoOcurrencias);
                sumarOcurrencias();

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoOcurrenciasNuevo = new EventoDiaCompleto(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion());

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
