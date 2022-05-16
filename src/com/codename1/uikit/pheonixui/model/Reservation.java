package com.codename1.uikit.pheonixui.model;

import java.util.Date;

public class Reservation {
    int idReservation;
    Date dateReservation;
    String nbplaceReservation;
    String idUser;
    String archive="0";
    String nomResto;
    String image;
    String adresse;
    String idRestaurant;

    public Reservation() {
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getNbplaceReservation() {
        return nbplaceReservation;
    }

    public void setNbplaceReservation(String nbplaceReservation) {
        this.nbplaceReservation = nbplaceReservation;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public String getNomResto() {
        return nomResto;
    }

    public void setNomResto(String nomResto) {
        this.nomResto = nomResto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
