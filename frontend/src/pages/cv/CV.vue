<template>
  <div v-if="showEmpty" v-loading="loading">
    <el-empty description="暂无简历" :image-size="screenHeight">
      <el-button type="primary" @click="$router.push('/cv/add')"
        >去上传</el-button
      >
    </el-empty>
  </div>
  <div class="h-780px" v-loading="loading">
    <el-row :gutter="20" class="flex items-center">
      <el-col
        :lg="8"
        :md="12"
        :sm="24"
        v-for="cvItem in cvss.records"
        :key="cvItem.id"
        class="card-container justify-center items-center"
      >
        <el-skeleton style="width: 240px" :loading="loading" animated>
          <template #template>
            <el-skeleton-item
              variant="image"
              style="width: 400px; height: 267px"
            />
            <div style="padding: 14px">
              <div class="flex"></div>
              <el-skeleton-item variant="h3" style="width: 50%" />
              <el-skeleton-item variant="text" />
              <div
                style="
                  display: flex;
                  align-items: center;
                  justify-items: space-between;
                  margin-top: 16px;
                  height: 16px;
                "
              >
                <el-skeleton-item variant="text" style="width: 30%" />
              </div>
            </div>
          </template>
          <template #default>
            <resumeCard :cv="cvItem"></resumeCard>
          </template>
        </el-skeleton>
      </el-col>
    </el-row>

    <div class="flex justify-center items-center">
      <el-pagination
        background
        :page-size="3"
        class="mt-5 justify-center items-center"
        layout="prev, pager, next"
        :total="totalItems"
        @current-change="handlePageChange"
        @prev-click="handlePageChange"
        @next-click="handlePageChange"
        :hide-on-single-page="true"
      >
      </el-pagination>
    </div>
  </div>
</template>


<script setup>
import { onMounted, ref, reactive } from "vue";
import { getCvs, getTagByResumeId, getCvsAndTags } from "~/api/mannager";
import resumeCard from "~/components/cv/resumeCard.vue";

const loading = ref(true);
const totalItems = ref(0);
const showEmpty = ref(false);
const cvss = reactive({
  records: [],
});
const axiosPromises = [];
const pageSize = 3;

onMounted(() => {
  //   getCvs(1, pageSize).then((res) => {
  //     cvss.records = res.data.records;
  //     //get the resume's tags
  //     const requests = cvss.records.map((record) => getTagByResumeId(record.id));
  //     Promise.all(requests).then((tagResponses) => {
  //       tagResponses.forEach((tagResponse, index) => {
  //         const tags = tagResponse.data == null ? [] : tagResponse.data;
  //         const resume = cvss.records[index];
  //         tags.push({
  //           id: "0",
  //           name: resume.intention,
  //         });
  //         resume.tags = tags;
  //       });
  //     });
  //     totalItems.value = res.data.total;
  //     loading.value = false;
  //     showEmpty.value = totalItems.value == 0;
  //   });
  getCvsAndTags(1, pageSize).then((cvsp) => {
    cvss.records = cvsp.records;
    totalItems.value = cvsp.totalItems;
    loading.value = false;
    showEmpty.value = totalItems.value == 0;
  });
});

//分页相关
const handlePageChange = (currentPage) => {
  loading.value = true;
  getCvsAndTags(currentPage, pageSize).then((cvsp) => {
    cvss.records = cvsp.records;
    totalItems.value = cvsp.totalItems;
    loading.value = false;
    showEmpty.value = totalItems.value == 0;
  });
};
</script>

<style scoped>
.upload-demo {
  @apply my-5;
}

.cv-container {
  @apply min-h-screen;
}

[v-cloak] {
  display: none;
}
</style>