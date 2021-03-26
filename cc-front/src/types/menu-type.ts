export default interface MenuType {
    //ID
    id?: string;

    //父ID
    parentId?: string;

    //名称
    text: string;

    //图标
    iconCls?: string;

    //是否展开
    state?: string;

    //URL
    attributes?: any;

    //子节点
    children?: MenuType[];

    menuId?: string;

    menuCode?: string;

    menuName?: string;

    accessUrl?: string;

    parentMenuId?: string;

    sortNum?: string;

    menuLevel?: string;
}
