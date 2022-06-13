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
            <v-toolbar-title>Daftar Kerjasama</v-toolbar-title>
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
                          v-model="editedItem.judul"
                          label="Judul"
                          required
                          @input="$v.editedItem.judul.$touch()"
                          @blur="$v.editedItem.judul.$touch()"
                          :error-messages="judulErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.nilai"
                          label="Nilai (IDR)"
                          required
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idTipeKerjasama"
                          :items="tipeKerjasama"
                          item-text="nama"
                          item-value="id"
                          label="Tipe Kerjasama"
                          required
                          :error-messages="idTipeKerjasamaErrors"
                          @input="$v.editedItem.idTipeKerjasama.$touch()"
                          @blur="$v.editedItem.idTipeKerjasama.$touch()"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idPihakPertama"
                          :items="institusi"
                          item-text="nama"
                          item-value="id"
                          label="Pihak Pertama"
                          required
                          :error-messages="idPihakPertamaErrors"
                          @input="$v.editedItem.idPihakPertama.$touch()"
                          @blur="$v.editedItem.idPihakPertama.$touch()"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idPihakKedua"
                          :items="institusi"
                          item-text="nama"
                          item-value="id"
                          label="Pihak Kedua"
                          required
                          :error-messages="idPihakKeduaErrors"
                          @input="$v.editedItem.idPihakKedua.$touch()"
                          @blur="$v.editedItem.idPihakKedua.$touch()"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-textarea
                          v-model="editedItem.deskripsi"
                          color="blue"
                          label="Deskripsi"
                          required
                          @input="$v.editedItem.deskripsi.$touch()"
                          @blur="$v.editedItem.deskripsi.$touch()"
                          :error-messages="deskripsiErrors"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col md="6">
                        <v-menu
                          v-model="menu1"
                          :close-on-content-click="false"
                          :nudge-right="40"
                          transition="scale-transition"
                          offset-y
                          min-width="290px"
                        >
                            <template v-slot:activator="{ on }">
                            <v-text-field
                              v-model="editedItem.tanggalMulai"
                              prepend-icon="mdi-calendar"
                              readonly
                              label="Tanggal Mulai"
                              :error-messages="$v.editedItem.tanggalMulai.$error ? ['Tanggal Mulai harus diisi'] : []"
                              v-on="on"
                            />
                          </template>
                          <v-date-picker
                            ref="picker"
                            v-model="editedItem.tanggalMulai"
                            @input="$v.editedItem.tanggalMulai.$touch()"
                          />
                        </v-menu>
                      </v-col>
                      <v-col md="6">
                        <v-menu
                          v-model="menu2"
                          :close-on-content-click="false"
                          :nudge-right="40"
                          transition="scale-transition"
                          offset-y
                          min-width="290px"
                        >
                          <template v-slot:activator="{ on }">
                            <v-text-field
                              v-model="editedItem.tanggalSelesai"
                              prepend-icon="mdi-calendar"
                              readonly
                              label="Tanggal Selesai"
                              :error-messages="$v.editedItem.tanggalSelesai.$error ? ['Tanggal Selesai harus diisi'] : []"
                              v-on="on"
                            />
                          </template>
                          <v-date-picker
                            ref="picker"
                            v-model="editedItem.tanggalSelesai"
                            @input="$v.editedItem.tanggalSelesai.$touch()"
                          />
                        </v-menu>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col md="6">
                        <v-menu
                          v-model="menu"
                          :close-on-content-click="false"
                          :nudge-right="40"
                          transition="scale-transition"
                          offset-y
                          min-width="290px"
                        >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="editedItem.tanggalPerjanjian"
                            prepend-icon="mdi-calendar"
                            readonly
                            label="Tanggal Perjanjian"
                            @blur="$v.editedItem.tanggalPerjanjian.$touch()"
                            v-bind="attrs"
                            v-on="on"
                          />
                        </template>
                        <v-date-picker
                          ref="picker"
                          v-model="editedItem.tanggalPerjanjian"
                          label="Tanggal Perjanjian"
                          @input="$v.editedItem.tanggalPerjanjian.$touch()"
                          @blur="$v.editedItem.tanggalPerjanjian.$touch()"
                          :error-messages="tanggalPerjanjianErrors"
                        />
                      </v-menu>
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
import moment from "moment";

