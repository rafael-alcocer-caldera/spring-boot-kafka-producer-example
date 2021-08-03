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
package rafael.alcocer.caldera;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import net.sf.json.JSONObject;

public class RacConsumer {

    public static void main(String[] args) {
        RacConsumer racConsumer = new RacConsumer();
        racConsumer.go();
    }

    public void go() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, UUID.randomUUID().toString());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                org.apache.kafka.common.serialization.StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, rafael.alcocer.caldera.serializer.CustomSerializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                org.apache.kafka.common.serialization.StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, rafael.alcocer.caldera.deserializer.CustomDeserializer.class);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, Object> kstream = streamsBuilder.stream("rac1-topic");
        kstream.groupBy(new KeyValueMapper<String, Object, String>() {

            @Override
            public String apply(String key, Object value) {
                System.out.println("##### key: " + key);
                System.out.println("##### value: " + value);
                System.out.println("##### value.getClass(): " + value.getClass());

                if (value instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) value;
                    System.out.println("##### jsonObject.keySet(): " + jsonObject.keySet());
                    System.out.println("##### jsonObject.values(): " + jsonObject.values());
                } else if (value instanceof byte[]) {
                    byte[] b = (byte[]) value;
                    String s = Arrays.toString(b);
                    System.out.println("##### s: " + s);
                    
                    // byte[] to string
                    String s2 = new String(b, StandardCharsets.UTF_8);
                    System.out.println("##### s2: " + s2);
                    
                    String s3 = Base64.getEncoder().encodeToString(b);
                    System.out.println("##### s3: " + s3);
                    
                    System.out.println("##### deserialize(): " + SerializationUtils.deserialize(b));
                }

                return key;
            }
        });

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), props);

        kafkaStreams.start();
    }
}
