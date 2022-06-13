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
                        <v-text-field v-model="judulKerjasama" label="Kerjasama" disabled></v-text-field>
                      </v-col>
                    </v-row>

                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.namaDokumen"
                          label="Nama Dokumen"
                          required
                          @input="$v.editedItem.namaDokumen.$touch()"
                          @blur="$v.editedItem.namaDokumen.$touch()"
                          :error-messages="namaDokumenErrors"
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
                          required
                          @input="$v.editedItem.idTipeDokumen.$touch()"
                          @blur="$v.editedItem.idTipeDokumen.$touch()"
                          :error-messages="tipeDokumenErrors"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.tahunDokumen"
                          label="Tahun"
                          required
                          @input="$v.editedItem.tahunDokumen.$touch()"
                          @blur="$v.editedItem.tahunDokumen.$touch()"
                          :error-messages="tahunDokumenErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-file-input
                          v-model="fileUpload"
                          @change="onFilePicked"
                          label="Upload Dokumen"
                          accept="application/pdf"
                          ref="fileInput"
                          :rules="rules"
                          required
                          @input="$v.fileUpload.$touch()"
                          @blur="$v.fileUpload.$touch()"
                          :error-messages="fileUploadErrors"
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
          <v-btn class="pl-0" small color="blue darken-1" text @click="editItem(item)">Edit</v-btn>
          <v-btn small color="blue darken-1" text @click="deleteItem(item)">Hapus</v-btn>
          <v-btn small color="blue darken-1" text @click="downloadItem(item)">Download</v-btn>
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
import { apiServer, fileUrl } from "../setup-axios";
import { required, maxLength, numeric } from "vuelidate/lib/validators";

