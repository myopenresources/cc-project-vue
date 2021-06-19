export default interface DeptType {
    //部门id
    deptId?: string;

    //部门编号
    deptCode?: string;

    //部门名称
    deptName?: string;

    //部门简称
    deptSortName?: string;

    //部门级别
    deptLevel?: string;

    //部门描述
    deptDescript?: string;

    //上一级部门
    parentDeptId?: string;


    //部门负责人Id
    deptLeaderId?: string;

    //部门负责人
    deptLeader?: string;

    /**
     * 部门联系电话
     */
    deptTel?: string;

    //部门地址
    deptAddr?: string;

    //下级部门
    childDepts?: DeptType[];

    //图标
    iconCls?: string;

    //左值
    leftValue?: number;

    //右值
    rightValue?: number;
}
