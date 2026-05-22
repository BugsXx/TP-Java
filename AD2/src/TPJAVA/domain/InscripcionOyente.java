package TPJAVA.domain;

public class InscripcionOyente extends Inscripcion{
    //agregar posibles metodos

    @Override
    public boolean Promociona(){ // el oyente no puede promocionar
        return false;
    }
    public boolean Habilita(){ // el oyente no puede habilitar
        return false;
    }
}
