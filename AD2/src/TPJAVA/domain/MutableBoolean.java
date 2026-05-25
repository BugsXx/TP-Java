package TPJAVA.domain;

class MutableBoolean {
    public boolean valor;
    public void SetTrue() { this.valor = true; }
    public void SetFalse() { this.valor = false; }

    public boolean equals(boolean valor) {
        return this.valor == valor;
    }
}
