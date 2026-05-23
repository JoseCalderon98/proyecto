package application.config;

import application.adapters.persistence.sql.IndividualClientPersistenceAdapter;
import application.usecases.ClientUseCase;
import domain.ports.client.IndividualClientPort;
import domain.ports.client.CorporateClientPort;
import domain.services.CreateNaturalClient;
import domain.services.CreateCompanyClient;
import application.usecases.*;
import domain.ports.account.BankAccountPort;
import domain.ports.events.EventPublisher;
import domain.ports.loan.LoanPort;
import domain.ports.transfer.TransferPort;
import domain.ports.user.UserPort;
import domain.services.CreateUser;
import domain.services.CreateBankAccount;
import domain.services.CreateLoan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateNaturalClient createNaturalClient(IndividualClientPort individualClientPort) {
        CreateNaturalClient service = new CreateNaturalClient();
        service.setPort(individualClientPort);
        return service;
    }

    @Bean
    public CreateCompanyClient createCompanyClient(CorporateClientPort corporateClientPort) {
        CreateCompanyClient service = new CreateCompanyClient();
        service.setPort(corporateClientPort);
        return service;
    }

    @Bean
    public ClientUseCase clientUseCase(CreateNaturalClient createNaturalClient, CreateCompanyClient createCompanyClient) {
        ClientUseCase service = new ClientUseCase();
        service.setCreateNaturalClient(createNaturalClient);
        service.setCreateCompanyClient(createCompanyClient);
        return service;
    }

    @Bean
    public ApproveLoanUseCase approveLoanUseCase(LoanPort loanPort, EventPublisher eventPublisher, UserPort userPort) {
        ApproveLoanUseCase service = new ApproveLoanUseCase();
        service.setLoanPort(loanPort);
        service.setEventPublisher(eventPublisher);
        service.setUserPort(userPort);
        return service;
    }

    @Bean
    public DisburseLoanUseCase disburseLoanUseCase(LoanPort loanPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher, UserPort userPort) {
        DisburseLoanUseCase service = new DisburseLoanUseCase();
        service.setLoanPort(loanPort);
        service.setBankAccountPort(bankAccountPort);
        service.setEventPublisher(eventPublisher);
        service.setUserPort(userPort);
        return service;
    }

    @Bean
    public CreateTransferUseCase createTransferUseCase(TransferPort transferPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher, @Value("${banking.transfer.enterprise.threshold:10000}") BigDecimal enterpriseApprovalThreshold, UserPort userPort) {
        CreateTransferUseCase service = new CreateTransferUseCase();
        service.setTransferPort(transferPort);
        service.setBankAccountPort(bankAccountPort);
        service.setEventPublisher(eventPublisher);
        service.setEnterpriseApprovalThreshold(enterpriseApprovalThreshold);
        service.setUserPort(userPort);
        return service;
    }

    @Bean
    public ApproveTransferUseCase approveTransferUseCase(TransferPort transferPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher) {
        ApproveTransferUseCase service = new ApproveTransferUseCase();
        service.setTransferPort(transferPort);
        service.setBankAccountPort(bankAccountPort);
        service.setEventPublisher(eventPublisher);
        return service;
    }

    @Bean
    public ExpirePendingTransfersUseCase expirePendingTransfersUseCase(TransferPort transferPort, EventPublisher eventPublisher) {
        ExpirePendingTransfersUseCase service = new ExpirePendingTransfersUseCase();
        service.setTransferPort(transferPort);
        service.setEventPublisher(eventPublisher);
        return service;
    }
    @Bean
    public RejectLoanUseCase rejectLoanUseCase(LoanPort loanPort, EventPublisher eventPublisher, UserPort userPort) {
        RejectLoanUseCase service = new RejectLoanUseCase();
        service.setLoanPort(loanPort);
        service.setEventPublisher(eventPublisher);
        service.setUserPort(userPort);
        return service;
    }

    @Bean
    public RejectTransferUseCase rejectTransferUseCase(TransferPort transferPort, EventPublisher eventPublisher) {
        RejectTransferUseCase service = new RejectTransferUseCase();
        service.setTransferPort(transferPort);
        service.setEventPublisher(eventPublisher);
        return service;
    }

    @Bean
    public CreateUser createUser(UserPort userPort) {
        CreateUser service = new CreateUser();
        service.setUserPort(userPort);
        return service;
    }

    @Bean
    public CreateUserUseCase createUserUseCase(CreateUser createUserDomainService, UserPort userPort, IndividualClientPort clientPort, CorporateClientPort corporateClientPort) {
        CreateUserUseCase service = new CreateUserUseCase();
        service.setCreateUserDomainService(createUserDomainService);
        service.setUserPort(userPort);
        service.setClientPort(clientPort);
        service.setCorporateClientPort(corporateClientPort);
        return service;
    }

    @Bean
    public CreateBankAccount createBankAccount(BankAccountPort bankAccountPort) {
        CreateBankAccount service = new CreateBankAccount();
        service.setBankAccountPort(bankAccountPort);
        return service;
    }

    @Bean
    public CreateBankAccountUseCase createBankAccountUseCase(CreateBankAccount createBankAccountDomainService, BankAccountPort bankAccountPort, IndividualClientPort clientPort, CorporateClientPort corporateClientPort, UserPort userPort) {
        CreateBankAccountUseCase service = new CreateBankAccountUseCase();
        service.setCreateBankAccountDomainService(createBankAccountDomainService);
        service.setBankAccountPort(bankAccountPort);
        service.setClientPort(clientPort);
        service.setCorporateClientPort(corporateClientPort);
        service.setUserPort(userPort);
        return service;
    }

    @Bean
    public CreateLoan createLoan(LoanPort loanPort) {
        CreateLoan service = new CreateLoan();
        service.setPort(loanPort);
        return service;
    }

    @Bean
    public CreateLoanUseCase createLoanUseCase(CreateLoan createLoanDomainService, LoanPort loanPort, IndividualClientPort clientPort, CorporateClientPort corporateClientPort, UserPort userPort, BankAccountPort bankAccountPort) {
        CreateLoanUseCase service = new CreateLoanUseCase();
        service.setCreateLoanDomainService(createLoanDomainService);
        service.setLoanPort(loanPort);
        service.setClientPort(clientPort);
        service.setCorporateClientPort(corporateClientPort);
        service.setUserPort(userPort);
        service.setBankAccountPort(bankAccountPort);
        return service;
    }

    @Bean
    public FindLoanUseCase findLoanUseCase(LoanPort loanPort) {
        FindLoanUseCase service = new FindLoanUseCase();
        service.setLoanPort(loanPort);
        return service;
    }
}

