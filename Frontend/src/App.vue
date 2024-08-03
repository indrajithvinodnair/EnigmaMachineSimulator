<template>
  <header class="column is-full has-text-centered">
    <div class="wrapper">
      <div class="logo">
        <img src="/enigma-mtl.png" alt="">
      </div>

      <div class="description">
        <p>
          This replicates the <strong>Wehrmacht I (or the Enigma I)</strong> machine, which was used by the German
          military during World War II.It resembles the real machine and its encryption process hence can be used for educational purposes.
        </p>

        <p>
          I used this <a href="https://www.youtube.com/watch?v=ybkkiGtJmkM" target="_blank">excellent video
            resource</a> to learn about the machine and to build this simulator. And the code is heavily inspired on the 
            <a href="https://github.com/khaosdoctor" target="_blank">Khaosdoctor's</a> implementation of the Enigma machine.
        </p>

        <p>
          You can find the source code of this project <a href="https://github.com/indrajithvinodnair/EnigmaMachineSimulator"
            target="_blank">here</a> with all the details and instructions.
        </p>
      </div>
    </div>

  </header>

  <div class="settings-wrapper">
    <div class="overlay-top-section">
      <section class="columns settings mb-5 is-flex is-align-items-center">
        <RotorComponent />

      </section>
      <section class="plugboard column is-half">
        <Plugboard />
      </section>
    </div>
  </div>

  <section class="is-flex is-align-items-center">
    <SendConfigs />
  </section>

  <section class="output has-text-centered">
    <Output />
  </section>

  <section
    class="keyboard is-flex is-flex-direction-column is-align-items-center is-align-content-space-between is-justify-content-space-around has-text-centered">
    <div class="overlay-bottom-section keyboard-wrapper">
      <h1>Input</h1>
      <div class="description">
        <p> Input the text through the keyboard buttons or through the input field.
          Once you are done, press the <b>Enter</b> key to see the output from the Enigma machine based on the chosen
          configurations.
        </p>
      </div>
      <KeyboardInput @keyboard-input="handleInputFieldKeyPress" @invoke-backend="invokeBackend" />
      <Keyboard @key-pressed="handleKeyBoardButtonInput" />
    </div>
  </section>

  <section class="steps">
   <div class="overlay-bottom-section steps-wrapper">
    <h1 class="has-text-centered"><a @click="showSteps = !showSteps">{{ showSteps ? 'Hide' : 'Show' }} encoding
        steps</a>
    </h1>
    <div v-show="showSteps" class="has-text-centered">
      <div v-show="state.steps.length === 0">No steps to show, please type something in the keyboard</div>
      <ul v-show="state.steps.length > 0">
        <div>"You might have to scroll the steps sections if the number of input characters is high"</div>
        <li v-for="step in state.steps" :key="step" :class="{ separator: step.charAt(0) === '-' }">
          {{ step }}
        </li>
      </ul>
    </div>
   </div>
  </section>

  <footer>
    <div class="footer-wrapper">
      <div class="columns">
        <div class="column is-full has-text-centered">
          <p>Made with 💛 with Java and Vue.js</p>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup lang="ts">
import Keyboard from './components/Keyboard.vue'
import Plugboard from './components/Plugboard.vue'
import RotorComponent from './components/Rotors.vue'
import SendConfigs from './components/SendConfigs.vue'
import Output from './components/Output.vue'
import KeyboardInput from './fragments/KeyboardInput.vue'

import { reactive } from '@vue/reactivity'
import { AllowedAlphabet, GlobalState } from './types'
import { Rotor, RotorPositions, Rotors } from './enigma/Rotor'
import { Reflector, Reflectors } from './enigma/Reflector'
import { provide, ref } from 'vue'
import { EnigmaDataModel } from './enigma/EnigmaDataModel'
import axios from 'axios';

axios.defaults.baseURL = process.env.VUE_APP_BACKEND_URL || 'http://localhost:8080';
const serviceEndpoint = '/wehrmacht/enigma/encrypt';
axios.defaults.headers.common['Content-Type'] = 'application/json';

/**
 * The global state approach is not the best option in the case of any app
 * however, since this is a single machine I could chose from two options:
 * - Use a global state like this and have a single source of truth, therefore
 *   the application itself represents the Enigma machine and the state is declared
 *   in the topmost component (this one)
 * - Use a local state and make the application create a new machine every time
 *   the configuration changes, this would be a better approach if we had multiple
 *   machines and we wanted to keep the state of each one of them like other sims
 *   since this only emulates Enigma I this is not needed
 */
const state = reactive<GlobalState>({
  rotors: {
    [RotorPositions.LEFT]: new Rotor(Rotors.III, 0, 0),
    [RotorPositions.MIDDLE]: new Rotor(Rotors.II, 0, 0),
    [RotorPositions.RIGHT]: new Rotor(Rotors.I, 0, 0)
  },
  // UKW-B was the most common reflector used by the German military during WWII
  // since changing reflectors was a time-consuming process.
  reflector: new Reflector(Reflectors.B),
  plugboard: {
    A: '',
    B: '',
    C: '',
    D: '',
    E: '',
    F: '',
    G: '',
    H: '',
    I: '',
    J: '',
    K: '',
    L: '',
    M: '',
    N: '',
    O: '',
    P: '',
    Q: '',
    R: '',
    S: '',
    T: '',
    U: '',
    V: '',
    W: '',
    X: '',
    Y: '',
    Z: ''
  },
  output: '',
  keyboardInput: '',
  steps: []
})

// REFS AND VARIABLES

provide('state', state)
const showSteps = ref(false)
// letter that's currently being switched on in the lampboard
const switchedOnLetter = ref('')

