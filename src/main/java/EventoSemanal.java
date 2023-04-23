//import java.time.LocalDateTime;
//import java.time.DayOfWeek;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EventoSemanal extends Evento {
//    private List<DayOfWeek> diasSemana;
//
//    public EventoSemanal() {
//        super();
//        this.diasSemana = null;
//    }
//
//    public EventoSemanal(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,int maxOcurrencias,Repeticion tipoRepeticion ,List<DayOfWeek> diasSemana) {
//        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias,tipoRepeticion);;
//        this.diasSemana = diasSemana;
//    }
//
//    public List<DayOfWeek> obtenerDiasSemana() {
//        return diasSemana;
//    }
//
//    public void establecerDiasSemana(List<DayOfWeek> diasSemana) {
//        this.diasSemana = diasSemana;
//    }
//
//    @Override
//    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
//        if (diasSemana == null || diasSemana.isEmpty()) {
//
//            this.establecerFechaFin(fecha.plusWeeks(1));
//            return fecha.plusWeeks(1);
//        }
//        for (int i = 1; i <= 7; i++) {
//            LocalDateTime siguienteFecha = fecha.plusDays(i);
//            DayOfWeek siguienteDia = siguienteFecha.getDayOfWeek();
//            if (diasSemana.contains(siguienteDia)) {
//                return siguienteFecha;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<Evento> obtenerProximosEventos() {
//
//        List<Evento> proximosEventos = new ArrayList<>();
//
//        // Añade el primer evento con la fecha ingresada
//        var primerEvento = new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), obtenerFechaInicio(), obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana);
//
//        Repeticion repeticion = primerEvento.obtenerTipoRepeticion();
//        int maxOcurrenciasPermitidas = primerEvento.obtenerMaxOcurrencias();
//        int ocurrenciasRealizadas = primerEvento.obtenerOcurrencias();
//        LocalDateTime fechaFin = primerEvento.obtenerFechaFin();
//
//        proximosEventos.add(primerEvento);
//        sumarOcurrencias();
//
//        LocalDateTime proximaFecha = calcularSiguienteOcurrencia(obtenerFechaInicio());
//
//        return switchCaseRepeticion(repeticion, proximaFecha, proximosEventos, maxOcurrenciasPermitidas, ocurrenciasRealizadas, fechaFin);
//    }
//
//
//    protected List<Evento> switchCaseRepeticion(Repeticion repeticion,LocalDateTime proximaFecha,List<Evento>  proximosEventos,int maxOcurrenciasPermitidas,int ocurrenciasRealizadas, LocalDateTime fechaFin  ){
//        switch (repeticion) {
//
//            case INFINITA:
//
//                while (ocurrenciasRealizadas < maxOcurrenciasPermitidas)
//                {
//                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
//                    proximosEventos.add(new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana));
//                    sumarOcurrencias();
//                }
//                return proximosEventos;
//
//            case HASTA_FECHA_FIN:
//                if ( !fechaFinNula() ) {
//                    proximosEventos.add(new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana));
//                }
//
//                while(proximaFecha.isBefore(fechaFin)) {
//
//                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
//                    proximosEventos.add((new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana)));
//
//                }
//                return proximosEventos;
//
//            case HASTA_OCURRENCIAS:
//
//                proximosEventos.add(new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana));
//                sumarOcurrencias();
//
//                while (ocurrenciasRealizadas < maxOcurrenciasPermitidas)
//                {
//                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
//                    proximosEventos.add(new EventoSemanal(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), diasSemana));
//                    sumarOcurrencias();
//                }
//                return proximosEventos;
//
//            default:
//                return proximosEventos;
//        }
//    }
//}
