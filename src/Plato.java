import java.util.*;
import java.io.*;

 enum TipoPlato {
    ENTRANTE,
    PRIMER_PLATO,
    SEGUNDO_PLATO,
    POSTRE
}
public abstract class Plato {
    protected String nombre;
    private TipoPlato tipo;


    public Plato(String nombre, TipoPlato tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoPlato getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlato tipo) {
        this.tipo = tipo;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}

