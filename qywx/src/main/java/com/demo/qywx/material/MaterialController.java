package com.demo.qywx.material;

import com.demo.qywx.content.WxContent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jjxiek
 * @since 2019/11/25 20:45
 */
@RestController
@RequestMapping("/material")
public class MaterialController {
    @GetMapping("/test")
    public void test(){
        System.out.println(WxContent.CORP_ID);
    }

}
