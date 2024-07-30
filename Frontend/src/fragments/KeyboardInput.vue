<template>
  <input type="text" class="input keyboard-input" name="keyboardInput" id="" :value="state.keyboardInput" @keydown.enter="handleEnterKey" @keydown="handleInputFieldKeyPress">
</template>
<script setup lang="ts">
import { inject } from 'vue'
import { AllowedAlphabet, ALLOWED_ALPHABET, GlobalState } from '../types'
import EventBus from '@/utils/eventBus.js'
import { reactive } from 'vue';

const uiState = reactive({
  isInputDisabled: false
})

EventBus.$on('settingsLocked', (value: boolean) => {
  uiState.isInputDisabled = value;
});

const state = inject<GlobalState>('state')!
const emit = defineEmits(['keyboard-input','invoke-backend'])

  const handleInputFieldKeyPress = (e: Event) => {
  e.preventDefault()
  if(!uiState.isInputDisabled) {
    window.alert('Please lock the settings first');
    return false
  }else{
    const key = (e as KeyboardEvent).key.toUpperCase()
    if (!ALLOWED_ALPHABET.includes(key as AllowedAlphabet)) {
      return false
    }
    emit('keyboard-input', key)
    return true
  }
}

// when enter key is pressed, take all the user input and enigma configurations and emit and event that
// will be listened by a method that invokes the backend
const handleEnterKey = (e: Event) => {
  e.preventDefault()
  if(!uiState.isInputDisabled) {
    window.alert('Please lock the settings first');
    return false
  }else if(state.keyboardInput.length == 0) {
    window.alert('Please enter some text to encrypt/decrypt');
    return false
  }else {
    emit('invoke-backend');
    return true;
  }
}
</script>
<style scoped>
</style>
