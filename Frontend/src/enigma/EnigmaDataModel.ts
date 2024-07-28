export interface EnigmaDataModel {
  rotors: RotorModel[]
  reflectorCode: string
  plugBoardMappings: string[]
  output: string
  keyboardInput: string
  steps: string[]
}

export interface RotorModel {

  rotorCode: string
  rotorPlacement: string
  ringSetting: string
  startingIndex:string
}
