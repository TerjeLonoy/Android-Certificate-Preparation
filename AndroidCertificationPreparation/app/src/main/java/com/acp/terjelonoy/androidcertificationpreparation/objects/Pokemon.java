package com.acp.terjelonoy.androidcertificationpreparation.objects;

/**
 * Created by terjelonoy on 2/26/17.
 */

public class Pokemon {
    private String name, number;

    public Pokemon(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber () {
        return this.number;
    }

    public void setNumber (String number) {
        this.number = number;
    }
}
