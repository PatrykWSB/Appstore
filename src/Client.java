public class Client {
    public Integer chanceForPaymentDelay;
    public Integer chanceForNoPenalty;
    public Integer chanceForLoosingProject;
    public Integer chanceForNoPayment;

    public Client(Integer chanceForPaymentDelay, Integer chanceForNoPenalty, Integer chanceForLoosingProject, Integer chanceForNoPayment){
        this.chanceForPaymentDelay = chanceForPaymentDelay;
        this.chanceForNoPenalty = chanceForNoPenalty;
        this.chanceForLoosingProject = chanceForLoosingProject;
        this.chanceForNoPayment = chanceForNoPayment;
    }

}