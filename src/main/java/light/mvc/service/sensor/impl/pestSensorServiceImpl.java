package light.mvc.service.sensor.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sensor.TmonitoringNode;
import light.mvc.model.sensor.Tsensor;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.Sensor;
import light.mvc.pageModel.sensor.Station;
import light.mvc.pageModel.sensor.monitoringNode;
import light.mvc.service.sensor.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class pestSensorServiceImpl implements pestSensorServiceI{

	@Autowired
	private BaseDaoI<TmonitoringNode> nodeDao;
	@Autowired
	private BaseDaoI<Tsensor> Dao;
	
	@Override
	public void add(Sensor data) {
		// TODO Auto-generated method stub
		Tsensor t= new Tsensor();
		BeanUtils.copyProperties(data,t);
		Dao.save(t);
	}

	@Override
	public List<Sensor> dataGrid(Sensor data, PageFilter ph) {
		// TODO Auto-generated method stub
		List<Sensor> ul = new ArrayList<Sensor>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tsensor t ";
		List<Tsensor> l = Dao.find(hql + whereHql(data, params)+ orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tsensor t : l) {
			Sensor u = new Sensor();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(Sensor data, PageFilter ph) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tsensor t ";
		return Dao.count("select count(*) " + hql + whereHql(data, params), params);
	}

	@Override
	public void delete(Long id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Tsensor t= Dao.get(Tsensor.class,id);
		Dao.delete(t);
	}

	@Override
	public Sensor get(Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Tsensor t = Dao.get("from Tsensor t  where t.autoId = :id", params);
		Sensor u = new Sensor();
		BeanUtils.copyProperties(t, u);
		return u;
	}

	@Override
	public void edit(Sensor data) {
		// TODO Auto-generated method stub
		Tsensor t=Dao.get(Tsensor.class, data.getAutoID());
		Dao.update(t);
	}
	
	
	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}
	
	private String whereHql(Sensor data, Map<String, Object> params) {//待修改
		String hql = " where t.deleted=0 ";
		if (data != null) {
			if(data.getMonitoringNodeId()!=null&&data.getMonitoringNodeId()!=0)
			{
				hql+=" and t.monitoringNodeId = :monitoringNodeId ";
				params.put("monitoringNodeId", data.getMonitoringNodeId());
			}
		
			if(data.getSerialNum()!=null)
			{
				
				hql+=" and t.serialNum = :serialNum ";
				params.put("serialNum", data.getSerialNum());
			}
			
			
		}

		return hql;
	}

	@Override
	public List<Object[]> getPredictChart(int moniNodeId, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		String hql="SELECT DATE_FORMAT(createDate,'%m-%d'),avg(sensor1),avg(sensor2),avg(sensor3),avg(sensor4),avg(sensor5),avg(sensor6) FROM t_sensor as t where t.createDate between'"+startTime+"' and '"+ endTime+"' group by year(createDate) , month(createDate) , day(createDate)";
		 List<Object[]> obj=	Dao.findBySql(hql);
		return obj;
	}

	@Override
	public void insertApi(JSONArray dataJson, Station station, List<monitoringNode> nodeList) throws JSONException {
		// TODO Auto-generated method stub
		String sqlPre = " INSERT INTO sensor ";
		String left =" ( ";
		String right= " ) ";
		String sqlMid=" VALUES ";
		String columsName ="";
		String value = "";
		
		for(int i=0;i<dataJson.length();i++)
		{
			Map<String, Object> params = new HashMap<String, Object>();
			
			JSONObject jo = (JSONObject)dataJson.get(i);
			
			String dateStr=jo.getString("date");
			dateStr= dateStr.replace("T", " ");
			
			columsName+=" "+"createDate"+",";
			//value+=" :"+"createDate"+",";
			//params.put("createDate", dateStr);
			value+=" '"+dateStr+"',";
			
			for(int j=0 ; j<nodeList.size();j++)
			{
				monitoringNode node = nodeList.get(j);
				columsName+=" "+node.getMap()+",";
			//	value+=" :"+node.getDataKey()+",";
			//	params.put(node.getDataKey(), jo.getString(node.getDataKey()));
				value+=" "+jo.getString(node.getDataKey())+",";
				
				
			}
			columsName+=" "+"serialNum"+" ";
			//value+=" :"+"serialNum"+" ";
			//params.put("serialNum", station.getSerialNum());
			value+=" "+station.getSerialNum()+" ";
			
			String sql = sqlPre+left+columsName+right+sqlMid+left+value+right;
			System.out.println(sql);
			//Dao.executeSql(sql,params);
			Dao.executeSql(sql);
			
			columsName="";
			value="";
		}
		
		
	}

	@Override
	public Sensor getLastSensor(long serialNum) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serialNum", serialNum);
		List<Tsensor> l = Dao.find("from Tsensor t  where t.deleted=0 and t.serialNum = :serialNum order by t.createDate Desc", params,1,1);
		if(l!=null&&l.size()>0)
		{
			Tsensor t = l.get(0);
			Sensor u = new Sensor();
			BeanUtils.copyProperties(t, u);
			return u;
		}
		
		return null;
		
	}
}
