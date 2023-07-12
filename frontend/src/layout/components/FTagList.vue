<template>
  <div class="f-tag-list" :style="{left:$store.state.asideWidth}">
    <el-tabs v-model="editableTabsValue" type="card" class="demo-tabs" closable @tab-remove="removeTab">
      <el-tab-pane v-for="item in editableTabs" :key="item.name" :label="item.title" :name="item.name"></el-tab-pane>
    </el-tabs>



    <span class="tag-btn">
      <el-dropdown>
        <span class="el-dropdown-link">
        
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>Action 1</el-dropdown-item>
            <el-dropdown-item>Action 2</el-dropdown-item>
            <el-dropdown-item>Action 3</el-dropdown-item>
            <el-dropdown-item disabled>Action 4</el-dropdown-item>
            <el-dropdown-item divided>Action 5</el-dropdown-item>
          </el-dropdown-menu> 
        </template>
      </el-dropdown>
    </span>

  </div>
</template>

<script setup>
import { ref } from 'vue'

let tabIndex = 2
const editableTabsValue = ref('2')
const editableTabs = ref([
  {
    title: 'Tab 1',
    name: '1',
    content: 'Tab 1 content',
  },
  {
    title: 'Tab 2',
    name: '2',
    content: 'Tab 2 content',
  },
])

const addTab = (targetName) => {
  const newTabName = `${++tabIndex}`
  editableTabs.value.push({
    title: 'New Tab',
    name: newTabName,
    content: 'New Tab content',
  })
  editableTabsValue.value = newTabName
}
const removeTab = (targetName) => {
  const tabs = editableTabs.value
  let activeName = editableTabsValue.value
  if (activeName === targetName) {
    tabs.forEach((tab, index) => {
      if (tab.name === targetName) {
        const nextTab = tabs[index + 1] || tabs[index - 1]
        if (nextTab) {
          activeName = nextTab.name
        }
      }
    })
  }

  editableTabsValue.value = activeName
  editableTabs.value = tabs.filter((tab) => tab.name !== targetName)
}
</script>


<style scoped>
.f-tag-list{
  @apply bg-gray-100 flex items-center  px-2; 
  top: 64px;
  right: 0px;
  height: 44px;
}

.tag-btn{
  @apply bg-white ml-auto rounded flex items-center justify-center px-2;
  height:32px;
  width: 32px;
}
:deep(.el-tabs__header){
  @apply mb-0;
}
:deep(.el-tabs__nav){
  border: 0!important;
}
:deep(.el-tabs__item){
  border: 0!important;
  height: 32px;
  line-height: 32px;
  @apply bg-white mx-1 rounded;
}
</style>