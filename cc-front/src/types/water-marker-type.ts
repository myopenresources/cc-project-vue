
export default interface WaterMarkerType {
    //水印文本
    watermarkLabel: string;
    //水印起始位置x轴坐标
    watermarkX: number;
    //水印起始位置Y轴坐标
    watermarkY: number;
    //水印x轴间隔
    watermarkXSpace: number;
    //水印y轴间隔
    watermarkYSpace: number;
    //水印字体颜色
    watermarkColor: string;
    //水印字体大小
    watermarkFontSize: string;
    //水印透明度，要求设置在大于等于0.003
    watermarkAlpha?: number;
    //水印宽度
    watermarkWidth: number;
    //水印高度
    watermarkHeight: number;
    //水印倾斜度数
    watermarkAngle?: number;
    //是否显示
    showWaterMark?: boolean;
    //用户对属性列表
    userKeys?: any;
    //分割符号
    splitChar?: string;
}
