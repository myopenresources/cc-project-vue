<template>
  <app-def-main-layout>
    <template #headerBtnsTpl>
      <app-custom-button :type="'error-hollow'">
        <app-dynamic-icon iconName="DeleteOutlined" /> 删除
      </app-custom-button>
      <app-custom-button :type="'success'" class="app-margin-left10">
        <app-dynamic-icon iconName="PlusOutlined" /> 添加
      </app-custom-button>
    </template>
    <div>
      <a-form layout="inline" class="app-query-form app-margin-bottom15">
        <a-form-item label="内容标题">
          <a-input v-model:value="queryParams.title" placeholder="请输入..." />
        </a-form-item>
        <a-form-item>
          <a-button type="primary"> <app-dynamic-icon iconName="SearchOutlined" /> 查询 </a-button>
        </a-form-item>
      </a-form>

      <a-row :gutter="16">
        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="Hippo"
              description="hippo是一个个人开源软件，其github中有许多开源产品，git地址：https://github.com/myopenresources。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/hippo.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>

        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="Angular"
              description="学会用 Angular 构建Web、移动 Web、移动应用、原生应用和桌面原生应用。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/angular.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="React"
              description="React 使创建交互式 UI 变得轻而易举。为你应用的每一个状态设计简洁的视图。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/react.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="Bootstrap"
              description="Bootstrap 是HTMLCSS和JS框架，用于开发响应式布局、移动设备优先的 WEB 项目。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/bootstrap.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="Vue"
              description="Vue是一套构建用户界面的渐进式框架。 Vue 只关注视图层, 采用自底向上增量开发的设计。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/vue.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>

        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="Webpack"
              description="ebpack 是一个用于现代 JavaScript 应用程序的_静态模块打包工具。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/webpack.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>

        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="Ant Design"
              description="基于Ant Design 设计体系的 React UI 组件库,用于研发企业级中后台产品。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/ant-design.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>

        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="ng-zorro-antd"
              description="ng-zorro-antd 是遵循 Ant Design 设计规范的 Angular UI 组件库，主要用于研发企业级中后台产品。。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/ng-zorro-antd.svg" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>

        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="jquery"
              description="jQuery是一个快速、简洁的JavaScript框架，是继Prototype之后又一个优秀的JavaScript代码库（或JavaScript框架）。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/jquery.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>

        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="Java"
              description="Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/java.jpg" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>

        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="spring"
              description="spring框架是由于软件开发的复杂性而创建的。Spring使用的是基本的JavaBean来完成以前只可能由EJB完成的事情。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/spring.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">
          <a-card :title="''" :bordered="false" class="app-card-container">
            <template class="ant-card-actions" #actions>
              <app-dynamic-icon iconName="PlusOutlined" />
              <app-dynamic-icon iconName="DeleteOutlined" />
              <app-dynamic-icon iconName="SettingOutlined" />
            </template>
            <a-card-meta
              title="Github"
              description="Websites for you and your projects, hosted directly from your GitHub repository. Just edit, push, and your changes are live.。"
            >
              <template #avatar>
                <a-avatar :size="64" src="./src/assets/platform/img/demo/github.jpg" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>
      </a-row>

      <div class="app-card-pagination">
        <a-pagination show-size-changer :current="1" :pageSize="10" :total="500" />
      </div>
    </div>
  </app-def-main-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { Avatar, Pagination } from 'ant-design-vue';
import CommonUtil from '/@/platform/commons/utils/common-util';

export default defineComponent({
  name: 'CardList',
  components: {
    AAvatar: Avatar,
    APagination: Pagination,
  },
  setup() {
    const queryParams = CommonUtil.queryParamsInit({
      title: '',
    });

    return {
      queryParams,
    };
  },
});
</script>

<style lang="less" scoped>
@import './CardList.less';
</style>
