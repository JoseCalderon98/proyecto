package application.adapters.persistence.sql;

import application.adapters.persistence.sql.entities.LoanEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaLoanRepository;
import domain.models.loan.Loan;
import domain.ports.loan.LoanPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LoanPersistenceAdapter implements LoanPort {

    @Autowired
    private SpringDataJpaLoanRepository loanRepository;

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
        Loan loan = new Loan();
        loan.setLoanId(entity.getLoanId());
        loan.setLoanType(entity.getLoanType());
        loan.setApplicantClientId(entity.getApplicantClientId());
        loan.setRequestedAmount(entity.getRequestedAmount());
        loan.setApprovedAmount(entity.getApprovedAmount());
        loan.setInterestRate(entity.getInterestRate());
        loan.setTermMonths(entity.getTermMonths());
        loan.setLoanStatus(entity.getLoanStatus());
        loan.setApprovalDate(entity.getApprovalDate());
        loan.setDisbursementDate(entity.getDisbursementDate());
        loan.setDestinationAccountForDisbursement(entity.getDestinationAccountForDisbursement());
        loan.setCreatorUserId(entity.getCreatorUserId());
        return loan;
    }
}

