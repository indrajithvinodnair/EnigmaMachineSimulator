package com.wehrmacht.enigma.components;

import com.wehrmacht.enigma.utils.EnigmaUtils;
import com.wehrmacht.enigma.enums.ReflectorEnum;


public class Reflector {

    int[] reflectorWiring;

    public Reflector(String reflectorCode) {
        ReflectorEnum reflectorWiring = ReflectorEnum.getReflectorByCode(reflectorCode.charAt(0));
        this.reflectorWiring = new int[26];
        int index=0;
        for(char letter : reflectorWiring.getWiring()){
            this.reflectorWiring[index] = EnigmaUtils.toAlphabetIndex(letter);
            index++;
        }
    }

    public char getReflectedOutput(int letterIndex){
       char output =  EnigmaUtils.toChar(this.reflectorWiring[letterIndex]);
       EnigmaUtils.logComponentOutput("Reflector",-1,EnigmaUtils.toChar(letterIndex),output);
        return output;
    }
}
