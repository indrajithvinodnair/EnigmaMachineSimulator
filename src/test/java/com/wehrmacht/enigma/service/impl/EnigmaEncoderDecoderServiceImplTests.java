package com.wehrmacht.enigma.service.impl;

import com.wehrmacht.enigma.dataModels.EnigmaDataModel;
import com.wehrmacht.enigma.dataModels.RotorDataModel;
import com.wehrmacht.enigma.enums.ReflectorEnum;
import com.wehrmacht.enigma.enums.RotorEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.wehrmacht.enigma.constants.AppConstants.*;

public class EnigmaEncoderDecoderServiceImplTests {

	EnigmaEncoderDecoderServiceImpl underTest = new EnigmaEncoderDecoderServiceImpl();
	@Test
	public void encryptTest(){
		String input = "THISISENIGMAANDTHISWPSCCC";
		List<String> plugBoardMappings = new ArrayList<>();

		EnigmaDataModel enigmaDataModel = new EnigmaDataModel();
		List<RotorDataModel> rotors = new ArrayList<>();
		RotorDataModel leftRotor = new RotorDataModel();
		leftRotor.setStartingIndex("0");
		leftRotor.setRotorCode(RotorEnum.ROTOR_I.getCode());
		leftRotor.setRingSetting("0");
		leftRotor.setRotorPlacement(LEFT_ROTOR);

		RotorDataModel middleRotor = new RotorDataModel();
		middleRotor.setStartingIndex("0");
		middleRotor.setRotorCode(RotorEnum.ROTOR_II.getCode());
		middleRotor.setRingSetting("0");
		middleRotor.setRotorPlacement(MIDDLE_ROTOR);


		RotorDataModel rightRotor = new RotorDataModel();
		rightRotor.setStartingIndex("0");
		rightRotor.setRotorCode(RotorEnum.ROTOR_III.getCode());
		rightRotor.setRingSetting("0");
		rightRotor.setRotorPlacement(RIGHT_ROTOR);

		rotors.add(leftRotor);
		rotors.add(middleRotor);
		rotors.add(rightRotor);

		enigmaDataModel.setKeyboardInput(input);
		enigmaDataModel.setPlugBoardMappings(plugBoardMappings);
		enigmaDataModel.setRotors(rotors);
		enigmaDataModel.setReflectorCode(String.valueOf(ReflectorEnum.REFLECTOR_B.getCode()));

		EnigmaDataModel result = underTest.getEncryptedMessage(enigmaDataModel);

		Assertions.assertEquals("OPGNDXKMJYTSBSPWWTRKRIWJE",result.getOutput());
	}

	@Test
	public void encryptTest_reflectorA(){
		String input = "HELLOGOODMORNING";
		List<String> plugBoardMappings = new ArrayList<>();
		EnigmaDataModel enigmaDataModel = new EnigmaDataModel();
		List<RotorDataModel> rotors = new ArrayList<>();
		RotorDataModel leftRotor = new RotorDataModel();
		leftRotor.setStartingIndex("0");
		leftRotor.setRotorCode(RotorEnum.ROTOR_I.getCode());
		leftRotor.setRingSetting("0");
		leftRotor.setRotorPlacement(LEFT_ROTOR);

		RotorDataModel middleRotor = new RotorDataModel();
		middleRotor.setStartingIndex("0");
		middleRotor.setRotorCode(RotorEnum.ROTOR_II.getCode());
		middleRotor.setRingSetting("0");
		middleRotor.setRotorPlacement(MIDDLE_ROTOR);


		RotorDataModel rightRotor = new RotorDataModel();
		rightRotor.setStartingIndex("0");
		rightRotor.setRotorCode(RotorEnum.ROTOR_III.getCode());
		rightRotor.setRingSetting("0");
		rightRotor.setRotorPlacement(RIGHT_ROTOR);

		rotors.add(leftRotor);
		rotors.add(middleRotor);
		rotors.add(rightRotor);

		enigmaDataModel.setKeyboardInput(input);
		enigmaDataModel.setPlugBoardMappings(plugBoardMappings);
		enigmaDataModel.setRotors(rotors);
		enigmaDataModel.setReflectorCode(String.valueOf(ReflectorEnum.REFLECTOR_A.getCode()));

		EnigmaDataModel result = underTest.getEncryptedMessage(enigmaDataModel);
		Assertions.assertEquals("KCUBRBILSPYOBMPD",result.getOutput());

	}

