package com.codename1.uikit.pheonixui.model;

import java.util.Date;

public class Evenement {
    int idEvent;
    String nomEvent;
    Date ddEvent;
    Date dfEvent;
    String themeEvent;
    String adresseEvent;
    String telephone;
    String archive="0";

    public Evenement() {
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public Date getDdEvent() {
        return ddEvent;
    }

    public void setDdEvent(Date ddEvent) {
        this.ddEvent = ddEvent;
    }

    public Date getDfEvent() {
        return dfEvent;
    }

    public void setDfEvent(Date dfEvent) {
        this.dfEvent = dfEvent;
    }

    public String getThemeEvent() {
        return themeEvent;
    }

    public void setThemeEvent(String themeEvent) {
        this.themeEvent = themeEvent;
    }

    public String getAdresseEvent() {
        return adresseEvent;
    }

    public void setAdresseEvent(String adresseEvent) {
        this.adresseEvent = adresseEvent;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }
}
