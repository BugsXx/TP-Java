package TPJAVA.domain.inscripciones;


public enum TipoMateria  {
    OptativaPromocion(0.6),
    OptativaHabilita(0.5),
    ObligatoriaPromocion(0.8),
    ObligatoriaHabilita(0.6),
    PasantiaHabilita(0.75);
    private final double valor;


    TipoMateria(double valor) {
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
}
