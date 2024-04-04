package kimlamdo.my_project_backend.service.email;

public interface EmailService {
    public void sendEmail(String from, String to, String subject, String text);
}
