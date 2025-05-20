package Flipkart.MachineCoding.Entity;

import Flipkart.MachineCoding.Enums.Speciality;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Doctor{
    String name;
    Speciality speciality;
    Map<String,Slot> availabilitySlots;

    public Doctor(String name, Speciality speciality){
        this.name = name;
        this.speciality = speciality;
        this.availabilitySlots=new HashMap<>();
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void updateAvailability(String start, String end){
        Slot s = new Slot(start,end);
        this.availabilitySlots.put(start,s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailabilitySlots(Map<String, Slot> availabilitySlots) {
        this.availabilitySlots = availabilitySlots;
    }

    public Map<String,Slot> getAvailabilitySlots(){
        return this.availabilitySlots;
    }
}
