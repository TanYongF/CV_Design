<template>
    <el-card shadow="never">
        <template #header>
            <div class="flex justify-between">
                <span class="text-xl chart-title"> {{ chartTitle }}</span>
                <div>
                    <el-check-tag v-for='(item, index) in options' :key="index" :checked="current == item.value"
                        style="margin-right:8px" @click="handleChoose(item.value)"> {{ item.text }}
                    </el-check-tag>
                    <!-- <el-check-tag checked style="margin-right: 8px">Checked</el-check-tag> -->
                </div>
            </div>
        </template>
        <div :id = "chartId" style="width:100%; height:300px">
        </div>
    </el-card>

</template>
    

<script setup>
import * as echarts from 'echarts';
import { getStatistics } from "~/api";
import { ref, reactive, onMounted, onBeforeUnmount, watch } from "vue";
const props = defineProps(['chartData', 'chartTitle', 'cardTitle', 'chartId'])
const current = ref("pie")
const options = [{
    text: "扇形图",
    value: "pie"
},  {
    text: "柱状图",
    value: "bar"
}]


var myChart, option
const handleChoose = (value) => {
    current.value = value
    console.log(value);
    // 将数据转换为数组
    const ages = Object.entries(props.chartData).map(([age, count]) => ({
        name: age,
        value: count
    }));

    // 配置 Option
    const option = {
        title: {
            text: props.chartTitle,
            left: 'center',
            textStyle: {
                color: '#333',
                fontWeight: 'bold'
            }
        },
        series: [{
            name: '年龄分布',
            type: current.value,
            radius: '55%',
            center: ['50%', '50%'],
            data: ages,
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },
            label: {
                show: true,
                formatter: '{b}: {c}人'
            },
            labelLine: {
                show: true
            }
        }]
    };

    myChart.setOption(option);
    

   
}

const getData = ()=>{
    // 将数据转换为数组
    const ages = Object.entries(props.chartData).map(([age, count]) => ({
        name: age,
        value: count
    }));

    // 配置 Option
    const option = {
        title: {
            text: props.chartTitle,
            left: 'center',
            textStyle: {
                color: '#333',
                fontWeight: 'bold'
            }
        },
        series: [{
            name: '年龄分布',
            type: current.value,
            radius: '55%',
            center: ['50%', '50%'],
            data: ages,
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },
            label: {
                show: true,
                formatter: '{b}: {c}人'
            },
            labelLine: {
                show: true
            }
        }]
    };

    myChart.setOption(option);


}

onMounted(() => {
    console.log(props.chartId);
  
    var chartDom = document.getElementById(props.chartId);
    myChart = echarts.init(chartDom);
    myChart.showLoading();
    getData()
    myChart.hideLoading();

})



</script>
<style scoped>
.chart-title {
    font-family: 'JMH';
}
</style>