	@Test
	public void encryptTest_RingSettings(){
		// ring settings {25,0,25}; //left,mid,right
		String input = "RINGSETTINGSAPPLIED";
		List<String> plugBoardMappings = new ArrayList<>();
		EnigmaDataModel enigmaDataModel = new EnigmaDataModel();
		List<RotorDataModel> rotors = new ArrayList<>();
		RotorDataModel leftRotor = new RotorDataModel();
		leftRotor.setStartingIndex("0");
		leftRotor.setRotorCode(RotorEnum.ROTOR_I.getCode());
		leftRotor.setRingSetting("25");
		leftRotor.setRotorPlacement(LEFT_ROTOR);

		RotorDataModel middleRotor = new RotorDataModel();
		middleRotor.setStartingIndex("0");
		middleRotor.setRotorCode(RotorEnum.ROTOR_II.getCode());
		middleRotor.setRingSetting("0");
		middleRotor.setRotorPlacement(MIDDLE_ROTOR);


		RotorDataModel rightRotor = new RotorDataModel();
		rightRotor.setStartingIndex("0");
		rightRotor.setRotorCode(RotorEnum.ROTOR_III.getCode());
		rightRotor.setRingSetting("25");
		rightRotor.setRotorPlacement(RIGHT_ROTOR);

		rotors.add(leftRotor);
		rotors.add(middleRotor);
		rotors.add(rightRotor);

		enigmaDataModel.setKeyboardInput(input);
		enigmaDataModel.setPlugBoardMappings(plugBoardMappings);
		enigmaDataModel.setRotors(rotors);
		enigmaDataModel.setReflectorCode(String.valueOf(ReflectorEnum.REFLECTOR_A.getCode()));

		EnigmaDataModel result = underTest.getEncryptedMessage(enigmaDataModel);
		Assertions.assertEquals("BVELPZJLVTNKITZEQNS",result.getOutput());
	}

	@Test
	public void encryptTest_Offset(){
		String input = "ROTOROFFSETAPPLIED";

		// ring settings {25,0,25}; //left,mid,right
		// rotor offsets {0,0,8}; //left,mid,right
		List<String> plugBoardMappings = new ArrayList<>();
		EnigmaDataModel enigmaDataModel = new EnigmaDataModel();
		List<RotorDataModel> rotors = new ArrayList<>();
		RotorDataModel leftRotor = new RotorDataModel();
		leftRotor.setStartingIndex("0");
		leftRotor.setRotorCode(RotorEnum.ROTOR_I.getCode());
		leftRotor.setRingSetting("25");
		leftRotor.setRotorPlacement(LEFT_ROTOR);

		RotorDataModel middleRotor = new RotorDataModel();
		middleRotor.setStartingIndex("0");
		middleRotor.setRotorCode(RotorEnum.ROTOR_II.getCode());
		middleRotor.setRingSetting("0");
		middleRotor.setRotorPlacement(MIDDLE_ROTOR);


		RotorDataModel rightRotor = new RotorDataModel();
		rightRotor.setStartingIndex("8");
		rightRotor.setRotorCode(RotorEnum.ROTOR_III.getCode());
		rightRotor.setRingSetting("25");
		rightRotor.setRotorPlacement(RIGHT_ROTOR);

		rotors.add(leftRotor);
		rotors.add(middleRotor);
		rotors.add(rightRotor);

		enigmaDataModel.setKeyboardInput(input);
		enigmaDataModel.setPlugBoardMappings(plugBoardMappings);
		enigmaDataModel.setRotors(rotors);
		enigmaDataModel.setReflectorCode(String.valueOf(ReflectorEnum.REFLECTOR_A.getCode()));

		EnigmaDataModel result = underTest.getEncryptedMessage(enigmaDataModel);
		Assertions.assertEquals("YPZLNRSMGNGXGQXBCK",result.getOutput());
	}

	@Test
	public void encryptTest_PlugBoard(){
		String input = "PLUGBOARDCONFIGADDED";

		// ring settings {25,0,25}; //left,mid,right
		// rotor offsets {0,0,8}; //left,mid,right
		List<String> plugBoardMappings = Arrays.asList("I-K", "K-I", "M-O", "O-M", "P-S", "P-S");
		EnigmaDataModel enigmaDataModel = new EnigmaDataModel();
		List<RotorDataModel> rotors = new ArrayList<>();
		RotorDataModel leftRotor = new RotorDataModel();
		leftRotor.setStartingIndex("0");
		leftRotor.setRotorCode(RotorEnum.ROTOR_I.getCode());
		leftRotor.setRingSetting("25");
		leftRotor.setRotorPlacement(LEFT_ROTOR);

		RotorDataModel middleRotor = new RotorDataModel();
		middleRotor.setStartingIndex("0");
		middleRotor.setRotorCode(RotorEnum.ROTOR_II.getCode());
		middleRotor.setRingSetting("0");
		middleRotor.setRotorPlacement(MIDDLE_ROTOR);


		RotorDataModel rightRotor = new RotorDataModel();
		rightRotor.setStartingIndex("8");
		rightRotor.setRotorCode(RotorEnum.ROTOR_III.getCode());
		rightRotor.setRingSetting("25");
		rightRotor.setRotorPlacement(RIGHT_ROTOR);

		rotors.add(leftRotor);
		rotors.add(middleRotor);
		rotors.add(rightRotor);

		enigmaDataModel.setKeyboardInput(input);
		enigmaDataModel.setPlugBoardMappings(plugBoardMappings);
		enigmaDataModel.setRotors(rotors);
		enigmaDataModel.setReflectorCode(String.valueOf(ReflectorEnum.REFLECTOR_A.getCode()));

		EnigmaDataModel result = underTest.getEncryptedMessage(enigmaDataModel);
		Assertions.assertEquals("EJVSUFLVEACWLCMLVIJB",result.getOutput());
	}
}
