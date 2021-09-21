package pbao.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class PartitionerProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "srv1:9092,srv2:9092,srv3:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "pbao.partitioner.MyPartitioner");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("first", "aaa-" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null) {
                        System.out.println(metadata.timestamp() + "--" + metadata.topic() + "--" + metadata.partition() + "--" + metadata.offset());
                    } else {
                        exception.printStackTrace();
                    }

                }
            });
        }
        producer.close();
    }

}
