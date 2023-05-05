import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Set;

public class EventoSemanal extends Evento {
    private Set<DayOfWeek> diasSemana;

    public EventoSemanal() {
        super();
        this.diasSemana = null;
    }

    public EventoSemanal(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,int maxOcurrencias,Repeticion tipoRepeticion ,Set<DayOfWeek> diasSemana) {
        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias,tipoRepeticion);
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

    public Set<DayOfWeek> obtenerDiasSemana() {
        return diasSemana;
    }

    public void establecerDiasSemana(Set<DayOfWeek> diasSemana) {
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

    //Metodo que crea una nueva instancia del objeto EventoSemanal según la cantidad de veces que indique el tipo de repeticion
    //Por cada fecha en la que el evento se deba repetir, se crea un nuevo objeto con dicha fecha como una nueva fecha de inicio

    //PRE: Recibe una fecha y una lista de objetos evento
    //POS: Segun la repeticion que le corresponda al objeto, crea una nueva instancia de EventoSemanal con la proxima fecha de ocurrencia como nueva fecha de inicio del evento
    //Agrega esta nueva instancia del objeto a la array list y la devuelve
    protected ArrayList<Evento> switchCaseRepeticion(LocalDateTime proximaFecha,ArrayList<Evento>  proximosEventos)
    {
        switch (obtenerTipoRepeticion()) {

            case INFINITA -> {

                var eventoInfinito = new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana);
                proximosEventos.add(eventoInfinito);

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {
                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoInfinitoNuevo = new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana);

                    proximosEventos.add(eventoInfinitoNuevo);

                    sumarOcurrencias();
                }
                return proximosEventos;
            }
            case HASTA_FECHA_FIN -> {

                if (!fechaFinNula()) {

                    var eventoFechaFin = new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana);

                    proximosEventos.add(eventoFechaFin);
                }
                while (proximaFecha.isBefore(obtenerFechaFin())) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoFechaFinNuevo = new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana);
                    proximosEventos.add(eventoFechaFinNuevo);

                }
                return proximosEventos;
            }
            case HASTA_OCURRENCIAS -> {

                var eventoOcurrencias = new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana);
                proximosEventos.add(eventoOcurrencias);
                sumarOcurrencias();

                while (obtenerOcurrencias() < obtenerMaxOcurrencias()) {

                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);

                    var eventoOcurrenciasNuevo = new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana);

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
