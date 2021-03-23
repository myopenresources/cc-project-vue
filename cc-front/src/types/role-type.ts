export default interface RoleType {

    roleId?: string;

    //角色编码
    roleCode?: string;

    //角色名称
    roleName?: string;

    //角色描述
    roleDescribe?: string;

    //是否超级管理员(1否，2是)
    isSuper?: string;

     //提交人
     createUser?: string;
     //创建时间
     createDate?: string;
 
     status?: string;

    accessUrls?: any[];

    
}
