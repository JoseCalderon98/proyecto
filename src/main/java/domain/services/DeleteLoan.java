package domain.services;


import domain.ports.loan.LoanPort;

public class DeleteLoan {
    private LoanPort Port;
    public void setPort(LoanPort Port) {
        this.Port = Port;
    }

    public void execute(int id) {
        Port.delete(id);
    }
}





