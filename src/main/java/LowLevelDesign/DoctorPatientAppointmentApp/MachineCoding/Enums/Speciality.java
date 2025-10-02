package LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Enums;

public enum Speciality {
    CARDIOLOGIST("Cardiologist"), DERMATOLOGIST("Dermatologist"), ORTHOPEDIC("Orthopedic"), GENERAL_PHYSICIAN("General Physician");
    String value;
    Speciality(String val){
        this.value=val;
    }
}
