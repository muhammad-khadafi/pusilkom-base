<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="institusi"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Institusi</v-toolbar-title>
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
                          label="Nama Institusi"
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
                          v-model="editedItem.idParentInstitusi"
                          :items="parentInstitusi"
                          item-text="nama"
                          item-value="id"
                          label="Institusi Induk"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idNegara"
                          :items="negara"
                          item-text="nama"
                          item-value="id"
                          label="Negara"
                          required
                          :error-messages="idNegaraErrors"
                          @input="$v.editedItem.idNegara.$touch()"
                          @blur="$v.editedItem.idNegara.$touch()"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idTipeInstitusi"
                          :items="tipeInstitusi"
                          item-text="nama"
                          item-value="id"
                          label="Tipe Institusi"
                          required
                          :error-messages="idTipeInstitusiErrors"
                          @input="$v.editedItem.idTipeInstitusi.$touch()"
                          @blur="$v.editedItem.idTipeInstitusi.$touch()"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.alamat"
                          label="Alamat Institusi"
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
                          v-model="editedItem.kodeSso"
                          label="Kode SSO"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-checkbox v-model="editedItem.isUnhan" label="Status Aktif" />
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

        <template v-slot:item.isUnhan="{ item }">
          <v-icon>{{ item.isUnhan ? "mdi-check" : "mdi-minus" }}</v-icon>
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
    institusi: [],
    tipeInstitusi: [],
    negara: [],
    parentInstitusi: [],
    headers: [
      { text: "Nama Institusi", value: "nama" },
      { text: "Induk Institusi", value: "parentInstitusiStr" },
      { text: "Tipe Institusi", value: "tipeInstitusiStr" },
      { text: "Negara", value: "negaraStr" },
      { text: "Kode SSO", value: "kodeSso" },
      { text: "Alamat", value: "alamat" },
      { text: "Institusi Unhan?", value: "isUnhan" },
      { text: "", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      nama: "",
      idParentInstitusi: "",
      idTipeInstitusi: "",
      idNegara: "",
      kodeSso: "",
      alamat: "",
      isUnhan: "",
      usernameSubmitter: ""
    },
    defaultItem: {
      nama: "",
      idParentInstitusi: "",
      idTipeInstitusi: "",
      idNegara: "",
      kodeSso: "",
      alamat: "",
      isUnhan: "",
      usernameSubmitter: ""
    },
    namaEdited: ""
  }),
  parentInstitusi: [],
  validations: {
    editedItem: {
      nama: {
        required,
        maxLength: maxLength(100),
        async isUnique(value) {
          if (value === "") return true;

          if (value === this.namaEdited) return true;

          for (var index in this.institusi) {
            if (
              this.institusi[index].nama.toUpperCase().trim() ===
              value.toUpperCase().trim()
            ) {
              return false;
            }
          }
          return true;
        }
      },
      idNegara: {
        required,
      },
      idTipeInstitusi: {
        required,
      },
      alamat: {
        required,
        maxLength: maxLength(200),
      },
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Tambah Institusi" : "Ubah Institusi";
    },
    namaErrors() {
      const errors = [];
      if (!this.$v.editedItem.nama.$dirty) return errors;
      !this.$v.editedItem.nama.required &&
        errors.push("Nama institusi harus di isi");
      !this.$v.editedItem.nama.maxLength &&
        errors.push("Nama institusi panjang maksimal 100 karakter");
      !this.$v.editedItem.nama.isUnique &&
        errors.push("Nama institusi sudah terdaftar");
      return errors;
    },
    idNegaraErrors() {
      const errors = [];
      if (!this.$v.editedItem.idNegara.$dirty) return errors;
      return errors;
    },
    idTipeInstitusiErrors() {
      const errors = [];
      if (!this.$v.editedItem.idTipeInstitusi.$dirty) return errors;
      return errors;
    },
    alamatErrors() {
      const errors = [];
      if (!this.$v.editedItem.alamat.$dirty) return errors;

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
    this.getParentInstitusi();
    this.getNegara();
    this.getTipeInstitusi();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/institusi")
        .then(response => {
          this.institusi = response.data;
          console.log(response.data);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    getParentInstitusi() {
      apiServer
        .get("/institusi")
        .then(response => {
          var tmp = [{id:null, nama:"--- Tanpa institusi induk ---"}];
          tmp = tmp.concat(response.data);
          this.parentInstitusi = tmp;
          console.log(this.parentInstitusi);
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    getNegara() {
      apiServer
        .get("/negara")
        .then(response => {
          this.negara = response.data;
          console.log(response.data);
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    getTipeInstitusi() {
      apiServer
        .get("/tipe-institusi")
        .then(response => {
          this.tipeInstitusi = response.data;
          console.log(response.data);
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    postData() {
      apiServer
        .post("/institusi", this.editedItem)
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
        .delete("/institusi/" + id + "/" + this.$store.state.user.name)
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
        .put("/institusi", this.editedItem)
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
      this.kodeSso = item.kodeSso;
      this.idParentInstitusi = item.idParentInstitusi;
      this.editedIndex = this.institusi.indexOf(item);
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
