package Flipkart.MachineCoding.Entity;

import Flipkart.MachineCoding.Constants;

import java.util.LinkedList;
import java.util.Queue;

public class Slot {
    String startTime;
    String endTime;
    boolean isBooked;
    Queue<Integer> patientWaitingQueue;//contains booking id

    public Slot(String startTime, String endTime) {
        this.endTime = endTime;
        this.startTime = startTime;
        this.isBooked=false;
        this.patientWaitingQueue = new LinkedList<>();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getSlotString(){
        return this.startTime+ Constants.SLOT_TIME_SEPARATOR+this.endTime;
    }

    public static int getTimeInMinute(String time){
        String[] s = time.split(":");
        return Integer.parseInt(s[0])*60+Integer.parseInt(s[1]);
    }

    public Queue<Integer> getPatientWaitingQueue() {
        return patientWaitingQueue;
    }

    public void setPatientWaitingQueue(Queue<Integer> patientWaitingQueue) {
        this.patientWaitingQueue = patientWaitingQueue;
    }
}
