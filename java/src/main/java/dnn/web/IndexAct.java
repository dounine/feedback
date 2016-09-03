package dnn.web;

import dnn.service.user.ISerUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by huanghuanlai on 16/3/29.
 */
@RestController
public class IndexAct {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexAct.class);

    @Autowired
    ISerUser iSerUser;

    /**
     * 重写向到首页
     * @return 首页位置
     */
    @RequestMapping(value = {"","admin"})
    public ModelAndView index() {
        return  new ModelAndView("redirect:admin");
    }

}
