package LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Validation.implementation;

import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Entity.Slot;
import LowLevelDesign.DoctorPatientAppointmentApp.MachineCoding.Validation.SlotValidator;

public class HalfHourlySlotValidator implements SlotValidator {
    @Override
    public boolean validateSlot(String start, String end) {
        int diff = Slot.getTimeInMinute(end) - Slot.getTimeInMinute(start);
        if(diff == 30)
            return true;
        return false;
    }

    @Override
    public String slotErrorMsg() {
        return " slots are 30 mins only";
    }
}
