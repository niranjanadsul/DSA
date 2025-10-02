package LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Service;

import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Constants;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Entity.Doctor;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Enums.Speciality;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Strategies.SlotRanker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorService {
    private HashMap<String, Doctor> doctors;
    private SlotRanker slotRanker;

    public DoctorService(SlotRanker  slotRanker) {
        this.doctors = new HashMap<>();
        this.slotRanker=slotRanker;
    }

    public HashMap<String, Doctor> getDoctors() {
        return doctors;
    }

    public SlotRanker getSlotRanker() {
        return slotRanker;
    }

    public Doctor registerDoctor(String name, String speciality){
        Speciality s = getSpeciality(speciality);
        Doctor d = new Doctor(name,s);
        this.doctors.put(name,d);
        System.out.println("Welcome Dr. "+name+" !!");
        return d;
    }

    private static Speciality getSpeciality(String speciality) {
        Speciality s = null;
        if(speciality.equals("Orthopedic"))
            s=Speciality.ORTHOPEDIC;
        if(speciality.equals("Dermatologist"))
            s=Speciality.DERMATOLOGIST;
        if(speciality.equals("Cardiologist"))
            s=Speciality.CARDIOLOGIST;
        if(speciality.equals("General Physician"))
            s=Speciality.GENERAL_PHYSICIAN;
        return s;
    }

    public void updateAvailability(String name, String start, String end){
        if (this.doctors.containsKey(name)){
            this.doctors.get(name).updateAvailability(start,end);
        }
    }

    public List<Doctor> getDoctorBasedOnSpeciality(String spec){
        Speciality s = getSpeciality(spec);
        return this.doctors.values().stream().filter(doctor -> doctor.getSpeciality().equals(s)).toList();
    }

    public Map<String,List<Doctor>> getAvailableDoctors(HashMap<String,String> searchParameter){
        if(searchParameter.containsKey(Constants.SPECIALITY)){
            List<Doctor> d = getDoctorBasedOnSpeciality(searchParameter.get(Constants.SPECIALITY));
            return this.slotRanker.rank(searchParameter,d);
        }
        return this.slotRanker.rank(searchParameter, (List<Doctor>) this.doctors.values());
    }

}
