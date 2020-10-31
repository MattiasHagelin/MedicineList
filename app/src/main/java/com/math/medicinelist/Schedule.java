package com.math.medicinelist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule {
    private ArrayList<LocalTime> doseTimes;
    private LocalDate startDate;
    private LocalDate endDate;
    private Medicine medicine;

    public ArrayList<LocalTime> getDoseTimes() {
        return doseTimes;
    }

    public void setDoseTimes(ArrayList<LocalTime> doseTimes) {
        this.doseTimes = doseTimes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
