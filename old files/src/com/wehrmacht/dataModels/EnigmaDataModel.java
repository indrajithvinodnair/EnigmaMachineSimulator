package com.wehrmacht.dataModels;

public class EnigmaDataModel {
    RotorDataModel[] rotors;
    String reflectorName;
    String plugBoardConnections;
    String output;
    String keyboardInput;
    String[] steps;

    public RotorDataModel[] getRotors() {
        return rotors;
    }

    public void setRotors(RotorDataModel[] rotors) {
        this.rotors = rotors;
    }

    public String getReflectorName() {
        return reflectorName;
    }

    public void setReflectorName(String reflectorName) {
        this.reflectorName = reflectorName;
    }

    public String getPlugBoardConnections() {
        return plugBoardConnections;
    }

    public void setPlugBoardConnections(String plugBoardConnections) {
        this.plugBoardConnections = plugBoardConnections;
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

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }
}
