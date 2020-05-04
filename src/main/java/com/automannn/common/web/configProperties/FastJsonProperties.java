package com.automannn.common.web.configProperties;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.automannn.common.web.util.JsonFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

/**
 * @author automannn@163.com
 * @time 2020/5/4 11:18
 */


@Component
@ConfigurationProperties(
        prefix = "atm.json"
)
public class FastJsonProperties extends FastJsonConfig {

    private static final SimpleDateFormatSerializer DATE_FORMAT = new SimpleDateFormatSerializer("yyyy-MM-dd");
    private static final SimpleDateFormatSerializer DATETIME_FORMAT = new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss");

    public FastJsonProperties() {
        super.setCharset(StandardCharsets.UTF_8);
        super.setSerializerFeatures(new SerializerFeature[]{SerializerFeature.PrettyFormat});
        super.setSerializeConfig(JsonFactory.config);
    }

    public void setSerializeConfig(SerializeConfig serializeConfig) {
        if (serializeConfig.get(Date.class) == null) {
            serializeConfig.put(Date.class, DATE_FORMAT);
        }

        if (serializeConfig.get(Timestamp.class) == null) {
            serializeConfig.put(Timestamp.class, DATETIME_FORMAT);
        }

        super.setSerializeConfig(serializeConfig);
    }

    public void setSerializerFeatures(SerializerFeature... serializerFeatures) {
        if (null != serializerFeatures) {
            boolean hasPrettyFormat = false;
            SerializerFeature[] serializerFeatureArr;
            if (serializerFeatures.length > 0) {
                serializerFeatureArr = serializerFeatures;
                int var4 = serializerFeatures.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    SerializerFeature feature = serializerFeatureArr[var5];
                    if (feature.equals(SerializerFeature.PrettyFormat)) {
                        hasPrettyFormat = true;
                        break;
                    }
                }
            }

            if (!hasPrettyFormat) {
                serializerFeatureArr = (SerializerFeature[]) Arrays.copyOf(serializerFeatures, serializerFeatures.length + 1);
                serializerFeatureArr[serializerFeatures.length] = SerializerFeature.PrettyFormat;
                super.setSerializerFeatures(serializerFeatureArr);
            } else {
                super.setSerializerFeatures(serializerFeatures);
            }
        }
    }
}
