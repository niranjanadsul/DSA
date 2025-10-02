package LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Strategies;

import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Entity.Doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SlotRanker {
    public Map<String, List<Doctor>> rank(HashMap<String,String> searchParameter, List<Doctor> doctors);
}
