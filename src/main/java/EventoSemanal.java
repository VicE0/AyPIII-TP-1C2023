import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class EventoSemanal extends Evento {
    private List<DayOfWeek> diasSemana;

    public EventoSemanal() {
        super();
        this.diasSemana = null;
    }

    public EventoSemanal(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,int maxOcurrencias,Repeticion tipoRepeticion ,List<DayOfWeek> diasSemana) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias,tipoRepeticion);;
        this.diasSemana = diasSemana;
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

    public List<DayOfWeek> obtenerDiasSemana() {
        return diasSemana;
    }

    public void establecerDiasSemana(List<DayOfWeek> diasSemana) {
        this.diasSemana = diasSemana;
    }

    @Override
    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
        if (diasSemana == null || diasSemana.isEmpty()) {

            this.establecerFechaFin(fecha.plusWeeks(1));
            return fecha.plusWeeks(1);
        }
        for (int i = 1; i <= 7; i++) {

            LocalDateTime siguienteFecha = fecha.plusDays(i);

            DayOfWeek siguienteDia = siguienteFecha.getDayOfWeek();

            if (diasSemana.contains(siguienteDia)) {
                return siguienteFecha;
            }
        }
        return null;
    }


    protected ArrayList<Evento> switchCaseRepeticion(LocalDateTime proximaFecha,ArrayList<Evento>  proximosEventos)
    {
        switch (obtenerTipoRepeticion()) {

            case INFINITA:

                var eventoInfinito = new EventoSemanal(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion(), diasSemana);

                proximosEventos.add(eventoInfinito);

                while (obtenerOcurrencias() < obtenerMaxOcurrencias())
                {
                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoInfinitoNuevo = new EventoSemanal(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion(), diasSemana);

                    proximosEventos.add(eventoInfinitoNuevo);

                    sumarOcurrencias();
                }
                return proximosEventos;

            case HASTA_FECHA_FIN:
                if ( !fechaFinNula() ) {

                    var eventoFechaFin = new EventoSemanal(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion(),diasSemana);

                    proximosEventos.add(eventoFechaFin);
                }

                while(proximaFecha.isBefore(obtenerFechaFin())) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoFechaFinNuevo = new EventoSemanal(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion(), diasSemana);
                    proximosEventos.add(eventoFechaFinNuevo);

                }
                return proximosEventos;

            case HASTA_OCURRENCIAS:

                var eventoOcurrencias = new EventoSemanal(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion(), diasSemana);
                proximosEventos.add(eventoOcurrencias);
                sumarOcurrencias();

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoOcurrenciasNuevo = new EventoSemanal(obtenerTitulo(),obtenerDescripcion(),proximaFecha,obtenerFechaFin(),obtenerMaxOcurrencias(),obtenerTipoRepeticion(), diasSemana);

                    proximosEventos.add(eventoOcurrenciasNuevo);
                    sumarOcurrencias();

                }
                return proximosEventos;

            default:
                return proximosEventos;
        }
    }


}
