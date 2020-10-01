package pl.training.payments;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class PaymentsGeneratorTest {
    private PaymentsGenerator paymentsGenerator = new PaymentsGenerator();
    
    @Test
    void shouldGeneratePayments() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        paymentsGenerator.paymentStream(Duration.ofSeconds(1))
                .subscribe(this::onPayment, this::onError, countDownLatch::countDown);
        countDownLatch.await();
    }

    private void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    private void onPayment(Payment payment) {
        System.out.println(payment);
    }

}