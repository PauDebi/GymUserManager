/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Pau_Clase
 */
public class Review {
    int id;
    int idIntent;
    int idReviewer;
    int valoracion;
    String comentari;

    public Review(int id, int idIntent, int idReviewer, int valoracion, String comentari) {
        this.id = id;
        this.idIntent = idIntent;
        this.idReviewer = idReviewer;
        this.valoracion = valoracion;
        this.comentari = comentari;
    }

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdIntent() {
        return idIntent;
    }

    public void setIdIntent(int idIntent) {
        this.idIntent = idIntent;
    }

    public int getIdReviewer() {
        return idReviewer;
    }

    public void setIdReviewer(int idReviewer) {
        this.idReviewer = idReviewer;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", idIntent=" + idIntent + ", idReviewer=" + idReviewer + ", valoracion=" + valoracion + ", comentari=" + comentari + '}';
    }

    
    
}
