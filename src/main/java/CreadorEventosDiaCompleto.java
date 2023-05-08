public class CreadorEventosDiaCompleto implements CreadorDeEventos {

    public Evento construirEvento(ConstructorEventos constructor) {

        constructor.setTitulo();
        constructor.setDescripcion();
        constructor.setFechaInicio();
        constructor.setFechaFin();
        constructor.setMaxOcurrencias();
        constructor.setTipoRepeticion();

        return constructor.obtenerEventoCreado();
    }
}
