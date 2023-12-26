package com.example.controller;

import com.example.common.config.CaptureConfig;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping
public class CaptureController {
    @RequestMapping("/captcha")
    public void captcha(@RequestParam String key,HttpServletRequest request, HttpServletResponse response)throws Exception{
        //指定验证码的长宽以及字符的个数
        SpecCaptcha captcha=new SpecCaptcha(135,33,5);
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        //首先把验证码在后台存一份，但是不能在session，可以存在redis或者后台的某个Map内
        CaptureConfig.CAPTURE_MAP.put(key, captcha.text().toLowerCase());
        CaptchaUtil.out(captcha,request,response);
    }
}
