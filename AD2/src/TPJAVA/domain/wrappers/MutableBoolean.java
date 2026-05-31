package TPJAVA.domain.wrappers;

public class MutableBoolean {   //PREGUNTAR SI SE PUEDE USAR CLASS BOOLEAN
    public boolean valor;
    public void SetTrue() { this.valor = true; }
    public void SetFalse() { this.valor = false; }

    @Override
    public boolean equals(Object valor) {
        if(valor instanceof Boolean)
            return this.valor == (boolean) valor;
        else return false;
    }

    public MutableBoolean(boolean valor){
        this.valor = valor;
    }
}
