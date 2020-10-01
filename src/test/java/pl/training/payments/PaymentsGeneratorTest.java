package pl.training.payments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentsGeneratorTest {
    private PaymentsGenerator paymentsGenerator = new PaymentsGenerator();
    
    @Test
    void shouldGeneratePayments(){
        paymentsGenerator.paymentStream()
                .subscribe(this::onPayment, this::onError);
    }

    private void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    private void onPayment(Payment payment) {
        System.out.println(payment);
    }

}