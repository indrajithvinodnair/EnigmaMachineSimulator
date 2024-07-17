package com.wehrmacht.utils;

import java.util.logging.Logger;

public class EnigmaUtils {

    private static final Logger logger = Logger.getLogger(EnigmaUtils.class.getName());
    public static final int ASCII_CODE= 65;

    public static void logOutput(String component, int offset,char initialInput, char finalOutput) {
        if(-1 == offset){
            logger.fine(component+": "+initialInput+" -> "+finalOutput);
        }else{
            logger.fine(component+formatCharOutput(offset)+": "+initialInput+" -> "+finalOutput);
        }

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
