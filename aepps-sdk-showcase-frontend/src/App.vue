<template>
  <v-app>
    <Header />
    <v-content>
      <v-container fluid>
        <router-view />
      </v-container>
    </v-content>
    <Footer />
  </v-app>
</template>

<script>
import Header from "./components/layouts/Header";
import Footer from "./components/layouts/Footer";
import { mapActions } from "vuex";

export default {
  name: "app",
  components: {
    Header,
    Footer
  },
  methods: {
    ...mapActions(["checkAvailableServices"])
  },
  created() {
    this.checkAvailableServices();
  }
};
</script>

