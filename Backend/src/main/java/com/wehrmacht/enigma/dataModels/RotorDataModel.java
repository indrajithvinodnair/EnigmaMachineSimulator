package com.wehrmacht.enigma.dataModels;

public class  RotorDataModel {
    // I,II,III,IV,V
    String rotorCode;
    String ringSetting;
    String startingIndex;
    // left, mid , right
    String rotorPlacement;


    public String getRotorCode() {
        return rotorCode;
    }

    public void setRotorCode(String rotorCode) {
        this.rotorCode = rotorCode;
    }

    public String getRotorPlacement() {
        return rotorPlacement;
    }

    public void setRotorPlacement(String rotorPlacement) {
        this.rotorPlacement = rotorPlacement;
    }

    public String getRingSetting() {
        return ringSetting;
    }

    public void setRingSetting(String ringSetting) {
        this.ringSetting = ringSetting;
    }

    public String getStartingIndex() {
        return startingIndex;
    }

    public void setStartingIndex(String startingIndex) {
        this.startingIndex = startingIndex;
    }
}
