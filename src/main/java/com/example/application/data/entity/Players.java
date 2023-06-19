package com.example.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Players extends AbstractEntity {
    private String imie;
    private String nazwisko;
    private String nick;
    private String pozycja;


    @OneToMany(mappedBy = "druzyna")
    private Team druzyna;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPozycja() {
        return pozycja;
    }

    public void setPozycja(String pozycja) {
        this.pozycja = pozycja;
    }

    public String getDruzyna() {
        return druzyna;
    }

    public void setDruzyna(String druzyna) {
        this.druzyna = druzyna;
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