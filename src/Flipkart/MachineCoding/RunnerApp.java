package Flipkart.MachineCoding;

import Flipkart.MachineCoding.Entity.Booking;
import Flipkart.MachineCoding.Entity.Doctor;
import Flipkart.MachineCoding.Service.BookingService;
import Flipkart.MachineCoding.Service.DoctorService;
import Flipkart.MachineCoding.Service.PatientService;
import Flipkart.MachineCoding.Strategies.Implementations.RankSlotsByStartTime;
import Flipkart.MachineCoding.Validation.SlotValidator;
import Flipkart.MachineCoding.Validation.implementation.HalfHourlySlotValidator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunnerApp {
    private SlotValidator slotValidator;
    private DoctorService doctorService;
    private BookingService bookingService;

    public RunnerApp(SlotValidator slotValidator, DoctorService doctorService,
                     BookingService bookingService){
        this.slotValidator = slotValidator;
        this.doctorService = doctorService;
        this.bookingService = bookingService;
    }

    public SlotValidator getSlotValidator() {
        return slotValidator;
    }

    public void setSlotValidator(SlotValidator slotValidator) {
        this.slotValidator = slotValidator;
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void registerDoctor(String name, String spec){
        this.doctorService.registerDoctor(name,spec);
    }

    public void updateDoctorAvailability(String doc,List<String> slots){
        boolean isAllSlotValid = true;
        for(String sl:slots){
            String[] s = sl.split(Constants.SLOT_TIME_SEPARATOR);
            if(!this.slotValidator.validateSlot(s[0],s[1])){
                String error = "Sorry Dr. " + doc + this.slotValidator.slotErrorMsg();
                System.out.println(error);
                isAllSlotValid = false;
                break;
            }
        }
        if(isAllSlotValid) {
            slots.stream().map(x->x.split(Constants.SLOT_TIME_SEPARATOR))
                    .forEach(s -> this.doctorService.updateAvailability(doc, s[0], s[1]));
            System.out.println("Done Doc!");
        }
    }

    public void displayAvailableSlotsBySpeciality(String speciality){
        //Dr.Curious: (9:30-10:00)
        HashMap<String, String> param = new HashMap<>();
        param.put(Constants.SPECIALITY,speciality);
        Map<String,List<Doctor>> availableSlotwiseDoctors =
                this.doctorService.getAvailableDoctors(param);
        for(Map.Entry<String,List<Doctor>> e:availableSlotwiseDoctors.entrySet()){
            for(Doctor d:e.getValue()){
                System.out.println("Dr."+d.getName()+": ("+e.getKey()+")");
            }
        }
    }

    public void bookAppointment(String p, String d, String st){
        Booking booking = this.bookingService.bookAppointment(p,d,st);
        if(booking!=null){
            System.out.println("Booking id: "+booking.getId());
        }
    }

    public void cancelBooking(int id){
        this.bookingService.cancelBooking(id);
    }

    public void displayAllBookingsOfDoctor(String docName){
        this.bookingService.displayAllDoctorAppointments(docName);
    }

    public void displayAllBookingsOfPatient(String docName){
        this.bookingService.displayAllPatientAppointments(docName);
    }

    public void getTrendingDoctor(){
        System.out.println("Dr. "+this.bookingService.getTrendingDoctor()+
                " trending doctor");
    }

    public static void main(String[] args) {
        DoctorService doctorService1 = new DoctorService(new RankSlotsByStartTime());
        PatientService patientService1 = new PatientService();
        RunnerApp runnerApp = new RunnerApp(new HalfHourlySlotValidator(),
                doctorService1,
                new BookingService(doctorService1,patientService1));

        runnerApp.registerDoctor("Curious","Cardiologist");
        runnerApp.updateDoctorAvailability("Curious",Arrays.asList("9:30-10:30"));
        runnerApp.updateDoctorAvailability("Curious",
                Arrays.asList("9:30-10:00", "12:30-13:00", "16:00-16:30"));
        System.out.println("################################################");

        runnerApp.registerDoctor("Dreadful","Dermatologist");
        runnerApp.updateDoctorAvailability("Dreadful",
                Arrays.asList("9:30-10:00", "12:30-13:00", "16:00-16:30"));
        System.out.println("################################################");

        runnerApp.displayAvailableSlotsBySpeciality("Cardiologist");
        System.out.println("################################################");

        runnerApp.bookAppointment("PatientA", "Curious", "12:30");
        System.out.println("################################################");

        runnerApp.displayAvailableSlotsBySpeciality("Cardiologist");
        System.out.println("################################################");

        runnerApp.displayAvailableSlotsBySpeciality("Dermatologist");
        System.out.println("################################################");

        runnerApp.bookAppointment("PatientA", "Dreadful", "12:30");
        System.out.println("################################################");

        runnerApp.bookAppointment("PatientB", "Curious", "12:30");
        System.out.println("################################################");

        runnerApp.cancelBooking(1234);
        System.out.println("################################################");

        runnerApp.displayAvailableSlotsBySpeciality("Cardiologist");
        System.out.println("################################################");

        runnerApp.bookAppointment("PatientB", "Curious", "12:30");
        System.out.println("################################################");

        runnerApp.displayAvailableSlotsBySpeciality("Cardiologist");
        System.out.println("################################################");

        runnerApp.registerDoctor("Daring","Dermatologist");
        runnerApp.updateDoctorAvailability("Daring",
                Arrays.asList("11:30-12:00", "14:00-14:30"));
        System.out.println("################################################");

        runnerApp.displayAvailableSlotsBySpeciality("Dermatologist");
        System.out.println("################################################");

        runnerApp.displayAllBookingsOfDoctor("Curious");
        System.out.println("################################################");

        runnerApp.displayAllBookingsOfDoctor("Daring");
        System.out.println("################################################");

        runnerApp.displayAllBookingsOfDoctor("Dreadful");
        System.out.println("################################################");

        runnerApp.displayAllBookingsOfPatient("PatientA");
        System.out.println("################################################");
        runnerApp.displayAllBookingsOfPatient("PatientB");
        System.out.println("################################################");

        runnerApp.getTrendingDoctor();
        System.out.println("################################################");

        runnerApp.bookAppointment("PatientC", "Dreadful", "9:30");
        System.out.println("################################################");
        runnerApp.bookAppointment("PatientD", "Dreadful", "12:30");
        System.out.println("################################################");
        runnerApp.getTrendingDoctor();
        System.out.println("################################################");

        System.out.println();
    }
}
