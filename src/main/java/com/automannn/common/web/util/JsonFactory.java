package com.automannn.common.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author automannn@163.com
 * @time 2020/5/4 11:21
 */
public class JsonFactory {
    public static SerializeConfig config = new SerializeConfig();

    private JsonFactory() {
    }

    public static String bean2json(Object bean) {
        return bean == null ? "" : JSON.toJSONString(bean, config, new SerializerFeature[]{SerializerFeature.WriteSlashAsSpecial, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect});
    }

    public static <T> T json2bean(String json, Class<T> clazz) {
        return json == null ? null : JSON.parseObject(json, clazz);
    }

    public static String getJson(String data) {
        String json = JSON.toJSONString(data);
        return data != null && data.length() > 0 ? json.substring(1, json.length() - 1) : "";
    }

    public static String toJson(String data) {
        if (data == null) {
            return null;
        } else {
            String reDate = StringUtils.replace(data, "\\", "\\\\");
            reDate = StringUtils.replace(reDate, "\r", "\\\\r");
            reDate = StringUtils.replace(reDate, "\t", "\\\\t");
            reDate = StringUtils.replace(reDate, "\b", "\\\\b");
            reDate = StringUtils.replace(reDate, "\f", "\\\\f");
            reDate = StringUtils.replace(reDate, "\n", "\\\\n");
            reDate = StringUtils.replace(reDate, "\"", "\\\"");
            return reDate;
        }
    }

    static {
        SimpleDateFormatSerializer dateFormat = new SimpleDateFormatSerializer("yyyy-MM-dd");
        SimpleDateFormatSerializer dateTimeFormat = new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss");
        config.put(Date.class, dateFormat);
        config.put(Timestamp.class, dateTimeFormat);
    }
}
