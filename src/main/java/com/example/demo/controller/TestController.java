package com.example.demo.controller;

import com.example.demo.entity.User;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author liufei
 * @since 2019-10-31 17:53
 */
@RestController
@RequestMapping("/test")
@Validated
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * BindingResult必须加上即使不使用否则针对当前方法的AOP拦截
     * {@link com.example.demo.config.GlobalLogAspect}将会失效
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping
    public void save(@Valid @RequestBody User user, BindingResult bindingResult) {
        System.out.println(user);
    }

    @GetMapping("/{age}")
    public int test(@Range(min = 10, max = 200, message = "年龄不符") @PathVariable("age") int age) {
        return age;
    }

    @GetMapping
    public String test(@NotEmpty(message = "邮箱不可为空") @Email(message = "邮箱格式不正确") @RequestParam("email") String email) {
        return email;
    }

}
