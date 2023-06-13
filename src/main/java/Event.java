import java.time.LocalDate;
//CLASE SUSTITUTA PARA PROBAR EL TEMA DEL CALENDARIO Y MOSTRAR INFORMACION
class Event {
    private LocalDate fecha;
    private String titulo;

    public Event(LocalDate fecha, String title) {
        this.fecha = fecha;
        this.titulo = title;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTitulo() {
        return titulo;
    }
}
