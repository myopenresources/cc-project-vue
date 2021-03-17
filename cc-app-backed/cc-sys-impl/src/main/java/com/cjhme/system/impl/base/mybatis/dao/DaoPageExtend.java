package com.cjhme.system.impl.base.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.system.impl.base.mybatis.pagination.interactive.PaginationInteractive;

public class DaoPageExtend {

	/**
	 * SqlSessionTemplate
	 */
	protected SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 有参分页实现
	 * @param <E>
	 * @param statement sql语句
	 * @param parameter 参数
	 * @param offset  起始位置
	 * @param limit   结束位置 
	 * @return
	 */
	public <E> DataPaging<E> selectPaging(String statement, Object parameter, int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<E> rows = sqlSessionTemplate.selectList(statement, parameter, rowBounds);
        int total = PaginationInteractive.getPaginationTotal();
        PaginationInteractive.cleanPaginationTotal();
        DataPaging<E> dataPaging = new DataPaging<E>();
        dataPaging.setRows(rows);
        dataPaging.setTotal(total);
        
        return dataPaging;
    }
	
	/**
	 * 无参分页实现
	 * @param <E>
	 * @param statement sql语句
	 * @param offset 起始位置
	 * @param limit 结束位置 
	 * @return
	 */
	public <E> DataPaging<E> selectPaging(String statement, int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<E> rows = sqlSessionTemplate.selectList(statement, rowBounds);
        int total = PaginationInteractive.getPaginationTotal();
        PaginationInteractive.cleanPaginationTotal();
        DataPaging<E> dataPaging = new DataPaging<E>();
        dataPaging.setRows(rows);
        dataPaging.setTotal(total);
        
        return dataPaging;
    }
	

	/**
	 * 有参分页实现
	 * @param <E>
	 * @param statement sql语句
	 * @param pageParameter 参数
	 * @return
	 */
	public <E> DataPaging<E> selectPaging(String statement,DataPaging<E> pageParameter) {
        RowBounds rowBounds = new RowBounds(pageParameter.getOffset(), pageParameter.getLimit());
        List<E> rows=null;
        
        //无参数
        if(null==pageParameter.getParameter()){
        	rows= sqlSessionTemplate.selectList(statement, rowBounds);
        //有参数
        }else{
        	rows = sqlSessionTemplate.selectList(statement, pageParameter.getParameter(), rowBounds);
        }
        
        int total = PaginationInteractive.getPaginationTotal();
        PaginationInteractive.cleanPaginationTotal();
        DataPaging<E> dataPaging = new DataPaging<E>();
        dataPaging.setRows(rows);
        dataPaging.setTotal(total);
        
        return dataPaging;
    }
	
	
}
