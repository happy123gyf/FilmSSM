package org.gec.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gec.pojo.Filminfo;
import org.gec.pojo.FilminfoExample;
import org.gec.service.FilmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
public class FilmInfoController {

    @Autowired
    private FilmInfoService filmInfoService;

    //页面大小
    public static final Integer pageSize = 3;

    //电影list
    List<Filminfo> result;

    public List<Filminfo> getResult() {
        return result;
    }


    //寻找电影
    @RequestMapping("/findFilms")
    public ModelAndView findFilms(FilminfoExample filminfoExample, Filminfo filminfo
            , @RequestParam(value = "typeid", required = false, defaultValue = "1") Integer typeid
            , String smallprice, String bigprice
            , @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) throws Exception {



        if ((smallprice != null && bigprice != null) && (!smallprice.equals("") && !bigprice.equals(""))) {
            filminfo.setSmallprice(Double.parseDouble(smallprice));
            filminfo.setBigprice(Double.parseDouble(bigprice));
        }
        if (typeid != 0) {
            FilminfoExample.Criteria criteria = filminfoExample.createCriteria();

            criteria.andTypeidEqualTo(typeid);
        }
        //设置分页
        PageHelper.startPage(pageNum, pageSize);

        //电影条件
        //查询
        result = filmInfoService.findAllInfo2(filminfoExample);

        ModelAndView mv = new ModelAndView();

        //分页上一页，下一页，尾页，总记录数...
        PageInfo info = new PageInfo(result);

        mv.addObject("info", info);
        mv.addObject("result", result);
        mv.addObject("typeid", typeid);
        mv.setViewName("page/result");
        return mv;
    }

    //删除电影
    @RequestMapping("/deleteFilms")
    public String deleteFilms(int[] filmIds) {

        filmInfoService.deleteByIds(filmIds);

        return "redirect:/toCinema";
    }

    //检查用户名是否被占用
    @RequestMapping("/checkName")
    @ResponseBody
    public String checkName(String filmname) {
        boolean result = filmInfoService.checkName(filmname);
        System.out.println("result:" + result);
        return result + "";
    }


    //增加电影
    @RequestMapping(value = "/FilmAddServlet", method = RequestMethod.POST)
    public String FilmAddServlet(Model model, @Validated Filminfo filminfo, BindingResult result,
                                 @RequestParam(value = "picFile", required = false) MultipartFile picFile,
                                 HttpSession session) throws Exception {
        System.out.println("进入/FilmAddServlet");
        //判断有无检验错误
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
            }

            return "forward:/toAddFilm";
        }

        //文件上传
        String fileName = picFile.getOriginalFilename();//得到文件名
        System.out.println("上传文件名:" + fileName);
        try {
            String path = session.getServletContext().getRealPath("/upload");
            System.out.println(path);
            File file = new File(path);
            if (!file.exists())
                file.mkdirs();
            //获取到上传的文件
            File targetFile = new File(file, picFile.getOriginalFilename());
            //SpringMVC封装的复制文件的方法,直接将页面获取的文件内容写到目标文件中
            picFile.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        filminfo.setPic(fileName);
        System.out.println(filminfo.getPic());
        filmInfoService.save(filminfo);
        return "redirect:/toCinema";
    }

    //根据fid查询电影
 /*   @RequestMapping("/queryDetails/{filmid}/{filmname}")
    @ResponseBody//返回是json  /queryDetails?filmid=${film.filmid} -->request.getParameter("filmid");
    public  Filminfo queryDetails(@PathVariable("filmid") Integer filmId,@PathVariable("filmname")String filmname){
        System.out.println("filmname:" + filmname);
        Filminfo filminfo = filmInfoService.selectByPrimaryKey(filmId);

        //重定向
        return filminfo;
    }
*/

    @RequestMapping("/queryDetails/{filmid}/{filmname}")
    public @ResponseBody Filminfo queryDetails(@PathVariable("filmid") Integer fid,@PathVariable("filmname") String filmname){
        System.out.println("进来了吗"+fid+filmname);
        Filminfo filminfo = filmInfoService.selectByPrimaryKey(fid);
        System.out.println(filminfo.toString());
        return  filminfo;
    }

}
