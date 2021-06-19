import TaskType from "./task-type";

export default interface TaskObjType {
    //总条数
    count: number;
    //列表
    tasks: TaskType[];
}
