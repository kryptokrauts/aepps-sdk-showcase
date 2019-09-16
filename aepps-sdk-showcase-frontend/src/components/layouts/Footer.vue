<template>
  <v-footer app height="auto">
    <v-card class="flex" flat tile>
      <v-card-title class="green accent-1">
        <strong class="subheading">
          &copy;2019 -
          <strong>kryptokrauts</strong>
        </strong>
        <v-spacer></v-spacer>
        <v-btn v-for="icon in icons" :key="icon" class="mx-3" icon :href="icon.url" target="_blank">
          <v-icon size="24px">{{ icon.icon }}</v-icon>
        </v-btn>
      </v-card-title>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: "Footer",
  data: () => ({
    icons: [
      { url: "https://twitter.com/kryptokrauts", icon: "fab fa-twitter" },
      { url: "https://github.com/kryptokrauts", icon: "fab fa-github" }
    ]
  })
};
</script>
