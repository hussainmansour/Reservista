package Reservista.example.Backend.MailComponent;

public class Mail {
    String body;
    String subject;

    String to;

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public String getTo() {
        return to;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
