import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreadorEventosDiarios implements CreadorDeEventos {

    public Evento construirEvento(ConstructorEventos constructor) {

        constructor.setTitulo();
        constructor.setDescripcion();
        constructor.setFechaInicio();
        constructor.setFechaFin();
        constructor.setMaxOcurrencias();
        constructor.setTipoRepeticion();
        constructor.setIntervalo();

        return constructor.obtenerEventoCreado();
    }

}





//    //El parámetro "intervalo" puede ser usado por EventoDiario, EventoMensual y EventoAnual, ya que todos usan el tipo int y la misma lógica pero distinta aplicación.
//    public Evento crearEvento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int maxOcurrencias, Repeticion tipoRepeticion, int intervalo,Set<DayOfWeek> diasSemana ) {
//        return new EventoDiario(titulo,descripcion,fechaInicio,fechaFin,maxOcurrencias, tipoRepeticion,intervalo);
//    }

//    public ArrayList<Evento> proximosEventos(Evento evento){
//
//        ArrayList<Evento> proximosEventos = new ArrayList<>();
//
//        // Añade la primera fecha ingresada
//        proximosEventos.add(evento);
//        evento.sumarOcurrencias();
//
//        LocalDateTime proximaFecha = evento.calcularSiguienteOcurrencia(evento.obtenerFechaInicio());
//
//        return repeticionEvento(proximaFecha, proximosEventos, evento);
//    }

//    //Metodo que crea una nueva instancia del objeto EventoDiario según la cantidad de veces que indique el tipo de repeticion
//    //Por cada fecha en la que el evento se deba repetir, se crea un nuevo objeto con dicha fecha como una nueva fecha de inicio
//
//    //PRE: Recibe una fecha y una lista de objetos evento
//    //POS: Segun la repeticion que le corresponda al objeto, crea una nueva instancia de EventoDiario con la proxima fecha de ocurrencia como nueva fecha de inicio del evento
//    //Agrega esta nueva instancia del objeto a la array list y la devuelve
//
//    public ArrayList<Evento> repeticionEvento(LocalDateTime proximaFecha,ArrayList<Evento>  proximosEventos, Evento evento)
//    {
//        switch (evento.obtenerTipoRepeticion()) {
//            case INFINITA -> {
//
//                var eventoInfinito = new EventoDiario(evento.obtenerTitulo(), evento.obtenerDescripcion(), proximaFecha,evento. obtenerFechaFin(), evento.obtenerMaxOcurrencias(),evento. obtenerTipoRepeticion(), 1);
//                proximosEventos.add(eventoInfinito);
//
//                while (evento.obtenerOcurrencias() < evento.obtenerMaxOcurrencias()) {
//
//                    proximaFecha = evento.calcularSiguienteOcurrencia(proximaFecha);
//
//                    var eventoInfinitoNuevo = new EventoDiario(evento.obtenerTitulo(), evento.obtenerDescripcion(), proximaFecha, evento.obtenerFechaFin(), evento.obtenerMaxOcurrencias(), evento.obtenerTipoRepeticion(), 1);
//
//                    proximosEventos.add(eventoInfinitoNuevo);
//
//                    evento.sumarOcurrencias();
//                }
//                return proximosEventos;
//            }
//            case HASTA_FECHA_FIN -> {
//
//                if (!evento.fechaFinNula()) {
//
//                    var eventoFechaFin = new EventoDiario(evento.obtenerTitulo(), evento.obtenerDescripcion(), proximaFecha,evento.obtenerFechaFin(), evento.obtenerMaxOcurrencias(), evento.obtenerTipoRepeticion(), 1);
//
//                    proximosEventos.add(eventoFechaFin);
//                }
//
//                while (proximaFecha.isBefore(evento.obtenerFechaFin())) {
//
//                    proximaFecha = evento.calcularSiguienteOcurrencia(proximaFecha);
//
//                    var eventoFechaFinNuevo = new EventoDiario(evento.obtenerTitulo(),evento. obtenerDescripcion(), proximaFecha,evento. obtenerFechaFin(), evento.obtenerMaxOcurrencias(),evento. obtenerTipoRepeticion(), 1);
//                    proximosEventos.add(eventoFechaFinNuevo);
//
//                }
//                return proximosEventos;
//            }
//            case HASTA_OCURRENCIAS -> {
//
//                var eventoOcurrencias = new EventoDiario(evento.obtenerTitulo(), evento.obtenerDescripcion(), proximaFecha,evento. obtenerFechaFin(), evento.obtenerMaxOcurrencias(), evento.obtenerTipoRepeticion(), 1);
//                proximosEventos.add(eventoOcurrencias);
//                evento.sumarOcurrencias();
//
//                while (evento.obtenerOcurrencias() < evento.obtenerMaxOcurrencias()) {
//
//                    proximaFecha = evento.calcularSiguienteOcurrencia(proximaFecha);
//
//                    var eventoOcurrenciasNuevo = new EventoDiario(evento.obtenerTitulo(), evento.obtenerDescripcion(), proximaFecha, evento.obtenerFechaFin(), evento.obtenerMaxOcurrencias(), evento.obtenerTipoRepeticion(), 1);
//
//                    proximosEventos.add(eventoOcurrenciasNuevo);
//                    evento.sumarOcurrencias();
//
//                }
//                return proximosEventos;
//            }
//            default -> {
//                return proximosEventos;
//            }
//        }
//    }


