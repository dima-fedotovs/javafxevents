package guru.bug.javafxevents;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.GestureEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EventsApp extends Application {
    private String lastMsg;
    private int count;

    @Override
    public void start(Stage primaryStage) throws Exception {
        var root = new Pane();
        root.addEventHandler(ScrollEvent.ANY, this::logEvent);
        root.addEventHandler(ZoomEvent.ANY, this::logEvent);
        var scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void logEvent(GestureEvent event) {
        var builder = new StringBuilder();
        builder.append(event.getEventType());
        if (event.isInertia()) builder.append(" INERTIA");
        if (event.isDirect()) builder.append(" DIRECT");
        if (event.isAltDown()) builder.append(" ALT");
        if (event.isControlDown()) builder.append(" CTRL");
        if (event.isMetaDown()) builder.append(" META");
        if (event.isShiftDown()) builder.append(" SHIFT");
        if (event.isShortcutDown()) builder.append(" SHORTCUT");

        var msg = builder.toString();
        if (msg.equals(lastMsg)) {
            count++;
            System.out.print('\r');
        } else {
            System.out.println();
            count = 1;
        }

        System.out.print(builder);
        System.out.print(' ');
        System.out.print(count);

        lastMsg = msg;

        event.consume();
    }

}
