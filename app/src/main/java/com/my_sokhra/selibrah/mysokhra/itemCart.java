package com.my_sokhra.selibrah.mysokhra;

public class itemCart {
    String imgurl;
    String prix;
    String name;
    String resto;
    String menu;
    int nbr;
    int total;

    public String getResto() {
        return resto;
    }

    public void setResto(String resto) {
        this.resto = resto;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public itemCart() {
    }

    public itemCart(String imgurl, String prix, String name, String resto, String menu, int nbr, int total) {
        this.imgurl = imgurl;
        this.prix = prix;
        this.name = name;
        this.resto = resto;
        this.menu = menu;
        this.nbr = nbr;
        this.total = total;
    }
}
