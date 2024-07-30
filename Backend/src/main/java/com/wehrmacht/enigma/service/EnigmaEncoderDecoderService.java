package com.wehrmacht.enigma.service;

import com.wehrmacht.enigma.dataModels.EnigmaDataModel;

public interface EnigmaEncoderDecoderService {

     EnigmaDataModel getEncryptedMessage(EnigmaDataModel enigmaDataModel);
}
