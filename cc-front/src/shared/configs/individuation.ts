import { individuationConst } from '/@/platform/commons/consts/individuation-const';




export const individuationConfig = {
    antdTheme: {
        default: [
            '#1890ff'
        ],
        blue: [
            '#1890ff'
        ],
        red: [
            '#df434f'
        ],
        green: [
            '#07d091'
        ]
    },
    appTheme: {
        default: {
            '--primary-color': '#1890ff',
            '--login-bg': 'linear-gradient(88deg, #18bbff 0, #06bfcc 50%)',
            '--login-bg-img': 'var(--default-login-bg)',
            '--login-img': 'var(--default-login-img)',
            '--layout-left-toolbar-bg': '#001529',
            '--layout-left-toolbar-color': '#97cbfc',
            '--layout-left-toolbar-item-hover-bc': '#193754',
            '--layout-left-toolbar-item-hover-fc': '#97cbfc',
            '--layout-left-toolbar-menu-bg': '#021c35',
            '--layout-left-toolbar-menu-color': '#97cbfc',
            '--layout-left-toolbar-menu-n-h-color': '#fff',
            '--layout-left-toolbar-menu-n-h-bg': '#4ba9ff',
            '--layout-left-toolbar-menu-empty-color': '#97cbfc',
            '--layout-left-toolbar-menu-input-bg': '#021c35',
            '--layout-left-toolbar-menu-input-bor-c': '#6fafeb',
            '--layout-left-toolbar-menu-input-color': '#6fafeb',
            '--layout-left-toolbar-menu-input-b-f-c': '#6fafeb',
            '--layout-left-toolbar-menu-input-placeholder': '#6fafeb',
            '--layout-left-toolbar-menu-bor-color': 'rgba(255, 255, 255, 0.2)',
            '--layout-left-toolbar-menu-header-close-h-color': '#197cda',
            '--layout-page-tabs-bg': 'var(--component-background)',
            '--layout-page-tabs-color': 'var(--text-color)',
            '--layout-page-tabs-close-color': 'var(--error-color)',
            '--layout-page-tabs-active-color': 'var(--white)',
            '--layout-page-tabs-active-bg': 'linear-gradient(110deg, #18bbff, var(--primary-color), #0a86f8)',
            '--layout-page-tabs-active-bor-color': '#18bbff',
            '--layout-page-tabs-item-bg': 'var(--component-background)',
            '--layout-page-tabs-item-bor-color': 'var(--border-color-split)',
            '--layout-page-tabs-btn-hov-color': 'var(--primary-color)'
        },
        blue: {
            '--primary-color': '#1890ff',
            '--login-bg': 'linear-gradient(88deg, #18bbff 0, #06bfcc 50%)',
            '--login-bg-img': 'var(--blue-login-bg)',
            '--login-img': 'var(--blue-login-img)',
            '--layout-left-toolbar-bg': '#047ceb',
            '--layout-left-toolbar-color': '#d3e9fd',
            '--layout-left-toolbar-item-hover-bc': '#0467c3',
            '--layout-left-toolbar-item-hover-fc': '#d3e9fd',
            '--layout-left-toolbar-menu-bg': '#49a2f4',
            '--layout-left-toolbar-menu-color': '#d3e9fd',
            '--layout-left-toolbar-menu-n-h-color': '#fff',
            '--layout-left-toolbar-menu-n-h-bg': '#087ce8',
            '--layout-left-toolbar-menu-empty-color': '#fff',
            '--layout-left-toolbar-menu-input-bg': '#49a2f4',
            '--layout-left-toolbar-menu-input-bor-c': '#cde6fd',
            '--layout-left-toolbar-menu-input-color': '#cde6fd',
            '--layout-left-toolbar-menu-input-b-f-c': '#cde6fd',
            '--layout-left-toolbar-menu-input-placeholder': '#cde6fd',
            '--layout-left-toolbar-menu-bor-color': 'rgba(255, 255, 255, 0.2)',
            '--layout-left-toolbar-menu-header-close-h-color': '#94c3ef',
            '--layout-page-tabs-bg': 'var(--component-background)',
            '--layout-page-tabs-color': 'var(--text-color)',
            '--layout-page-tabs-close-color': 'var(--error-color)',
            '--layout-page-tabs-active-color': 'var(--white)',
            '--layout-page-tabs-active-bg': 'linear-gradient(110deg, #18bbff, var(--primary-color), #0a86f8)',
            '--layout-page-tabs-active-bor-color': '#18bbff',
            '--layout-page-tabs-item-bg': 'var(--component-background)',
            '--layout-page-tabs-item-bor-color': 'var(--border-color-split)',
            '--layout-page-tabs-btn-hov-color': 'var(--primary-color)'
        },
        red: {
            '--primary-color': '#f1623c',
            '--login-bg': 'linear-gradient(88deg, #f1623c 0, #cf3641 50%)',
            '--login-bg-img': 'var(--red-login-bg)',
            '--login-img': 'var(--red-login-img)',
            '--layout-left-toolbar-bg': '#720c13',
            '--layout-left-toolbar-color': '#f9d3d6',
            '--layout-left-toolbar-item-hover-bc': '#a40b15',
            '--layout-left-toolbar-item-hover-fc': '#f9d3d6',
            '--layout-left-toolbar-menu-bg': '#a02a32',
            '--layout-left-toolbar-menu-color': '#f9d3d6',
            '--layout-left-toolbar-menu-n-h-color': '#fff',
            '--layout-left-toolbar-menu-n-h-bg': '#f4a713',
            '--layout-left-toolbar-menu-empty-color': '#f9d3d6',
            '--layout-left-toolbar-menu-input-bg': '#a02a32',
            '--layout-left-toolbar-menu-input-bor-c': '#fdcdd9',
            '--layout-left-toolbar-menu-input-color': '#fdcdd9',
            '--layout-left-toolbar-menu-input-b-f-c': '#fdcdd9',
            '--layout-left-toolbar-menu-input-placeholder': '#fdcdd9',
            '--layout-left-toolbar-menu-bor-color': 'rgba(255, 255, 255, 0.2)',
            '--layout-left-toolbar-menu-header-close-h-color': '#e86f8d',

            '--layout-page-tabs-bg': 'var(--component-background)',
            '--layout-page-tabs-color': 'var(--text-color)',
            '--layout-page-tabs-close-color': 'var(--error-color)',
            '--layout-page-tabs-active-color': 'var(--white)',
            '--layout-page-tabs-active-bg': 'linear-gradient(110deg, #df434f, var(--primary-color), #cf3641)',
            '--layout-page-tabs-active-bor-color': '#df434f',
            '--layout-page-tabs-item-bg': 'var(--component-background)',
            '--layout-page-tabs-item-bor-color': 'var(--border-color-split)',
            '--layout-page-tabs-btn-hov-color': 'var(--primary-color)'
        },
        green: {
            '--primary-color': '#07d091',
            '--login-bg': 'linear-gradient(88deg, #18bbff 0, #06bfcc 50%)',
            '--login-bg-img': 'var(--green-login-bg)',
            '--login-img': 'var(--green-login-img)',
            '--layout-left-toolbar-bg': '#02bd83',
            '--layout-left-toolbar-color': '#d4fcf0',
            '--layout-left-toolbar-item-hover-bc': '#04a372',
            '--layout-left-toolbar-item-hover-fc': '#d4fcf0',
            '--layout-left-toolbar-menu-bg': '#1fce98',
            '--layout-left-toolbar-menu-color': '#d4fcf0',
            '--layout-left-toolbar-menu-n-h-color': '#fff',
            '--layout-left-toolbar-menu-n-h-bg': '#22f4de',
            '--layout-left-toolbar-menu-empty-color': '#fff',
            '--layout-left-toolbar-menu-input-bg': '#1fce98',
            '--layout-left-toolbar-menu-input-bor-c': '#cdfdef',
            '--layout-left-toolbar-menu-input-color': '#cdfdef',
            '--layout-left-toolbar-menu-input-b-f-c': '#cdfdef',
            '--layout-left-toolbar-menu-input-placeholder': '#cdfdef',
            '--layout-left-toolbar-menu-bor-color': 'rgba(255, 255, 255, 0.2)',
            '--layout-left-toolbar-menu-header-close-h-color': '#78eecc',

            '--layout-page-tabs-bg': 'var(--component-background)',
            '--layout-page-tabs-color': 'var(--text-color)',
            '--layout-page-tabs-close-color': 'var(--error-color)',
            '--layout-page-tabs-active-color': 'var(--white)',
            '--layout-page-tabs-active-bg': 'linear-gradient(110deg, #07d091, var(--primary-color), #06bfcc)',
            '--layout-page-tabs-active-bor-color': '#07d091',
            '--layout-page-tabs-item-bg': 'var(--component-background)',
            '--layout-page-tabs-item-bor-color': 'var(--border-color-split)',
            '--layout-page-tabs-btn-hov-color': 'var(--primary-color)'
        }
    },
    themeMenuList: [{
        key: individuationConst.theme.default,
        color: '#001529',
        label: '亚光蓝主题'
    }, {
        key: individuationConst.theme.blue,
        color: '#1890ff',
        label: '静谧蓝主题'
    }, {
        key: individuationConst.theme.red,
        color: '#720c13',
        label: '中国红主题'
    }, {
        key: individuationConst.theme.green,
        color: '#02bd83',
        label: '森林绿主题'
    }],
    /*layoutList: [{
        key: individuationConst.layout.adminLayout,
        img: adminLayout,
        label: '应用布局',
        cssVariable: {
            '--layout-left-toolbar-width': '55px',
            '--layout-left-toolbar-menu-width': '230px'
        }
    },
    {
        key: individuationConst.layout.deskLayout,
        img: deskLayout,
        label: '桌面布局',
        cssVariable: {
            '--layout-left-toolbar-width': '55px',
            '--layout-left-toolbar-menu-width': '250px'
        }
    }]*/

}

