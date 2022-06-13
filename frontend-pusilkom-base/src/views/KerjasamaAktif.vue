<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="userweb"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Kerjasama Aktif</v-toolbar-title>
          </v-toolbar>
          <v-divider />
          <v-toolbar flat class="mt-1">
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Cari"
              single-line
              hide-details
              class="search"
            />
            <div class="flex-grow-1" />
          </v-toolbar>
        </template>

        <template v-slot:no-data />
      </v-data-table>

    </div>
  </v-container>
</template>

<script>
import { apiServer } from "../setup-axios";

export default {
  data: () => ({
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    userweb: [],
    headers: [
      { text: "Username", value: "judul" },
      { text: "Waktu Login Terakhir", value: "tanggalSelesai" }
    ]
  }),

  created() {
    this.getData();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/kerjasama-aktif")
        .then(response => {
          this.userweb = response.data;
          console.log("getData() " + response.data); // For now, logs out the response
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    }
  }
};
</script>
