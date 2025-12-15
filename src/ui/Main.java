package ui;

import proxy.CategoryServiceProxy;
import services.CategoryService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== Admin Login ===");
        System.out.print("Enter username: ");
        String user = input.nextLine();

        System.out.print("Enter password: ");
        String pass = input.nextLine();

        boolean isLoggedIn = user.equals("admin") && pass.equals("1234");

        if (!isLoggedIn) {
            System.out.println("Invalid login. Exiting...");
            return;
        }

        System.out.println("Login Successful!");

        
        CategoryService service = new CategoryServiceProxy(isLoggedIn);

        
        new UI(service);
    }
}
