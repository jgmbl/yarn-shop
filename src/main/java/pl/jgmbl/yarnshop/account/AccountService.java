package pl.jgmbl.yarnshop.account;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    HttpSession httpSession;
    public void endSession() {
        httpSession.invalidate();
    }
}
