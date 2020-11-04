package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;
import org.joda.time.Years;

public final class Client {
    private int id;
    private @NotNull String fio;
    private LocalDate birthday;
    private @NotNull String gender;
    private int passport_num_series;

    public Client(int id, @NotNull String fio, LocalDate birthday, @NotNull String gender, int passport_num_series) {
        this.id = id;
        this.fio = fio;
        this.birthday = birthday;
        this.gender = gender;
        this.passport_num_series = passport_num_series;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPassport_num_series() {
        return passport_num_series;
    }

    public void setPassport_num_series(int passport_num_series) {
        this.passport_num_series = passport_num_series;
    }
    public int getAge(){
        return Years.yearsBetween(birthday,LocalDate.now()).getYears();
    }
}
