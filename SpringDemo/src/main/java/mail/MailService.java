package mail;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class MailService {
    private String getTime() {
        SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss");
        Date date = new Date();
        return dateformat.format(date);
    }

    public void sendLoginMes(User user) {
        System.out.printf("Hi, %s! You are logged in at %s", user.getName(), this.getTime());
    }

    public void sendRegistrationMes(User user) {
        System.out.printf("Hi, %s! Congratulation for join us at %s", user.getName(), this.getTime());
    }
}
