package com.lida.mongo.sensor.service.impl;

import com.lida.mongo.sensor.Enum.StationType;
import com.lida.mongo.sensor.service.UpdateData;
import light.mvc.framework.constant.GlobalConstant;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/12/6.
 */
@Repository("UpdateDataImpl")
public class UpdateDataImpl implements UpdateData {
    @Autowired
    private monitoringNodeServiceI moniService;

    @Autowired
    private stationServiceI stationS;

    @Autowired
    private pestSensorServiceI Service;

    @Resource
    private SensorServiceImpl sensorService;



    private static Logger log = LoggerFactory.getLogger(UpdateDataImpl.class);

    /**
     **更新数据的时间间隔
     **/
    private static final Long timeInterval = 1000L;

    @Override
    public void updateDataByCloud(Long stationId) {
        try {

            Station station = stationS.get(stationId);
            if (station.getType() != StationType.NoNeedUpdate.getValue()) {
                log.info("该节点无需更新");
                return ;
            }
            Date startUpdateDate = station.getSensorDateUpdate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDateStr=dateFormat.format(startUpdateDate);
            startDateStr=startDateStr.replace(' ', 'T');
            String[] cookiesByLogin=getCookieByLogin(station.getLoginName(),station.getPassword());
            for(String cookieStr:cookiesByLogin) {
                String[] coookieInfo = cookStringToItemString(cookieStr);
                String getStationDateURL = GlobalConstant.caipoStationDateURL + "?serial=" + station.getSerialNum().toString() + "&group=0&limit=100&verb=FromDate&dtfirst=";
                String getData = getStationInfo(getStationDateURL + startDateStr, coookieInfo);
                JSONObject jsonObj = new JSONObject(getData);
                Long serialNum = jsonObj.getLong("serial_number");
                if (!serialNum.equals(station.getSerialNum())) {
                    continue;
                }
                JSONArray array = jsonObj.getJSONArray("data");
                monitoringNode node = new monitoringNode();
                node.setStation(stationId);
                List<monitoringNode> nodeList = moniService.dataGrid(node, null);
                sensorService.insertApi(array,station,nodeList);
                Service.insertApi(array, station,nodeList);

               //修改下次更新数据
                light.mvc.pageModel.sensor.Sensor sLast = Service.getLastSensor(station.getSerialNum());
                Date lastDate = new Date(sLast.getCreateDate().getTime()+timeInterval);
                stationS.updateSensorLastTime(lastDate,stationId);
            }
            log.info("更新数据成功===================================================");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

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