module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': 'vuetify',
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'vuetify/grid-unknown-attributes': 'warn',
    'vuetify/no-deprecated-classes': 'warn',
    'vuetify/no-legacy-grid': 'warn',
  },
  parserOptions: {
    parser: 'babel-eslint'
  }
}
