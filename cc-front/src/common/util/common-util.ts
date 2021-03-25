import BusinessBtnType from "/@/types/business-btn-type";
import * as lodash from 'lodash';
import PaginationType from "/@/types/pagination-type";
import { computed, ref } from "vue";
import HttpResultUtils from "./http-result-utils";

export default class CommonUtil {

    /**
     * 转换成boolean
     * @param value 
     * @returns 
     */
    static toBoolean(value: string | number | boolean): boolean {
        return [true, 'true', 'True', 'TRUE', '1', 1].includes(value)
    }


    /**
     * url中如果有双斜杠替换为单斜杠
     * @param url
     * @returns {string}
     */
    static replaceUrl(url: string): string {
        if (-1 !== url.indexOf('http://')) {
            return 'http://' + url.substring(7).replace(/\/\//g, '/');
        } else if (-1 !== url.indexOf('https://')) {
            return 'https://' + url.substring(8).replace(/\/\//g, '/');
        } else {
            return url;
        }
    }

    /**
     *  UUID生成
     *  @returns {string}
     */
    static UUID(): string {
        return 'xxxxxxxx-xxxx-6xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
            const r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    /**
     *  短UUID生成
     *  @returns {string}
     */
    static shortUUID(): string {
        return 'xx-x-6xy'.replace(/[xy]/g, (c) => {
            const r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(8);
        });
    }

    /**
     * 获得绝对位置
     * @param element
     * @param target
     */
    static absolutePosition(element: any, target: any): void {
        const elementDimensions = element.offsetParent ?
            { width: element.offsetWidth, height: element.offsetHeight } : this.getHiddenElementDimensions(element);
        const elementOuterHeight = elementDimensions.height;
        const elementOuterWidth = elementDimensions.width;
        const targetOuterHeight = target.offsetHeight;
        const targetOuterWidth = target.offsetWidth;
        const targetOffset = target.getBoundingClientRect();
        const windowScrollTop = this.getWindowScrollTop();
        const windowScrollLeft = this.getWindowScrollLeft();
        const viewport = this.getViewport();
        let top, left;

        if (targetOffset.top + targetOuterHeight + elementOuterHeight > viewport.height) {
            top = targetOffset.top + windowScrollTop - elementOuterHeight;
            if (top < 0) {
                top = 0 + windowScrollTop;
            }
        } else {
            top = targetOuterHeight + targetOffset.top + windowScrollTop;
        }

        if (targetOffset.left + targetOuterWidth + elementOuterWidth > viewport.width) {
            left = targetOffset.left + windowScrollLeft + targetOuterWidth - elementOuterWidth;
        } else {
            left = targetOffset.left + windowScrollLeft;
        }




        element.style.top = top + 'px';
        element.style.left = left + 'px';
    }

    /**
     * 获得尺寸
     * @param element
     */
    static getHiddenElementDimensions(element: any): any {
        const dimensions: any = {};
        element.style.visibility = 'hidden';
        element.style.display = 'block';
        dimensions.width = element.offsetWidth;
        dimensions.height = element.offsetHeight;
        element.style.display = 'none';
        element.style.visibility = 'visible';

        return dimensions;
    }

    /**
    * 获得视图大小
    */
    static getViewport(): any {
        const win = window,
            d = document,
            e = d.documentElement,
            g = (d.getElementsByTagName('body')[0]) as Element,
            w = win.innerWidth || e.clientWidth || g.clientWidth,
            h = win.innerHeight || e.clientHeight || g.clientHeight;

        return { width: w, height: h };
    }

    /**
     * 获得窗口滚动高度
     */
    static getWindowScrollTop(): number {
        const doc = document.documentElement;
        return (window.pageYOffset || doc.scrollTop) - (doc.clientTop || 0);
    }

    /**
     * 获得窗口滚动宽度
     */
    static getWindowScrollLeft(): number {
        const doc = document.documentElement;
        return (window.pageXOffset || doc.scrollLeft) - (doc.clientLeft || 0);
    }


    /**
    * 获得实际位置
    * @param element
    * @param target
    */
    static relativePosition(element: any, target: any): void {
        const elementDimensions = element.offsetParent ?
            { width: element.offsetWidth, height: element.offsetHeight } : this.getHiddenElementDimensions(element);
        const targetHeight = target.offsetHeight;
        const targetWidth = target.offsetWidth;
        const targetOffset = target.getBoundingClientRect();
        // const windowScrollTop = this.getWindowScrollTop();
        const viewport = this.getViewport();
        let top, left;

        if ((targetOffset.top + targetHeight + elementDimensions.height) > viewport.height) {
            top = -1 * (elementDimensions.height);
            if (targetOffset.top + top < 0) {
                top = 0;
            }
        } else {
            top = targetHeight;
        }


        if ((targetOffset.left + elementDimensions.width) > viewport.width) {
            left = targetWidth - elementDimensions.width;
        } else {
            left = 0;
        }


        element.style.top = top + 'px';
        element.style.left = left + 'px';
    }


    /**
     * 对象转换成key数组
     * @param obj 
     */
    static objToKeyArray(obj: any) {
        const list = new Array<String>();
        Object.keys(obj).forEach((key) => {
            list.push(key);
        });
        return list;
    }

    /**
     * 对象转换成key数组
     * @param obj 
     */
    static objToArray(obj: any) {
        const list = new Array<String>();
        Object.keys(obj).forEach((key) => {
            const object: any = {
                key: obj[key]
            };
            list.push(object);
        });
        return list;
    }

    /**
    * 数组转换成单数组
    * @param list 
    * @param key 
    */
    static ArrayToSimpleArray(list: Array<any>, key: string) {
        const keys: any[] = [];
        for (const item of list) {
            keys.push(item[key]);
        }

        return keys;
    }

    /**
     * 防抖
     */
    static debounce(cb: Function, time: number, args?: any) {
        let timeout: any = null;

        if (timeout) {
            clearTimeout(timeout);
        }

        timeout = setTimeout(() => {
            if (args) {
                cb(args);
            } else {
                cb();
            }

        }, time);
    }


    /**
     * 转换json到url参数
     * @param data
     */
    static parseJsonToUrlParams(data: any) {
        try {
            const tempArr: string[] = [];
            Object.keys(data).forEach((i) => {
                const key = encodeURIComponent(i);
                const value = encodeURIComponent(data[i]);
                tempArr.push(key + '=' + value);
            });
            const urlParamsStr = '?' + tempArr.join('&');
            return urlParamsStr;
        } catch (err) {
            return '';
        }
    }

    /**
     * 转换url参数到json
     * @param data
     */
    static parseUrlParamsToJson(url: any) {
        const str = url.split('?')[1];
        const iterms = str.split('&');
        let arr = {};
        const json = {};
        for (let i = 0; i < iterms.length; i++) {
            arr = iterms[i].split('=');
            json[arr[0]] = arr[1];
        }
        return json;
    }

    /**
     * 表达式执行
     * @param exp 
     */
    static expressionExe(exp: any, data: any) {
        return new Function('data', 'return ' + exp)(data);
    }

    /**
     * 抽屉初始化
     * @param visible 
     * @param props 
     * @param cb 
     */
    static drawerAddOrViewInit(visible: boolean, cb: Function, resetFields: Function = () => { }) {
        resetFields();
        if (visible) {
            cb();
        }
    }

    /**
     * 抽屉关闭
     * @param context 
     */
    static drawerClose(context: any, reload = false) {
        context.emit('update:visible', false);
        context.emit('update:id', '');
        if (reload) {
            context.emit('reload');
        }

    }


    /**
     * 按钮计算
     */
    static computedBtns(props): BusinessBtnType[] {
        const list: BusinessBtnType[] = [];
        if (props.buttonList && props.buttonList.length) {
            props.buttonList.forEach((btn: BusinessBtnType) => {
                if (btn.layoutMark === props.layoutMark) {
                    if (props.data && props.showBtnFunction) {
                        if (props.showBtnFunction(btn, props.data)) {
                            list.push(btn);
                        }
                    } else if (props.data && btn.btnExp) {
                        if (CommonUtil.expressionExe(btn.btnExp, props.data)) {
                            list.push(btn);
                        }
                    } else {
                        list.push(btn);
                    }
                }
            });
        }
        return list;
    }

    /**
     * 装载form
     */
    static loadFormData(data: any, modelRef: any) {
        if (data) {
            Object.keys(modelRef).forEach((key) => {
                if (data[key]) {
                    modelRef[key] = data[key];
                }
            });
        }

    }

    /**
     * 分页初始化参数
     */
    static paginationInitParams(customParams?: any) {
        const paginationModel: PaginationType = {
            total: 0,
            page: 1,
            rows: 10,
            pageSizeOptions: ['10', '20', '30', '40', '50']
        };
        return lodash.extend(paginationModel, customParams);
    }

    /**
     * 重置分页参数
     */
    static resetPaginationParams(oldPageParams: any, customPageParams?: any) {
        return lodash.merge(oldPageParams, CommonUtil.paginationInitParams(customPageParams));
    }

    /**
     * 查询参数合并分页参数
     */
    static queryParamsMergePageParams(queryParams: any, customPageParams?: any): any {
        return lodash.merge(queryParams, CommonUtil.paginationInitParams(customPageParams));
    }

    /**
     * 分页参数合并查询结果
     */
    static paginationParamsMergeResultParams(paginationParams: any, resultParams: any) {
        return lodash.merge(paginationParams, {
            total: resultParams.total
        });
    }

    /**
     * 分页计算
     * @param queryParams 
     * @returns 
     */
    static paginationComputed(queryParams: any) {
        return computed(() => ({
            total: queryParams.value.total,
            current: queryParams.value.page,
            pageSize: queryParams.value.rows,
            showSizeChanger: true,
            pageSizeOptions: queryParams.value.pageSizeOptions,
            showTotal: (total, range) => `共 ${total} 条数据，当前显示 ${range[0]} 到 ${range[1]}`,
        }));
    }

    /**
     * 分页改变
     * @param queryParams 
     * @returns 
     */
    static paginationChange(queryParams: any, pag: any, cb: Function) {
        queryParams.value.page = pag.current;
        if (pag.pageSize !== queryParams.value.rows) {
            queryParams.value.page = 1;
        }
        queryParams.value.rows = pag.pageSize;
        cb();
    }

    /**
     * 查询参数初始化
     * @param params 
     * @returns 
     */
    static queryParamsInit(params: any) {
        return ref(CommonUtil.queryParamsMergePageParams(params));
    }

    /**
     * 根据条件查询数据
     * @param params 
     * @returns 
     */
    static queryDataByConditionPaging(btnQuery = false, queryParams: any, queryService: any, dataSource: any) {
        dataSource.value = [];
        if (btnQuery) {
            queryParams.value = CommonUtil.resetPaginationParams(queryParams.value);
        }
        queryService(queryParams.value).then((res) => {
            if (HttpResultUtils.isSuccess(res)) {
                queryParams.value = CommonUtil.paginationParamsMergeResultParams(
                    queryParams.value,
                    res.data.resultData
                );
                dataSource.value = res.data.resultData.rows;
            } else {
                HttpResultUtils.failureTipMsg(res);
            }
        });
    }
}
