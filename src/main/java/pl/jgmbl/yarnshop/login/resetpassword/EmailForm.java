package pl.jgmbl.yarnshop.login.resetpassword;

public class EmailForm {
    String email;

    public EmailForm() {
    }

    public EmailForm(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
