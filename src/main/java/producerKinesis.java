import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;

import java.nio.ByteBuffer;

import static java.nio.charset.StandardCharsets.UTF_8;


public class producerKinesis {


    public static void main(String[] args) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AWS_ACCESS_KEY_ID", "AWS_SECRET_ACCESS_KEY");

        KinesisProducerConfiguration config = new KinesisProducerConfiguration();
        config.setRecordMaxBufferedTime(3000);
        config.setMaxConnections(1);
        config.setRequestTimeout(60000);
        config.setCollectionMaxCount(100);
        config.setCollectionMaxSize(52224);
        config.setAggregationMaxCount(100);
        config.setRegion("eu-west-1");
        config.setCredentialsProvider(new AWSStaticCredentialsProvider(awsCreds));

        KinesisProducer kinesis = new KinesisProducer(config);

        for (int i = 0; i < 10000000; ++i) {
            String partitionKey = String.valueOf(i);
            String myData = "it's just test record implement to flux Amazon Web Services kinesis stream";
            ByteBuffer data = ByteBuffer.wrap(myData.getBytes(UTF_8));
            /* doesn't block */
           // kinesis.addUserRecord("fluxKinesis", "shard_test", data);
            kinesis.addUserRecord("fluxKinesis", partitionKey, data);
        }
    }
}
