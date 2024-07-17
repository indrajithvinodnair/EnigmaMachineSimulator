package com.wehrmacht.enigma.enums;

public enum ReflectorEnum {
    //                     'A'  'B'  'C'  'D'  'E'  'F'  'G'  'H'  'I'  'J'  'K'  'L'  'M'  'N'  'O'  'P'  'Q'  'R'  'S'  'T'  'U'  'V'  'W'  'X'  'Y'  'Z'
    REFLECTOR_A(new char[]{'E', 'J', 'M', 'Z', 'A', 'L', 'Y', 'X', 'V', 'B', 'W', 'F', 'C', 'R', 'Q', 'U', 'O', 'N', 'T', 'S', 'P', 'I', 'K', 'H', 'G', 'D'}, 'A'),
    REFLECTOR_B(new char[]{'Y', 'R', 'U', 'H', 'Q', 'S', 'L', 'D', 'P', 'X', 'N', 'G', 'O', 'K', 'M', 'I', 'E', 'B', 'F', 'Z', 'C', 'W', 'V', 'J', 'A', 'T'}, 'B'),
    REFLECTOR_C(new char[]{'F', 'V', 'P', 'J', 'I', 'A', 'O', 'Y', 'E', 'D', 'R', 'Z', 'X', 'W', 'G', 'C', 'T', 'K', 'U', 'Q', 'S', 'B', 'N', 'M', 'H', 'L'}, 'C');

    private final char[] wiring;
    private final char code;

    ReflectorEnum(char[] wiring,char code) {
        this.wiring = wiring;
        this.code = code;
    }

   //get reflector by code
    public static ReflectorEnum getReflectorByCode(char code){
        for(ReflectorEnum reflectorEnum : ReflectorEnum.values()){
            if(reflectorEnum.code == code){
                return reflectorEnum;
            }
        }
        return null;
    }

    public char[] getWiring() {
        return wiring;
    }

    public char getCode() {
        return code;
    }
}
