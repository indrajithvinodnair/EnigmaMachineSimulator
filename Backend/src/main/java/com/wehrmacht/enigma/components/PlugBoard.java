package com.wehrmacht.enigma.components;


import com.wehrmacht.enigma.utils.EnigmaUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlugBoard {

    private final Map<Character, Character> plugboardMap;

    public PlugBoard(List<String> connections) {
        plugboardMap = new HashMap<>();
        initializePlugboard(connections);
    }

    private void initializePlugboard(List<String> connections) {
        // Initialize plugboard with direct mappings
        for (char c = 'A'; c <= 'Z'; c++) {
            plugboardMap.put(c, c);
        }

        // Add plugboard connections
        for (String connection : connections) {
            if (connection.length() == 3) {
                char first = connection.charAt(0);
                char second = connection.charAt(2);
                plugboardMap.put(first, second);
//                Bi directional mapping is handled by UI hence no need to set this here
//                plugboardMap.put(second, first);
            }
        }
    }

    /**
     * gets the mapped value from plug board config or
     * returns the param as it is if it has no mapping
     * in the plug board
     *
     * @param c - character to be swapped
     * @return pluboards output of the given character
     */
    public char swap(char c) {
        EnigmaUtils.logComponentOutput("PlugBoard", -1, c, plugboardMap.getOrDefault(c, c));
        return plugboardMap.getOrDefault(c, c);
    }

}
