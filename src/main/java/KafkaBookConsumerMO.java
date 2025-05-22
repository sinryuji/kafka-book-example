import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaBookConsumerMO {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.67.3:9092,192.168.67.4:9092,192.168.67.5:9092");
        props.put("group.id", "peter-manual");
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "latest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("peter-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("Topic: %s, Partition: %s, Offset: %d, Key: %s, Value: %s\n",
                    record.topic(), record.partition(), record.offset(), record.key(),
                    record.value());
            }
            try {
                consumer.commitSync();
            } catch (CommitFailedException e) {
                System.out.printf("commit failed", e);
            }
        }
    }
}
