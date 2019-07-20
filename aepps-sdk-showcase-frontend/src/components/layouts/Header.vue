<template>
  <header class="header">
    <div class="network-info">
      <div class="networks">
        <select>
          <option v-for="network in getAvailableNetworks">{{ network.name }}</option>
        </select>
      </div>
      <div class="health">
        <div v-for="service in getAvailableServices" :key="service.name">
          {{service.name}} -
          <span
            class="not-available"
            v-bind:class="{'available':service.alive}"
          >{{service.alive?'Available':'Not Available'}}</span>
        </div>
      </div>
    </div>
    <h1>aepp Java SDK Showcase</h1>
    <div>
      <router-link to="/">Home</router-link>|
      <router-link to="/about">About</router-link>
    </div>
  </header>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "Header",
  computed: mapGetters(["getAvailableNetworks", "getAvailableServices"])
};
</script>

<style scoped>
.service {
  width: 30%;
}
.available {
  background-color: #43a047!Important;
}
.not-available {
  background-color: red;
}
.network-info {
  display: flex;
  justify-content: center;
}
.networks,
.health {
  position: relative;
  width: 50%;
}
select {
  -moz-appearance: none;
  -webkit-appearance: none;
  appearance: none;
  border: none;
  background: #43a047;
  width: 100%;
  height: 40px;
  padding-left: 10px;
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  box-shadow: 2px 2px 5px 1px rgba(0, 0, 0, 0.3);
  border-radius: 3px;
  box-shadow: 2px 2px 5px 1px rgba(0, 0, 0, 0.3);
  border-radius: 3px;
}
select::-ms-expand {
  display: none;
}
.networks::before {
  font-family: fontello;
  content: "\290B";
  font-size: 20px;
  position: absolute;
  right: 15px;
  top: 10px;
  color: #fff;
}
</style>
