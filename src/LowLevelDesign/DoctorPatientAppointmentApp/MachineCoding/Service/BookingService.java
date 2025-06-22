package LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Service;


import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Entity.Booking;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Entity.Doctor;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Entity.Patient;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Entity.Slot;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Enums.BookingType;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Enums.Speciality;

import java.util.*;

public class BookingService {
    int bookingIds;
    DoctorService doctorService;
    PatientService patientService;
    Map<Integer,Booking> bookings;
    Map<Doctor, List<Booking>> doctorBookings;
    Map<Patient,List<Booking>> patientBookings;

    public BookingService(DoctorService doctorService,PatientService patientService){
        this.bookingIds = 1234;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.doctorBookings = new HashMap<>();
        this.patientBookings = new HashMap<>();
        this.bookings=new HashMap<>();
    }

    public Booking bookAppointment(String patient, String doctor, String startTime){
        Patient p = this.patientService.registerPatient(patient);
        if(this.patientBookings.containsKey(p)) {
            for (Booking b : this.patientBookings.get(p)) {
                if (b.getBookingType().equals(BookingType.Confirmed) && b.getSlot().getStartTime().equals(startTime)) {
                    System.out.println("Sorry "+b.getPatientName()+" you have other booking for Dr. "
                            +b.getDoctorName()+" for slot " + startTime);
                    return null;
                }
            }
        }
        Doctor d = doctorService.getDoctors().get(doctor);
        Slot slot = d.getAvailabilitySlots().get(startTime);
        if(slot == null){
            System.out.println("Sorry slot not available");
            return null;
        }
        BookingType bookingType=BookingType.Confirmed;
        if(slot.isBooked()){
            bookingType = BookingType.Waiting;
            slot.getPatientWaitingQueue().add(this.bookingIds);
            System.out.println("Adding to waiting list as slot is already booked");
        }
        Booking booking= createBooking(patient, doctor, slot, p, d);
        booking.setBookingType(bookingType);
        return booking;
    }

    private Booking createBooking(String patient, String doctor, Slot slot, Patient p, Doctor d) {
        slot.setBooked(true);
        Booking booking=new Booking(this.bookingIds++, patient, doctor, slot);
        this.bookings.put(booking.getId(),booking);
        this.patientBookings.computeIfAbsent(p, x->new ArrayList<>()).add(booking);
        this.doctorBookings.computeIfAbsent(d, x->new ArrayList<>()).add(booking);
        return booking;
    }

    public void cancelBooking(int bookingId){
        Booking booking = this.bookings.get(bookingId);
        booking.setBookingType(BookingType.Cancelled);
        System.out.println("Booking Cancelled");

        Slot slot = booking.getSlot();
        if(slot.getPatientWaitingQueue().isEmpty()){
            slot.setBooked(false);
        }else{
            int id = slot.getPatientWaitingQueue().poll();
            Booking b=this.bookings.get(id);
            b.setBookingType(BookingType.Confirmed);
        }
    }

    public void displayAllDoctorAppointments(String name){
        Doctor d = this.doctorService.getDoctors().get(name);
        List<Booking> bks = this.doctorBookings.get(d);
        if(bks==null)
            return;
        for(Booking b:bks){
            if(!b.getBookingType().equals(BookingType.Cancelled)){
                System.out.println(b.getPatientName()+" "+b.getBookingType()+" "+
                        b.getSlot().getStartTime()+"-"+b.getSlot().getEndTime());
            }
        }
    }

    public void displayAllPatientAppointments(String name){
        Patient d = this.patientService.getPatients().get(name);
        List<Booking> bks = this.patientBookings.get(d);
        if(bks == null)
            return;
        for(Booking b:bks){
            if(!b.getBookingType().equals(BookingType.Cancelled)){
                Speciality sp =this.doctorService.getDoctors().get(b.getDoctorName()).getSpeciality();
                System.out.println("Dr. "+b.getDoctorName()+" "+sp.toString()+" "
                        +b.getBookingType()+" "+
                        b.getSlot().getStartTime()+"-"+b.getSlot().getEndTime());
            }
        }
    }

    public String getTrendingDoctor(){
        Map<String,Integer> docAppointmentCount = new HashMap<>();
        for(Map.Entry<Doctor, List<Booking>> entry : this.doctorBookings.entrySet()){
            int count = entry.getValue()==null || entry.getValue().isEmpty()?0:
                    this.getBookingCountOfDoctor(entry.getValue());
            docAppointmentCount.put(entry.getKey().getName(),count);
        }
        List<Map.Entry<String, Integer>> ls = new ArrayList<>(docAppointmentCount.entrySet());
        Collections.sort(ls,(a,b)-> b.getValue() - a.getValue());
        return ls.get(0).getKey();
    }

    public int getBookingCountOfDoctor(List<Booking> ls){
        int cnt = 0;
        for(Booking bk:ls){
            if(!bk.getBookingType().equals(BookingType.Cancelled))
                cnt++;
        }
        return cnt;
    }
}
