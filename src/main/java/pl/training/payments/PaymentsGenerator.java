package pl.training.payments;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentsGenerator {
    private static final int MAX_AMOUNT = 10_000;
    private final Random random = new Random();

    public Flux<Payment> paymentStream(Duration duration){
        return Flux.generate(this::paymentsSink)
                .zipWith(Flux.interval(duration))
                .map(Tuple2::getT1);
    }

    private void paymentsSink(SynchronousSink<Payment> sink){
        sink.next(createPayment());
    }

    public Payment createPayment(){
        String paymentsId = UUID.randomUUID().toString();
        long amount= random.nextInt(MAX_AMOUNT) + 1;
        Instant timeStamp = Instant.now();
        return new Payment(paymentsId, amount, timeStamp);
    }
}
