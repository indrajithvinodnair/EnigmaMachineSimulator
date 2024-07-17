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

        EnigmaEncoderDecoder encryptor= new EnigmaEncoderDecoder();
        String message = "TCMRXUAFBO";
        String[] plugBoardMappings = {"I-K","M-O","P-S"};
        int[] ringSettings = {0,0,0}; //left,mid,right
        int[] startingOffsets = {0,0,8};
        String encryptedMessage = encryptor.getEncryptedMessage(RotorEnum.ROTOR_III, RotorEnum.ROTOR_II, RotorEnum.ROTOR_I,ringSettings, startingOffsets,ReflectorEnum.REFLECTOR_B,plugBoardMappings,message);
        System.out.println("Encoded Message : "+ encryptedMessage);

    }
}
