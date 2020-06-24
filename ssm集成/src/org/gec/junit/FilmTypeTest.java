package org.gec.junit;

import org.gec.mapper.FilmtypeMapper;
import org.gec.pojo.Filmtype;
import org.gec.service.FilmTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@RunWith(JUnit4.class) //添加junit测试注解
@ContextConfiguration(locations ={"classpath:spring/applicationContext-*.xml"} )
public class FilmTypeTest {
    @Autowired
    private FilmTypeService filmTypeService;

    @Before
    public void testBefore(){
        //初始化spring容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        //返回的是接口接收
        filmTypeService = (FilmTypeService)ioc.getBean("filmTypeServiceImpl");
    }


    @Test
    public void testFindAllTypes() {

        List<Filmtype> filmtypeList = filmTypeService.findAllTypes();
        System.out.println("种类数:" + filmtypeList.size());
        filmtypeList.forEach(filmtype -> {
            System.out.println(filmtype.getTypename());
        });

    }
}
