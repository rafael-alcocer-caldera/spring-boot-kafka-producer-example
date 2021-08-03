/**
 * Copyright [2021] [RAFAEL ALCOCER CALDERA]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rafael.alcocer.caldera.serializer;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Serializer;

/**
 * This class was gotten from
 * https://mail-narayank.medium.com/kafka-custom-serializer-and-deserializer-884213edbed1
 * 
 * @author Rafael Alcocer Caldera
 * @version 1.0
 *
 * @param <T>
 */
public class CustomSerializer<T extends Serializable> implements Serializer<T> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, T data) {
        return SerializationUtils.serialize(data);
    }

    @Override
    public void close() {
    }
}