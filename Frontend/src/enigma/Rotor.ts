// Code heavily inspired on Professor Mike Pound's (@mikepound) Enigma Machine: https://github.com/mikepound/enigma/blob/07b8469d29850499df105f3077df32a638c68cc4/src/com/mikepound/enigma/Rotor.java
import {toAlphabetIndex, normalizeToAlphabetBounds} from '../util'

enum RotorWirings {
  I = "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
  II = "AJDKSIRUXBLHWTMCQGZNPYFVOE",
  III = "BDFHJLCPRTXVZNYEIWGAKMUSQO",
  IV = "ESOVPZJAYQUIRHXLNFTGKDCMWB",
  V = "VZBRGITYUPSDNHLXAWMJQOFECK",
}

enum RotorDirection {
  INPUT,
  OUTPUT
}

export enum RotorPositions {
  LEFT = "LEFT",
  MIDDLE = "MIDDLE",
  RIGHT = "RIGHT"
}

export enum Rotors {
  I = "I",
  II = "II",
  III = "III",
  IV = "IV",
  V = "V",
}

const rotorConfigurations = {
  I: {
    notch: {
      position: 16,
      letter: "Q",
    },
    wiring: RotorWirings.I,
  },
  II: {
    notch: {
      position: 4,
      letter: "E",
    },
    wiring: RotorWirings.II
  },
  III: {
    notch: {
      position: 21,
      letter: "V",
    },
    wiring: RotorWirings.III
  },
  IV: {
    notch: {
      position: 9,
      letter: "J",
    },
    wiring: RotorWirings.IV

  },
  V: {
    notch: {
      position: 25,
      letter: "Z",
    },
    wiring: RotorWirings.V
  }
}

export class Rotor {
  private wiring: RotorWirings
  private inputWiringAsIndexArray: number[] = []
  private outputWiringAsIndexArray: number[] = []
  private notch: typeof rotorConfigurations.I.notch

  constructor (public readonly name: Rotors, public position: number, public ringSetting: number) {
    this.wiring = rotorConfigurations[name].wiring
    this.notch = rotorConfigurations[name].notch
  }

}
