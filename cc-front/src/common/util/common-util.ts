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
}
