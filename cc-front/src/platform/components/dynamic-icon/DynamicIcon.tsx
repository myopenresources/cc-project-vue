import { defineComponent, h, PropType } from 'vue';
import Icon,{ createFromIconfontCN } from '@ant-design/icons-vue'
import * as AntIcon from '@ant-design/icons-vue';
import { DynamicIconEnum } from '/@/platform/commons/enums/dynamic-icon-enum';
import { extendFonticonUrls, extendSvgIcons } from '/@/shared/configs/extend-icon';

import './DynamicIcon.less'

let IconFont:any;
if (extendFonticonUrls && extendFonticonUrls.length > 0) {
    IconFont = createFromIconfontCN({
        scriptUrl: extendFonticonUrls
    });
}

export default defineComponent({
  name: 'DynamicIcon',
  props:{
    iconName: {
        type: String,
        default:''
    },
    iconProps: {
        type: Object,
        default:{}
    },
    tagIconCls: {
        type: Array as PropType<string[]>,
        default:[]
    }
  },
  setup(props) {
    const getIcon = () => {
        if (props.iconName?.indexOf(DynamicIconEnum.SvgIcon) !== -1) {
            return (<Icon component={extendSvgIcons[props.iconName as string]} {...props.iconProps} />);
        }

        if (props.iconName?.indexOf(DynamicIconEnum.IconFont) !== -1) {
            const iconFont = props.iconName?.replace(DynamicIconEnum.IconFont, '');
            return (<IconFont type={iconFont as string} {...props.iconProps} />)
        }

        if (props.iconName?.indexOf(DynamicIconEnum.TagIcon) !== -1) {
            const tagIcon = props.iconName?.replace(DynamicIconEnum.TagIcon, '');
            const classList = ['iconfont', tagIcon].concat(props.tagIconCls ? props.tagIconCls : []);
            return (<span class={classList.join(' ')} {...props.iconProps} />);
        }

        

        if(AntIcon[props.iconName as string]!==undefined){
            return h(
                AntIcon[props.iconName as string],
                props.iconProps
            )
        }
    
        return <span/>
        
    }
    
    return () => (
      <>
        {
            getIcon()
        }
      </>
    );
  }
});
