package Flipkart.MachineCoding.Validation;

public interface SlotValidator {
    boolean validateSlot(String start, String end);
    String slotErrorMsg();

}
