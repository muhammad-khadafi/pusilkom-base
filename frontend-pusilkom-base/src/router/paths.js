/**
 * Define all of your application routes here
 * for more information on routes,
 * official documentation https://router.vuejs.org/en/
 */

 import DashboardView from '@/views/DashboardView.vue'
 import LoginHome from '@/views/LoginHome.vue'
 import LoginForm from '@/views/LoginForm.vue'
 import ForgotPasswordForm from '@/views/ForgotPasswordForm.vue'
 import Userweb from '@/views/UserWeb.vue'
 import Menu from '@/views/Menu.vue'
 import Role from '@/views/Role.vue'
 import ChangePassword from '@/views/usersetting/ChangePassword.vue'
 import UserRole from '@/views/UserRole'
 import RoleMenu from '@/views/RoleMenu'
 import OrganisasiList from '@/views/organisasi/OrganisasiList.vue'
 import OrganisasiAddUpdate from '@/views/organisasi/OrganisasiAddUpdate.vue'
 import Home from '@/views/Home.vue'
 import Page404 from '@/views/404.vue'
 import Page403 from '@/views/403.vue'
 import ProfilPengguna from '@/views/ProfilPengguna.vue'
 import LogAktifitas from '@/views/LogAktifitas.vue'
 import TipeDokumen from '@/views/TipeDokumen.vue'
 import Institusi from '@/views/Institusi.vue'
 import Negara from '@/views/Negara.vue'
 import UserLogin from '@/views/UserLogin.vue'
 import TipeKerjasama from '@/views/TipeKerjasama.vue'
 import Kerjasama from '@/views/Kerjasama.vue'
 import TipeInstitusi from '@/views/TipeInstitusi.vue'
 import PenanggungJawab from '@/views/PenanggungJawab.vue'
 import LaporanTipeKerjasama from '@/views/laporan/LaporanTipeKerjasama.vue'
 import KerjasamaEdit from '@/views/KerjasamaEdit.vue'
 import DokumenKerjasama from '@/views/DokumenKerjasama.vue'
 import LaporanTipeInstitusi from '@/views/laporan/LaporanTipeInstitusi.vue'
import LaporanNegara from '@/views/laporan/LaporanNegara.vue'
import LaporanRangeNilai from '@/views/laporan/LaporanRangeNilai.vue'
import KerjasamaAktif from '@/views/KerjasamaAktif.vue'
import CariKerjasama from '@/views/CariKerjasama.vue'

export default [
  {
    path: '/',
    component: LoginHome,
    children: [
      {
        path: '',
        name: 'LOGIN_HOME',
        component: LoginForm,
        meta: { loginForm: true },
      },
      {
        path: '/forgot-password',
        name: 'FORGOT_PASSWORD',
        component: ForgotPasswordForm,
        meta: { ForgotPasswordForm: true },
      },
      {
        path: '/404',
        name: '404',
        component: Page404,
        meta: { Page404: true },
      },
      {
        path: '/403',
        name: '403',
        component: Page403,
        meta: { Page403: true },
      },
    ],
  },
  {
    path: '/dashboard-view',
    component: DashboardView,
    children: [
      {
        path: '/home',
        name: 'HOME',
        component: Home,
        meta: { requiresAuth: true },
      },
      {
        path: '/userweb',
        name: 'USERWEB',
        component: Userweb,
        meta: { requiresAuth: true, kode: 'USER' },
      },
      {
        path: '/menu',
        name: 'MENU',
        component: Menu,
        meta: { requiresAuth: true },
      },
      {
        path: '/role',
        name: 'ROLE',
        component: Role,
        meta: { requiresAuth: true },
      },
      {
        path: '/user-role',
        name: 'USER_ROLE',
        component: UserRole,
        meta: { requiresAuth: true },
      },
      {
        path: '/role-menu',
        name: 'ROLE_MENU',
        component: RoleMenu,
        meta: { requiresAuth: true },
      },
      {
        path: '/change-password',
        name: 'CHANGE_PASSWORD',
        component: ChangePassword,
        meta: { requiresAuth: true },
      },
      {
        path: '/institusi',
        name: 'INSTITUSI',
        component: Institusi,
        meta: { requiresAuth: true },
      },
      {
        path: '/organisasi',
        name: 'ORGANISASI',
        component: OrganisasiList,
        meta: { requiresAuth: true },
      },
      {
        path: '/organisasi/:id',
        name: 'ORGANISASI_ADD_UPDATE',
        component: OrganisasiAddUpdate,
        meta: { requiresAuth: true },
      },
      {
        path: '/profil-pengguna',
        name: 'PROFIL_PENGGUNA',
        component: ProfilPengguna,
        meta: { requiresAuth: true },
      },
      {
        path: '/log-aktifitas',
        name: 'LOG_AKTIFITAS',
        component: LogAktifitas,
        meta: { requiresAuth: true },
      },
      {
        path: '/tipe-dokumen',
        name: 'TIPE_DOKUMEN',
        component: TipeDokumen,
        meta: { requiresAuth: true },
      },
      {
        path: '/negara',
        name: 'NEGARA',
        component: Negara,
        meta: { requiresAuth: true },
      },
      {
        path: '/pengguna-login',
        name: 'PENGGUNA_LOGIN',
        component: UserLogin,
        meta: { requiresAuth: true },
      },
      {
        path: '/tipe-kerjasama',
        name: 'TIPE_KERJASAMA',
        component: TipeKerjasama,
        meta: { requiresAuth: true },
      },
      {
        path: '/kerjasama',
        name: 'KERJASAMA',
        component: Kerjasama,
        meta: { requiresAuth: true },
      },
      {
        path: '/kerjasama-edit',
        name: 'KERJASAMA_EDIT',
        component: KerjasamaEdit,
        meta: { requiresAuth: true },
      },
      {
        path: '/tipe-institusi',
        name: 'TIPE_INSTITUSI',
        component: TipeInstitusi,
        meta: { requiresAuth: true },
      },
      {
        path: '/penanggung-jawab',
        name: 'PENANGGUNG_JAWAB',
        component: PenanggungJawab,
        meta: { requiresAuth: true },
      },
      {
        path: '/laporan/laporan-tipe-kerjasama',
        name: 'LAPORAN_TIPE_KERJASAMA',
        component: LaporanTipeKerjasama,
        meta: { requiresAuth: true },
      },
      {
        path: '/dokumen-kerjasama',
        name: 'DOKUMEN_KERJASAMA',
        component: DokumenKerjasama,
        meta: { requiresAuth: true },
      },
      {
        path: '/laporan/laporan-tipe-institusi',
        name: 'LAPORAN_TIPE_INSTITUSI',
        component: LaporanTipeInstitusi,
        meta: { requiresAuth: true },
      },
      {
        path: '/laporan/laporan-negara',
        name: 'LAPORAN_NEGARA',
        component: LaporanNegara,
        meta: { requiresAuth: true },
      },
      {
        path: '/laporan/laporan-range-nilai',
        name: 'LAPORAN_RANGE_NILAI',
        component: LaporanRangeNilai,
        meta: { requiresAuth: true },
      },
      {
        path: '/kerjasama-aktif',
        name: 'KERJASAMA_AKTIF',
        component: KerjasamaAktif,
        meta: { requiresAuth: true },
      },
      {
        path: '/cari-kerjasama',
        name: 'CARI_KERJASAMA',
        component: CariKerjasama,
        meta: { requiresAuth: true },
      },
    ],
  },
]
