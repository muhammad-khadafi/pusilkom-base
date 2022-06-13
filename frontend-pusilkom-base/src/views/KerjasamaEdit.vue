<template>
  <v-container fluid>
    <div>
      <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="2000" :top="true">
        {{ snackbarText }}
        <v-btn dark text @click="snackbar = false">Close</v-btn>
      </v-snackbar>
      <v-card>
        <v-card-title>
          <span class="headline">Kerjasama Edit</span>
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
                  @input="$v.editedItem.nilai.$touch()"
                  @blur="$v.editedItem.nilai.$touch()"
                  :error-messages="nilaiErrors"
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
          <v-btn color="blue darken-1" text @click="close">Kembali</v-btn>
          <v-btn color="blue darken-1" text @click="save">Edit</v-btn>
        </v-card-actions>
      </v-card>

      <v-card class="mt-4 mb-6">
        <v-tabs v-model="tab" background-color="primary" dark>
          <v-tab v-for="item in items" :key="item.tab">{{ item.tab }}</v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item v-for="item in items" :key="item.tab">
            <v-card flat>
              <v-card-text v-if="item.id == 1">
                <PenanggungJawab />
              </v-card-text>
              <v-card-text v-if="item.id == 2">
                <Dokumen />
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </div>
  </v-container>
</template>

<script>
import { apiServer } from "../setup-axios";
import { required, maxLength } from "vuelidate/lib/validators";
import moment from "moment";

export default {
  data() {
    return {
      tab: null,
      items: [
        { id: 1, tab: "Penanggung Jawab" },
        { id: 2, tab: "Dokumen" }
      ],
      editedItem: {
        id: "",
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
      tipeKerjasama: [],
      institusi: [],
      menu: false,
      menu1: false,
      menu2: false,
      snackbar: false,
      snackbarText: "",
      snackbarColor: "",
      kerjasama: [],
      judulCurrent: "",
      kerjasamaDefault: {
        id: null,
        idTipeKerjasama: null,
        idPihakPertama: null,
        idPihakKedua: null,
        judul: null,
        nilai: null,
        deskripsi: null,
        tanggalPerjanjian: null,
        tanggalMulai: null,
        tanggalSelesai: null,
        message: null,
        snackbarColor: null
      }
    };
  },
  validations: {
    editedItem: {
      judul: {
        required,
        maxLength: maxLength(200),
        async isUnique(value) {
          if (value === "") return true;

          if (value === this.judulCurrent) return true;

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
        required
      },
      idPihakPertama: {
        required
      },
      idPihakKedua: {
        required
      },
      deskripsi: {
        required
      },
      nilai: {
        required
      },
      tanggalMulai: {},
      tanggalSelesai: {},
      tanggalPerjanjian: {}
    }
  },

  components: {
    PenanggungJawab: () => import("@/views/KerjasamaPenanggungJawab.vue"),

    Dokumen: () => import("@/views/KerjasamaDokumen.vue")
  },

  created() {
    this.editedItem = this.$store.state.kerjasama;
    this.judulCurrent = this.$store.state.kerjasama.judul;
    this.reformatDate();
    this.getInstitusi();
    this.getTipeKerjasama();
    this.getKerjasamaList();
  },

  methods: {
    getKerjasamaList() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/kerjasama")
        .then(response => {
          this.kerjasama = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.isLoading = false;
        });
    },
    reformatDate() {
      if (this.editedItem.tanggalMulai != null) {
        this.editedItem.tanggalMulai = moment(
          this.editedItem.tanggalMulai
        ).format("YYYY-MM-DD");
      }

      if (this.editedItem.tanggalSelesai != null) {
        this.editedItem.tanggalSelesai = moment(
          this.editedItem.tanggalSelesai
        ).format("YYYY-MM-DD");
      }

      if (this.editedItem.tanggalPerjanjian != null) {
        this.editedItem.tanggalPerjanjian = moment(
          this.editedItem.tanggalPerjanjian
        ).format("YYYY-MM-DD");
      }
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
    close() {
      this.$store.dispatch("setKerjasama", this.kerjasamaDefault);
      this.$router.replace("/kerjasama");
    },
    save() {
      this.$v.$touch();
      console.log("val debug", this.$v);
      if (!this.$v.$invalid) {
        this.editedItem.usernameSubmitter = this.$store.state.user.name;
        this.updateData();
      }
    },
    updateData() {
      apiServer
        .put("/kerjasama", this.editedItem)
        .then(response => {
          this.showSnackbar("success", "Edit Data Berhasil");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", "Error! Gagal edit data");
        });
    },

    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    }
  },
  computed: {
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
    computedTanggalPerjanjian() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      this.editedItem.tanggalPerjanjianFormatted = this.editedItem.tanggalPerjanjian;
      return this.editedItem.tanggalPerjanjianFormatted
        ? moment(this.editedItem.tanggalPerjanjianFormatted).format(
            "DD-MM-YYYY"
          )
        : "";
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
    nilaiErrors() {
      const errors = [];
      if (!this.$v.editedItem.nilai.$dirty) return errors;
      !this.$v.editedItem.nilai.required &&
        errors.push("Nilai harus di isi");

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
  }
};
</script>

<style>
</style>
