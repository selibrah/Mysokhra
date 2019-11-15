package com.my_sokhra.selibrah.mysokhra;

import android.content.Context;
import android.content.SharedPreferences;

public class itemnbr {
    int nbr;


    public itemnbr(int nbr) {
        this.nbr = nbr;
    }

    public itemnbr() {
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {

        this.nbr = nbr;
    }
}
