package com.wehrmacht.EncryptorDecryptor;

import com.wehrmacht.components.PlugBoard;
import com.wehrmacht.components.Reflector;
import com.wehrmacht.components.Rotor;
import com.wehrmacht.enums.ReflectorEnum;
import com.wehrmacht.enums.RotorEnum;
import com.wehrmacht.logging.SimpleMessageFormatter;
import com.wehrmacht.utils.EnigmaUtils;

import java.util.Arrays;
import java.util.List;
import java.util.logging.*;

public class EnigmaEncoderDecoder {

    List<Rotor> rotorList;
    PlugBoard plugBoard;
    Reflector reflector;

    private final String RIGHT_ROTOR = "Right Rotor";
    private final String MIDDLE_ROTOR = "Middle Rotor";
    private final String LEFT_ROTOR = "Left Rotor";

    private static final Logger logger = Logger.getLogger(EnigmaEncoderDecoder.class.getName());

    /**
     * Rotor order given from right to left
     * i.e left most one will be rotor 1
     *
     * @param r3 - right rotor wiring config
     * @param r2 - middle rotor wiring config
     * @param r1 - left rotor wiring config
     * @param reflector - reflector to use
     * @param plainMessage - plain message to encrypt
     * @return
     */
    public String getEncryptedMessage(RotorEnum r3, RotorEnum r2, RotorEnum r1,int[] ringSettings, int[] startingOffsets, ReflectorEnum reflector, String[] plugBoardMappings, String plainMessage) {
        // Set up the logger programmatically
        setupLogger();
        initializeComponents(r3, r2, r1, ringSettings,startingOffsets,reflector, plugBoardMappings);
        return encode(plainMessage);
    }

    /**
     * technically all this method does is the encoding of the given string,
     * but when an encoded message is provided to this method with the same set of
     * rotors , rotor order, reflector, ring and position settings, this method would
     * yield the decrypted form of the message
     * @param plainMessage - string to encrypt
     * @return
     */
    private String encode(String plainMessage) {
        StringBuilder encryptedMessage = new StringBuilder();
        char[] charArray = plainMessage.toCharArray();
        for(char inputLetter : charArray){
            rotateRotors();
            Rotor rightRotor = findRotorById(RIGHT_ROTOR);
            Rotor middleRotor = findRotorById(MIDDLE_ROTOR);
            Rotor leftRotor = findRotorById(LEFT_ROTOR);


            // right to left flow
            char encodedLetter = this.plugBoard.swap(inputLetter);
            encodedLetter = rightRotor.encodeRightToLeft(encodedLetter);
            encodedLetter = middleRotor.encodeRightToLeft(encodedLetter);
            encodedLetter = leftRotor.encodeRightToLeft(encodedLetter);

            encodedLetter = this.reflector.getReflectedOutput(EnigmaUtils.toAlphabetIndex(encodedLetter));

            // left to right flow
            encodedLetter = leftRotor.encodeLeftToRight(encodedLetter);
            encodedLetter = middleRotor.encodeLeftToRight(encodedLetter);
            encodedLetter = rightRotor.encodeLeftToRight(encodedLetter);

            encodedLetter = this.plugBoard.swap(encodedLetter);

            encryptedMessage.append(encodedLetter);

        }
        return encryptedMessage.toString();
    }



    private void rotateRotors() {
        Rotor rightRotor = findRotorById(RIGHT_ROTOR);
        Rotor middleRotor = findRotorById(MIDDLE_ROTOR);
        Rotor leftRotor = findRotorById(LEFT_ROTOR);


        // if the middle rotor is at notch
        // there's a double stepping mechanism
        // that makes the middle rotor turns twice
        // read more: https://www.cryptomuseum.com/crypto/enigma/working.htm#double
        if (middleRotor.isAtNotch()) {
            middleRotor.turn();
            leftRotor.turn();
        } else if (rightRotor.isAtNotch()) {
            middleRotor.turn();
        }

        rightRotor.turn();

    }

    Rotor findRotorById (String id){
        return this.rotorList.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }


    /**
     * initializes the different components with the given configs
     *
     * @param r3 - right rotors wiring config
     * @param r2 - middle rotors wiring config
     * @param r1 - left rotors wiring config
     * @param reflector - reflector to use
     * @param plugBoardMappings - message to encrypt
     */
    private void initializeComponents(RotorEnum r3, RotorEnum r2, RotorEnum r1,int[] ringSettings,  int[] startingOffsets,ReflectorEnum reflector, String[] plugBoardMappings) {
        this.rotorList = initializeRotors(r3, r2, r1,ringSettings,startingOffsets);
        this.plugBoard = initializePlugBoard(plugBoardMappings);
        this.reflector = initializeReflector(reflector);
    }

    private Reflector initializeReflector(ReflectorEnum reflectorEnum) {
       return new Reflector(reflectorEnum);
    }

    private PlugBoard initializePlugBoard(String[] plugBoardMappings) {
        return new PlugBoard(plugBoardMappings);
    }

    private List<Rotor> initializeRotors(RotorEnum r3, RotorEnum r2, RotorEnum r1,int[] ringSettings, int[] startingOffsets) {
        Rotor rotor3 = new Rotor(ringSettings[2],startingOffsets[2], r3,RIGHT_ROTOR);
        Rotor rotor2 = new Rotor(ringSettings[1],startingOffsets[1], r2, MIDDLE_ROTOR);
        Rotor rotor1 = new Rotor(ringSettings[0], startingOffsets[0],r1,LEFT_ROTOR);

        return Arrays.asList(rotor3, rotor2, rotor1);
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
