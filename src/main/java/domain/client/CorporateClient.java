package domain.client;

public class CorporateClient extends Client {
    private String companyName;
    private String taxId;
    private String legalRepresentativeId;

    public CorporateClient(String fullName, String identification, ClientStatus clientStatus, String companyName, String taxId, String legalRepresentativeId) {
        super(fullName, identification, clientStatus);
        this.companyName = companyName;
        this.taxId = taxId;
        this.legalRepresentativeId = legalRepresentativeId;
    }

    public CorporateClient(String companyName, String taxId, String legalRepresentativeId) {
        super(companyName, taxId, ClientStatus.ACTIVE);
        this.companyName = companyName;
        this.taxId = taxId;
        this.legalRepresentativeId = legalRepresentativeId;
    }

    public CorporateClient() {
        super();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public void setLegalRepresentativeId(String legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }
}
