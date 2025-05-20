package Flipkart.MachineCoding;

import Flipkart.MachineCoding.Entity.Doctor;
import Flipkart.MachineCoding.Service.DoctorService;
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

    public RunnerApp(SlotValidator slotValidator, DoctorService doctorService){
        this.slotValidator = slotValidator;
        this.doctorService = doctorService;
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

    public static void main(String[] args) {
        RunnerApp runnerApp = new RunnerApp(new HalfHourlySlotValidator(),
                new DoctorService(new RankSlotsByStartTime()));

        runnerApp.registerDoctor("Curious","Cardiologist");
        runnerApp.updateDoctorAvailability("Curious",Arrays.asList("9:30-10:30"));
        runnerApp.updateDoctorAvailability("Curious",
                Arrays.asList("9:30-10:00", "12:30-13:00", "16:00-16:30"));

        runnerApp.registerDoctor("Dreadful","Dermatologist");
        runnerApp.updateDoctorAvailability("Dreadful",
                Arrays.asList("9:30-10:00", "12:30-13:00", "16:00-16:30"));

        runnerApp.displayAvailableSlotsBySpeciality("Cardiologist");
        System.out.println();
    }
}
