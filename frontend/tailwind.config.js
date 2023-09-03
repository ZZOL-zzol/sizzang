/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    colors: {
      'primary': '#006684',
      'secondary': '#5954A8',
      'error': '#BA1A1A',
      'neutral': '#001F2A',
      'gray': '#787D85',
      'midgray': '#DCE1E9',
      'whitegray': '#F3F4F8',
    },
    fontFamily: {
      spoqa: ['Spoqa Han Sans', 'spoqa'],
    },
    extend: {},
  },
  plugins: [require("daisyui")],
}