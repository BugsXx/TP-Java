package TPJAVA.domain.wrappers;

public class MutableBoolean {   //PREGUNTAR SI SE PUEDE USAR CLASS BOOLEAN
    public boolean valor;
    public void SetTrue() { this.valor = true; }
    public void SetFalse() { this.valor = false; }

    public boolean equals(boolean valor) {
        return this.valor == valor;
    }

    public MutableBoolean(boolean valor){
        this.valor = valor;
    }
}
