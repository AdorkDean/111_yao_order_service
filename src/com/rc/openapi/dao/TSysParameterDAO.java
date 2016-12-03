package com.rc.openapi.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dubbo.vo.TSysParameter;
import com.rc.openapi.dubbo.vo.TSysParameterExample;

public interface TSysParameterDAO {
    int countByExample(TSysParameterExample example) throws SQLException;

    int deleteByExample(TSysParameterExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TSysParameter record) throws SQLException;

    Long insertSelective(TSysParameter record) throws SQLException;

    List selectByExample(TSysParameterExample example) throws SQLException;

    TSysParameter selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TSysParameter record, TSysParameterExample example) throws SQLException;

    int updateByExample(TSysParameter record, TSysParameterExample example) throws SQLException;

    int updateByPrimaryKeySelective(TSysParameter record) throws SQLException;

    int updateByPrimaryKey(TSysParameter record) throws SQLException;

    String getKeys(String sysKey) throws SQLException;
}
