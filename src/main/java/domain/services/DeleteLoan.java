package domain.services;


import domain.ports.loan.LoanPort;

public class DeleteLoan {
    private final LoanPort Port;

    public DeleteLoan(LoanPort Port) {
        this.Port = Port;
    }

    public void execute(int id) {
        Port.delete(id);
    }
}




