package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Teams extends AbstractEntity {
    private String nazwa;
    private String image;
    private String podtytul;
    private String opiskrotki;
    private String opisdlugi;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPodtytul() {
        return podtytul;
    }

    public void setPodtytul(String podtytul) {
        this.podtytul = podtytul;
    }

    public String getOpiskrotki() {
        return opiskrotki;
    }

    public void setOpiskrotki(String opiskrotki) {
        this.opiskrotki = opiskrotki;
    }

    public String getOpisdlugi() {
        return opisdlugi;
    }

    public void setOpisdlugi(String opisdlugi) {
        this.opisdlugi = opisdlugi;
    }
}


/*
    private String nazwa;
    private String image;
    private String podtytul;
    private String opiskrotki;
    private String opisdlugi;

    @OneToMany(mappedBy = "druzyna")
    private List<Playersi> playersis;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPodtytul() {
        return podtytul;
    }

    public void setPodtytul(String podtytul) {
        this.podtytul = podtytul;
    }

    public String getOpiskrotki() {
        return opiskrotki;
    }

    public void setOpiskrotki(String opiskrotki) {
        this.opiskrotki = opiskrotki;
    }

    public String getOpisdlugi() {
        return opisdlugi;
    }

    public void setOpisdlugi(String opisdlugi) {
        this.opisdlugi = opisdlugi;
    }

    public List<Playersi> getPlayers() {
        return playersis;
    }

    public void setPlayers(List<Playersi> playersis) {
        this.playersis = playersis;
    }
}
*/