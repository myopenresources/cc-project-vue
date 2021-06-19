// 第一类 TagIcon 图标的引入
import '/@/assets/shared/icon/tag-icon.less';

// 第二类 SvgIcon 图标的引入，需要在extendSvgIcons配置


/**
 *  配置扩展的svg图标
 */
import { extendSvgIcons as platformExtendSvgIcons } from '/@/platform/configs/extend-icon';
import { extendSvgIcons as businessExtendSvgIcons } from '/@/business/configs/extend-icon';

export const extendSvgIcons = Object.assign(platformExtendSvgIcons, businessExtendSvgIcons);

/**
 * 第三类，IconFont图标的引入，需要配置扩展的fonticon图标js地址
 */
export const extendFonticonUrls = [
    //'//at.alicdn.com/t/font_2484839_74ilqk365wl.js'
]

/**
 * 使用方法
 * 第一类 TagIcon图标使用， iconName:图标名称，iconType：图标类型，tagIconCls：图标扩展样式（数组，非必须），iconProps：图标扩展属性（非必须）
 * <app-dynamic-icon iconName="tagicon-icon-cpu" tagIconCls={['app-icon']} iconProps={{ style: { fontSize: '20px' } }} />
 *
 * 第二类 SvgIcon图标使用， iconName:图标名称，这里面的名称为extendSvgIcons配置的图标，iconType：图标类型，iconProps：图标扩展属性（非必须）
 * <app-dynamic-icon iconName="SvgIconBusinessMark" :iconProps="{ spin: true }" />
 *
 * 第三类 IconFont图标使用， iconName:图标名称，iconType：图标类型，iconProps：图标扩展属性（非必须）
 * <app-dynamic-icon iconName="iconfont-icon-link" :iconProps="{ spin: true }" />
 *
 * 第四类 Default图标使用(这个是antd的默认图标)， iconName:图标名称，iconType：图标类型，这里面默认了可以不填，iconProps：图标扩展属性（非必须）
 * <app-dynamic-icon iconName="UserOutlined" :iconProps="{ spin: true }" />
 */
