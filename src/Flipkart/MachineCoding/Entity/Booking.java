package Flipkart.MachineCoding.Entity;

import Flipkart.MachineCoding.Enums.BookingType;

public class Booking {
    int id;
    BookingType bookingType;
    String patientName;
    String doctorName;
    Slot slot;

    public Booking(int id, String patientName, String doctorName, Slot slot) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.slot = slot;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
