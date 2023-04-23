//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EventoAnual extends Evento {
//    private int cantidadAnios;
//    public EventoAnual(){
//        super();
//        this.cantidadAnios = 1;
//    }
//    public EventoAnual(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int cantidadAnios ) {
//        super(titulo, descripcion, fechaInicio, fechaFin, maxOcurrencias,tipoRepeticion);
//        this.cantidadAnios = cantidadAnios;
//    }
//
//    public int obtenerCantidadAnios() {
//        return cantidadAnios;
//    }
//
//    public void establecerCantidadAnios(int cantidadAnios) {
//        this.cantidadAnios = cantidadAnios;
//    }
//
//    @Override
//    protected LocalDateTime calcularSiguienteOcurrencia(LocalDateTime fecha) {
//        if (cantidadAnios > 1) {
//            fecha = fecha.plusYears(cantidadAnios - 1);
//        }
//        else if (cantidadAnios == 0){
//            return fecha;
//        }
//
//        else if (cantidadAnios < 0){
//            throw new RuntimeException("Cantidad de años negativa");
//        }
//        return fecha.plusYears(1);
//    }
//
//    @Override
//    public List<Evento> obtenerProximosEventos() {
//
//        List<Evento> proximosEventos = new ArrayList<>();
//
//        // Añade el primer evento con la fecha ingresada
//        var primerEvento = new EventoAnual(obtenerTitulo(), obtenerDescripcion(), obtenerFechaInicio(), obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios);
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
//    protected List<Evento> switchCaseRepeticion(Repeticion repeticion,LocalDateTime proximaFecha,List<Evento>  proximosEventos,int maxOcurrenciasPermitidas,int ocurrenciasRealizadas, LocalDateTime fechaFin  ){
//        switch (repeticion) {
//
//            case INFINITA:
//
//                while (ocurrenciasRealizadas < maxOcurrenciasPermitidas)
//                {
//                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
//                    proximosEventos.add(new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios));
//                    sumarOcurrencias();
//                }
//                return proximosEventos;
//
//            case HASTA_FECHA_FIN:
//                if ( !fechaFinNula() ) {
//                    proximosEventos.add(new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios));
//                }
//
//                while(proximaFecha.isBefore(fechaFin)) {
//
//                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
//                    proximosEventos.add((new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios)));
//
//                }
//                return proximosEventos;
//
//            case HASTA_OCURRENCIAS:
//
//                proximosEventos.add(new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios));
//                sumarOcurrencias();
//
//                while (ocurrenciasRealizadas < maxOcurrenciasPermitidas)
//                {
//                    proximaFecha = calcularSiguienteOcurrencia(proximaFecha);
//                    proximosEventos.add(new EventoAnual(obtenerTitulo(), obtenerDescripcion(), proximaFecha, obtenerFechaFin(), obtenerMaxOcurrencias(), obtenerTipoRepeticion(), cantidadAnios));
//                    sumarOcurrencias();
//                }
//                return proximosEventos;
//
//            default:
//                return proximosEventos;
//        }
//    }
//}
