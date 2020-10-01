package pl.training.payments;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Payment {

    private String id;
    @NonNull
    private String transactionId;
    @NonNull
    private Long amount;
    @NonNull
    private Instant timeStamp;

    public boolean hasTransactionId(){
        return !transactionId.isEmpty();
    }
}
