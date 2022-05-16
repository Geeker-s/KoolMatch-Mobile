package com.codename1.uikit.pheonixui.model;

public class Recette {
    int idRecette;
    String nomRecette;
    String photoRecette;
    String descriptionRecette;
    String categorieRecette;
    String dureeRecette;
    String archive="0";

    public Recette() {
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public String getNomRecette() {
        return nomRecette;
    }

    public void setNomRecette(String nomRecette) {
        this.nomRecette = nomRecette;
    }

    public String getPhotoRecette() {
        return photoRecette;
    }

    public void setPhotoRecette(String photoRecette) {
        this.photoRecette = photoRecette;
    }

    public String getDescriptionRecette() {
        return descriptionRecette;
    }

    public void setDescriptionRecette(String descriptionRecette) {
        this.descriptionRecette = descriptionRecette;
    }

    public String getCategorieRecette() {
        return categorieRecette;
    }

    public void setCategorieRecette(String categorieRecette) {
        this.categorieRecette = categorieRecette;
    }

    public String getDureeRecette() {
        return dureeRecette;
    }

    public void setDureeRecette(String dureeRecette) {
        this.dureeRecette = dureeRecette;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }
}
