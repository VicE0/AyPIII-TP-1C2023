import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConstructorEventosDiarios implements ConstructorEventos{
    private  EventoDiario eventoDiario;

    public ConstructorEventosDiarios(){
        this.eventoDiario = new EventoDiario();
    }

    public ConstructorEventosDiarios(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias,Repeticion tipoRepeticion,int intervalo){
        this.eventoDiario = new EventoDiario(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias, tipoRepeticion, intervalo);
    }

    public void setTitulo() {
        this.eventoDiario.obtenerTitulo();
    }

    public void setDescripcion() {
        this.eventoDiario.obtenerDescripcion();
    }

    public void setFechaInicio() {
        this.eventoDiario.obtenerFechaInicio();
    }

    public void setFechaFin() {
        this.eventoDiario.obtenerFechaFin();
    }

    public void setMaxOcurrencias() {
        this.eventoDiario.obtenerMaxOcurrencias();
    }

    public void setTipoRepeticion() {
        this.eventoDiario.obtenerTipoRepeticion();
    }

    public void setDiasSemana() {
    }

    public void setIntervalo() {
        this.eventoDiario.obtenerIntervalo();
    }

    public EventoDiario obtenerEventoCreado(){
        EventoDiario clase = this.eventoDiario;
        return clase;
    }

    public ArrayList<Evento> repeticionEvento(LocalDateTime proximaFecha, ArrayList<Evento> proximosEventos) {

        switch (eventoDiario.obtenerTipoRepeticion()) {

            case INFINITA -> {
                var eventoInfinito = new EventoDiario(eventoDiario.obtenerTitulo(), eventoDiario.obtenerDescripcion(), eventoDiario.obtenerFechaInicio(), eventoDiario.obtenerFechaFin(), eventoDiario.obtenerMaxOcurrencias(), eventoDiario.obtenerTipoRepeticion(), eventoDiario.obtenerIntervalo());

                proximosEventos.add(eventoInfinito);

                while (eventoDiario.obtenerOcurrencias() < eventoDiario.obtenerMaxOcurrencias()) {

                    proximaFecha = eventoDiario.calcularSiguienteOcurrencia(proximaFecha);

                    var eventoInfinitoNuevo = new EventoDiario(eventoDiario.obtenerTitulo(), eventoDiario.obtenerDescripcion(), proximaFecha, eventoDiario.obtenerFechaFin(), eventoDiario.obtenerMaxOcurrencias(), eventoDiario.obtenerTipoRepeticion(), eventoDiario.obtenerIntervalo());

                    proximosEventos.add(eventoInfinitoNuevo);

                    eventoDiario.sumarOcurrencias();
                }
                return proximosEventos;
            }

            case HASTA_FECHA_FIN -> {

                if (!eventoDiario.fechaFinNula()) {

                    var eventoFechaFin = new EventoDiario(eventoDiario.obtenerTitulo(), eventoDiario.obtenerDescripcion(), proximaFecha, eventoDiario.obtenerFechaFin(), eventoDiario.obtenerMaxOcurrencias(), eventoDiario.obtenerTipoRepeticion(), eventoDiario.obtenerIntervalo());

                    proximosEventos.add(eventoFechaFin);
                }

                while (proximaFecha.isBefore(eventoDiario.obtenerFechaFin())) {

                    proximaFecha = eventoDiario.calcularSiguienteOcurrencia(proximaFecha);

                    var eventoFechaFinNuevo = new EventoDiario(eventoDiario.obtenerTitulo(), eventoDiario.obtenerDescripcion(), proximaFecha, eventoDiario.obtenerFechaFin(), eventoDiario.obtenerMaxOcurrencias(), eventoDiario.obtenerTipoRepeticion(), eventoDiario.obtenerIntervalo());

                    eventoFechaFinNuevo.establecerFechaInicio(proximaFecha);
                    proximosEventos.add(eventoFechaFinNuevo);

                }
                return proximosEventos;
            }

            case HASTA_OCURRENCIAS -> {

                var eventoOcurrencias = new EventoDiario(eventoDiario.obtenerTitulo(), eventoDiario.obtenerDescripcion(), proximaFecha, eventoDiario.obtenerFechaFin(), eventoDiario.obtenerMaxOcurrencias(), eventoDiario.obtenerTipoRepeticion(), eventoDiario.obtenerIntervalo());
                proximosEventos.add(eventoOcurrencias);

                eventoDiario.sumarOcurrencias();

                while (eventoDiario.obtenerOcurrencias() < eventoDiario.obtenerMaxOcurrencias()) {

                    proximaFecha = eventoDiario.calcularSiguienteOcurrencia(proximaFecha);

                    var eventoOcurrenciasNuevo = new EventoDiario(eventoDiario.obtenerTitulo(), eventoDiario.obtenerDescripcion(), proximaFecha, eventoDiario.obtenerFechaFin(), eventoDiario.obtenerMaxOcurrencias(), eventoDiario.obtenerTipoRepeticion(), eventoDiario.obtenerIntervalo());
                    ;
                    eventoOcurrenciasNuevo.establecerFechaInicio(proximaFecha);

                    proximosEventos.add(eventoOcurrenciasNuevo);
                    eventoDiario.sumarOcurrencias();

                }
                return proximosEventos;
            }

            default -> {
                return proximosEventos;
            }

        }
    }
}
