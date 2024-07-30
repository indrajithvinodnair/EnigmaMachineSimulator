package com.wehrmacht.enigma.service.impl;




import com.wehrmacht.enigma.components.PlugBoard;
import com.wehrmacht.enigma.components.Reflector;
import com.wehrmacht.enigma.components.Rotor;
import com.wehrmacht.enigma.dataModels.EnigmaDataModel;
import com.wehrmacht.enigma.dataModels.RotorDataModel;
import com.wehrmacht.enigma.enums.RotorEnum;
import com.wehrmacht.enigma.logger.SimpleMessageFormatter;
import com.wehrmacht.enigma.service.EnigmaEncoderDecoderService;
import com.wehrmacht.enigma.utils.EnigmaUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.*;

import static com.wehrmacht.enigma.constants.AppConstants.*;


@Service
public class EnigmaEncoderDecoderServiceImpl implements EnigmaEncoderDecoderService {

    List<Rotor> rotorList = new ArrayList<>();
    PlugBoard plugBoard;
    Reflector reflector;



    @Override
    public EnigmaDataModel getEncryptedMessage(EnigmaDataModel enigmaDataModel){
        setupLogger();
        initializeComponents(enigmaDataModel);
        encode(enigmaDataModel);
        return enigmaDataModel;
    }

    private void encode(EnigmaDataModel enigmaDataModel) {
        StringBuilder encryptedMessage = new StringBuilder();
        char[] charArray = enigmaDataModel.getKeyboardInput().replaceAll("\\s+", "").toCharArray();
        for(char inputLetter : charArray){

            Rotor rightRotor = findRotorById(RIGHT_ROTOR);
            Rotor middleRotor = findRotorById(MIDDLE_ROTOR);
            Rotor leftRotor = findRotorById(LEFT_ROTOR);

            EnigmaUtils.logInfo("--------- Rotation for letter: "+inputLetter+" ---------");
            EnigmaUtils.logInfo("--------- Rotors before rotation :"+EnigmaUtils.toChar(leftRotor.getPosition())+" "+EnigmaUtils.toChar(middleRotor.getPosition())+" "+EnigmaUtils.toChar(rightRotor.getPosition())+" ---------");


            rotateRotors();



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
        updateModel(enigmaDataModel,encryptedMessage.toString());
    }

    private void updateModel(EnigmaDataModel enigmaDataModel, String encryptedMessage) {
        enigmaDataModel.setOutput(encryptedMessage);
        enigmaDataModel.setSteps(EnigmaUtils.getLogMessages());
        updateRotorDetailsInModel(enigmaDataModel);
    }

    private void updateRotorDetailsInModel(EnigmaDataModel enigmaDataModel) {
        for(RotorDataModel rotorModel : enigmaDataModel.getRotors()){
            Rotor rotor = findRotorById(rotorModel.getRotorPlacement());
            rotorModel.setStartingIndex(""+rotor.getPosition());
        }
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
        return this.rotorList.stream().filter(r -> Objects.equals(r.getId(), id)).findFirst().orElse(null);
    }

    private void initializeComponents(EnigmaDataModel enigmaDataModel) {
        intializeRotors(enigmaDataModel.getRotors());
        initializePlugBoard(enigmaDataModel.getPlugBoardMappings());
        initializeReflector(enigmaDataModel.getReflectorCode());
        // clear existing log messages
        EnigmaUtils.clearLogs();
    }

    private void initializeReflector(String reflectorName) {
        reflector = new Reflector(reflectorName);
    }

    private void initializePlugBoard(List<String> plugBoardConnections) {
        plugBoard = new PlugBoard(plugBoardConnections);
    }

    private void intializeRotors(List<RotorDataModel> rotors) {
        rotorList.clear();
        for(RotorDataModel rotorDataModel : rotors){
            Rotor rotor = new Rotor(rotorDataModel.getRingSetting(),rotorDataModel.getStartingIndex(), RotorEnum.getRotorByCode(rotorDataModel.getRotorCode()),rotorDataModel.getRotorPlacement());
            rotorList.add(rotor);
        }
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
