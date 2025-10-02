package LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Service;

import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Entity.Patient;

import java.util.HashMap;
import java.util.Map;

public class PatientService {
    HashMap<String, Patient> patients;

    public PatientService() {
        this.patients = new HashMap<>();
    }

    public Patient registerPatient(String name){
        if(this.patients.containsKey(name))
            return this.patients.get(name);
        Patient p = new Patient(name);
        this.patients.put(name,p);
        return p;
    }

    public Map<String, Patient> getPatients(){
        return this.patients;
    }
}
