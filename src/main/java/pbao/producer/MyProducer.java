package pbao.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyProducer {

    public static void main(String[] args) {
//        ProducerConfig;
        Properties props = new Properties();
        props.put("bootstrap.servers", "srv1:9092,srv2:9092,srv3:9092");
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "all");
//        props.put("retries", 1);
//        props.put("batch.size", 16 * 1024);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 32 * 1024 * 1024);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for(int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("first", "key123","chriszt--" + i));
        }

        producer.close();

    }

}
