package Flipkart.MachineCoding.Strategies.Implementations;

import Flipkart.MachineCoding.Constants;
import Flipkart.MachineCoding.Entity.Doctor;
import Flipkart.MachineCoding.Entity.Slot;
import Flipkart.MachineCoding.Strategies.SlotRanker;
import Flipkart.MachineCoding.Validation.SlotValidator;

import java.util.*;

public class RankSlotsByStartTime implements SlotRanker {
    @Override
    public Map<String, List<Doctor>> rank(HashMap<String, String> searchParameter, List<Doctor> doctors) {
        Map<String,List<Doctor>> rankedSlot = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1=o1.split(Constants.SLOT_TIME_SEPARATOR)[0];
                int i1 = Slot.getTimeInMinute(s1);

                String s2=o2.split(Constants.SLOT_TIME_SEPARATOR)[0];
                int i2 = Slot.getTimeInMinute(s2);
                return i1-i2;
            }
        });

        for(Doctor d:doctors){
            for(Slot s:d.getAvailabilitySlots().values()){
                if(!s.isBooked())
                    rankedSlot.computeIfAbsent(s.getSlotString(),k->new ArrayList<>()).add(d);
            }
        }
        return rankedSlot;
    }
}
