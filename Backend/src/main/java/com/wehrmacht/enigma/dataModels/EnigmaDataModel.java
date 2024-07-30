package com.wehrmacht.enigma.dataModels;

import java.util.List;

public class EnigmaDataModel {
    List<RotorDataModel> rotors;
    String reflectorCode;
    List<String> plugBoardMappings;
    String output;
    String keyboardInput;
    List<String> steps;

    public List<RotorDataModel> getRotors() {
        return rotors;
    }

    public void setRotors(List<RotorDataModel> rotors) {
        this.rotors = rotors;
    }

    public String getReflectorCode() {
        return reflectorCode;
    }

    public void setReflectorCode(String reflectorCode) {
        this.reflectorCode = reflectorCode;
    }

    public List<String> getPlugBoardMappings() {
        return plugBoardMappings;
    }

    public void setPlugBoardMappings(List<String> plugBoardMappings) {
        this.plugBoardMappings = plugBoardMappings;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getKeyboardInput() {
        return keyboardInput;
    }

    public void setKeyboardInput(String keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
