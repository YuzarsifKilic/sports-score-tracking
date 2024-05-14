/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        'brand-green': '#99d250',
      }
    },
    fontFamily: {
      'quicksand': ['Quicksand', 'sans-serif'],
      'exo2': ['Exo 2', 'sans-serif'],
    }
  },
  plugins: [],
}

