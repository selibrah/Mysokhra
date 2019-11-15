package com.my_sokhra.selibrah.mysokhra;

public class Kind {
    String imgurl;
    String prix;
    String name;
    int nbr;


    public Kind(String imgurl, String prix, String name, int nbr) {
        this.imgurl = imgurl;
        this.prix = prix;
        this.name = name;
        this.nbr = nbr;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public Kind() {
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
