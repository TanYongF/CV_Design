import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import WindiCSS from 'vite-plugin-windicss'

import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  resolve:{
    alias:{
      "~": path.resolve(__dirname, "src")
    }
  },
  plugins: [vue(),WindiCSS()],
  server: {
    proxy: {
      "/api": {
        target: "http://81.68.239.206:8083",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
      "/ai": {
        target: "http://127.0.0.1:8084",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/ai/, ""),
      }
      
    },
  },
})

