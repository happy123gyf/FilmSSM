package org.gec.service.impl;

import org.gec.mapper.FilminfoMapper;
import org.gec.pojo.Filminfo;
import org.gec.pojo.FilminfoExample;
import org.gec.service.FilmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FilmInfoServiceImpl implements FilmInfoService {

    @Autowired
    private FilminfoMapper filminfoMapper;

    @Override
    public List<Filminfo> findAllInfo(Filminfo example) {
        System.out.println("example: " + example);
        //查询
        return filminfoMapper.queryInfos(example);
    }

    @Override
    public void save(Filminfo filminfo) {

        filminfoMapper.insert(filminfo);
    }

    @Override
    public void deleteByIds(int[] filmIds) {
        for(int id:filmIds){
            filminfoMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Filminfo> findAllInfo2(FilminfoExample filminfo) {
        List<Filminfo> filminfos = filminfoMapper.selectByExample(filminfo);
        return filminfos;
    }

    @Override
    public boolean checkName(String filmname) {
        FilminfoExample filminfoExample = new FilminfoExample();
        FilminfoExample.Criteria criteria = filminfoExample.createCriteria();
          criteria.andFilmnameEqualTo(filmname);
      List<Filminfo> filminfoList =  filminfoMapper.selectByExample(filminfoExample);
      if(filminfoList!=null&&filminfoList.size()>0)
          return true;
        return false;
    }

    @Override
    public Filminfo selectByPrimaryKey(Integer filmid) {
        Filminfo filminfo = filminfoMapper.selectByPrimaryKey(filmid);
        return filminfo;
}

}
