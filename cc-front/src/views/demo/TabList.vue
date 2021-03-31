<template>
  <app-def-main-layout>
    <template #headerBtnsTpl>
      <app-custom-button :type="'error-hollow'">
        <span class="iconfont icon-delete"></span> 删除
      </app-custom-button>
      <app-custom-button :type="'success'" class="app-margin-left10">
        <span class="iconfont icon-plus"></span> 添加
      </app-custom-button>
    </template>
    <div>
      <a-form layout="inline" class="app-query-form app-margin-bottom15">
        <a-form-item label="编号">
          <a-input v-model:value="queryParams.title" placeholder="请输入..." />
        </a-form-item>
        <a-form-item label="姓名">
          <a-input v-model:value="queryParams.name" placeholder="请输入..." />
        </a-form-item>
        <a-form-item label="地址">
          <a-input v-model:value="queryParams.addr" placeholder="请输入..." />
        </a-form-item>
        <a-form-item>
          <a-button type="primary"> <span class="iconfont icon-search"></span> 查询 </a-button>
        </a-form-item>
      </a-form>

      <a-tabs type="card">
        <a-tab-pane key="1" tab="订单信息">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <a-descriptions title="订单1" :column="4">
              <template #extra>
                <a-button :size="'small'" type="primary">
                  <span class="iconfont icon-edit"></span> 编辑
                </a-button>
                <a-button :size="'small'" type="danger" class="app-margin-left10">
                  <span class="iconfont icon-delete"></span> 删除
                </a-button>
              </template>
              <a-descriptions-item label="编号">2021033100321</a-descriptions-item>
              <a-descriptions-item label="姓名">张三</a-descriptions-item>
              <a-descriptions-item label="电话">1810000000</a-descriptions-item>
              <a-descriptions-item label="物品">宠物狗</a-descriptions-item>
              <a-descriptions-item label="地址">
                中国福建省福州市鼓楼区软件大道福州软件园A区
              </a-descriptions-item>
            </a-descriptions>
            <a-row :gutter="16">
              <a-col :span="6">
                <img src="../../assets/img/demo/1.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/2.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/3.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/4.jpg" />
              </a-col>
            </a-row>
          </a-card>

          <a-card :title="''" :bordered="false" class="app-card-container">
            <a-descriptions title="订单2" :column="4">
              <template #extra>
                <a-button :size="'small'" type="primary">
                  <span class="iconfont icon-edit"></span> 编辑
                </a-button>
                <a-button :size="'small'" type="danger" class="app-margin-left10">
                  <span class="iconfont icon-delete"></span> 删除
                </a-button>
              </template>
              <a-descriptions-item label="编号">2021033100126</a-descriptions-item>
              <a-descriptions-item label="姓名">张三</a-descriptions-item>
              <a-descriptions-item label="电话">1810000000</a-descriptions-item>
              <a-descriptions-item label="物品"> 可爱的宠物猫 </a-descriptions-item>
              <a-descriptions-item label="地址">
                中国福建省福州市鼓楼区软件大道福州软件园B区
              </a-descriptions-item>
            </a-descriptions>
            <a-row :gutter="16">
              <a-col :span="6">
                <img src="../../assets/img/demo/5.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/6.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/7.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/8.jpg" />
              </a-col>
            </a-row>
          </a-card>

          <a-card :title="''" :bordered="false" class="app-card-container">
            <a-descriptions title="订单3" :column="4">
              <template #extra>
                <a-button :size="'small'" type="primary">
                  <span class="iconfont icon-edit"></span> 编辑
                </a-button>
                <a-button :size="'small'" type="danger" class="app-margin-left10">
                  <span class="iconfont icon-delete"></span> 删除
                </a-button>
              </template>
              <a-descriptions-item label="编号">2021033100326</a-descriptions-item>
              <a-descriptions-item label="姓名">张三</a-descriptions-item>
              <a-descriptions-item label="电话">1810000000</a-descriptions-item>
              <a-descriptions-item label="物品">
                大型一级能耗全自动人工智能洗衣机
              </a-descriptions-item>
              <a-descriptions-item label="地址">
                中国福建省福州市鼓楼区软件大道福州软件园C区
              </a-descriptions-item>
            </a-descriptions>
            <a-row :gutter="16">
              <a-col :span="6">
                <img src="../../assets/img/demo/5.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/6.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/7.jpg" />
              </a-col>
              <a-col :span="6">
                <img src="../../assets/img/demo/8.jpg" />
              </a-col>
            </a-row>
          </a-card>
        </a-tab-pane>
        <a-tab-pane key="2" tab="客户信息" force-render>
          <a-card :title="''" :bordered="false" class="app-card-container">
            <a-descriptions :title="''" :column="4">
              <a-descriptions-item label="姓名">张三</a-descriptions-item>
              <a-descriptions-item label="性别">男</a-descriptions-item>
              <a-descriptions-item label="电话">1810000000</a-descriptions-item>
              <a-descriptions-item label="爱号">喜欢狗、猫等动物</a-descriptions-item>
              <a-descriptions-item label="购物次数">150次</a-descriptions-item>
              <a-descriptions-item label="积分">256</a-descriptions-item>
              <a-descriptions-item label="优惠券">15张</a-descriptions-item>
              <a-descriptions-item label="有无会员卡">有</a-descriptions-item>
              <a-descriptions-item label="会员卡">21256555555</a-descriptions-item>
              <a-descriptions-item label="支付宝">1810000000</a-descriptions-item>
              <a-descriptions-item label="消息">20条</a-descriptions-item>
              <a-descriptions-item label="备注">无</a-descriptions-item>
              <a-descriptions-item label="地址1">中国福建省福州市鼓楼区软件大道福州软件园A区</a-descriptions-item>
              <a-descriptions-item label="地址2">中国福建省福州市鼓楼区软件大道福州软件园B区</a-descriptions-item>
              <a-descriptions-item label="地址3">中国福建省福州市鼓楼区软件大道福州软件园C区</a-descriptions-item>
              <a-descriptions-item label="地址4">中国福建省福州市鼓楼区软件大道福州软件园D区</a-descriptions-item>
              <a-descriptions-item label="地址5">中国福建省福州市鼓楼区软件大道福州软件园F区</a-descriptions-item>
              <a-descriptions-item label="地址6">中国福建省福州市鼓楼区软件大道福州软件园G区</a-descriptions-item>
            </a-descriptions>
          </a-card>
        </a-tab-pane>
      </a-tabs>
    </div>
  </app-def-main-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { Descriptions } from 'ant-design-vue';
import CommonUtil from '/@/common/util/common-util';

export default defineComponent({
  name: 'TabList',
  components: {
    ADescriptions: Descriptions,
  },
  setup() {
    const queryParams = CommonUtil.queryParamsInit({
      title: '',
      name: '',
      addr: '',
    });

    return {
      queryParams,
    };
  },
});
</script>

<style lang="less" scoped>
@import './TabList.less';
</style>
