public class Client {
    private Integer chanceForPaymentDelay;
    private Integer chanceForNoPenalty;
    private Integer chanceForLoosingProject;
    private Integer chanceForNoPayment;

    public Client(Integer chanceForPaymentDelay, Integer chanceForNoPenalty, Integer chanceForLoosingProject, Integer chanceForNoPayment){
        this.chanceForPaymentDelay = chanceForPaymentDelay;
        this.chanceForNoPenalty = chanceForNoPenalty;
        this.chanceForLoosingProject = chanceForLoosingProject;
        this.chanceForNoPayment = chanceForNoPayment;
    }

}