package com.rc.openapi.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dubbo.vo.TMemberIntegral;
import com.rc.openapi.dubbo.vo.TMemberIntegralExample;

public interface TMemberIntegralDAO {
    int countByExample(TMemberIntegralExample example) throws SQLException;

    int deleteByExample(TMemberIntegralExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TMemberIntegral record) throws SQLException;

    Long insertSelective(TMemberIntegral record) throws SQLException;

    List selectByExample(TMemberIntegralExample example) throws SQLException;

    TMemberIntegral selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TMemberIntegral record, TMemberIntegralExample example) throws SQLException;

    int updateByExample(TMemberIntegral record, TMemberIntegralExample example) throws SQLException;

    int updateByPrimaryKeySelective(TMemberIntegral record) throws SQLException;

    int updateByPrimaryKey(TMemberIntegral record) throws SQLException;


}
