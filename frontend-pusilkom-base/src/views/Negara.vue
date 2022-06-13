<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="negara"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Negara</v-toolbar-title>
          </v-toolbar>
          <v-divider></v-divider>
          <v-toolbar flat class="mt-1">
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Cari"
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
                          label="Nama Negara"
                          required
                          @input="$v.editedItem.nama.$touch()"
                          @blur="$v.editedItem.nama.$touch()"
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
    negara: [],
    headers: [
      { text: "Nama Negara", value: "nama" },
      { text: "Aksi", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      nama: "",
      username : "",
    },
    defaultItem: {
      roleCode: "",
      roleName: "",
      username: ""
    },
    namaEdited: "",
  }),

  validations: {
    editedItem: {
      roleCode: {
        required,
        maxLength: maxLength(50),
        async isUnique(value) {
          if (value === "") return true;

          if (value === this.roleCodeEdited) return true;

          for (var index in this.role) {
            if (
              this.role[index].roleCode.toUpperCase().trim() ===
              value.toUpperCase().trim()
            ) {
              return false;
            }
          }
          return true;
        }
      },
      roleName: {
        required,
        maxLength: maxLength(100),
        async isUnique(value) {
          if (value === "") return true;

          if (value === this.roleNameEdited) return true;

          for (var index in this.role) {
            if (
              this.role[index].roleName.toUpperCase().trim() ===
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
      return this.editedIndex === -1 ? "Tambah Negara" : "Ubah Negara";
    },
    roleCodeErrors() {
      const errors = [];
      if (!this.$v.editedItem.roleCode.$dirty) return errors;
      !this.$v.editedItem.roleCode.required &&
        errors.push("Role code harus di isi");
      !this.$v.editedItem.roleCode.maxLength &&
        errors.push("Role code panjang maksimal 50 karakter");
      !this.$v.editedItem.roleCode.isUnique &&
        errors.push("Role code sudah terdaftar");
      return errors;
    },
    roleNameErrors() {
      const errors = [];
      if (!this.$v.editedItem.roleName.$dirty) return errors;
      !this.$v.editedItem.roleName.required &&
        errors.push("Role name harus di isi");
      !this.$v.editedItem.roleName.maxLength &&
        errors.push("Role name panjang maksimal 100 karakter");
      !this.$v.editedItem.roleName.isUnique &&
        errors.push("Role name sudah terdaftar");
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
        .get("/negara")
        .then(response => {
          this.negara = response.data;
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
        .post("/negara", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Berhasil menambah data baru");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error!"
          );
        });
    },
    deleteData(id) {
      apiServer
        .delete("/negara/" + id + "/" + this.$store.state.user.name)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Hapus data berhasil");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", error.response.data);
        });
    },
    updateData() {
      apiServer
        .put("/negara", this.editedItem)
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
      this.editedIndex = this.negara.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      confirm("Apakah Anda yakin menghapus data ini?") &&
        this.deleteData(item.id);
    },
    close() {
      this.$v.$reset();
      this.roleCodeEdited = "";
      this.roleNameEdited = "";
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
        this.editedItem.username = this.$store.state.user.name;
        if (this.editedIndex > -1) {
          this.updateData();
        } else {
          this.postData();
        }
        this.close();
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

