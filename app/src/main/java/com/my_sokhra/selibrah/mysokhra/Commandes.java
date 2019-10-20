package com.my_sokhra.selibrah.mysokhra;

public class Commandes {
    String date;
    String hr;
    int total;
    String tmtdlvry;
    String name;

    public Commandes(String date, String hr, int total, String tmtdlvry, String name) {
        this.date = date;
        this.hr = hr;
        this.total = total;
        this.tmtdlvry = tmtdlvry;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commandes() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTmtdlvry() {
        return tmtdlvry;
    }

    public void setTmtdlvry(String tmtdlvry) { tmtdlvry = tmtdlvry;
    }
}
