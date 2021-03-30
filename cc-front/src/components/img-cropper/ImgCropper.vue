<template>
  <div class="app-img-cropper-container" :style="containerStyle">
    <img class="app-img-cropper-img" :src="imgSrc" ref="imgRef" />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch, onBeforeUnmount, onMounted } from 'vue';
import Cropper from 'cropperjs';
import 'cropperjs/dist/cropper.css';

export default defineComponent({
  name: 'ImgCropper',
  props: {
    containerStyle: {
      type: Object,
      required: false,
    },
    imgSrc: {
      type: String,
      required: true,
      default: '',
    },
    options: {
      type: Object,
      required: false,
      default: () => {},
    },
  },
  setup(props) {
    const cropperInstance = ref<any>();
    const imgRef = ref<any>(null);
    const mimeType = ref<string>('');

    watch(
      () => props.imgSrc,
      () => {
        if (!cropperInstance.value) {
          initImgCropper();
          return;
        }

        cropperInstance.value.replace(props.imgSrc);
      },
      { deep: false }
    );

    const initImgCropper = () => {
      if (imgRef.value) {
        console.info(imgRef.value);
        cropperInstance.value = new Cropper(imgRef.value, props.options);
        getImgSuffix();
      }
    };

    const getImgSuffix = () => {
      const IMG_ARR: string[] = props.imgSrc.split(',');
      const IMG_INFO: string = IMG_ARR[0];
      const IMG_MIME_TYPE: string = IMG_INFO.replace(/data:(.*);base64/, '$1');
      mimeType.value = IMG_MIME_TYPE;
    };

    const getDataURL = (opts: any = {}) => {
      try {
        const RESULT: string = cropperInstance.value
          .getCroppedCanvas(opts)
          .toDataURL(mimeType.value);
        return RESULT;
      } catch (e) {
        return '';
      }
    };

    const getBlob = () => {
      const DATA_URL: string = getDataURL();
      const IMG_ARR: string[] = DATA_URL.split(',');
      const IMG_CONTENT: string = IMG_ARR[1].substring(0, IMG_ARR[1].length - 2);
      const A2B: string = (window as any).atob(IMG_CONTENT);
      let n: number = A2B.length;
      const U8_ARR: any = new Uint8Array(n);
      while (n--) {
        U8_ARR[n] = A2B.charCodeAt(n);
      }
      return new Blob([U8_ARR], {
        type: mimeType.value,
      });
    };

    const destroy = () => {
      if (cropperInstance.value) {
        cropperInstance.value.destroy();
      }
    };

    onBeforeUnmount(() => {
      destroy();
    });

    onMounted(() => {
      initImgCropper();
    });

    return {
      imgRef,
      cropperInstance,
      getDataURL,
      getBlob,
      getImgSuffix,
      destroy,
    };
  },
});
</script>

<style lang="less" scoped>
@import './ImgCropper.less';
</style>
