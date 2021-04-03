<template>
  <div class="app-water-marker-container" :style="{ zIndex: zIndex }" ref="waterMarkerRef"></div>
</template>

<script lang="ts">
import { defineComponent, onMounted, onUnmounted, PropType, ref, watch } from 'vue';
import WaterMarkerType from '/@/types/water-marker-type';
import * as lodash from 'lodash';

export default defineComponent({
  name: 'WaterMarker',
  props: {
    options: {
      type: Object as PropType<WaterMarkerType>,
      default: () => {},
    },
    zIndex: {
      type: Number,
      default: 999999,
    },
  },
  setup(props) {
    const waterMarkerRef = ref();
    let waterMarkerObj: any = undefined;

    let options: WaterMarkerType = {
      watermarkLabel: '水印',
      watermarkXSpace: 100,
      watermarkYSpace: 35,
      watermarkColor: 'rgba(0,0,0,0.65)',
      watermarkFontSize: '13px',
      watermarkAlpha: 0.08,
      watermarkWidth: 130,
      watermarkHeight: 55,
      watermarkAngle: 15,
      watermarkX: 0,
      watermarkY: 0,
      showWaterMark: true,
    };

    watch(
      () => props.options,
      () => {
        init();
      },
      { deep: true }
    );

    const init = () => {
      options = lodash.merge({}, options, props.options);
      initWaterEvent();
    };

    const initWaterEvent = () => {
      if (options.showWaterMark) {
        initWaterMarker();
      } else {
        waterMarkerDestroy();
      }
    };

    const initWaterMarker = () => {
      waterMarkerDestroy();
      const elementAttrs = getElementAttrs();
      waterMarkerObj = createWaterMarkerObj(elementAttrs);
      if (waterMarkerObj) {
        waterMarkerRef.value.appendChild(waterMarkerObj);

        const watermarkCols = Math.ceil(
          elementAttrs.width / (options.watermarkWidth + options.watermarkXSpace)
        );
        const watermarkRows = Math.ceil(
          elementAttrs.height / (options.watermarkHeight + options.watermarkYSpace)
        );

        let x: number;
        let y: number;
        for (let i = 0; i < watermarkRows; i++) {
          y = options.watermarkY + (options.watermarkYSpace + options.watermarkHeight) * i;
          for (let j = 0; j < watermarkCols; j++) {
            x = options.watermarkX + (options.watermarkWidth + options.watermarkXSpace) * j;

            const maskDiv = document.createElement('div');
            const oText = document.createTextNode(options.watermarkLabel);
            maskDiv.appendChild(oText);

            maskDiv.style.transform = 'rotate(-' + options.watermarkAngle + 'deg)';
            maskDiv.style.visibility = '';
            maskDiv.style.position = 'absolute';
            maskDiv.style.left = x + 'px';
            maskDiv.style.top = y + 'px';
            maskDiv.style.overflow = 'hidden';
            maskDiv.style.zIndex = props.zIndex + '';
            maskDiv.style.opacity = options.watermarkAlpha + '';
            maskDiv.style.fontSize = options.watermarkFontSize;
            maskDiv.style.color = options.watermarkColor;
            maskDiv.style.textAlign = 'center';
            maskDiv.style.width = options.watermarkWidth + 'px';
            maskDiv.style.height = options.watermarkHeight + 'px';
            maskDiv.style.display = 'block';
            waterMarkerObj.appendChild(maskDiv);
          }
        }
      }
    };

    const createWaterMarkerObj = (elementAttrs: any) => {
      const divObj = document.createElement('div');
      divObj.style.position = 'fixed';
      divObj.style.pointerEvents = 'none';
      divObj.style.width = elementAttrs.width + 'px';
      divObj.style.height = elementAttrs.height + 'px';
      divObj.style.zIndex = props.zIndex + '';
      divObj.style.top = elementAttrs.top + 'px';
      divObj.style.left = elementAttrs.left + 'px';
      return divObj;
    };

    const getElementAttrs = () => {
      const elementAttrs: any = {};
      const clientRect = waterMarkerRef.value.getBoundingClientRect();
      if (!!clientRect) {
        elementAttrs.width = clientRect.width;
        elementAttrs.height = clientRect.height;
        elementAttrs.left = clientRect.left;
        elementAttrs.top = clientRect.top;
      } else {
        elementAttrs.width = waterMarkerRef.value.offsetWidth;
        elementAttrs.height = waterMarkerRef.value.offsetHeight;
        elementAttrs.left = waterMarkerRef.value.offsetLeft;
        elementAttrs.top = waterMarkerRef.value.offsetTop;
      }
      return elementAttrs;
    };

    const waterMarkerDestroy = () => {
      if (waterMarkerObj !== undefined &&  waterMarkerRef.value) {
        waterMarkerRef.value.removeChild(waterMarkerObj);
        waterMarkerObj = undefined;
      }
    };

    const resize = () => {
      const time = setTimeout(() => {
        initWaterMarker();
        clearTimeout(time);
      }, 100);
    };

    onMounted(() => {
      window.addEventListener('resize', resize);
      init();
    });

    onUnmounted(() => {
      window.removeEventListener('resize', resize);
      waterMarkerDestroy();
    });

    return {
      waterMarkerRef,
    };
  },
});
</script>

<style lang="less" scoped>
@import './WaterMarker.less';
</style>
