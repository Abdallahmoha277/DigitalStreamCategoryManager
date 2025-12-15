package ui.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import services.CategoryService;
import proxy.CategoryServiceProxy;

public class LoginFX extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(12);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Admin Login");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");
        loginBtn.setPrefWidth(200);

        Label message = new Label();

        loginBtn.setOnAction(e -> {
            if (username.getText().equals("admin")
                    && password.getText().equals("1234")) {

                CategoryService service =
                        new CategoryServiceProxy(true);

                new DashboardFX(service);
                stage.close();

            } else {
                message.setText("Invalid credentials");
                message.setStyle("-fx-text-fill: red;");
            }
        });

        root.getChildren().addAll(
                title, username, password, loginBtn, message
        );

        stage.setScene(new Scene(root, 320, 280));
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
