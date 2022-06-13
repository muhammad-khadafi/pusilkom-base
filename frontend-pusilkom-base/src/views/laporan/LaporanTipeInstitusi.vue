<template>
  <v-container fluid>
    <material-card
      color="red"
      title="Laporan"
    >
      <div v-if="hasAccess === false">
        <v-card>
          <v-row>
            <v-col class="align">
              <page403 />
            </v-col>
          </v-row>
        </v-card>
      </div>
      <div v-else>
        <v-data-table
          :headers="headers"
          :items="daftar"
          class="elevation-1"
          :loading="isLoading"
          loading-text="Loading... Please wait"
          :search="search"
          item-key="request"
        >
          <template v-slot:top>
            <v-toolbar dense>
              <v-toolbar-title>Per Tipe Institusi</v-toolbar-title>
            </v-toolbar>
            <v-toolbar
              flat
              class="mt-1"
            >
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Cari"
                single-line
                hide-details
                class="search"
              />
            </v-toolbar>
          </template>
        </v-data-table>
      </div>
    </material-card>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'
  import Page403 from '@/views/403.vue'

  export default {
    components: { Page403 },
    data: () => ({
      headers: [
        { text: 'Tipe Institusi', width: '10%', value: 'grouping' },
        { text: 'Tahun', width: '15%', value: 'periode' },
        { text: 'Jumlah Penelitian', width: '10%', value: 'jumlah' },
      ],
      daftar: [],
      isLoading: false,
      search: '',
      hasAccess: null,
      menu: false,
    }),

    created () {
        this.getData()
    },

    methods: {
      getData () {
        console.log('periode:', this.periode)
        this.isLoading = true
        apiServer
          .get('/laporan/perTipeInstitusi')
          .then(response => {
            this.daftar = response.data
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },

    },

  }
</script>

<style>

</style>
