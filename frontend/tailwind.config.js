/** @type {import('tailwindcss').Config} */
const colors = require('tailwindcss/colors');

module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      margin: {
        center: '0 auto',
      },
      fontFamily: {
        environmentR: ['EnvironmentR'],
        spoqa : ['Spoqa Han Sans Neo'],
      },
    },
    colors: {

      transparent: 'transparent',
      current: 'currentColor',
      black: colors.black,
      white: colors.white,
      gray: colors.gray,
      emerald: colors.emerald,
      indigo: colors.indigo,
      yellow: colors.yellow,
      slate: colors.slate,
      zinc: colors.zinc,
      neutral: colors.neutral,
      stone: colors.stone,
      red: colors.red,
      orange: colors.orange,
      amber: colors.amber,
      lime: colors.lime,
      green: colors.green,
      teal: colors.teal,
      cyan: colors.cyan,
      sky: colors.sky,
      blue: colors.blue,
      violet: colors.violet,
      fuchsia: colors.fuchsia,
      pink: colors.pink,
      rose: colors.rose,
      'primary': '#277CE0',
      'primary-container': '#D5F1FF',
      'secondary' : '#5954A8',
      'secondary-container': '#EBE8FF',
      'surface' : '#001B2A',
      'outline': '#787D85',
      'outline-container' : '#DCE1E9',
      'error' : '#C21B43',
      'error-container' : '#FCD5DA',
      'background-fill' : '#F3F4F8',
    },
    // fontFamily: {
    //   spoqa: ['Spoqa Han Sans', 'spoqa'],
    // },
  },
  plugins: [require("daisyui")],
}