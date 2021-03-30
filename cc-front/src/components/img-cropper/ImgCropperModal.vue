<template>
  <a-modal
    :width="'660px'"
    :visible="visible"
    :destroyOnClose="true"
    :forceRender="true"
    :maskClosable="maskClosable"
    :keyboard="keyboard"
    :zIndex="zIndex"
    :title="title"
    @cancel="handleCancel"
  >
    <template #footer>
      <div class="app-img-cropper-btns">
        <div :class="['app-img-cropper-upload-btn', uploadBtnCls]">
          <a-button>
            <span :class="['iconfont', browseBtnIcon]"></span> {{ browseBtnText }}
          </a-button>
          <input
            ref="uploadInputRef"
            type="file"
            accept="image/jpg, image/jpeg, image/png, image/gif"
            @change="selectFile"
          />
        </div>

        <a-button type="primary" @click="upload">
          <span :class="['iconfont', uploadBtnIcon]"></span> {{ uploadBtnText }}
        </a-button>
      </div>
    </template>

    <div class="app-img-cropper-container">
      <div :class="['app-img-cropper-cut-container', imgCropperCutCls]">
        <app-img-cropper
          ref="imgCropperRef"
          :imgSrc="imgSrc"
          :containerStyle="{
            backgroundColor: '#f8f8f8',
          }"
          :options="{
            viewMode: 1,
            aspectRatio: 1 / 1,
            minCropBoxWidth: 100,
            minCropBoxHeight: 100,
            movable: false,
            zoomable: false,
            preview: '.' + previewCls,
          }"
        />
      </div>
      <div :class="['app-img-preview-container', imgPreviewCls]">
        <div class="app-img-preview-title">{{ previewTitle }}</div>
        <div class="app-original-img">
          <img
            :src="originalImgSrc"
            :class="{
              'app-img-preview-square': previewSquare,
              'app-img-preview-round': !previewSquare,
            }"
            v-app-error-src="previewSquare ? squareImg : circularImg"
          />
          <div class="app-preview-img-label">{{ originalImgLabel }}</div>
        </div>
        <div class="app-preview-lg-img">
          <div
            :class="[
              'app-img-cropper-preview',
              previewCls,
              previewSquare ? 'app-img-preview-square' : 'app-img-preview-round',
            ]"
          >
          </div>
          <div class="app-preview-img-label">64*64</div>
        </div>
        <div class="app-preview-sm-img">
          <div
            :class="[
              'app-img-cropper-preview',
              previewCls,
              previewSquare ? 'app-img-preview-square' : 'app-img-preview-round',
            ]"
          >
          </div>
          <div class="app-preview-img-label">32*32</div>
        </div>
      </div>
    </div>

    <div class="app-img-cropper-alert">
      注：请上传小于{{ maxSize / 1024 / 1024 }}M的JPG/JPEG、PNG、GIF图片。
    </div>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import ImgCropper from './ImgCropper.vue';
import defaultSquareImg from '/@/assets/img/img-cropper/default-square-img.png';
import defaultCircularImg from '/@/assets/img/img-cropper/default-circular-img.png';
import { notification } from 'ant-design-vue';

export default defineComponent({
  name: 'ImgCropperModal',
  components: {
    AppImgCropper: ImgCropper,
  },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    title: {
      type: String,
      default: '图片裁剪',
    },
    maskClosable: {
      type: Boolean,
      default: false,
    },
    keyboard: {
      type: Boolean,
      default: false,
    },
    zIndex: {
      type: Number,
      default: 1001,
    },
    imgCropperCutCls: {
      type: String,
      default: '',
    },
    imgPreviewCls: {
      type: String,
      default: '',
    },
    uploadBtnCls: {
      type: String,
      default: '',
    },
    previewTitle: {
      type: String,
      default: '图片预览',
    },
    originalImgLabel: {
      type: String,
      default: '原图片',
    },
    originalImgSrc: {
      type: String,
      default: '',
    },
    maxSize: {
      type: Number,
      default: 8388608,
    },
    previewSquare: {
      type: Boolean,
      default: false,
    },
    browseBtnText: {
      type: String,
      default: '选择',
    },
    uploadBtnText: {
      type: String,
      default: '上传',
    },
    uploadBtnIcon: {
      type: String,
      default: 'icon-cloud-upload',
    },
    browseBtnIcon: {
      type: String,
      default: 'icon-search',
    },
  },
  emits: ['update:visible', 'imgUpload'],
  setup(props, context) {
    const imgSrc = ref('');
    const previewCls = ref<string>('app-preview-cls' + new Date().getTime());
    const imgCropperRef = ref();
    const uploadInputRef = ref();

    const circularImg = ref(defaultCircularImg);
    const squareImg = ref(defaultSquareImg);

    const selectFile = (e: any): void => {
      const file: any = e.target.files[0];
      const fileSize = file.size;
      if (fileSize > props.maxSize) {
        notification.warning({
          message: '提示',
          description: `请${props.browseBtnText}小于${props.maxSize}的图片!`,
        });
        uploadInputRef.value.value = '';
        return;
      }

      const reader: any = new FileReader();
      reader.onload = (): void => {
        imgSrc.value = reader.result;
        uploadInputRef.value.value = '';
      };
      reader.readAsDataURL(file);
    };

    const upload = () => {
      const imgData = imgCropperRef.value.getDataURL();

      if (!!imgData && imgData.length) {
        context.emit('imgUpload', imgData);
      } else {
        notification.warning({
          message: '提示',
          description: `请${props.browseBtnText}图片!`,
        });
      }
    };

    /**
     * 取消
     */
    const handleCancel = () => {
      context.emit('update:visible', false);
    };

    return {
      imgSrc,
      handleCancel,
      selectFile,
      imgCropperRef,
      upload,
      previewCls,
      circularImg,
      squareImg,
      uploadInputRef,
    };
  },
});
</script>

<style lang="less" scoped>
@import './ImgCropperModal.less';
</style>
