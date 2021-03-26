export default interface PaginationType {
    total: number; //总条数
    page: number; //当前页
    rows: number; //每页行数
    showSizeChanger: boolean,
    pageSizeOptions?: string[]; //每页显示多少条
}
