import java.time.LocalDateTime;

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

}
