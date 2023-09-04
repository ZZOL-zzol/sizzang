/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    colors: {
      'primary': '#277CE0',
      'secondary-container': '#EBE8FF',
      'gray': '#787D85',
      'midgray': '#DCE1E9',
      'whitegray': '#F3F4F8',
      'white': '#FFFFFF',
    },
    fontFamily: {
      spoqa: ['Spoqa Han Sans', 'spoqa'],
    },
    extend: {},
  },
  plugins: [require("daisyui")],
}