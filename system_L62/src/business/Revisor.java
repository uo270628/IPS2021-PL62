package business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Revisor {

    private String id;
    private String nombre;
    private List<Tema> listOfTemas;
    private int tiempoDeRevision;

    public Revisor(String id) {
	this.id = id;
    }

    public Revisor(int tiempoDeRevision, String nombre) {
	this.id = UUID.randomUUID().toString();
	this.listOfTemas = new ArrayList<Tema>();
	this.tiempoDeRevision = tiempoDeRevision;
	this.nombre = nombre;
    }

    public Revisor(int id, int tiempoDeRevision, String nombre) {
	this.id = id + "";
	this.listOfTemas = new ArrayList<Tema>();
	this.tiempoDeRevision = tiempoDeRevision;
	this.nombre = nombre;
    }

    public Revisor(List<Tema> temas, int tiempoDeRevision, String nombre, String id) {
	this.listOfTemas = temas;
	this.tiempoDeRevision = tiempoDeRevision;
	this.id = id;
	this.nombre = nombre;
    }

    public List<Tema> getListOfTemas() {
	return listOfTemas;
    }

    public int getTiempoDeRevision() {
	return tiempoDeRevision;
    }

    public String getId() {
	return id;
    }

    public String getNombre() {
	return nombre;
    }

    @Override
    public String toString() {
	return "Nombre:" + nombre + " tiempo de revision : " + tiempoDeRevision;
    }

}
