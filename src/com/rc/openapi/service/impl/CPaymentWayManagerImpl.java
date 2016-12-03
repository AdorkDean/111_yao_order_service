package com.rc.openapi.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dao.CPaymentWayDAO;
import com.rc.openapi.dubbo.vo.CPaymentWay;
import com.rc.openapi.dubbo.vo.CPaymentWayExample;
import com.rc.openapi.service.CPaymentWayManager;

public class CPaymentWayManagerImpl  implements CPaymentWayManager {

    private CPaymentWayDAO cpaymentwaydao;

    public CPaymentWayManagerImpl() {
        super();
    }
    public void  setCpaymentwaydao(CPaymentWayDAO cpaymentwaydao){
        this.cpaymentwaydao=cpaymentwaydao;
    }
    public CPaymentWayDAO getCpaymentwaydao(){
        return this.cpaymentwaydao;
    }
    public     int countByExample(CPaymentWayExample example) throws SQLException{
        return cpaymentwaydao. countByExample( example);
    }

    public     int deleteByExample(CPaymentWayExample example) throws SQLException{
        return cpaymentwaydao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return cpaymentwaydao. deleteByPrimaryKey( id);
    }

    public     Long insert(CPaymentWay record) throws SQLException{
        return cpaymentwaydao. insert( record);
    }

    public     Long insertSelective(CPaymentWay record) throws SQLException{
        return cpaymentwaydao. insertSelective( record);
    }

    public     List selectByExample(CPaymentWayExample example) throws SQLException{
        return cpaymentwaydao. selectByExample( example);
    }

    public     CPaymentWay selectByPrimaryKey(Long id) throws SQLException{
        return cpaymentwaydao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(CPaymentWay record, CPaymentWayExample example) throws SQLException{
        return cpaymentwaydao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(CPaymentWay record, CPaymentWayExample example) throws SQLException{
        return cpaymentwaydao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(CPaymentWay record) throws SQLException{
        return cpaymentwaydao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(CPaymentWay record) throws SQLException{
        return cpaymentwaydao. updateByPrimaryKey( record);
    }


}
