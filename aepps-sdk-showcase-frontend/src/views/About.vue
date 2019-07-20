<template>
  <v-container>
    <span class="headline">Healthcheck status of backend services</span>
    <div v-for="service in getAvailableServices" :key="service.name">
      {{service.name}} -
      <span
        color="error"
        v-bind:color="{'success':service.alive}"
      >{{service.alive?'Available':'Not Available'}}</span>
    </div>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "About",
  computed: mapGetters(["getAvailableServices"])
};
</script>
