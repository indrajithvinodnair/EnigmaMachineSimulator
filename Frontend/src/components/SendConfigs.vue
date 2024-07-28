<script lang="ts">
import { ref } from 'vue';
import  EventBus  from '@/utils/eventBus.js';

</script>

<script setup lang="ts">
const isLocked = ref(false);

const toggleSettings = () => {
  isLocked.value = !isLocked.value;
  EventBus.$emit('settingsLocked', isLocked.value);
};
</script>

<template>
  <div class="send-configs">
    <div class="overlay-bottom-section config-wrapper">
      <div class="description">
        <p>
          In a real usecase of the machine, there would never be a scenario that requires altering the machine settings amidst encryption or decryption process.
          Hence to simulate the same environment click the Lock Settings to lock the configurations.
        </p>
      </div>
      <div class="btn">
        <button @click="toggleSettings" :class="isLocked ? 'unlock-button' : 'lock-button'">
          {{ isLocked ? 'Unlock Settings' : 'Lock Settings' }}
        </button>
      </div>
    </div>
    </div>
</template>

<style>

.send-configs{
  background-image: url("@/assets/1920.JPG");
  background-repeat: no-repeat;
  width: 100%;
  padding-bottom: 3%;
}
.btn{
  margin-top: 20px;
  margin-left: 44%;
}
.lock-button,
.unlock-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  color: white;
  cursor: pointer;
  margin: 0 10px; /* Add some space between the buttons */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.lock-button {
  background-color: green;
  color: white;
}

.lock-button:hover {
  background-color: darkgreen;
  transform: translateY(-2px); /* Slightly lift the button on hover */
}

.unlock-button {
  background-color: grey;
  color: white;
}
.unlock-button:hover {
  background-color: darkgrey;
  transform: translateY(-2px); /* Slightly lift the button on hover */
}

.description {
  max-width: 50%;
  margin: 0 auto;
  padding-top: 10px;
}

</style>