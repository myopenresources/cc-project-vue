import http from "../commons/utils/axios-util";


export default class TaskApi {
    public static REQUEST_PREFIX = 'task/';

    /**
    * 初始待办管理数据
    * @param params
    */
    static initTaskManageData(params: any) {
        return http.post(`${TaskApi.REQUEST_PREFIX}initTaskManageData`, params);
    }


    /**
     * 根据条件分页查询待办
     */
    static queryTaskByConditionPaging(params: any) {
        return http.post(`${TaskApi.REQUEST_PREFIX}queryTaskByConditionPaging`, params);
    }

   
    /**
     * 根据id修改待办状态
     */
    static updateTaskById(taskId: string) {
        return http.get(`${TaskApi.REQUEST_PREFIX}updateTaskById`, {
            params: {
                taskId: taskId
            }
        });
    }
}
