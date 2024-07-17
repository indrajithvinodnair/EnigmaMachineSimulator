package com.wehrmacht.test;

import com.wehrmacht.EncryptorDecryptor.EnigmaEncoderDecoder;
import com.wehrmacht.enums.ReflectorEnum;
import com.wehrmacht.enums.RotorEnum;
import org.junit.Assert;
import org.junit.Test;

public class EnigmaTest {
     EnigmaEncoderDecoder underTest = new EnigmaEncoderDecoder();
    @Test
    public void encryptTest(){
        String message = "THISISENIGMAANDTHISWPSCCC";
        String[] plugBoardMappings = {};
        int[] ringSettings = {0,0,0}; //left,mid,right
        int[] startingOffsets = {0,0,0};
        Assert.assertEquals("OPGNDXKMJYTSBSPWWTRKRIWJE",underTest.getEncryptedMessage(RotorEnum.ROTOR_III, RotorEnum.ROTOR_II, RotorEnum.ROTOR_I,ringSettings, startingOffsets,ReflectorEnum.REFLECTOR_B,plugBoardMappings,message));

    }

    @Test
    public void encryptTest_reflectorA(){
        String message = "HELLOGOODMORNING";
        String[] plugBoardMappings = {};
        int[] ringSettings = {0,0,0}; //left,mid,right
        int[] startingOffsets = {0,0,0};
        Assert.assertEquals("KCUBRBILSPYOBMPD",underTest.getEncryptedMessage(RotorEnum.ROTOR_III, RotorEnum.ROTOR_II, RotorEnum.ROTOR_I,ringSettings, startingOffsets,ReflectorEnum.REFLECTOR_A,plugBoardMappings,message));

    }

    @Test
    public void encryptTest_RingSettings(){
        String message = "RINGSETTINGSAPPLIED";
        String[] plugBoardMappings = {};
        int[] ringSettings = {25,0,25}; //left,mid,right
        int[] startingOffsets = {0,0,0};
        Assert.assertEquals("BVELPZJLVTNKITZEQNS",underTest.getEncryptedMessage(RotorEnum.ROTOR_III, RotorEnum.ROTOR_II, RotorEnum.ROTOR_I,ringSettings, startingOffsets,ReflectorEnum.REFLECTOR_A,plugBoardMappings,message));

    }

    @Test
    public void encryptTest_Offset(){
        String message = "ROTOROFFSETAPPLIED";
        String[] plugBoardMappings = {};
        int[] ringSettings = {25,0,25}; //left,mid,right
        int[] startingOffsets = {0,0,8};
        Assert.assertEquals("YPZLNRSMGNGXGQXBCK",underTest.getEncryptedMessage(RotorEnum.ROTOR_III, RotorEnum.ROTOR_II, RotorEnum.ROTOR_I,ringSettings, startingOffsets,ReflectorEnum.REFLECTOR_A,plugBoardMappings,message));

    }

    @Test
    public void encryptTest_PlugBoard(){
        String message = "PLUGBOARDCONFIGADDED";
        String[] plugBoardMappings = {"I-K","M-O","P-S"};
        int[] ringSettings = {25,0,25}; //left,mid,right
        int[] startingOffsets = {0,0,8};
        Assert.assertEquals("EJVSUFLVEACWLCMLVIJB",underTest.getEncryptedMessage(RotorEnum.ROTOR_III, RotorEnum.ROTOR_II, RotorEnum.ROTOR_I,ringSettings, startingOffsets,ReflectorEnum.REFLECTOR_A,plugBoardMappings,message));

    }
}
