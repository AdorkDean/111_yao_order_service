package com.rc.openapi.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dao.TSysParameterDAO;
import com.rc.openapi.dubbo.vo.TSysParameter;
import com.rc.openapi.dubbo.vo.TSysParameterExample;
import com.rc.openapi.service.TSysParameterManager;

public class TSysParameterManagerImpl  implements TSysParameterManager {

    private TSysParameterDAO tsysparameterdao;

    public TSysParameterManagerImpl() {
        super();
    }
    public void  setTsysparameterdao(TSysParameterDAO tsysparameterdao){
        this.tsysparameterdao=tsysparameterdao;
    }
    public TSysParameterDAO getTsysparameterdao(){
        return this.tsysparameterdao;
    }
    public     int countByExample(TSysParameterExample example) throws SQLException{
        return tsysparameterdao. countByExample( example);
    }

    public     int deleteByExample(TSysParameterExample example) throws SQLException{
        return tsysparameterdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tsysparameterdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TSysParameter record) throws SQLException{
        return tsysparameterdao. insert( record);
    }

    public     Long insertSelective(TSysParameter record) throws SQLException{
        return tsysparameterdao. insertSelective( record);
    }

    public     List selectByExample(TSysParameterExample example) throws SQLException{
        return tsysparameterdao. selectByExample( example);
    }

    public     TSysParameter selectByPrimaryKey(Long id) throws SQLException{
        return tsysparameterdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TSysParameter record, TSysParameterExample example) throws SQLException{
        return tsysparameterdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TSysParameter record, TSysParameterExample example) throws SQLException{
        return tsysparameterdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TSysParameter record) throws SQLException{
        return tsysparameterdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TSysParameter record) throws SQLException{
        return tsysparameterdao. updateByPrimaryKey( record);
    }

    public String getKeys(String sysKey) throws SQLException{
    	TSysParameterExample example = new TSysParameterExample();
    	example.createCriteria().andSysKeyEqualTo(sysKey);
    	List<TSysParameter> list = tsysparameterdao.selectByExample(example);
    	String val = "";
    	if(null!=list&&0<list.size()){
    		TSysParameter obj = list.get(0);
    		val = obj.getSysValue();
    	}
    	return val;
    }
}
