package com.wehrmacht.components;

import com.wehrmacht.enums.FlowDirection;
import com.wehrmacht.enums.RotorEnum;
import com.wehrmacht.utils.EnigmaUtils;

public class Rotor {

    String id;
    // rotors outer labels , can be rotated while the wiring remains stationary
    int ringSetting;
    // tracks the rotation of rotor with each key pressed
    int position;
    int notchPosition;
    // used for encoding
    int[] inputWiringAsIndexArray;
    // used for decoding
    int[] outputWiringAsIndexArray;

    public Rotor(int ringSetting,int startingPosition, RotorEnum wiring, String id) {
        this.ringSetting = ringSetting;
        this.id = id;
        this.position = startingPosition;
        createInputWirings(wiring);
    }

    private void createInputWirings(RotorEnum wiring) {
        this.inputWiringAsIndexArray = new int[26];
        this.outputWiringAsIndexArray = new int[26];
        int arrayIndex =0;
        for(char letter : wiring.getWiring()){
            int letterIndex = EnigmaUtils.toAlphabetIndex(letter);
            this.inputWiringAsIndexArray[arrayIndex]=letterIndex;
            this.outputWiringAsIndexArray[letterIndex] = arrayIndex;
            arrayIndex++;

        }
        this.notchPosition = EnigmaUtils.toAlphabetIndex(wiring.getNotch());
    }

    public int getRingSetting() {
        return ringSetting;
    }

    public void setRingSetting(int ringSetting) {
        this.ringSetting = ringSetting;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNotchPosition() {
        return notchPosition;
    }

    public void setNotchPosition(int notchPosition) {
        this.notchPosition = notchPosition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getInputWiringAsIndexArray() {
        return inputWiringAsIndexArray;
    }

    public void setInputWiringAsIndexArray(int[] inputWiringAsIndexArray) {
        this.inputWiringAsIndexArray = inputWiringAsIndexArray;
    }

    public int[] getOutputWiringAsIndexArray() {
        return outputWiringAsIndexArray;
    }

    public void setOutputWiringAsIndexArray(int[] outputWiringAsIndexArray) {
        this.outputWiringAsIndexArray = outputWiringAsIndexArray;
    }

    /**
     * increments offset position by 1 , simulating the rotation of the rings.
     * if the rotation goes beyond 25, its reset to 0
     */
    public void turn() {
        this.position = (this.position + 1) % 26;
    }


    private void reset() {
        this.ringSetting = 0;
        this.position = 0;
    }

    public boolean isAtNotch(){
        return this.position == this.notchPosition;
    }

    /**
     * Encodes given character in right to left flow
     * @param inputLetter
     * @return
     */
    public char encodeRightToLeft(char inputLetter){
        return EnigmaUtils.toChar(encode(inputLetter,FlowDirection.INPUT));
    }

    /**
     * Encodes given character in left to right flow
     * @param inputLetter
     * @return
     */
    public char encodeLeftToRight(char inputLetter){
        char output = EnigmaUtils.toChar(encode(inputLetter,FlowDirection.OUTPUT));
        EnigmaUtils.logOutput(id,position,inputLetter,output);
        return output;

    }
    public int encode(char letter, FlowDirection direction) {
        int letterIndex = EnigmaUtils.toAlphabetIndex(letter);
        int shift = position - ringSetting;

        int[] wireMapping = direction == FlowDirection.INPUT ? inputWiringAsIndexArray : outputWiringAsIndexArray;

        // Input letter is taken an alphabet index from 0
        // Shift the index by the position of the rotor - the ring setting
        // Sum with 26 just so we can get the new index as a positive number
        // Normalize to a number between 0 and 25 which is in the alphabet , for 0 based indexing
        // Ex: letterIndex = 0 (A), position = 1 (B, as it turns before the input), ringSetting = 0 (no shift), mapping = Input (look at createInputWirings)
        // wireMapping[(0 + 1 - 0 +26) % 26] = wireMapping[27%26] = wireMapping[1] = 10 => K (alphabet index - 1 since we start from 0, otherwise it would be 11 as 10 is J)

        int outboundLetterIndex = wireMapping[EnigmaUtils.normalizeToAlphabetBounds(letterIndex + shift)];
//        System.out.println("letterIndex: " + letterIndex + ", ringSetting: " + ringSetting + ", position: " + position + ", shift = position - offset = " + shift + ", outboundIndex: " + outboundLetterIndex + ", normalizeToAlphabetBounds: " + EnigmaUtils.normalizeToAlphabetBounds(outboundLetterIndex - shift));

        // Sending the letter one shift below to the next rotor
        return EnigmaUtils.normalizeToAlphabetBounds(outboundLetterIndex - shift);
    }


}
