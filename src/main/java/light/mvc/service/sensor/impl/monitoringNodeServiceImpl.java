package light.mvc.service.sensor.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sensor.TmonitoringNode;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.monitoringNode;
import light.mvc.service.sensor.monitoringNodeServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class monitoringNodeServiceImpl implements monitoringNodeServiceI {

    @Autowired
    private BaseDaoI<TmonitoringNode> nodeDao;

    @Override
    public monitoringNode add(monitoringNode node) {
        // TODO Auto-generated method stub
        TmonitoringNode t = new TmonitoringNode();
        BeanUtils.copyProperties(node, t);
        nodeDao.save(t);
        BeanUtils.copyProperties(t, node);
        return node;
    }

    @Override
    public List<monitoringNode> dataGrid(monitoringNode node, PageFilter ph) {
        // TODO Auto-generated method stub
        List<monitoringNode> ul = new ArrayList<monitoringNode>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from TmonitoringNode t";
        List<TmonitoringNode> l = nodeDao.find(hql + whereHql(node, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
        if (l != null && l.size() > 0) {
            for (TmonitoringNode t : l) {
                monitoringNode u = new monitoringNode();
                BeanUtils.copyProperties(t, u);
                ul.add(u);
            }
        }

        return ul;
    }

    @Override
    public Long count(monitoringNode node, PageFilter ph) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TmonitoringNode t ";
        return nodeDao.count("select count(*) " + hql + whereHql(node, params), params);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        TmonitoringNode t = nodeDao.get(TmonitoringNode.class, id);
        if (t != null) {
            t.setDeleted(1);
        }

    }

    @Override
    public monitoringNode get(Long id) {
        // TODO Auto-generated method stub
        TmonitoringNode t = nodeDao.get(TmonitoringNode.class, id);
        monitoringNode u = new monitoringNode();
        BeanUtils.copyProperties(t, u);
        return u;
    }

    @Override
    public void edit(monitoringNode node) {
        // TODO Auto-generated method stub
        TmonitoringNode t = nodeDao.get(TmonitoringNode.class, node.getAutoID());
        if (t != null) {
            if (node.getCode() != null && node.getCode() != "") {
                t.setCode(node.getCode());
            }
            if (node.getAddress() != null && node.getAddress() != "") {
                t.setAddress(node.getAddress());
            }
            if (node.getDescription() != null && node.getDescription() != "") {
                t.setDescription(node.getDescription());
            }
            if (node.getFps() != null && node.getFps() != 0) {
                t.setFps(node.getFps());
            }
            if (node.getLocationX() != null && node.getLocationX() != 0) {
                t.setLocationX(node.getLocationX());
            }
            if (node.getLocationY() != null && node.getLocationY() != 0) {
                t.setLocationY(node.getLocationY());
            }
            if (node.getName() != null && node.getName() != "") {
                t.setName(node.getName());
            }
            if (node.getStation() != null && node.getStation() != 0) {
                t.setStation(node.getStation());
            }
            if (node.getType() != null && node.getType() != 0) {
                t.setType(node.getType());
            }
            t.setTS(new Date());
            t.setUpdateDate(new Date());
            nodeDao.update(t);
        }

    }

    private String orderHql(PageFilter ph) {
        String orderString = "";
        if ((ph.getSort() != null) && (ph.getOrder() != null)) {
            orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
        }
        return orderString;
    }

    private String whereHql(monitoringNode node, Map<String, Object> params) {
        String hql = " where t.deleted=0 ";
        if (node != null) {
            if (node.getCode() != null && node.getCode() != "") {
                hql += " and t.code=:code ";
                params.put("code", node.getCode());
            }
            if (node.getAddress() != null && node.getAddress() != "") {
                hql += " and t.address=:address ";
                params.put("address", node.getAddress());
            }
            if (node.getDescription() != null && node.getDescription() != "") {
                hql += " and t.description=:description ";
                params.put("description", node.getDescription());
            }
            if (node.getFps() != null && node.getFps() != 0) {
                hql += " and t.fps=:fps ";
                params.put("fps", node.getFps());
            }

            if (node.getName() != null && node.getName() != "") {
                hql += " and t.name=:name ";
                params.put("name", node.getName());
            }
            if (node.getStation() != null && node.getStation() != 0) {
                hql += " and t.station=:station ";
                params.put("station", node.getStation());
            }
            if (node.getType() != null && node.getType() != 0) {
                hql += " and t.type=:type ";
                params.put("type", node.getType());
            }

        }

        return hql;
    }

}
