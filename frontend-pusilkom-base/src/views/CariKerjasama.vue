<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="kerjasama"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Cari Dokumen - Kerjasama</v-toolbar-title>
          </v-toolbar>
          <v-divider></v-divider>
          <v-toolbar flat class="mt-1">
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Cari..."
              single-line
              hide-details
              class="search"
            ></v-text-field>
            <div class="flex-grow-1"></div>

          </v-toolbar>
        </template>

        <template v-slot:item.action="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
        </template>
        <template v-slot:no-data></template>
      </v-data-table>

      <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="2000" :top="true">
        {{ snackbarText }}
        <v-btn dark text @click="snackbar = false">Close</v-btn>
      </v-snackbar>
    </div>
  </v-container>
</template>

<script>


import { apiServer } from "../setup-axios";
import { required, maxLength } from "vuelidate/lib/validators";
import moment from "moment";

export default {
  data: () => ({
    isTest: true,
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    kerjasama: [],
    headers: [
      { text: "Judul", value: "judul" },
      { text: "Partner", value: "namaPartner" },
      { text: "Tahun Dokumen", value: "tahunDokumen" },
      { text: "Negara", value: "namaNegara" },
      { text: "Jenis Dokumen", value: "namaJenisDokumen" },
      { text: "Jenis Partner", value: "namaJenisPartner" },
      { text: "Tipe Kerjasama", value: "namaTipeKerjasama" },
    ],
    editedIndex: -1,
  }),

  created() {
    this.getData();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/cari-kerjasama")
        .then(response => {
          this.kerjasama = response.data;
          console.log(response.data);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
  }
};
</script>
