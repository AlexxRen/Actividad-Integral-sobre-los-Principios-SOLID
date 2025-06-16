package Old;

import java.util.regex.Pattern;

class UserManager {
    //  VIOLACIÓN SRP: Esta clase tiene múltiples responsabilidades

    public void addUser(String email, String password) {
        // Responsabilidad 1: Validación
        if (isValidEmail(email) && isValidPassword(password)) {
            // Responsabilidad 2: Persistencia
            saveToDatabase(email, password);
            // Responsabilidad 3: Notificaciones
            sendWelcomeEmail(email);
        } else {
            System.out.println(" Invalid email or password. User not added.");
        }
    }

    // RESPONSABILIDAD 1: VALIDACIÓN
    private boolean isValidEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    // RESPONSABILIDAD 2: PERSISTENCIA
    private void saveToDatabase(String email, String password) {
        System.out.println(" Saving user to the database...");
        System.out.println(" Email: " + email);
        System.out.println(" Password: " + password);
    }

    // RESPONSABILIDAD 3: NOTIFICACIONES
    private void sendWelcomeEmail(String email) {
        System.out.println(" Sending welcome email to " + email);
    }
}