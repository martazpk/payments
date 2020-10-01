package pl.training.payments;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentsGeneratorTest {
    private PaymentsGenerator paymentsGenerator = new PaymentsGenerator();
    
    @Test
    void shouldGeneratePayments()  {
        StepVerifier.create(paymentsGenerator.paymentStream(Duration.ofSeconds(1)).take(2))
                .recordWith(ArrayList::new)
                .consumeNextWith(Payment::hasTransactionId)
                .expectNextCount(1)
                .consumeRecordedWith(payments -> assertThat(payments).allMatch(Payment::hasTransactionId))
                .verifyComplete();
    }

    private void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    private void onPayment(Payment payment) {
        System.out.println(payment);
    }

}