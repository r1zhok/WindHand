package org.windhand;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Manager extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Manager.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.getIcons().add(new Image("https://genuinecoder.com/wp-content/uploads/2022/06/genuine_coder-3.png"));
        stage.setTitle("WindHand");
        stage.setScene(scene);
        stage.show();

        /*Timer timer = new Timer();
        timer.start();*/

        /*DNSServer server = new DNSServer();
        server.addSiteToBlock("http://www.java2s.com/Code/Java/Network-Protocol/Asimpleproxyserver.htm");
        server.start();

        ApplicationBlocker app = new ApplicationBlocker();
        app.addAppToBlock("notepad", 30000);
        app.addAppToBlock("chrome", 30000);
        app.startToBlock();*/

        /*SiteBlock block = new SiteBlock();
        //block.addSiteToBlock("www.java2s.com");
        block.removeSite("www.java2s.com");
        block.blockingSite();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}