<template>
  <app-def-main-layout :showTool="false" :usePadding="false">
    <div class="app-sys-info-container">
      <a-row class="app-sys-info-row">
        <a-col
          :xs="12"
          :sm="14"
          :md="14"
          :lg="16"
          :xl="18"
          class="app-sys-info-col"
          v-app-scrollbar
        >
          <a-card :bordered="false" class="app-sys-info-left-container">
            <div>
              <app-line-title title="磁盘信息" />
              <a-card
                :bordered="false"
                class="app-disk-item app-margin-top15"
                v-for="(item, index) in sysInfoData.diskInfo"
                :key="index"
              >
                <div class="app-flex">
                  <img :src="diskImg" />
                  <div class="app-disk-progress">
                    <div class="app-disk-path">
                      盘符路径：{{ item.dirName || '未知' }}，盘符类型：{{
                        item.sysTypeName || '未知'
                      }}，已使用百分比：{{ item.usage || '0' }}%
                    </div>
                    <div class="app-disk-progress-bar">
                      <a-progress
                        :percent="item.usage"
                        :status="item.usage>80?'exception':'normal'"
                        :strokeWidth="15"
                        :show-info="false"
                      />
                    </div>
                  </div>
                </div>
                <a-descriptions
                  bordered
                  :column="{ xxl: 2, xl: 2, lg: 1, md: 1, sm: 1, xs: 1 }"
                  :size="'small'"
                  class="app-margin-top15"
                >
                  <a-descriptions-item label="文件系统：">
                    {{ item.typeName || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="总容量：">
                    {{ item.total || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="已用容量：">
                    {{ item.used || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="可用容量：">
                    {{ item.free || '未知' }}
                  </a-descriptions-item>
                </a-descriptions>
              </a-card>
            </div>

            <div class="app-sys-info-left-row">
              <app-line-title title="服务器信息" />
              <a-card :bordered="false" class="app-computer-item app-margin-top15">
                <a-descriptions
                  bordered
                  :column="{ xxl: 2, xl: 2, lg: 1, md: 1, sm: 1, xs: 1 }"
                  :size="'small'"
                >
                  <a-descriptions-item label="服务器名称：">
                    {{ sysInfoData?.computerInfo?.computerName || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="服务器IP：">
                    {{ sysInfoData?.computerInfo?.computerIp || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="操作系统：">
                    {{ sysInfoData?.computerInfo?.osName || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="系统架构：">
                    {{ sysInfoData?.computerInfo?.osArch || '未知' }}
                  </a-descriptions-item>
                </a-descriptions>
              </a-card>
            </div>

            <div class="app-sys-info-left-row">
              <app-line-title title="JVM信息" />
              <a-card :bordered="false" class="app-computer-item app-margin-top15">
                <a-descriptions
                  bordered
                  :column="{ xxl: 2, xl: 2, lg: 1, md: 1, sm: 1, xs: 1 }"
                  :size="'small'"
                >
                  <a-descriptions-item label="JAVA名称：">
                    {{ sysInfoData?.jvmInfo?.name || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="JAVA版本：">
                    {{ sysInfoData?.jvmInfo?.version || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="启动时间：">
                    {{ sysInfoData?.jvmInfo?.startTime || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="运行时长：">
                    {{ sysInfoData?.jvmInfo?.runTime || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="安装目录：">
                    {{ sysInfoData?.jvmInfo?.home || '未知' }}
                  </a-descriptions-item>
                  <a-descriptions-item label="项目路径：">
                    {{ sysInfoData?.computerInfo?.userDir || '未知' }}
                  </a-descriptions-item>
                </a-descriptions>
              </a-card>
            </div>
          </a-card>
        </a-col>
        <a-col
          :xs="12"
          :sm="10"
          :md="10"
          :lg="8"
          :xl="6"
          class="app-sys-info-col app-sys-info-right-container"
          v-app-scrollbar
        >
          <a-card :bordered="false">
            <div>
              <app-line-title title="CPU信息" />
              <a-card :bordered="false" class="app-cpu-item app-cpu-number">
                <div class="app-flex">
                  <span class="iconfont icon-cpu"></span>
                  <div class="app-flex app-flex-direction-column">
                    <div class="app-cpu-label">CPU核心数</div>
                    <div class="app-cpu-count"> {{ sysInfoData?.cpuInfo?.cpuNum || '0' }}个 </div>
                  </div>
                </div>
              </a-card>
              <a-card :bordered="false" class="app-cpu-item app-cpu-sys">
                <div class="app-flex">
                  <span class="iconfont icon-cpu"></span>
                  <div class="app-flex app-flex-direction-column">
                    <div class="app-cpu-label">系统使用率</div>
                    <div class="app-cpu-count"> {{ sysInfoData?.cpuInfo?.sys || '0.0' }}% </div>
                  </div>
                </div>
              </a-card>
              <a-card :bordered="false" class="app-cpu-item app-cpu-used">
                <div class="app-flex">
                  <span class="iconfont icon-cpu"></span>
                  <div class="app-flex app-flex-direction-column">
                    <div class="app-cpu-label">用户使用率</div>
                    <div class="app-cpu-count"> {{ sysInfoData?.cpuInfo?.used || '0.0' }}% </div>
                  </div>
                </div>
              </a-card>
              <a-card :bordered="false" class="app-cpu-item app-cpu-free">
                <div class="app-flex">
                  <span class="iconfont icon-cpu"></span>
                  <div class="app-flex app-flex-direction-column">
                    <div class="app-cpu-label">当前空闲率</div>
                    <div class="app-cpu-count"> {{ sysInfoData?.cpuInfo?.free || '0.0' }}% </div>
                  </div>
                </div>
              </a-card>
            </div>

            <div class="app-sys-info-right-row">
              <app-line-title title="内存信息" />
              <a-card :bordered="false" class="app-memory-item app-memory-total">
                <div class="app-flex">
                  <span class="iconfont icon-memory"></span>
                  <div class="app-flex app-flex-direction-column">
                    <div class="app-memory-title">总内存</div>
                    <div class="app-memory-label">
                      系统：{{ sysInfoData?.memoryInfo?.total || '0' }}G
                    </div>
                    <div class="app-memory-jvm-label">
                      JVM：{{ sysInfoData?.jvmInfo?.total || '0' }}M
                    </div>
                  </div>
                </div>
              </a-card>
              <a-card :bordered="false" class="app-memory-item app-memory-used">
                <div class="app-flex">
                  <span class="iconfont icon-memory"></span>
                  <div class="app-flex app-flex-direction-column">
                    <div class="app-memory-title">已用内存</div>
                    <div class="app-memory-label">
                      系统：{{ sysInfoData?.memoryInfo?.used || '0' }}G
                    </div>
                    <div class="app-memory-jvm-label">
                      JVM：{{ sysInfoData?.jvmInfo?.used || '0' }}M
                    </div>
                  </div>
                </div>
              </a-card>
              <a-card :bordered="false" class="app-memory-item app-memory-free">
                <div class="app-flex">
                  <span class="iconfont icon-memory"></span>
                  <div class="app-flex app-flex-direction-column">
                    <div class="app-memory-title">剩余内存</div>
                    <div class="app-memory-label">
                      系统：{{ sysInfoData?.memoryInfo?.free || '0' }}G
                    </div>
                    <div class="app-memory-jvm-label">
                      JVM：{{ sysInfoData?.jvmInfo?.free || '0' }}M
                    </div>
                  </div>
                </div>
              </a-card>
              <a-card :bordered="false" class="app-memory-item app-memory-usage">
                <div class="app-flex">
                  <span class="iconfont icon-memory"></span>
                  <div class="app-flex app-flex-direction-column">
                    <div class="app-memory-title">内存使用率</div>
                    <div class="app-memory-label">
                      系统：{{ sysInfoData?.memoryInfo?.usage || '0' }}%
                    </div>
                    <div class="app-memory-jvm-label">
                      JVM：{{ sysInfoData?.jvmInfo?.usage || '0' }}
                    </div>
                  </div>
                </div>
              </a-card>
            </div>
          </a-card>
        </a-col>
      </a-row>
    </div>
  </app-def-main-layout>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import SysInfoApi from '/@/api/sys-info-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import diskIcon from '/@/assets/img/sys-info/disk.svg';

export default defineComponent({
  name: 'SysInfo',
  setup() {
    const sysInfoData: any = ref<any>({
      diskInfo: [],
      computerInfo: {},
      jvmInfo: {},
      memoryInfo: {},
      cpuInfo: {},
    });
    const diskImg = diskIcon;

    const initPageData = () => {
      SysInfoApi.initSysInfoData().then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          sysInfoData.value = res.data.resultData;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    onMounted(() => {
      initPageData();
    });

    return {
      diskImg,
      sysInfoData,
    };
  },
});
</script>

<style lang="less" scoped>
@import './SysInfo.less';
</style>
