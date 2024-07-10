package com.wehrmacht;

import com.wehrmacht.EncryptorDecryptor.EnigmaEncoderDecoder;
import com.wehrmacht.enums.ReflectorEnum;
import com.wehrmacht.enums.RotorEnum;
import com.wehrmacht.logging.SimpleMessageFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        // Set up the logger programmatically
        setupLogger();

        EnigmaEncoderDecoder encryptor= new EnigmaEncoderDecoder();
        String message = "THIS";
        String[] plugBoardMappings = {};
        int[] ringSettings = {0,0,0}; //left,mid,right
        int[] startingOffsets = {0,0,8};
        String encryptedMessage = encryptor.getEncryptedMessage(RotorEnum.ROTOR_III, RotorEnum.ROTOR_II, RotorEnum.ROTOR_I,ringSettings, startingOffsets,ReflectorEnum.REFLECTOR_B,plugBoardMappings,message);
        System.out.println(encryptedMessage);

    }

    private static void setupLogger() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.FINE);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINE);
        consoleHandler.setFormatter(new SimpleMessageFormatter());

        // Remove existing handlers
        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        // Add the custom handler
        rootLogger.addHandler(consoleHandler);
    }
}
