<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="penanggungJawab"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Penanggung Jawab</v-toolbar-title>
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
                          label="Nama PenanggungJawab"
                          required
                          @input="$v.editedItem.nama.$touch()"
                          @blur="$v.editedItem.nama.$touch()"
                          :error-messages="namaErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idInstitusi"
                          :items="institusi"
                          item-text="nama"
                          item-value="id"
                          label="Institusi Asal"
                          required
                          :error-messages="idInstitusiErrors"
                          @input="$v.editedItem.idInstitusi.$touch()"
                          @blur="$v.editedItem.idInstitusi.$touch()"
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
                          required
                          :error-messages="idKerjasamaErrors"
                          @input="$v.editedItem.idKerjasama.$touch()"
                          @blur="$v.editedItem.idKerjasama.$touch()"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.alamat"
                          label="Alamat"
                          required
                          @input="$v.editedItem.alamat.$touch()"
                          @blur="$v.editedItem.alamat.$touch()"
                          :error-messages="alamatErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.jabatan"
                          label="Jabatan"
                          required
                          @input="$v.editedItem.jabatan.$touch()"
                          @blur="$v.editedItem.jabatan.$touch()"
                          :error-messages="jabatanErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.email"
                          label="Email"
                          required
                          @input="$v.editedItem.email.$touch()"
                          @blur="$v.editedItem.email.$touch()"
                          :error-messages="emailErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.telepon"
                          label="Telepon"
                          required
                          @input="$v.editedItem.telepon.$touch()"
                          @blur="$v.editedItem.telepon.$touch()"
                          :error-messages="teleponErrors"
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
    penanggungJawab: [],
    institusi: [],
    kerjasama: [],
    headers: [
      { text: "Institusi", value: "institusiStr" },
      { text: "Kerjasama", value: "kerjasamaStr" },
      { text: "Nama", value: "nama" },
      { text: "Alamat", value: "alamat" },
      { text: "Jabatan", value: "jabatan" },
      { text: "Email", value: "email" },
      { text: "", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      nama: "",
      idKerjasama: "",
      idInstitusi: "",
      nama: "",
      alamat: "",
      jabatan: "",
      email: "",
      telepon: "",
      usernameSubmitter: ""
    },
    defaultItem: {
      nama: "",
      idKerjasama: "",
      idInstitusi: "",
      nama: "",
      alamat: "",
      jabatan: "",
      email: "",
      telepon: "",
      usernameSubmitter: ""
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

          for (var index in this.penanggungJawab) {
            if (
              this.penanggungJawab[index].nama.toUpperCase().trim() ===
              value.toUpperCase().trim()
            ) {
              return false;
            }
          }
          return true;
        }
      },
      idKerjasama: {
        required,
      },
      idInstitusi: {
        required,
      },
      nama: {
        required,
        maxLength: maxLength(200),
      },
      alamat: {
        required,
        maxLength: maxLength(200),
      },
      jabatan: {

      },
      email: {

      },
      telepon: {

      }
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Tambah PenanggungJawab" : "Ubah PenanggungJawab";
    },
    namaErrors() {
      const errors = [];
      if (!this.$v.editedItem.nama.$dirty) return errors;
      !this.$v.editedItem.nama.required &&
        errors.push("Nama penanggung jawab harus di isi");
      !this.$v.editedItem.nama.maxLength &&
        errors.push("Nama penanggung jawab panjang maksimal 200 karakter");
      return errors;
    },
    idKerjasamaErrors() {
      const errors = [];
      if (!this.$v.editedItem.idKerjasama.$dirty) return errors;
      !this.$v.editedItem.idKerjasama.required &&
        errors.push("Judul kerjasama harus di isi");
      return errors;
    },
    idInstitusiErrors() {
      const errors = [];
      if (!this.$v.editedItem.idInstitusi.$dirty) return errors;
      !this.$v.editedItem.idInstitusi.required &&
        errors.push("Institusi asal harus di isi");
      return errors;
    },
    alamatErrors() {
      const errors = [];
      if (!this.$v.editedItem.alamat.$dirty) return errors;

      return errors;
    },
    jabatanErrors() {
      const errors = [];
      if (!this.$v.editedItem.jabatan.$dirty) return errors;

      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.editedItem.email.$dirty) return errors;

      return errors;
    },
    teleponErrors() {
      const errors = [];
      if (!this.$v.editedItem.telepon.$dirty) return errors;

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
    this.getKerjasama();
    this.getInstitusi();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/penanggung-jawab")
        .then(response => {
          this.penanggungJawab = response.data;
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
    getInstitusi() {
      apiServer
        .get("/institusi")
        .then(response => {
          this.institusi = response.data;
          console.log(response.data);
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    postData() {
      apiServer
        .post("/penanggung-jawab", this.editedItem)
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
        .delete("/penanggung-jawab/" + id + "/" + this.$store.state.user.name)
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
        .put("/penanggung-jawab", this.editedItem)
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
      this.alamat = item.alamat;
      this.jabatan = item.jabatan;
      this.email = item.email;
      this.telepon = item.telepon;
      this.idKerjasama = item.idKerjasama;
      this.idInstitusi = item.idInstitusi;

      this.editedIndex = this.penanggungJawab.indexOf(item);
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
