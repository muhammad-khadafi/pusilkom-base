<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="tipeDokumen"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Peran</v-toolbar-title>
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
            <v-dialog v-model="dialog" max-width="500px">
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
                          v-model="editedItem.nama"
                          label="Nama Tipe Dokumen"
                          required
                          @input="$v.editedItem.nama.$touch()"
                          @blur="$v.editedItem.nama.$touch()"
                          :error-messages="namaErrors"
                        ></v-text-field>
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

        <template v-slot:item.isActive="{ item }">
          <v-icon>{{ item.isActive ? "mdi-check" : "mdi-minus" }}</v-icon>
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
    menu: false,
    dialog: false,
    tipeDokumen: [],
    headers: [
      { text: "Nama Tipe Dokumen", value: "nama" },
      { text: "", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      nama: "",
      username: ""
    },
    defaultItem: {
      nama: "",
      username: ""
    },
    namaEdited: ""
  }),

  validations: {
    editedItem: {
      nama: {
        required,
        maxLength: maxLength(100),
        async isUnique(value) {
          if (value === "") return true;

          if (value === this.namaEdited) return true;

          for (var index in this.tipeDokumen) {
            if (
              this.tipeDokumen[index].nama.toUpperCase().trim() ===
              value.toUpperCase().trim()
            ) {
              return false;
            }
          }
          return true;
        }
      }
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Tambah Tipe dokumen" : "Ubah Tipe Dokumen";
    },
    namaErrors() {
      const errors = [];
      if (!this.$v.editedItem.nama.$dirty) return errors;
      !this.$v.editedItem.nama.required &&
        errors.push("Nama tipe dokumen harus di isi");
      !this.$v.editedItem.nama.maxLength &&
        errors.push("Nama tipe dokumen panjang maksimal 100 karakter");
      !this.$v.editedItem.nama.isUnique &&
        errors.push("Nama tipe dokumen sudah terdaftar");
      return errors;
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    this.getData();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/tipe-dokumen")
        .then(response => {
          this.tipeDokumen = response.data;
          console.log(response.data);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    postData() {
      apiServer
        .post("/tipe-dokumen", this.editedItem)
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
        .delete("/tipe-dokumen/" + id + "/" + this.$store.state.user.name)
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
        .put("/tipe-dokumen", this.editedItem)
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
      this.namaEdited = item.nama;
      this.editedIndex = this.tipeDokumen.indexOf(item);
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
        this.editedItem.username = this.$store.state.user.name;
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
