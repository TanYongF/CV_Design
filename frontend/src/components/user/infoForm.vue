<template>
    <el-form >
        
    </el-form>
</template>


<script setup>
import { ref, reactive } from 'vue'
import { toast } from '~/composables/util.js'
import { postJob } from '~/api/mannager'
import { useRouter } from "vue-router";
const info = reactive({
    "gender": "",
    "phone": "",
    "nickname": "",
    "username":""
})
</script>

<style scoped>

</style>