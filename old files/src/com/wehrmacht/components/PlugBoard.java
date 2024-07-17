package com.wehrmacht.components;

import com.wehrmacht.utils.EnigmaUtils;

import java.util.HashMap;
import java.util.Map;

public class PlugBoard {

    private final Map<Character, Character> plugboardMap;

    public PlugBoard(String[] connections) {
        plugboardMap = new HashMap<>();
        initializePlugboard(connections);
    }

    private void initializePlugboard(String[] connections) {
        // Initialize plugboard with direct mappings
        for (char c = 'A'; c <= 'Z'; c++) {
            plugboardMap.put(c, c);
        }

        // Add plugboard connections
        for (String connection : connections) {
            if (connection.length() == 3) {
                char first = connection.charAt(0);
                char second = connection.charAt(2);

                // two put statements are used to make
                // the connection bidirectional
                plugboardMap.put(first, second);
                plugboardMap.put(second, first);
            }
        }
    }

    /**
     * gets the mapped value from plug board config or
     * returns the param as it is if it has no mapping
     * in the plug board
     * @param c
     * @return pluboards output of the given character
     */
    public char swap(char c) {
        EnigmaUtils.logOutput("PlugBoard",-1,c,plugboardMap.getOrDefault(c, c));
        return plugboardMap.getOrDefault(c, c);
    }

}
