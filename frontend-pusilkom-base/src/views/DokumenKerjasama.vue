<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="daftar"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Dokumen</v-toolbar-title>
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
            <v-dialog v-model="dialog" max-width="700px">
              <template v-slot:activator="{ on }">
                <v-btn color="blue" dark class="mb-2" v-on="on">Tambah</v-btn>
              </template>

              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.namaDokumen"
                          label="Nama Dokumen"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.namaFile"
                          label="Nama File"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.tahunDokumen"
                          label="Tahun"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idTipeDokumen"
                          :items="tipeDokumen"
                          item-text="nama"
                          item-value="id"
                          label="Tipe Dokumen"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idKerjasama"
                          :items="kerjasama"
                          item-text="judul"
                          item-value="id"
                          label="Kerjasama"
                        />
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <div class="flex-grow-1"></div>
                  <v-btn color="blue darken-1" text @click="close">Batal</v-btn>
                  <v-btn color="blue darken-1" text @click="save">Simpan</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
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

export default {
  data: () => ({
    isTest: true,
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    dialog: false,
    daftar: [],
    tipeDokumen: [],
    kerjasama: [],
    headers: [
      { text: "Nama Dokumen", value: "namaDokumen" },
      { text: "Tipe Dokumen", value: "tipeDokumenStr" },
      { text: "Kerjasama", value: "kerjasamaStr" },
      { text: "Tahun Dokumen", value: "tahunDokumen" },
      { text: "Aksi", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      namaDokumen: "",
      namaFile: "",
      tahunDokumen: "",
      idTipeDokumen: "",
      idKerjasama: "",
      usernameSubmitter: "",
    },
    defaultItem: {
      namaDokumen: "",
      namaFile: "",
      tahunDokumen: "",
      idTipeDokumen: "",
      idKerjasama: "",
      usernameSubmitter: ""
    },
    namaEdited: ""
  }),
  validations: {
    editedItem: {

    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Tambah Dokumen Kerjasama" : "Ubah Dokumen Kerjasama";
    },

  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    this.getData();
    this.getKerjasama();
    this.getTipeDokumen();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/dokumen-kerjasama")
        .then(response => {
          this.daftar = response.data;
          console.log(response.data);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    getKerjasama() {
      apiServer
        .get("/kerjasama")
        .then(response => {
          this.kerjasama = response.data;
          console.log(response.data);
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    getTipeDokumen() {
      apiServer
        .get("/tipe-dokumen")
        .then(response => {
          this.tipeDokumen = response.data;
          console.log(response.data);
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    postData() {
      apiServer
        .post("/dokumen-kerjasama", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Add new data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    deleteData(id) {
      apiServer
        .delete("/dokumen-kerjasama/" + id + "/" + this.$store.state.user.name)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Delete data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", error.response.data);
        });
    },
    updateData() {
      apiServer
        .put("/dokumen-kerjasama", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Update data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    editItem(item) {
      this.editedIndex = this.daftar.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      confirm("Are you sure you want to delete this item?") &&
        this.deleteData(item.id);
    },
    close() {
      this.$v.$reset();
      this.namaEdited = "";
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      this.$v.$touch();
      if (!this.$v.$invalid) {
        this.editedItem.usernameSubmitter = this.$store.state.user.name;
        if (this.editedIndex > -1) {
          this.updateData();
        } else {
          this.postData();
        }
        this.close();
      }
    },
    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    },

    changeBool() {
        this.isTest = !this.isTest;
      }
  }
};
</script>
