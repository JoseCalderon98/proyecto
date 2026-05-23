package application.usecases;

import application.adapters.api.requests.UserAlreadyExistsException;

import domain.models.user.User;
import domain.models.user.UserRole;
import domain.ports.user.UserPort;
import domain.ports.client.IndividualClientPort;
import domain.ports.client.CorporateClientPort;
import domain.services.CreateUser;

public class CreateUserUseCase {

    private CreateUser createUserDomainService;
    private UserPort userPort;
    private IndividualClientPort clientPort;
    private CorporateClientPort corporateClientPort;

    public void setCreateUserDomainService(CreateUser createUserDomainService) {
        this.createUserDomainService = createUserDomainService;
    }

    public void setUserPort(UserPort userPort) {
        this.userPort = userPort;
    }

    public void setClientPort(IndividualClientPort clientPort) {
        this.clientPort = clientPort;
    }

    public void setCorporateClientPort(CorporateClientPort corporateClientPort) {
        this.corporateClientPort = corporateClientPort;
    }

    public User execute(User user) {
        // Enforce validations from domain model (Rules 1.2, 1.3, 1.4)
        user.validate();

        // Enforce Rule 1.1: Uniqueness of Identification across users, individual clients, and corporate clients
        boolean idExistsInUsers = userPort.findAll().stream()
                .anyMatch(u -> u.getIdentification().equals(user.getIdentification()));
        if (idExistsInUsers) {
            throw new UserAlreadyExistsException("User identification already exists in the system");
        }

        boolean idExistsInIndividualClients = clientPort.findAll().stream()
                .anyMatch(c -> c.getIdentification().equals(user.getIdentification())
                        && !c.getIdentification().equals(user.getRelatedId()));

        boolean idExistsInCorporateClients = corporateClientPort.findAll().stream()
                .anyMatch(c -> c.getIdentification().equals(user.getIdentification())
                        && !c.getIdentification().equals(user.getRelatedId()));

        if (idExistsInIndividualClients || idExistsInCorporateClients) {
            throw new UserAlreadyExistsException("Identification already exists as a client in the system");
        }

        // Validate relatedId points to an existing client of the correct type for client roles
        if (user.getSystemRole() == UserRole.NATURAL_CLIENT) {
            boolean relatedClientExists = clientPort.findAll().stream()
                    .anyMatch(c -> c.getIdentification().equals(user.getRelatedId()) || String.valueOf(c.getId()).equals(user.getRelatedId()));
            if (!relatedClientExists) {
                throw new IllegalArgumentException("Related ID must reference an existing Individual Client for role NATURAL_CLIENT");
            }
        } else if (user.getSystemRole() == UserRole.ENTERPRISE_CLIENT
                || user.getSystemRole() == UserRole.ENTERPRISE_EMPLOYEE
                || user.getSystemRole() == UserRole.ENTERPRISE_SUPERVISOR) {
            boolean relatedCorporateExists = corporateClientPort.findAll().stream()
                    .anyMatch(c -> c.getIdentification().equals(user.getRelatedId()) || String.valueOf(c.getId()).equals(user.getRelatedId()));
            if (!relatedCorporateExists) {
                throw new IllegalArgumentException("Related ID must reference an existing Corporate Client for enterprise roles");
            }
        }

        return createUserDomainService.execute(user);
    }
}
