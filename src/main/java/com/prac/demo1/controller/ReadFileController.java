package com.prac.demo1.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prac.demo1.bean.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Auther: liweizhi
 * @Date: 2019/1/16 14:41
 * @Description:
 */
@RestController
@RequestMapping("rf")
public class ReadFileController {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Value("${nbd.datasource.url}")
    private String nbdUrl;

    @GetMapping("prop")
    public String getProp() {
        return nbdUrl;
    }

    @GetMapping("test")
    public Object firstTest() throws IOException {

        ArrayList<CommonResult> results = new ArrayList<>();

        InputStream is = null;
        try {
            is = new FileInputStream(new File(System.getProperty("user.dir") + File.separator + "config" + File.separator + "test.json"));
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            String content = new String(bytes, UTF8);
            JSONObject jsonObject = JSONObject.parseObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            Iterator<Object> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                JSONObject object = JSONObject.parseObject(next.toString());
                CommonResult commonResult = new CommonResult();
                commonResult.setName(object.getString("name"));
                BigDecimal maxCapacity = BigDecimal.ZERO;
                BigDecimal usedCapacity = BigDecimal.ONE;
                if (StringUtils.isNotEmpty(object.getString("maxCapacity"))) {
                    maxCapacity = new BigDecimal(object.getString("maxCapacity"));
                }
                if (StringUtils.isNotEmpty(object.getString("usedCapacity"))) {
                    usedCapacity = new BigDecimal(object.getString("usedCapacity"));
                }
                commonResult.setValue(usedCapacity.divide(maxCapacity, 2, BigDecimal.ROUND_HALF_DOWN).toString());
                results.add(commonResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return results;
    }

}
