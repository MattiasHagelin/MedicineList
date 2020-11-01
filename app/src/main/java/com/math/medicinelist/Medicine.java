package com.math.medicinelist;

import java.util.ArrayList;

public class Medicine {

    private String name;
    private String strength;
    private ArrayList<String> doses;
    private Schedule schedule;

    public Medicine(String name, String strength, String... doses) {
        this.doses = new ArrayList<>();
        setName(name);
        setStrength(strength);
        for (String s : doses){
            setDoses(s);
        }
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

    public ArrayList<String> getDoses() {
        return doses;
    }

    public void setDoses(String dose) {
        this.doses.add(dose);
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
