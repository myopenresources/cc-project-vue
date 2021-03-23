import http from "/@/common/util/axios-util";


export default class NoticeApi {
    public static REQUEST_PREFIX = 'notice/';

    /**
    * 初始系统公告管理数据
    * @param params
    */
    static initSysNoticeManageData(params: any) {
        return http.post(`${NoticeApi.REQUEST_PREFIX}initSysNoticeManageData`, params);
    }


    /**
     * 根据条件分页查询系统公告
     */
    static querySysNoticeByConditionPaging(params: any) {
        return http.post(`${NoticeApi.REQUEST_PREFIX}querySysNoticeByConditionPaging`, params);
    }

    /**
     * 初始化系统公告数据
     * @param params
     */
    static initSysNoticeAddData(params: any) {
        return http.post(`${NoticeApi.REQUEST_PREFIX}initSysNoticeAddData`, params);
    }


    /**
     * 保存系统公告
     * @param params
     */
    static saveSysNotice(params: any) {
        return http.post(`${NoticeApi.REQUEST_PREFIX}saveSysNotice`, params);
    }

    /**
    * 根据ids删除系统公告
    * @param params
    */
    static deleteSysNoticeByIds(params: any) {
        return http.post(`${NoticeApi.REQUEST_PREFIX}deleteSysNoticeByIds`, params);
    }


    /**
     * 查询系统公告信息
     */
    static querySysNoticeById(noticeId: string) {
        return http.get(`${NoticeApi.REQUEST_PREFIX}querySysNoticeById`, {
            params: {
                noticeId: noticeId
            }
        });
    }

    /**
     * 初始用户系统公告管理数据
     * @param params
     */
    static initUserSysNoticeManageData(params: any) {
        return http.post(`${NoticeApi.REQUEST_PREFIX}initUserSysNoticeManageData`, params);
    }


    /**
     * 根据条件分页查询用户系统公告
     */
    queryUserSysNoticeByConditionPaging(params: any) {
        return http.post(`${NoticeApi.REQUEST_PREFIX}queryUserSysNoticeByConditionPaging`, params);
    }

    /**
     * 查询用户系统公告信息
     */
    static queryUserSysNoticeById(noticeId: string) {
        return http.get(`${NoticeApi.REQUEST_PREFIX}queryUserSysNoticeById`, {
            params: {
                noticeId: noticeId
            }
        });
    }
}
