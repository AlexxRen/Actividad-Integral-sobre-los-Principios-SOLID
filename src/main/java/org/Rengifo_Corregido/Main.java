package org.Rengifo_Corregido;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        System.out.println("=== Testing with valid user ===");
        userManager.addUser("example@domain.com", "password123");

        System.out.println("\n=== Testing with invalid email ===");
        userManager.addUser("invalid-email", "password123");

        System.out.println("\n=== Testing with invalid password ===");
        userManager.addUser("valid@email.com", "1234");

        System.out.println("\n=== Testing with both invalid ===");
        userManager.addUser("invalid-email", "123");

    }
}

