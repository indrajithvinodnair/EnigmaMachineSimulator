import { createApp, reactive } from 'vue';

const EventBus = createApp({}).provide();

EventBus.$on = function (event, callback) {
  this._events = this._events || {};
  this._events[event] = this._events[event] || [];
  this._events[event].push(callback);
};

EventBus.$emit = function (event, data) {
  if (this._events && this._events[event]) {
    this._events[event].forEach(callback => callback(data));
  }
};

export default EventBus;