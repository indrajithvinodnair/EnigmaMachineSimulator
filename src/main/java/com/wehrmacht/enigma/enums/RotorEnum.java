package com.wehrmacht.enigma.enums;

public enum RotorEnum {
        //                0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25
        //               'A'  'B'  'C'  'D'  'E'  'F'  'G'  'H'  'I'  'J'  'K'  'L'  'M'  'N'  'O'  'P'  'Q'  'R'  'S'  'T'  'U'  'V'  'W'  'X'  'Y'  'Z'
    ROTOR_I(new char[]  {'E', 'K', 'M', 'F', 'L', 'G', 'D', 'Q', 'V', 'Z', 'N', 'T', 'O', 'W', 'Y', 'H', 'X', 'U', 'S', 'P', 'A', 'I', 'B', 'R', 'C', 'J'}, 'Q',"I"),
    ROTOR_II(new char[] {'A', 'J', 'D', 'K', 'S', 'I', 'R', 'U', 'X', 'B', 'L', 'H', 'W', 'T', 'M', 'C', 'Q', 'G', 'Z', 'N', 'P', 'Y', 'F', 'V', 'O', 'E'}, 'E', "II"),
    ROTOR_III(new char[]{'B', 'D', 'F', 'H', 'J', 'L', 'C', 'P', 'R', 'T', 'X', 'V', 'Z', 'N', 'Y', 'E', 'I', 'W', 'G', 'A', 'K', 'M', 'U', 'S', 'Q', 'O'}, 'V', "III"),;

    private final char[] wiring;
    private final char notch;
    private final String code;

    RotorEnum(char[] wiring, char notch, String code) {
        this.wiring = wiring;
        this.notch = notch;
        this.code = code;
    }

    public char[]   getWiring() {
        return wiring;
    }

    public char getNotch() {
        return notch;
    }

    // get rotor by code
    public static RotorEnum getRotorByCode(String code) {
        for (RotorEnum rotor : RotorEnum.values()) {
            if (rotor.code.equals(code)) {
                return rotor;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }



}
