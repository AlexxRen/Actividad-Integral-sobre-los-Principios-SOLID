package org.Rengifo_Corregido;

public class UserManager {


    private UserValidator validator;
    private UserRepository repository;
    private NotificationService notificationService;

    public UserManager() {
        this.validator = new UserValidator();
        this.repository = new UserRepository();
        this.notificationService = new NotificationService();
    }

    // Constructor for dependency injection (optional for testing)
    public UserManager(UserValidator validator, UserRepository repository, NotificationService notificationService) {
        this.validator = validator;
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void addUser(String email, String password) {
        if (validator.validateUser(email, password)) {
            repository.saveToDatabase(email, password);
            notificationService.sendWelcomeEmail(email);
            System.out.println("User successfully added and notified.");
        } else {
            System.out.println("Invalid email or password. User not added.");
        }
    }
}
