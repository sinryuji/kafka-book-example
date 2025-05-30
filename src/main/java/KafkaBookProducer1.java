import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaBookProducer1 {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.67.3:9092,192.168.67.4:9092,192.168.67.5:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            producer.send(new ProducerRecord<String, String>("peter-topic",
                "Apache Kafka is a distributed streaming platform"), new PeterCallback());
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
