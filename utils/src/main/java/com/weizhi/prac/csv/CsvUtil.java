package com.weizhi.prac.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.weizhi.prac.csv.model.Template;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class CsvUtil {

    /**
     * 解析csv文件并转成bean（方法三）
     *
     * @param fileInputStream csv文件
     * @param clazz           类
     * @param <T>             泛型
     * @return 泛型bean集合
     */
    public static <T> List<T> getCsvDataMethod(InputStream fileInputStream, Class<T> clazz) {
        CsvToBean<T> csvToBean = null;
        try (InputStreamReader in = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);
            csvToBean = new CsvToBeanBuilder<T>(in).withMappingStrategy(strategy).build();
            return csvToBean.parse();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }


}
