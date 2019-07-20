<template>
  <div id="app" @click="checkAvailableServices();">
    <Header />
    <router-view />
    <Footer />
  </div>
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
  updated() {
    this.checkAvailableServices();
  }
};
</script>


<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.header,
.footer {
  background: #333;
  color: #fff;
  text-align: center;
  padding: 10px;
}
.header a {
  color: #43a047;
  padding-right: 5px;
}
</style>
