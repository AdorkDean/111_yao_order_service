package com.rc.openapi.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dubbo.vo.TOrder;
import com.rc.openapi.dubbo.vo.TOrderExample;

public interface TOrderDAO {
    int countByExample(TOrderExample example) throws SQLException;

    int deleteByExample(TOrderExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TOrder record) throws SQLException;

    Long insertSelective(TOrder record) throws SQLException;

    List selectByExample(TOrderExample example) throws SQLException;

    TOrder selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TOrder record, TOrderExample example) throws SQLException;

    int updateByExample(TOrder record, TOrderExample example) throws SQLException;

    int updateByPrimaryKeySelective(TOrder record) throws SQLException;

    int updateByPrimaryKey(TOrder record) throws SQLException;
}
