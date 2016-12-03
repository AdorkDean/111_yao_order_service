package com.rc.openapi.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dao.TReturnItemDAO;
import com.rc.openapi.dubbo.vo.TReturnItem;
import com.rc.openapi.dubbo.vo.TReturnItemExample;
import com.rc.openapi.service.TReturnItemManager;

public class TReturnItemManagerImpl  implements TReturnItemManager {

    private TReturnItemDAO treturnitemdao;

    public TReturnItemManagerImpl() {
        super();
    }
    public void  setTreturnitemdao(TReturnItemDAO treturnitemdao){
        this.treturnitemdao=treturnitemdao;
    }
    public TReturnItemDAO getTreturnitemdao(){
        return this.treturnitemdao;
    }
    public     int countByExample(TReturnItemExample example) throws SQLException{
        return treturnitemdao. countByExample( example);
    }

    public     int deleteByExample(TReturnItemExample example) throws SQLException{
        return treturnitemdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return treturnitemdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TReturnItem record) throws SQLException{
        return treturnitemdao. insert( record);
    }

    public     Long insertSelective(TReturnItem record) throws SQLException{
        return treturnitemdao. insertSelective( record);
    }

    public     List selectByExample(TReturnItemExample example) throws SQLException{
        return treturnitemdao. selectByExample( example);
    }

    public     TReturnItem selectByPrimaryKey(Long id) throws SQLException{
        return treturnitemdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TReturnItem record, TReturnItemExample example) throws SQLException{
        return treturnitemdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TReturnItem record, TReturnItemExample example) throws SQLException{
        return treturnitemdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TReturnItem record) throws SQLException{
        return treturnitemdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TReturnItem record) throws SQLException{
        return treturnitemdao. updateByPrimaryKey( record);
    }


}
