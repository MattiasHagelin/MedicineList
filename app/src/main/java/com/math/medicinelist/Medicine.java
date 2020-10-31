package com.math.medicinelist;

public class Medicine {

    private String name;
    private String strength;
    private String dose;

    public Medicine(String name, String strength, String dose) {
        setName(name);
        setStrength(strength);
        setDose(dose);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}
