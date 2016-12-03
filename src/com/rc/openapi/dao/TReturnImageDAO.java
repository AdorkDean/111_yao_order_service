package com.rc.openapi.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dubbo.vo.TReturnImage;
import com.rc.openapi.dubbo.vo.TReturnImageExample;

public interface TReturnImageDAO {
    int countByExample(TReturnImageExample example) throws SQLException;

    int deleteByExample(TReturnImageExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TReturnImage record) throws SQLException;

    Long insertSelective(TReturnImage record) throws SQLException;

    List selectByExample(TReturnImageExample example) throws SQLException;

    TReturnImage selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TReturnImage record, TReturnImageExample example) throws SQLException;

    int updateByExample(TReturnImage record, TReturnImageExample example) throws SQLException;

    int updateByPrimaryKeySelective(TReturnImage record) throws SQLException;

    int updateByPrimaryKey(TReturnImage record) throws SQLException;


}
