export enum Reflectors {
  A = "A",
  B = "B",
  C = "C"
}

const reflectorConfigurations = {
  [Reflectors.A]: "EJMZALYXVBWFCRQUONTSPIKHGD",
  [Reflectors.B]: "YRUHQSLDPXNGOKMIEBFZCWVJAT",
  [Reflectors.C]: "FVPJIAOYEDRZXWGCTKUQSBNMHL"
}

export class Reflector {

  constructor (public readonly name: Reflectors) {
    // see src/enigma/Rotor.ts#122 for explanation
  }

  [Symbol.toStringTag] = `Reflector`
  toString() {
    return `${this[Symbol.toStringTag]} ${this.name}`
  }
}
