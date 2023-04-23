//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EventoMensual extends Evento{
//    private int cantidadMeses;
//
//    public EventoMensual(){
//        super();
//        this.cantidadMeses = 1;
//    }
//
//    public EventoMensual(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int cantidadMeses) {
//        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias,tipoRepeticion);
//        this.cantidadMeses = cantidadMeses;
//    }
//
//    public int obtenerCantidadMeses() {
//        return cantidadMeses;
//    }
//
//    public void establecerCantidadMeses(int cantidadMeses) {
//        this.cantidadMeses = cantidadMeses;
//    }
//    @Override
//    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
//        if (cantidadMeses > 1) {
//            fecha = fecha.plusMonths(cantidadMeses - 1);
//        }
//        else if (cantidadMeses == 0){
//            return fecha;
//        }
//
//        else if (cantidadMeses < 0){
//            throw new RuntimeException("Cantidad de meses negativo");
//        }
//        return fecha.plusMonths(1);
//    }
//    @Override
//    public List<Evento> obtenerProximosEventos() {
//
//        List<Evento> proximosEventos = new ArrayList<>();
//
//        // Añade el primer evento con la fecha ingresada
//        var primerEvento = new EventoMensual(obtenerTitulo(), obtenerDescripcion(), obtenerFechaInicio(), obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses);
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
//                    proximosEventos.add(new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses));
//                    sumarOcurrencias();
//                }
//                return proximosEventos;
//
//            case HASTA_FECHA_FIN:
//                if ( !fechaFinNula() ) {
//                    proximosEventos.add(new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses));
//                }
//
//                while(proximaFecha.isBefore(fechaFin)) {
//
//                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
//                    proximosEventos.add((new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses)));
//
//                }
//                return proximosEventos;
//
//            case HASTA_OCURRENCIAS:
//
//                proximosEventos.add(new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses));
//                sumarOcurrencias();
//
//                while (ocurrenciasRealizadas < maxOcurrenciasPermitidas)
//                {
//                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
//                    proximosEventos.add(new EventoMensual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadMeses));
//                    sumarOcurrencias();
//                }
//                return proximosEventos;
//
//            default:
//                return proximosEventos;
//        }
//    }
//}
