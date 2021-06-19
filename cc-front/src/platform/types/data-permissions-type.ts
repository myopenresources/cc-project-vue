export default interface DataPermissionsType {
    // id
    dataPermissionsId?: string;

    //数据权限名称
    dataPermissionsName?: string;

    //访问地址
    accessUrl?: string;

    //sql语句ID
    sqlId?: string;

    //处理器优先级
    processorPriority?: number;
    
    //基本处理器
    primaryProcessor?: string;

    //辅助处理器
    secondaryProcessor?: string;

    //匹配列名
    matchColumn?: string;


    //数据权限分组
    dataPermissionsCategory?: string;

    //参数关键字
    paramKey?: string;

    status?: string;

    createDate?: string;

    createUser?: string;

}
