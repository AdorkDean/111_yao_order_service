package com.rc.openapi.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dao.TReturnImageDAO;
import com.rc.openapi.dubbo.vo.TReturnImage;
import com.rc.openapi.dubbo.vo.TReturnImageExample;
import com.rc.openapi.service.TReturnImageManager;

public class TReturnImageManagerImpl  implements TReturnImageManager {

    private TReturnImageDAO treturnimagedao;

    public TReturnImageManagerImpl() {
        super();
    }
    public void  setTreturnimagedao(TReturnImageDAO treturnimagedao){
        this.treturnimagedao=treturnimagedao;
    }
    public TReturnImageDAO getTreturnimagedao(){
        return this.treturnimagedao;
    }
    public     int countByExample(TReturnImageExample example) throws SQLException{
        return treturnimagedao. countByExample( example);
    }

    public     int deleteByExample(TReturnImageExample example) throws SQLException{
        return treturnimagedao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return treturnimagedao. deleteByPrimaryKey( id);
    }

    public     Long insert(TReturnImage record) throws SQLException{
        return treturnimagedao. insert( record);
    }

    public     Long insertSelective(TReturnImage record) throws SQLException{
        return treturnimagedao. insertSelective( record);
    }

    public     List selectByExample(TReturnImageExample example) throws SQLException{
        return treturnimagedao. selectByExample( example);
    }

    public     TReturnImage selectByPrimaryKey(Long id) throws SQLException{
        return treturnimagedao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TReturnImage record, TReturnImageExample example) throws SQLException{
        return treturnimagedao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TReturnImage record, TReturnImageExample example) throws SQLException{
        return treturnimagedao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TReturnImage record) throws SQLException{
        return treturnimagedao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TReturnImage record) throws SQLException{
        return treturnimagedao. updateByPrimaryKey( record);
    }


}
