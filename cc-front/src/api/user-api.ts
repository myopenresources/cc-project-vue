import HttpUtil from '/@/common/util/http-util';


export default class UserApi {
    static previewUserAvatar() {
        return HttpUtil.stringUrl("user/previewUserAvatar");
    }
}
