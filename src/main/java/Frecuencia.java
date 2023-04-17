public enum Frecuencia {
    DIARIA(1),
    SEMANAL(7),
    MENSUAL(31),
    ANUAL(366); //Este seria el caso maximo de año biciesto

    private int intervalo; //No es final porque el usuario deberia ser capaz de cambiarlo

    Frecuencia(int intervalo) {
        this.intervalo = intervalo;
    }

    public int obtenerIntervalo(){return intervalo;}

    public void establecerIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }
}
