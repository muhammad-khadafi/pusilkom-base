<template>
  <v-container class="fill-height" fluid>
    <v-row justify="center">
      <v-col cols="12" md="10">
        <material-card color="blue" title="Profil Pengguna">
          <v-row>

            <v-col cols="12">
              <v-text-field
                label="Kode SSO"
                v-model="user.kodeSso"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Username"
                v-model="user.username"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Login Terakhir"
                v-model="user.lastLoginStr"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Logout Terakhir"
                v-model="user.lastLogoutStr"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="NIP"
                v-model="user.nip"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Nama"
                v-model="user.nama"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Email"
                v-model="user.email"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Institusi"
                v-model="user.institusi"
              />
            </v-col>
          </v-row>

          <v-divider></v-divider>

          <v-row>
            <v-form ref="form" v-model="isFormValid">
              <v-container class="py-0">

              <div class="mt-5 mb-5">
                <h2>Ganti Password</h2>
              </div>  
              
                <v-row>
                  <v-col cols="6">
                    <v-text-field
                     
                      ref="password"
                      v-model="defaultItem.passwordOld"
                      :rules="[() => !!defaultItem.passwordOld || 'Kolom harus terisi']"
                      :append-icon="showPassword2 ? 'mdi-eye-off' : 'mdi-eye'"
                      :type="showPassword2 ? 'text' : 'password'"
                      prepend-inner-icon="mdi-lock"
                      label="Password Lama"
                      placeholder="Password Lama"
                      counter
                      required
                      outlined
                      @click:append="showPassword2 = !showPassword2"
                    />
                  </v-col>
                  <v-col cols="6"></v-col>
                  <v-col cols="6">
                    <v-text-field
                     
                      ref="password"
                      v-model="defaultItem.password"
                      :rules="[() => !!defaultItem.password || 'Kolom harus terisi']"
                      :append-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
                      :type="showPassword ? 'text' : 'password'"
                      prepend-inner-icon="mdi-lock"
                      label="Password Baru"
                      placeholder="Password Baru"
                      counter
                      required
                      outlined
                      @click:append="showPassword = !showPassword"
                    />
                  </v-col>

                  <v-col cols="6">
                    <v-text-field
                    
                      ref="password"
                      v-model="defaultItem.passwordNew"
                      :rules="[() => !!defaultItem.passwordNew || 'Kolom harus terisi',
                            () => (defaultItem.password === defaultItem.passwordNew) || 'Password harus sama dengan Password Baru']"
                      :append-icon="showPassword1 ? 'mdi-eye-off' : 'mdi-eye'"
                      :type="showPassword1 ? 'text' : 'password'"
                      prepend-inner-icon="mdi-lock"
                      label="Konfirmasi Ulang Password Baru"
                      placeholder="Konfirmasi Ulang Password Baru"
                      counter
                      required
                      outlined
                      @click:append="showPassword1 = !showPassword1"
                    />
                  </v-col>

                  <v-col cols="12" class="text-right">
                    <v-btn
                      :disabled="!isFormValid"
                      color="blue"
                      @click="save"
                      class="white--text"
                    >Ganti Password</v-btn>
                  </v-col>
                </v-row>
              </v-container>
            </v-form>
          </v-row>
        </material-card>
      </v-col>
      <v-col cols="12" md="4"></v-col>
    </v-row>

    <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="3000" :top="true">
      {{ snackbarText }}
      <v-btn dark text @click="snackbar = false">Tutup</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import { apiServer } from "../setup-axios";
import { required, email, maxLength } from "vuelidate/lib/validators";

export default {
  data: () => ({
    user: {
      kodeSso: "",
      username: "",
      lastLoginStr: "",
      lastLogoutStr: "",
      nip: "",
      nama: "",
      email: "",
      institusi: ""
    },
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",

    isFormValid: false,
    showPassword: false,
    showPassword1: false,
    showPassword2: false,
    defaultItem: {
      id: "",
      password: "",
      passwordNew: "",
      passwordOld: ""
    }
  }),

  created() {
    this.getData();
    const userString = JSON.parse(localStorage.getItem("user"));
    this.defaultItem.id = userString.userId;
  },

  methods: {
    getData() {
      apiServer
        .get("/profil-pengguna/" + this.$store.state.user.userId)
        .then(response => {
          this.user = response.data;
        })
        .catch(error => {
          console.log("There was an error: ", error.response); // Logs out the error
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    save() {
      apiServer
        .put("/userweb/change-password", this.defaultItem)
        .then(response => {
          this.showSnackbar("success", "Password anda telah diubah");
          this.$refs.form.reset();
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", error.response.data);
        });
    },
    reset() {
      this.defaultItem.password = " ";
      this.defaultItem.passwordNew = " ";
    },
    showSnackbar(color, message) {
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    }
  }
};
</script>

<style>
</style>
