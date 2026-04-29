package application.config;

import application.adapters.persistence.sql.IndividualClientPersistenceAdapter;
import application.usecases.ClientUseCase;
import domain.ports.client.IndividualClientPort;
import domain.services.CreateNaturalClient;
import application.usecases.*;
import domain.ports.account.BankAccountPort;
import domain.ports.events.EventPublisher;
import domain.ports.loan.LoanPort;
import domain.ports.transfer.TransferPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateNaturalClient createNaturalClient(IndividualClientPort individualClientPort) {
        return new CreateNaturalClient(individualClientPort);
    }

    @Bean
    public ClientUseCase clientUseCase(CreateNaturalClient createNaturalClient) {
        return new ClientUseCase(createNaturalClient);
    }

    @Bean
    public ApproveLoanUseCase approveLoanUseCase(LoanPort loanPort, EventPublisher eventPublisher) {
        return new ApproveLoanUseCase(loanPort, eventPublisher);
    }

    @Bean
    public DisburseLoanUseCase disburseLoanUseCase(LoanPort loanPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher) {
        return new DisburseLoanUseCase(loanPort, bankAccountPort, eventPublisher);
    }

    @Bean
    public CreateTransferUseCase createTransferUseCase(TransferPort transferPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher, @Value("${banking.transfer.enterprise.threshold:10000}") BigDecimal enterpriseApprovalThreshold) {
        return new CreateTransferUseCase(transferPort, bankAccountPort, eventPublisher, enterpriseApprovalThreshold);
    }

    @Bean
    public ApproveTransferUseCase approveTransferUseCase(TransferPort transferPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher) {
        return new ApproveTransferUseCase(transferPort, bankAccountPort, eventPublisher);
    }

    @Bean
    public ExpirePendingTransfersUseCase expirePendingTransfersUseCase(TransferPort transferPort, EventPublisher eventPublisher) {
        return new ExpirePendingTransfersUseCase(transferPort, eventPublisher);
    }
}
