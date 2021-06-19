import MenuType from "./menu-type";
import UserType from "./user-type";

export default interface MainDataType {
    userData: UserType;

    //菜单数据
    menuData: MenuType[];
}
