package com.rc.openapi.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dubbo.vo.TOrderItem;
import com.rc.openapi.dubbo.vo.TOrderItemExample;

public interface TOrderItemDAO {
    int countByExample(TOrderItemExample example) throws SQLException;

    int deleteByExample(TOrderItemExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TOrderItem record) throws SQLException;

    Long insertSelective(TOrderItem record) throws SQLException;

    List selectByExample(TOrderItemExample example) throws SQLException;

    TOrderItem selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TOrderItem record, TOrderItemExample example) throws SQLException;

    int updateByExample(TOrderItem record, TOrderItemExample example) throws SQLException;

    int updateByPrimaryKeySelective(TOrderItem record) throws SQLException;

    int updateByPrimaryKey(TOrderItem record) throws SQLException;


}
