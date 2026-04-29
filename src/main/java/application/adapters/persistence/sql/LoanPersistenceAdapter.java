package application.adapters.persistence.sql;

import application.adapters.persistence.sql.entities.LoanEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaLoanRepository;
import domain.models.loan.Loan;
import domain.ports.loan.LoanPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LoanPersistenceAdapter implements LoanPort {

    private final SpringDataJpaLoanRepository loanRepository;

    public LoanPersistenceAdapter(SpringDataJpaLoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan save(Loan loan) {
        LoanEntity entity = toEntity(loan);
        LoanEntity savedEntity = loanRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Loan> findById(int id) {
        return loanRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void delete(int id) {
        loanRepository.deleteById(id);
    }

    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private LoanEntity toEntity(Loan loan) {
        LoanEntity entity = new LoanEntity();
        entity.setLoanId(loan.getLoanId());
        entity.setLoanType(loan.getLoanType());
        entity.setApplicantClientId(loan.getApplicantClientId());
        entity.setRequestedAmount(loan.getRequestedAmount());
        entity.setApprovedAmount(loan.getApprovedAmount());
        entity.setInterestRate(loan.getInterestRate());
        entity.setTermMonths(loan.getTermMonths());
        entity.setLoanStatus(loan.getLoanStatus());
        entity.setApprovalDate(loan.getApprovalDate());
        entity.setDisbursementDate(loan.getDisbursementDate());
        entity.setDestinationAccountForDisbursement(loan.getDestinationAccountForDisbursement());
        entity.setCreatorUserId(loan.getCreatorUserId());
        return entity;
    }

    private Loan toDomain(LoanEntity entity) {
        return new Loan(
                entity.getLoanId(),
                entity.getLoanType(),
                entity.getApplicantClientId(),
                entity.getRequestedAmount(),
                entity.getApprovedAmount(),
                entity.getInterestRate(),
                entity.getTermMonths(),
                entity.getLoanStatus(),
                entity.getApprovalDate(),
                entity.getDisbursementDate(),
                entity.getDestinationAccountForDisbursement(),
                entity.getCreatorUserId()
        );
    }
}
