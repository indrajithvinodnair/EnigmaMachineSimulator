<template>
<section class="rotor-setup is-flex is-flex is-flex-direction-row is-justify-content-space-evenly is-align-items-center">
  <div class="rotor-select">
    <span class="select">
      <select name="rotor" v-model="rotor" @change="selectRotor" :disabled="uiState.isInputDisabled">
        <option v-for="rotorName in allowedOptions">{{rotorName.toUpperCase()}}</option>
      </select>
    </span>
  </div>
  <span class="mid-text"><strong>ring:</strong></span>
  <div class="ring-setting">
    <span class="select">
      <select name="setting" v-model="setting" @change="selectSetting" :disabled="uiState.isInputDisabled">
        <option v-for="letter in ALLOWED_ALPHABET">{{letter.toUpperCase()}}</option>
      </select>
    </span>
  </div>
</section>
</template>
<script setup lang="ts">
import EventBus from '@/utils/eventBus.js'
import { ref } from 'vue'
import { Rotors, RotorPositions, Rotor } from '../enigma/Rotor'
import { ALLOWED_ALPHABET } from '../types'
import { reactive } from 'vue';

const uiState = reactive({
  isInputDisabled: false
})

const props = defineProps({
  position: String,
  allowedOptions: Array<Rotors>
})

EventBus.$on('settingsLocked', (value: boolean) => {
  uiState.isInputDisabled = value;
});

const emit = defineEmits(['rotor-set', 'setting-set', 'position-set'])
const rotor = ref(props.position === RotorPositions.LEFT ? Rotors.III : props.position === RotorPositions.MIDDLE ? Rotors.II : Rotors.I)
const setting = ref('A')

const selectRotor = () => {
  emit('rotor-set', new Rotor(rotor.value, 0, 0), props.position!)
}

const selectSetting = () => {
  emit('setting-set', props.position, setting.value)
}
</script>
<style scoped>
.mid-text{
  padding: 10px;
}
</style>
