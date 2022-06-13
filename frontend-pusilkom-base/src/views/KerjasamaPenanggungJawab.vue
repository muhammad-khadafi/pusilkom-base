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
            <v-dialog v-model="dialog" max-width="600px">
              <template v-slot:activator="{ on }">
                <v-btn color="black" dark class="mb-2" v-on="on">Tambah</v-btn>
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
                          v-model="judulKerjasama"
                          label="Judul Kerjasama"
                          disabled
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idInstitusi"
                          :items="institusi"
                          item-text="nama"
                          item-value="id"
                          label="Institusi"
                          required
                          :error-messages="institusiErrors"
                          @input="$v.editedItem.idInstitusi.$touch()"
                          @blur="$v.editedItem.idInstitusi.$touch()"
                        />
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.nama"
                          label="Nama"
                          required
                          :error-messages="namaErrors"
                          @input="$v.editedItem.nama.$touch()"
                          @blur="$v.editedItem.nama.$touch()"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.alamat"
                          label="Alamat"
                          required
                          :error-messages="alamatErrors"
                          @input="$v.editedItem.alamat.$touch()"
                          @blur="$v.editedItem.alamat.$touch()"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.jabatan"
                          label="Jabatan"
                          required
                          :error-messages="jabatanErrors"
                          @input="$v.editedItem.jabatan.$touch()"
                          @blur="$v.editedItem.jabatan.$touch()"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.email"
                          label="Email"
                          required
                          :error-messages="emailErrors"
                          @input="$v.editedItem.email.$touch()"
                          @blur="$v.editedItem.email.$touch()"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.telepon"
                          label="Telepon"
                          required
                          :error-messages="teleponErrors"
                          @input="$v.editedItem.telepon.$touch()"
                          @blur="$v.editedItem.telepon.$touch()"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <div class="flex-grow-1"></div>
                  <v-btn color="blue darken-1" text @click="close">Kembali</v-btn>
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
          <v-btn class="pl-0" small color="blue darken-1" text @click="editItem(item)">Edit</v-btn>
          <v-btn small color="blue darken-1" text @click="deleteItem(item)">Hapus</v-btn>
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
import { required, maxLength, email } from "vuelidate/lib/validators";

export default {
  data: () => ({
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    menu: false,
    dialog: false,
    penanggungJawab: [],
    headers: [
      { text: "Institusi Asal", value: "institusiStr" },
      { text: "Nama", value: "nama" },
      { text: "Alamat", value: "alamat" },
      { text: "Jabatan", value: "jabatan" },
      { text: "Email", value: "email" },
      { text: "Aksi", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
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
      idKerjasama: "",
      idInstitusi: "",
      nama: "",
      alamat: "",
      jabatan: "",
      email: "",
      telepon: "",
      usernameSubmitter: ""
    },
    institusi: [],
    judulKerjasama: ""
  }),

  validations: {
    editedItem: {
      idInstitusi: {
        required
      },
      nama: {
        required,
        maxLength: maxLength(200)
      },
      alamat: {
        required,
        maxLength: maxLength(200)
      },
      jabatan: {
        required,
        maxLength: maxLength(200)
      },
      email: {
        required,
        email,
        maxLength: maxLength(200)
      },
      telepon: {
        required,
        maxLength: maxLength(200)
      }
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1
        ? "Tambah Penanggung Jawab"
        : "Edit Penanggung Jawab";
    },
    institusiErrors() {
      const errors = [];
      if (!this.$v.editedItem.idInstitusi.$dirty) return errors;
      !this.$v.editedItem.idInstitusi.required &&
        errors.push("Institusi harus di isi");
      return errors;
    },
    namaErrors() {
      const errors = [];
      if (!this.$v.editedItem.nama.$dirty) return errors;
      !this.$v.editedItem.nama.required && errors.push("Nama harus di isi");
      !this.$v.editedItem.nama.maxLength &&
        errors.push("Nama maksimal 200 karakter");
      return errors;
    },
    alamatErrors() {
      const errors = [];
      if (!this.$v.editedItem.alamat.$dirty) return errors;
      !this.$v.editedItem.alamat.required && errors.push("Alamat harus di isi");
      !this.$v.editedItem.alamat.maxLength &&
        errors.push("Alamat maksimal 200 karakter");
      return errors;
    },
    jabatanErrors() {
      const errors = [];
      if (!this.$v.editedItem.jabatan.$dirty) return errors;
      !this.$v.editedItem.jabatan.required &&
        errors.push("Jabatan harus di isi");
      !this.$v.editedItem.jabatan.maxLength &&
        errors.push("Jabatan maksimal 200 karakter");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.editedItem.email.$dirty) return errors;
      !this.$v.editedItem.email.required && errors.push("Email harus di isi");
      !this.$v.editedItem.email.maxLength &&
        errors.push("Email maksimal 200 karakter");
      !this.$v.editedItem.email.email &&
        errors.push("Gunakan format email yang benar");
      return errors;
    },
    teleponErrors() {
      const errors = [];
      if (!this.$v.editedItem.telepon.$dirty) return errors;
      !this.$v.editedItem.telepon.required &&
        errors.push("Telepon harus di isi");
      !this.$v.editedItem.telepon.maxLength &&
        errors.push("Telepon maksimal 200 karakter");
      return errors;
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    this.judulKerjasama = this.$store.state.kerjasama.judul;
    this.getData();
    this.getInstitusi();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/penanggung-jawab/kerjasama/" + this.$store.state.kerjasama.id)
        .then(response => {
          this.penanggungJawab = response.data;
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.isLoading = false;
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
          this.showSnackbar("success", "Berhasil menambahkan data");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", "Error, gagal menambahkan data!");
        });
    },
    deleteData(id) {
      apiServer
        .delete("/penanggung-jawab/" + id + "/" + this.$store.state.user.name)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Berhasil hapus data");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, gagal hapus data!"
          );
        });
    },
    updateData() {
      apiServer
        .put("/penanggung-jawab", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Berhasil edit data");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, gagal edit data!"
          );
        });
    },
    editItem(item) {
      this.editedIndex = this.penanggungJawab.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      confirm("Apakah anda yakin akan menghapus data ini?") &&
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
      this.$v.$touch();
      if (!this.$v.$invalid) {
        this.editedItem.idKerjasama = this.$store.state.kerjasama.id;
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
    }
  }
};
</script>

<style>
</style>