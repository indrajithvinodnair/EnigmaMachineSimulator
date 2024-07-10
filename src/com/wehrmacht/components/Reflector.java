package com.wehrmacht.components;

import com.wehrmacht.enums.ReflectorEnum;
import com.wehrmacht.utils.EnigmaUtils;

import java.util.logging.Logger;

public class Reflector {

    int reflectorWiring[];

    public Reflector(ReflectorEnum reflectorWiring){
        this.reflectorWiring = new int[26];
        int index=0;
        for(char letter : reflectorWiring.getWiring()){
            this.reflectorWiring[index] = EnigmaUtils.toAlphabetIndex(letter);
            index++;
        }
    }

    public char getReflectedOutput(int letterIndex){
       char output =  EnigmaUtils.toChar(this.reflectorWiring[letterIndex]);
       EnigmaUtils.logOutput("Reflector",-1,EnigmaUtils.toChar(letterIndex),output);
        return output;
    }

    public int[] getReflectorWiring() {
        return reflectorWiring;
    }

    public void setReflectorWiring(int[] reflectorWiring) {
        this.reflectorWiring = reflectorWiring;
    }
}
