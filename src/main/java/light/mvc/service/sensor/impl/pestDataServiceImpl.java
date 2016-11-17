package light.mvc.service.sensor.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sensor.TpestData;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.pestData;
import light.mvc.service.sensor.pestDataServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class pestDataServiceImpl implements pestDataServiceI {

    @Autowired
    private BaseDaoI<TpestData> pestDao;

    @Override
    public pestData add(pestData u) {
        // TODO Auto-generated method stub
        TpestData t = new TpestData();
        BeanUtils.copyProperties(u, t);
        pestDao.save(t);
        BeanUtils.copyProperties(t, u);

        return u;
    }

    @Override
    public List<pestData> dataGrid(pestData pest, PageFilter ph) {
        // TODO Auto-generated method stub
        List<pestData> ul = new ArrayList<pestData>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from TpestData t";
        List<TpestData> l = pestDao.find(hql + whereHql(pest, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
        if (l != null && l.size() > 0) {
            for (TpestData t : l) {
                pestData u = new pestData();
                BeanUtils.copyProperties(t, u);
                ul.add(u);
            }
        }
        return ul;
    }

    @Override
    public Long count(pestData u, PageFilter ph) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from TpestData t";
        return pestDao.count("select count(*) " + hql + whereHql(u, params), params);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        TpestData t = pestDao.get(TpestData.class, id);
        if (t != null) {
            t.setDeleted(1);
        }

    }

    @Override
    public pestData get(Long id) {
        // TODO Auto-generated method stub
        TpestData t = pestDao.get(TpestData.class, id);
        pestData u = new pestData();

        BeanUtils.copyProperties(t, u);
        return u;
    }

    @Override
    public void edit(pestData u) {
        // TODO Auto-generated method stub
        TpestData t = pestDao.get(TpestData.class, u.getAutoID());
        if (t != null) {
            if (u.getCount() != null) {
                t.setCount(u.getCount());
            }
            if (u.getDescription() != null && u.getDescription() != "") {
                t.setDescription(u.getDescription());
            }
            if (u.getImgId() != null && u.getImgId() != 0) {
                t.setImgId(u.getImgId());
            }
            if (u.getMonitoringNodeId() != null && u.getMonitoringNodeId() != 0) {
                t.setMonitoringNodeId(u.getMonitoringNodeId());
            }
            if (u.getName() != null && u.getName() != "") {
                t.setName(u.getName());
            }
            if (u.getRealCount() != null) {
                t.setRealCount(u.getRealCount());
            }
            if (u.getSensorId() != null && u.getSensorId() != 0) {
                t.setSensorId(u.getSensorId());
            }
            if (u.getType() != null && u.getType() != 0) {
                t.setType(u.getType());
            }
            t.setUpdateDate(new Date());
        }

    }


    private String orderHql(PageFilter ph) {
        String orderString = "";
        if ((ph.getSort() != null) && (ph.getOrder() != null)) {
            orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
        }
        return orderString;
    }

    private String whereHql(pestData u, Map<String, Object> params) {
        String hql = " where t.deleted=0 ";
        if (u != null) {

            if (u.getDescription() != null && u.getDescription() != "") {
                hql += " and t.description=:description ";
                params.put("description", u.getDescription());
            }
            if (u.getName() != null && u.getName() != "") {
                hql += " and t.name=:name ";
                params.put("name", u.getName());
            }
            if (u.getType() != null && u.getType() != 0) {
                hql += " and t.type=:type ";
                params.put("type", u.getType());
            }
            if (u.getImgId() != null && u.getImgId() != 0) {
                hql += " and t.imgId=:imgId ";
                params.put("imgId", u.getImgId());
            }
            if (u.getMonitoringNodeId() != null && u.getMonitoringNodeId() != 0) {
                hql += " and t.monitoringNodeId=:monitoringNodeId ";
                params.put("monitoringNodeId", u.getMonitoringNodeId());
            }
            if (u.getSensorId() != null && u.getSensorId() != 0) {
                hql += " and t.sensorId=:sensorId ";
                params.put("sensorId", u.getSensorId());
            }

        }

        return hql;
    }
}