export default {
  data: () => ({
    isTest: true,
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    menu: false,
    menu1: false,
    menu2: false,
    dialog: false,
    kerjasama: [],
    tipeKerjasama: [],
    institusi: [],
    headers: [
      { text: "Judul", value: "judul" },
      { text: "Nilai (IDR)", value: "nilai" },
      { text: "Tipe Kerjasama", value: "tipeKerjasamaStr" },
      { text: "Pihak Pertama", value: "pihakPertamaStr" },
      { text: "Pihak Kedua", value: "pihakKeduaStr" },
      { text: "", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      judul: "",
      nilai: "",
      idTipeKerjasama: "",
      idPihakPertama: "",
      idPihakKedua: "",
      deskripsi: "",
      tanggalMulai: "",
      tanggalSelesai: "",
      tanggalPerjanjian: "",
      usernameSubmitter: "",
    },
    defaultItem: {
      judul: "",
      nilai: "",
      idTipeKerjasama: "",
      idPihakPertama: "",
      idPihakKedua: "",
      deskripsi: "",
      tanggalMulai: "",
      tanggalSelesai: "",
      tanggalPerjanjian: "",
      usernameSubmitter: ""
    },
    namaEdited: ""
  }),
  validations: {
    editedItem: {
      judul: {
        required,
        maxLength: maxLength(200),
        async isUnique(value) {
          if (value === "") return true;

          if (value === this.judul) return true;

          for (var index in this.kerjasama) {
            if (
              this.kerjasama[index].judul.toUpperCase().trim() ===
              value.toUpperCase().trim()
            ) {
              return false;
            }
          }
          return true;
        }
      },
      idTipeKerjasama: {
        required,
      },
      idPihakPertama: {
        required,
      },
      idPihakKedua: {
        required,
      },
      deskripsi: {
        required,
      },
      tanggalMulai: {

      },
      tanggalSelesai: {

      },
      tanggalPerjanjian: {

      },
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Tambah Kerjasama" : "Ubah Kerjasama";
    },
    judulErrors() {
      const errors = [];
      if (!this.$v.editedItem.judul.$dirty) return errors;
      !this.$v.editedItem.judul.required &&
        errors.push("Judul kerjasama harus di isi");
      !this.$v.editedItem.judul.maxLength &&
        errors.push("Judul kerjasama panjang maksimal 200 karakter");
      !this.$v.editedItem.judul.isUnique &&
        errors.push("Judul kerjasama sudah terdaftar");
      return errors;
    },
    computedTanggalPerjanjian () {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.editedItem.tanggalPerjanjianFormatted = this.editedItem.tanggalPerjanjian
        return this.editedItem.tanggalPerjanjianFormatted
          ? moment(this.editedItem.tanggalPerjanjianFormatted).format('DD-MM-YYYY')
          : ''
      },

    idTipeKerjasamaErrors() {
      const errors = [];
      if (!this.$v.editedItem.idTipeKerjasama.$dirty) return errors;
      !this.$v.editedItem.idTipeKerjasama.required &&
        errors.push("Tipe kerjasama harus di isi");
      return errors;
    },
    idPihakPertamaErrors() {
      const errors = [];
      if (!this.$v.editedItem.idPihakPertama.$dirty) return errors;
      !this.$v.editedItem.idPihakPertama.required &&
        errors.push("Pihak pertama harus di isi");
      return errors;
    },
    idPihakKeduaErrors() {
      const errors = [];
      if (!this.$v.editedItem.idPihakKedua.$dirty) return errors;
      !this.$v.editedItem.idPihakKedua.required &&
        errors.push("Pihak kedua harus di isi");
      return errors;
    },
    deskripsiErrors() {
      const errors = [];
      if (!this.$v.editedItem.deskripsi.$dirty) return errors;
      !this.$v.editedItem.deskripsi.required &&
        errors.push("Deskripsi harus di isi");

      return errors;
    },
    tanggalPerjanjianErrors() {
      const errors = [];
      if (!this.$v.editedItem.tanggalPerjanjian.$dirty) return errors;

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
    this.getInstitusi();
    this.getTipeKerjasama();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/kerjasama")
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
    getTipeKerjasama() {
      apiServer
        .get("/tipe-kerjasama")
        .then(response => {
          this.tipeKerjasama = response.data;
          console.log(response.data);
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    postData() {
      apiServer
        .post("/kerjasama", this.editedItem)
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
        .delete("/kerjasama/" + id + "/" + this.$store.state.user.name)
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
        .put("/kerjasama", this.editedItem)
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
      this.$store.dispatch("setKerjasama", item);
      this.$router.replace("/kerjasama-edit");
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
