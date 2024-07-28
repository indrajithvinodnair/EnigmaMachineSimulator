package com.wehrmacht.enigma.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EnigmaUtils {

    public static final int ASCII_CODE= 65;

    // logged messages will be saved here and then will be sent to FE
    private static final List<String> logMessages = new ArrayList<>();

    public static void logInfo(String message){
        logMessages.add(message);
    }

    public static void logComponentOutput(String component, int offset, char initialInput, char finalOutput) {
        String message;
        if(-1 == offset){
            message= component+": "+initialInput+" -> "+finalOutput;
        }else{
           message = component+formatCharOutput(offset)+": "+initialInput+" -> "+finalOutput;
        }
        logMessages.add(message);
    }


    public static List<String> getLogMessages() {
        return logMessages;
    }
    public static void clearLogs(){
        logMessages.clear();
    }

    public static String formatCharOutput(int letterIndex){
        return  "(" +toChar(letterIndex) + ")";
    }

    public static char toChar(int letterIndex){
       return (char) (letterIndex+ASCII_CODE);
    }

    public static int toAlphabetIndex(char letter){
        return normalizeToAlphabetBounds(letter - 65);
    }

    public static int normalizeToAlphabetBounds(int value){
        return (value + 26) % 26;
    }
}
