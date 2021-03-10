import * as lodash from 'lodash';
import CommonUtil from './common-util';
import Environments from './env-util';
export default class HttpUtil {
    static stringUrl(url: string, params: any = ''): string {
        return Environments.getEvnProp("VITE_APP_WEB_PREFIX") + url + Environments.getEvnProp("VITE_WEB_SUFFIX") + this.parseJsonToUrlParams(params);
    }

    static parseJsonToUrlParams(params: any) {
        if (!lodash.isEmpty(params)) {
          return CommonUtil.parseJsonToUrlParams(params);
        } else {
          return '';
        }
      }
}
