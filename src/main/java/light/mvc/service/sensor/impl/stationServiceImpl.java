package light.mvc.service.sensor.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sensor.Tstation;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sensor.Station;
import light.mvc.service.sensor.stationServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class stationServiceImpl implements stationServiceI{
	
	@Autowired
    private BaseDaoI<Tstation> stationDao;

	@Override
	public List<Station> treeGrid() {
		// TODO Auto-generated method stub
		List<Station> lr = new ArrayList<Station>();
		List<Tstation> l = stationDao.find("from Tstation t left join fetch t.station where t.deleted=0");
		if(l!=null&&l.size()>0)
		{
			for(Tstation t:l)
			{
				Station r = new Station();
				BeanUtils.copyProperties(t, r);
				r.setId(t.getAutoID());
				if (t.getStation() != null) {
					r.setPid(t.getStation().getAutoID());
					r.setPname(t.getStation().getName());
				}
				lr.add(r);
			}
		}
		return lr;
	}

	@Override
	public void add(Station station) {
		// TODO Auto-generated method stub
		Tstation t = new Tstation();
		BeanUtils.copyProperties(station, t);
		if(station.getPid()!=null&&!"".equals(station.getPid()))
		{
			t.setStation(stationDao.get(Tstation.class, station.getPid()));
		}
		t.setCreateDate(new Date());
		t.setSensorDateUpdate(new Date());
		stationDao.save(t);
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Station station) {
		// TODO Auto-generated method stub
		Tstation t = stationDao.get(Tstation.class, station.getId());
		t.setName(station.getName());
		t.setCode(station.getCode());
		if(station.getPid()!=null&&!"".equals(station.getPid()))
		{
			t.setStation(stationDao.get(Tstation.class, station.getId()));
		}
		stationDao.update(t);
	}

	@Override
	public Station get(Long id) {
		// TODO Auto-generated method stub
		Tstation t= stationDao.get(Tstation.class, id);
		if(t!=null&&t.getDeleted()==0)
		{
			Station r = new Station();
			BeanUtils.copyProperties(t, r);
			r.setId(t.getAutoID());
			if (t.getStation() != null) {
				r.setPid(t.getStation().getAutoID());
				r.setPname(t.getStation().getName());
			}
			return r;
		}
		return null;
	}

	@Override
	public List<Tree> tree() {
		// TODO Auto-generated method stub
		List<Tstation> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		
		l= stationDao.find("select distinct t from Tstation t where t.deleted= 0");
		if ((l != null) && (l.size() > 0)) {
			for (Tstation r : l) {
				Tree tree = new Tree();
				tree.setId(r.getAutoID().toString());
				if (r.getStation() != null) {
					tree.setPid(r.getStation().getAutoID().toString());
				}
				tree.setText(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<Station> getAuditPath(Long stationId) {
		// TODO Auto-generated method stub
		List<Station> result = new ArrayList<Station>();
		Station r = get(stationId);
		if(r!=null)
		{
			result.add(r);
			while(r.getPid() != null && r.getPid() > 0)
			{
				r = get(r.getPid());
				if(r != null)
					result.add(r);
			}
		}
		
		return result;
	}

	@Override
	public void update(Station station) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void updateSensorLastTime(Date lastDate,Long stationId) {
		// TODO Auto-generated method stub
		
		Tstation t= stationDao.get(Tstation.class, stationId);
		if(t!=null)
		{
			if(t.getDeleted()==0)
			{
				t.setSensorDateUpdate(lastDate);
			}
			stationDao.update(t);
		}
		
	}
	
	

}
