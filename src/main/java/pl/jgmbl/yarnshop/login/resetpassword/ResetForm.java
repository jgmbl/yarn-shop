package pl.jgmbl.yarnshop.login.resetpassword;

public class ResetForm {
    String newpassword;
    String confirmpassword;

    public ResetForm() {
    }

    public ResetForm(String newpassword, String confirmpassword) {
        this.newpassword = newpassword;
        this.confirmpassword = confirmpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
