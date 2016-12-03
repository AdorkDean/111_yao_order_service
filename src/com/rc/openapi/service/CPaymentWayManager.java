package com.rc.openapi.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dubbo.vo.CPaymentWay;
import com.rc.openapi.dubbo.vo.CPaymentWayExample;

public interface CPaymentWayManager {
    int countByExample(CPaymentWayExample example) throws SQLException;

    int deleteByExample(CPaymentWayExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(CPaymentWay record) throws SQLException;

    Long insertSelective(CPaymentWay record) throws SQLException;

    List selectByExample(CPaymentWayExample example) throws SQLException;
    
    CPaymentWay selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(CPaymentWay record, CPaymentWayExample example) throws SQLException;

    int updateByExample(CPaymentWay record, CPaymentWayExample example) throws SQLException;

    int updateByPrimaryKeySelective(CPaymentWay record) throws SQLException;

    int updateByPrimaryKey(CPaymentWay record) throws SQLException;



}
