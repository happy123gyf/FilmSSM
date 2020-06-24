package org.gec.controller;

import org.gec.pojo.Filmtype;
import org.gec.service.FilmTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@Controller
public class CinemaController {
    @Autowired
    private FilmTypeService filmTypeService;

    List<Filmtype> allFilmType;

    @RequestMapping("/toCinema")
    public ModelAndView toCinema() {
        //获取所有的电影类型
        allFilmType = filmTypeService.findAllTypes();

        ModelAndView mv = new ModelAndView();
        mv.addObject("allFilmType", allFilmType);
        mv.setViewName("cinema");
        return mv;
    }


    @RequestMapping("/toAddFilm")
    public ModelAndView toAddFilm(ModelAndView model) {

        allFilmType = filmTypeService.findAllTypes();

        model.addObject("allFilmType", allFilmType);
        model.setViewName("page/add");

        return model;
    }

    @RequestMapping("/toDownload")
    public ModelAndView toDownload(ModelAndView model) {


        model.setViewName("download");

        return model;
    }


    @RequestMapping("/download")
    public ResponseEntity<byte[]> fileDown(HttpServletRequest request, String name) throws Exception {
        ResponseEntity<byte[]> response = null;
        long start = System.currentTimeMillis();
        InputStream in = null;
        try {
            String path = request.getSession().getServletContext().getRealPath("/upload");
            in = new FileInputStream(path + File.separatorChar + name);
            byte[] body = new byte[in.available()];//返回下一次对此输入流调用的方法可以不受阻塞的从次输入流读取剩余字节数
            in.read(body); //读入到输入流中
            name = new String(name.getBytes("utf-8"));  //防止中文乱码
            HttpHeaders header = new HttpHeaders();  //设置响应头,可以设置为下载模式
            header.add("Content-Disposition", "attachment;filename=" + name);
            HttpStatus statuCode = HttpStatus.OK;  //响应成功
            response = new ResponseEntity<>(body, header, statuCode);  //将内容响应到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