import CommonService from '../services/CommonService.js';

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
      { text: "Tipe Dokumen", value: "tipeDokumenStr" },
      { text: "Nama Dokumen", value: "namaDokumen" },
      { text: "Nama File", value: "namaFile" },
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
      filePath: "",
      usernameSubmitter: ""
    },
    defaultItem: {
      namaDokumen: "",
      namaFile: "",
      tahunDokumen: "",
      idTipeDokumen: "",
      idKerjasama: "",
      filePath: "",
      usernameSubmitter: ""
    },
    namaEdited: "",
    judulKerjasama: "",
    rules: [
      value =>
        !value ||
        value.size < 5000000 ||
        "File harus bertipe pdf dengan ukuran kurang dari 5MB"
    ],
    fileUpload: null,
    fileUploadCurrent: null
  }),
  validations: {
    editedItem: {
      namaDokumen: {
        required,
        maxLength: maxLength(200)
      },
      tahunDokumen: {
        required,
        maxLength: maxLength(4),
        numeric
      },
      idTipeDokumen: {
        required
      }
    },
    fileUpload: {
      required
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1
        ? "Tambah Dokumen Kerjasama"
        : "Ubah Dokumen Kerjasama";
    },
    namaDokumenErrors() {
      const errors = [];
      if (!this.$v.editedItem.namaDokumen.$dirty) return errors;
      !this.$v.editedItem.namaDokumen.required &&
        errors.push("Nama dokumen harus di isi");
      !this.$v.editedItem.namaDokumen.maxLength &&
        errors.push("Nama dokumen maksimal 200 karakter");
      return errors;
    },
    tahunDokumenErrors() {
      const errors = [];
      if (!this.$v.editedItem.tahunDokumen.$dirty) return errors;
      !this.$v.editedItem.tahunDokumen.required &&
        errors.push("Tahun dokumen harus di isi");
      !this.$v.editedItem.tahunDokumen.maxLength &&
        errors.push("tahun dokumen maksimal 4 karakter");
      !this.$v.editedItem.tahunDokumen.numeric &&
        errors.push("Tahun dokumen harus angka");
      return errors;
    },
    tipeDokumenErrors() {
      const errors = [];
      if (!this.$v.editedItem.idTipeDokumen.$dirty) return errors;
      !this.$v.editedItem.idTipeDokumen.required &&
        errors.push("Tipe dokumen harus di isi");
      return errors;
    },
    fileUploadErrors() {
      const errors = [];
      if (!this.$v.fileUpload.$dirty) return errors;
      !this.$v.fileUpload.required && errors.push("File harus di isi");
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
    this.getTipeDokumen();
    this.judulKerjasama = this.$store.state.kerjasama.judul;
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/dokumen-kerjasama/" + this.$store.state.kerjasama.id)
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
    getFile() {
      apiServer
        .get("/dokumen-kerjasama-file/" + this.editedItem.id)
        .then(response => {
          this.fileUpload = CommonService.dataURLtoFile(
            response.data,
            this.editedItem.namaFile
          );
          this.fileUploadCurrent = this.fileUpload  
          const fileReader = new FileReader();
          fileReader.readAsDataURL(this.fileUpload);
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
      const formData = new FormData();
      formData.append("file", this.fileUpload);
      apiServer
        .post("/dokumen-kerjasama-upload", formData)
        .then(response => {
          this.editedItem.namaFile = response.data.fileName;
          this.editedItem.filePath = response.data.filePath;
          apiServer
            .post("/dokumen-kerjasama", this.editedItem)
            .then(response => {
              this.getData();
              this.showSnackbar("success", "berhasil menambahkan data");
              this.close();
            })
            .catch(error => {
              this.close();
              console.log("There was an error:", error.response);
              this.showSnackbar(
                "error",
                "Error, Error, gagal menambahkan data!"
              );
            });
        })
        .catch(error => {
          this.close();
          console.log("There was an error:", error.response);
          this.showSnackbar("error", "Error, gagal menambahkan data!");
        });
    },
    deleteData(id) {
      apiServer
        .delete("/dokumen-kerjasama-delete-file/" + id)
        .then(response => {
          apiServer
            .delete(
              "/dokumen-kerjasama/" + id + "/" + this.$store.state.user.name
            )
            .then(response => {
              this.getData();
              this.showSnackbar("success", "Hapus data berhasil");
            })
            .catch(error => {
              console.log("There was an error:", error.response);
              this.showSnackbar("error, gagal hapus data!");
            });
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error, gagal hapus data!");
        });
    },
    updateData() {
      if (this.fileUpload == this.fileUploadCurrent) {
        apiServer
          .put("/dokumen-kerjasama", this.editedItem)
          .then(response => {
            this.getData();
            this.showSnackbar("success", "Berhasil mengedit data");
            this.close();
          })
          .catch(error => {
            console.log("There was an error:", error.response);
            this.showSnackbar(
              "error",
              "Error, please contact your administrator!"
            );
          });
      } else {
        const formData = new FormData();
        formData.append("file", this.fileUpload);
        apiServer
        .put("/dokumen-kerjasama-upload/" + this.editedItem.id, formData)
        .then(response => {
          this.editedItem.namaFile = response.data.fileName;
          this.editedItem.filePath = response.data.filePath;
          apiServer
            .put("/dokumen-kerjasama/", this.editedItem)
            .then(response => {
              this.getData();
              this.showSnackbar("success", "Berhasil mengedit data");
              this.close();
            })
            .catch(error => {
              console.log("There was an error:", error.response);
            });
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
      }

    },
    editItem(item) {
      this.editedIndex = this.daftar.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.getFile();
      this.dialog = true;
    },
    deleteItem(item) {
      confirm("Are you sure you want to delete this item?") &&
        this.deleteData(item.id);
    },
    downloadItem(item) {
      apiServer.post("/dokumen-kerjasama/download/" + item.id).then(resp => {
        let ticket = resp.data.ticket;
        let root = window.location.protocol + "//" + window.location.host;
        window.location =
          fileUrl +
          "/download/dokumen-kerjasama/" +
          item.id +
          "?ticket=" +
          ticket;
      });
    },
    close() {
      this.$v.$reset();
      this.namaEdited = "";
      this.dialog = false;
      this.fileUpload = null;
      this.fileUploadCurrent = null;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      console.log("debug: ", this.editedItem);
      this.$v.$touch();
      if (!this.$v.$invalid) {
        this.editedItem.usernameSubmitter = this.$store.state.user.name;
        this.editedItem.idKerjasama = this.$store.state.kerjasama.id;
        if (this.editedIndex > -1) {
          this.updateData();
        } else {
          this.postData();
        }
      }
    },
    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    },
    onFilePicked(event) {
      console.log("File at picked ", this.fileUpload);
    }
  }
};
</script>
