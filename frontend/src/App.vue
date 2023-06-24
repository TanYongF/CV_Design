<script setup>
</script>

<template>
  <router-view></router-view>
</template>

<style>
.btn{
  @apply bg-purple-500 text-indigo-50 px-4 py-2 rounded-full hover:(bg-purple-900) transition-all  duration-500;
}

#nprogress .bar{
  background: whitesmoke!important;
  height: 4px!important;
}
</style>
