package application.jobs;

import application.usecases.ExpireTransfersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TransferExpirationJob {

    @Autowired
    private ExpireTransfersUseCase expireTransfersUseCase;

    // Checks every minute (60,000 ms)
    @Scheduled(fixedRate = 60000)
    public void runExpirationJob() {
        expireTransfersUseCase.expirePendingTransfers();
    }
}
