
import RoleType from './role-type';

export default interface DataDicItemType {

    dicItemId?: string;


    dicItemCode?: string;


    dicTypeId?: string;


    dicTypeCode?: string;


    dicItemParentId?: string;


    dicItemParentName?: string;


    dicItemName?: string;


    dicItemValue?: string;

    spareValue?: string;


    defaultValue?: string;


    dicItemDesc?: string;


    iconCls?: string;


    sortNum?: string;

    state?: string;

    status?: string;

    distributeRoles?: RoleType[];

    children?: DataDicItemType[];


}
