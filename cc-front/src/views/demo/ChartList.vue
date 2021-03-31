<template>
  <app-def-main-layout
    :showTool="false"
    :usePadding="false"
    :containerClass="'app-main-transparent-container'"
    :contentBodyStyle="{ padding: '8px' }"
  >
    <div class="app-chart-list">
      <a-row>
        <a-col :span="24">
          <a-card title="图表1" :bordered="false" class="app-card-container">
            <template #extra>
              <a href="#">更多</a>
            </template>
            <v-chart :option="centerEchartOption" style="height: 220px" />
          </a-card>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-card title="图表2" :bordered="false" class="app-card-container">
            <template #extra>
              <a href="#">更多</a>
            </template>
            <v-chart :option="leftEchartOption" style="height: 230px" />
          </a-card>
        </a-col>
        <a-col :span="12">
          <a-card title="图表3" :bordered="false" class="app-card-container">
            <template #extra>
              <a href="#">更多</a>
            </template>
            <v-chart :option="rightEchartOption" style="height: 230px" />
          </a-card>
        </a-col>
      </a-row>
    </div>
  </app-def-main-layout>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';

import VChart from 'vue-echarts';
import { use } from 'echarts/core';

import {
  TitleComponent,
  ToolboxComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
} from 'echarts/components';
import { BarChart, PieChart, LineChart } from 'echarts/charts';
import { CanvasRenderer } from 'echarts/renderers';

use([
  TitleComponent,
  ToolboxComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  BarChart,
  PieChart,
  LineChart,
  CanvasRenderer,
]);

export default defineComponent({
  name: 'ChartList',
  components: {
    VChart,
  },
  setup() {
    let centerEchartOption = ref<any>({});
    let leftEchartOption = ref<any>({
      title: {
        show: false,
      },
      tooltip: {
        trigger: 'axis',
      },
      legend: {
        show: false,
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        top: '3%',
        containLabel: true,
      },
      toolbox: {
        show: false,
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
      },
      yAxis: {
        type: 'value',
      },
      series: [
        {
          name: '邮件营销',
          type: 'line',
          stack: '总量',
          data: [120, 132, 101, 134, 90, 230, 210],
        },
        {
          name: '联盟广告',
          type: 'line',
          stack: '总量',
          data: [220, 182, 191, 234, 290, 330, 310],
        },
        {
          name: '视频广告',
          type: 'line',
          stack: '总量',
          data: [150, 232, 201, 154, 190, 330, 410],
        },
        {
          name: '直接访问',
          type: 'line',
          stack: '总量',
          data: [320, 332, 301, 334, 390, 330, 320],
        },
        {
          name: '搜索引擎',
          type: 'line',
          stack: '总量',
          data: [820, 932, 901, 934, 1290, 1330, 1320],
        },
      ],
    });

    let rightEchartOption = ref<any>({
      legend: {
        show: false,
      },
      toolbox: {
        show: true,
        feature: {
          mark: { show: true },
          dataView: { show: true, readOnly: false },
          restore: { show: true },
          saveAsImage: { show: true },
        },
      },
      series: [
        {
          name: '面积模式',
          type: 'pie',
          radius: [50, 100],
          center: ['50%', '50%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 8,
          },
          data: [
            { value: 40, name: 'rose 1' },
            { value: 38, name: 'rose 2' },
            { value: 32, name: 'rose 3' },
            { value: 30, name: 'rose 4' },
            { value: 28, name: 'rose 5' },
            { value: 26, name: 'rose 6' },
            { value: 22, name: 'rose 7' },
            { value: 18, name: 'rose 8' },
          ],
        },
      ],
    });

    const centerEchartOptionInit = () => {
      const data1 = [20, 30, 20, 30, 20, 30, 20, 30, 20, 30];
      const data2 = [9, 30, 9, 60, 70, 20, 59, 20, 49, 20];
      const data3 = [20, 30, 20, 30, 20, 30, 20, 30, 20, 30];
      const datacity = [
        '济北市',
        '青山市',
        '文博市',
        '红枣县',
        '东胜县',
        '烟花市',
        '莱平市',
        '济东市',
        '山海市',
        '贵泉市',
      ];

      centerEchartOption.value = {
        color: ['#388BFF', '#05C3FA', '#2eca94', '#18e09b'],
        tooltip: {
          trigger: 'axis',
        },
        legend: {
          top: '8%',
          data: ['存量', '新增', '拆除', '整改'],
        },
        grid: {
          //图表的位置
          top: '20%',
          left: '3%',
          right: '4%',
          bottom: '5%',
          containLabel: true,
        },
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              show: true,
              interval: 'auto',
              formatter: '{value} ',
            },
            splitLine: {
              show: true,
              lineStyle: {
                type: 'dashed',
              },
            },
            show: true,
          },
        ],
        xAxis: [
          {
            type: 'category',
            axisLabel: {
              interval: 0,
              show: true,
              splitNumber: 15,
            },
            data: datacity,
          },
        ],
        series: [
          {
            name: '存量',
            type: 'bar',
            stack: 'sum',
            barWidth: '20px',
            data: data1,
          },
          {
            name: '新增',
            type: 'bar',
            barWidth: '20px',
            stack: 'sum',
            data: data2,
          },
          {
            name: '拆除',
            type: 'bar',
            color: '#2eca94',
            stack: 'sum1',
            barWidth: '20px',
            data: data3,
          },
          {
            name: '整改',
            type: 'bar',
            color: '#18e09b',
            stack: 'sum1',
            barWidth: '20px',
            data: data3,
          },
        ],
      };
    };

    onMounted(() => {
      centerEchartOptionInit();
    });

    return {
      centerEchartOption,
      leftEchartOption,
      rightEchartOption,
    };
  },
});
</script>

<style lang="less" scoped>
@import './ChartList.less';
</style>
