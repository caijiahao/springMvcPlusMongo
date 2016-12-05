package light.mvc.controller.sensor;

import light.mvc.framework.constant.GlobalConstant;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.sensor.Sensor;
import light.mvc.pageModel.sensor.Station;
import light.mvc.pageModel.sensor.monitoringNode;
import light.mvc.service.sensor.monitoringNodeServiceI;
import light.mvc.service.sensor.pestSensorServiceI;
import light.mvc.service.sensor.stationServiceI;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sensorUpdate")
public class apiSensorController {
	@Autowired
	private monitoringNodeServiceI moniService;
	
	@Autowired
	private stationServiceI stationS;
	
	@Autowired
	private pestSensorServiceI Service;
	
	

	@RequestMapping("/updateStation")
	@ResponseBody
	public Json Update(Long stationId) {
		Json j = new Json();
		try {
			
			Station station = stationS.get(stationId);
			if(station.getType()!=61)
			{
				j.setSuccess(true);
				j.setMsg("该节点无需更新");
				return j;
				
			}
			Date startUpdateDate = station.getSensorDateUpdate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startDateStr=dateFormat.format(startUpdateDate);
			startDateStr=startDateStr.replace(' ', 'T');
			 String[] cookiesByLogin=getCookieByLogin(station.getLoginName(),station.getPassword());
			 for(String cookieStr:cookiesByLogin)
			 {
				 String[] coookieInfo=cookStringToItemString(cookieStr);
				 String getStationDateURL=GlobalConstant.caipoStationDateURL+"?serial="+station.getSerialNum().toString()+"&group=0&limit=100&verb=FromDate&dtfirst=";
				 String getData=getStationInfo(getStationDateURL+startDateStr,coookieInfo);  
				 JSONObject jsonObj = new JSONObject(getData);
				 Long serialNum=jsonObj.getLong("serial_number");
				 if(!serialNum.equals(station.getSerialNum()))
				 {
					 continue;
				 }
				 JSONArray array = jsonObj.getJSONArray("data");
				
				 monitoringNode node = new monitoringNode();
				 node.setStation(stationId);
				 List<monitoringNode> nodeList= moniService.dataGrid(node, null);
				 Service.insertApi(array, station,nodeList);
				
				 Sensor sLast = Service.getLastSensor(station.getSerialNum());		
				 Date lastDate = new Date(sLast.getCreateDate().getTime()+1000);
				 stationS.updateSensorLastTime(lastDate,stationId);
			 }
			 
			j.setSuccess(true);
			j.setMsg("更新成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	
	public String[] getCookieByLogin(String loginName, String password)
	{
		String[] cookieStr= null;
		String loginURL= GlobalConstant.caipoLoginURL+"?"+GlobalConstant.caipoLoginName+loginName+"&"+GlobalConstant.caipoPasswork+password;
		 BasicCookieStore cookieStore = new BasicCookieStore();
         CloseableHttpClient httpclient = HttpClients.custom()
                 .setDefaultCookieStore(cookieStore)
                 .build();
         try {
             HttpGet httpget = new HttpGet(loginURL);
             CloseableHttpResponse response1 = httpclient.execute(httpget);
             try {
                 HttpEntity entity = response1.getEntity();
             
                 EntityUtils.consume(entity);
           
                 List<Cookie> cookies = cookieStore.getCookies();
               
                 cookieStr=new String[cookies.size()];
                 if (cookies.isEmpty()) {
                     System.out.println("cookie is None");
                 } else {
                     for (int i = 0; i < cookies.size(); i++) {
                       
                         cookieStr[i]=new String(cookies.get(i).toString() );
                         System.out.println("\n(  getCookieByLogin(String loginURL) )in cookie i="+i+"     "+ cookieStr[i]);
                     }
                    
                 }
             }catch(Exception e){
            	 System.out.println(e);
             }finally {
                 try {
					response1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             }
         }catch(Exception e){
        	 System.out.println(e);
         } finally {
             try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
		return cookieStr;
	}
	
	
    public static String[] cookStringToItemString(String cookieStr){
    	
    	String temp[]=cookieStr.split("]");
    	String dest[]=new String[ temp.length ];
    	for(int i=0; i<temp.length; i++){
    	    int start=temp[i].indexOf(": ");
    	    dest[i]=new String( temp[i].substring(start+2) );   	       
    	}    
    	return dest;
    }
    
    public static String getStationInfo(String url,String[] cookieInfo){
    	   
    	String stationInfo=null;
    		 
         CookieStore cookieStore2 = new BasicCookieStore();
         BasicClientCookie cookie2 = new BasicClientCookie(cookieInfo[1], cookieInfo[2]);
         cookie2.setVersion(Integer.valueOf( cookieInfo[0]) );
         cookie2.setDomain(cookieInfo[3]);
         cookie2.setPath(cookieInfo[4]);
         cookieStore2.addCookie(cookie2);
         // Set the store
         CloseableHttpClient httpclient = HttpClients.custom()
                 .setDefaultCookieStore(cookieStore2)
                 .build();
         
         try{
         HttpPost get = new HttpPost(url);
         CloseableHttpResponse response2 = httpclient.execute(get);
           
          try{
        	   HttpEntity entity2 = response2.getEntity();
            
               stationInfo = EntityUtils.toString(entity2,"utf-8");
 	         
          }finally {
           response2.close();
         }
         }catch(Exception e){
        	 System.out.println(e);
         }finally{
        	 try {
				httpclient.close();
			 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         
 	    return stationInfo ;
    }
	
}
