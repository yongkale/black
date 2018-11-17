package heiqi.com.greenfoodapp.controller;

import com.qiniu.util.Auth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apiqiniu")
public class QiNiuController {
    private final static String accessKey = "CQv08jFgSgYogzSrZUKTFdZ3BqQVZVDzs2ATS2Vf";
    private final static String secretKey = "RZly4lQDtS3aPLEV5jhA2O4tYyNGBBAwD-jp9WJr";
    private final static String bucket = "date";

    @ResponseBody
    @RequestMapping(value = "/getQiNiuToken",method = RequestMethod.GET)
    public String getQiNiuToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }
}
