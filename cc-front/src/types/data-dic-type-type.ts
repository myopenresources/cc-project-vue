

import DataDicItemType from './data-dic-item-type';
export default interface DataDicTypeType {

    dicTypeId?: string;


    dicTypeCode?: string;


    dicTypeName?: string;


    dicCategory?: string;


    iconCls?: string;

    dataDicItems?: DataDicItemType[];
}
