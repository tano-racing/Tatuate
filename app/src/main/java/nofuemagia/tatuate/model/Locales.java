package nofuemagia.tatuate.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jlionti on 30/06/2016. No Fue Magia
 */
@Table(name = "Locales", id = "idLocal")
public class Locales extends Model {

    @Column(name = "idLocal")
    public int idLocal;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "descripcion")
    public String descripcion;

    @Column(name = "latitud")
    public float latitud;

    @Column(name = "longitud")
    public float longitud;

    public Locales() {
        super();
    }

    public Locales(int idLocal, String nombre, float latitud, float longitud) {
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public static List<Locales> getLocales() {
        return new Select().from(Locales.class).orderBy("idLocal DESC").execute();
    }
}
