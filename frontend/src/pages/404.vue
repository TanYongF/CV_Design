<template>
    <div>
        <el-result
        icon="warning"
        title="404提示"
        sub-title="您的页面走丢了"
      >
      <!-- $ 只能在template里面使用 -->
        <template #extra>
          <el-button type="primary" @click="$router.push('/')">Back</el-button>
        </template>
      </el-result>
    </div>
</template> 