const handleKeyBoardButtonInput = (keyChar: AllowedAlphabet) => {
  updateInputsTillNow(keyChar)
}

const handleInputFieldKeyPress = (keyChar: AllowedAlphabet) => {
  updateInputsTillNow(keyChar)
}

const updateInputsTillNow = (keyChar: AllowedAlphabet) => {
  // front-end management for the input
  state.keyboardInput += keyChar
}

// invoke the backend 
const invokeBackend = async () => {

  let enigmaDataModel: EnigmaDataModel = mapStateDetailsToEnigmaDataModel();
  try {
    const response = await axios.post(serviceEndpoint, enigmaDataModel);
    // Handle the response data here
    mapEnigmaDataModelToState(response.data);
  } catch (error) {
    console.error('Error invoking backend:', error);
    // Handle the error here
  }
}


// throttling the turn off of the lamp to make it look more realistic
let timeoutClock: number | null = null
const turnOnLampboard = (keyChar: AllowedAlphabet) => {
  switchedOnLetter.value = keyChar
  // debounce function to turn off the lamp
  // so they don't overlap
  if (timeoutClock) clearTimeout(timeoutClock)
  timeoutClock = window.setTimeout(() => {
    switchedOnLetter.value = ''
  }, 1000)
}

function mapStateDetailsToEnigmaDataModel(): EnigmaDataModel {
  const { rotors: { LEFT: leftRotor, MIDDLE: middleRotor, RIGHT: rightRotor } } = state
  const plugboardMappings = Object.entries(state.plugboard)
    .filter(([_, value]) => value !== '')
    .map(([key, value]) => `${key}-${value}`);

  const RIGHT_ROTOR = "Right";
  const MIDDLE_ROTOR = "Middle";
  const LEFT_ROTOR = "Left";

  let enigmaDataModel: EnigmaDataModel;
  enigmaDataModel = {
    rotors: [
      {
        rotorCode: leftRotor.name,
        rotorPlacement: LEFT_ROTOR,
        ringSetting: "" + leftRotor.ringSetting,
        startingIndex: "" + leftRotor.position,
      },
      {
        rotorCode: middleRotor.name,
        rotorPlacement: MIDDLE_ROTOR,
        ringSetting: "" + middleRotor.ringSetting,
        startingIndex: "" + middleRotor.position,
      },
      {
        rotorCode: rightRotor.name,
        rotorPlacement: RIGHT_ROTOR,
        ringSetting: "" + rightRotor.ringSetting,
        startingIndex: "" + rightRotor.position,
      }
    ],
    reflectorCode: state.reflector.name,
    plugBoardMappings: plugboardMappings,
    keyboardInput: state.keyboardInput,
    output: state.output,
    steps: state.steps
  }
  return enigmaDataModel;
}

function mapEnigmaDataModelToState(enigmaDataModel: EnigmaDataModel) {
  state.output = enigmaDataModel.output;
  state.steps = enigmaDataModel.steps;
  const { rotors: { LEFT: leftRotor, MIDDLE: middleRotor, RIGHT: rightRotor } } = state
  leftRotor.position = parseInt(enigmaDataModel.rotors[0].startingIndex);
  middleRotor.position = parseInt(enigmaDataModel.rotors[1].startingIndex);
  rightRotor.position = parseInt(enigmaDataModel.rotors[2].startingIndex);

  // look through the output and turn on the lampboard
  for (let i = 0; i < enigmaDataModel.output.length; i++) {
    turnOnLampboard(enigmaDataModel.output.charAt(i) as AllowedAlphabet);
  }
}
</script>

<style>
.wrapper {
  width: 80%;
  margin-left: 10%;
  background: #ffffffa6;
  border-radius: 14px;
  padding-top: 10px;
}

.overlay-top-section {
  width: 70%;
  background: #ffffffa6;
  border-radius: 14px 14px 0 0;
  padding-top: 10px;
  margin-left: 15%;
}

.overlay-bottom-section{
  width: 70%;
  background: #ffffffa6;
  border-radius: 0 0 14px 14px;
}

.overlay-bottom-section.config-wrapper{
  margin-left: 15%;
  padding-bottom: 37px;
}
.overlay-bottom-section.keyboard-wrapper{
  border-radius: 0 0 0 0 !important;
}
.overlay-bottom-section.steps-wrapper{
  width: 70%;
  margin-left: 15%;
  padding-bottom: 20px;
  border-radius: 0 0 14px 14px !important;
  max-height: 400px;
  overflow-y: scroll;
}
.overlay-top-section.columns{
  border-radius: 14px;
}
.settings {
  margin-left: 28%;
  padding-top: 53px;
}

.plugboard {
  margin-left: 28%;
}

.lampboard {
  margin-bottom: 50px;
  border-bottom: 18px solid #ededed;
}

header,
.settings-wrapper {
  background-image: url("@/assets/1920.JPG");
  background-repeat: no-repeat;
}

header .description {
  max-width: 50%;
  margin: 0 auto;
}

header p {
  margin-top: 5px;
  white-space: pre-wrap;
}

h1 {
  font-family: 'IBM Plex Mono', monospace;
  font-weight: bold;
  color: black;
  font-size: 1.5rem;
}

.keyboard-input {
  margin: 25px;
  width: 60%;
  text-align: center;
  font-family: 'IBM Plex Mono', monospace;
}

li.separator {
  font-weight: bold;
  margin-top: 15px;
  margin-bottom: 15px;
}

li.separator:first-child {
  margin-top: 10px;
}

.output,.keyboard,.steps{
  background-image: url("@/assets/1920.JPG");
  background-repeat: no-repeat;
}
.wrapper .description {
  padding-bottom:10px;
}
</style>
