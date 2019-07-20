
<template>
  <v-toolbar app color="green accent-1">
    <a href="https://kryptokrauts.com" target="_blank">
      <img src="../../assets/kryptokrauts.svg" width="32px" />
    </a>
    <v-toolbar-title class="headline text-uppercase">
      <span>aepp Java SDK</span>
      <span class="font-weight-light">Showcase</span>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-flex xs2>
      <v-select
        background-color="green"
        :value="getSelectedNetwork"
        :items="getAvailableNetworks"
        item-text="name"
        item-value="id"
        label="Selected network"
        solo
        single-line
        @change="updateNetwork"
      ></v-select>
    </v-flex>
    <v-btn class="mx-3" icon load @click="checkAvailableServices">
      <v-icon size="24px">fas fa-sync-alt</v-icon>
    </v-btn>
    <template v-slot:extension>
      <v-tabs v-model="model" centered color="green darken-1" slider-color="green accent-1">
        <v-tab to="/">Home</v-tab>
        <v-tab to="/about">About</v-tab>
      </v-tabs>
    </template>
  </v-toolbar>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "Header",
  methods: {
    ...mapActions(["checkAvailableServices"]),
    updateNetwork(event) {
      this.$store.commit("updateSelectedNetwork", event);
    }
  },
  computed: mapGetters(["getAvailableNetworks", "getSelectedNetwork"])
};
</script>
