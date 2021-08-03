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
package rafael.alcocer.caldera.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, JSONObject> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<String, JSONObject> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topicName, JSONObject json) {
        kafkaTemplate.send(topicName, json);
    }
}
