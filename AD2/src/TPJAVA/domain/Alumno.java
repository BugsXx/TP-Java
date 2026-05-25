package TPJAVA.domain;

import java.util.List;

public class Alumno {
    private List<Inscripcion> inscripciones;

    private String matricula;

    //agregar posibles metodos


    public String getMatricula(){
        return matricula;
    }

    public boolean equals(Alumno obj) {
        return obj.getMatricula().equals(getMatricula());
    }
}
