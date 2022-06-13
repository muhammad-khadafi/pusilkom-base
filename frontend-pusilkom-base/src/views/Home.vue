<template>
  <v-container fluid class="home">
    
    <div>
    <v-row v-if="showKerjaAktif === true" >
    <v-col cols="12" lg="12">
          <v-data-table
            :headers="headersKerjasama"
            :items="kerjasamaAktif"
            class="elevation-1"
            :loading="isLoading"
            loading-text="Loading... Please wait"
            :search="search"
          >
            <template v-slot:top>
              <v-toolbar color="primary" dark>
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
          </v-col>
          </v-row>

        </div>

        <div>
        <v-row v-if="showKerjaAktif === true" >
        <v-col cols="12" lg="12">
              <v-data-table
                :headers="headersKerjasamaTidakLengkap"
                :items="kerjasamaTidakLengkap"
                class="elevation-1"
                :loading="isLoading"
                loading-text="Loading... Please wait"
                :search="search2"
              >
            <template v-slot:top>
              <v-toolbar color="primary" dark>
                <v-toolbar-title>Daftar Kerjasama Tidak Lengkap</v-toolbar-title>
              </v-toolbar>
              <v-divider />
              <v-toolbar flat class="mt-1">
                <v-text-field
                  v-model="search2"
                  append-icon="mdi-magnify"
                  label="Cari"
                  single-line
                  hide-details
                  class="search"
                />
                <div class="flex-grow-1" />
              </v-toolbar>
            </template>
            <template v-slot:item.jmlDokumen="{ item }">
                      <v-icon>{{ item.jmlDokumen != 0 ? "mdi-check" : "mdi-close-thick" }}</v-icon>
            </template>
            
            <template v-slot:item.jmlPenanggungJawab="{ item }">
                      <v-icon>{{ item.jmlPenanggungJawab != 0 ? "mdi-check" : "mdi-close-thick" }}</v-icon>
            </template>
            <template v-slot:no-data />
          </v-data-table>
          </v-col>
          </v-row>

        </div>

    <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="4000" :top="true">
      {{ snackbarText }}
      <v-btn dark text @click="snackbar = false">Tutup</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import { apiServer, fileUrl } from "../setup-axios";

export default {
  data: () => ({
    daftarDokumen: [],
    headers: [
      { text: "Judul", width: "35%", value: "judul" },
      { text: "Institusi", value: "namaOrganisasi" },
      { text: "Tahun", align: "center", value: "tahunTerbit" },
      { text: "Aksi", value: "action", align: "center", sortable: false }
    ],
    isLoading: false,
    search: "",
    search2: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    showKerjaAktif:false,
    kerjasamaAktif :[],
    headersKerjasama: [
      { text: "Judul kerjasama", value: "judul" },
      { text: "Pihak Pertama", value: "pihakPertamaStr" },
      { text: "Pihak Kedua", value: "pihakKeduaStr" },
      { text: "Tanggal Mulai", value: "tanggalMulai" },
      { text: "Tanggal Selesai", value: "tanggalSelesai" }
    ],
    kerjasamaTidakLengkap:[],
    headersKerjasamaTidakLengkap: [
      { text: "Judul kerjasama", value: "judul" },
      { text: "Pihak Pertama", value: "pihakPertamaStr" },
      { text: "Pihak Kedua", value: "pihakKeduaStr" },
      { text: "Tanggal Mulai", value: "tanggalMulaiStr" },
      { text: "Tanggal Selesai", value: "tanggalSelesaiStr" },
      { text: "Dokumen", value: "jmlDokumen" },
      { text: "Penanggung Jawab", value: "jmlPenanggungJawab" }
    ],

  }),

  created() {
    this.getKerjasamaAktif()
    this.getKerjasamaTidakLengkap()
    if (this.$store.state.user.role.roleCode === 'STAF') {
            this.showKerjaAktif = true
          } else {
            this.showKerjaAktif = false
          }
  },

  methods: {
    getDokumen() {
      this.isLoading = true;
      apiServer
        .get("/dokumen-terkini/" + this.$store.state.user.organizationId)
        .then(response => {
          this.isLoading = false;
          this.daftarDokumen = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    downloadDokumen(item) {
      apiServer
        .post("/dokumen-organisasi/download/" + item.id)
        .then(response => {
          const ticket = response.data.ticket;
          window.location =
            fileUrl +
            "/download/dokumen-organisasi/" +
            item.id +
            "?ticket=" +
            ticket;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          if (error.response.status === 500) {
            this.showSnackbar("error", "File tidak ditemukan.");
          } else {
            this.showSnackbar("error", "Error, Harap hubungi Administrator!");
          }
        });
    },
    showSnackbar(color, message) {
      this.snackbar = true;
      this.snackbarColor = color;
      this.snackbarText = message;
    },
    getKerjasamaAktif() {
          this.isLoading = true;
          console.log(this.isLoading);
          apiServer
            .get("/kerjasama-aktif")
            .then(response => {
              this.kerjasamaAktif = response.data;
              console.log("getData() " + response.data); // For now, logs out the response
              this.isLoading = false;
            })
            .catch(error => {
              console.log("There was an error:", error.response); // Logs out the error
              this.isLoading = false;
            });
    },
    getKerjasamaTidakLengkap() {
      this.isLoading = true;
          console.log(this.isLoading);
          apiServer
            .get("/kerjasama-tidak-lengkap")
            .then(response => {
              this.kerjasamaTidakLengkap = response.data;
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

<style lang="scss">
.home {
  background-color: white;
  padding-bottom: 16%;
}

.selamat-home {
  margin-top: 10%;
}
.pointerCursor {
  cursor: pointer;
}
</style>
