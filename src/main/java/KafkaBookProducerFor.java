import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaBookProducerFor {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.68.7:9092,192.168.68.3:9092,192.168.68.8:9092");
        props.put("acks", "1");
        props.put("compression.type", "gzip");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 1; i < 11; i++) {
            producer.send(new ProducerRecord<String, String>("peter-topic",
                "Apache Kafka is a distributed streaming platform" + i));
        }

        producer.close();
    }
}
