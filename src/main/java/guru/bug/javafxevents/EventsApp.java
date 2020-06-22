package guru.bug.javafxevents;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EventsApp extends Application {
    private String lastMsg;
    private int count;

    @Override
    public void start(Stage primaryStage) {
        var root = new Pane();
        root.addEventHandler(ScrollEvent.ANY, this::logScrollEvent);
        root.addEventHandler(SwipeEvent.ANY, this::logSwipeEvent);
        root.addEventHandler(ZoomEvent.ANY, this::logGestureEvent);
        root.addEventHandler(RotateEvent.ANY, this::logGestureEvent);
        var scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void logScrollEvent(ScrollEvent event) {
        logEvent(event, event.getTouchCount());
    }

    private void logSwipeEvent(SwipeEvent event) {
        logEvent(event, event.getTouchCount());
    }

    private void logGestureEvent(GestureEvent event) {
        logEvent(event, 0);
    }

    private void logEvent(GestureEvent event, int touchCount) {
        var builder = new StringBuilder();
        builder.append(event.getEventType());
        if (touchCount != 0) {
            builder.append(" *").append(touchCount);
        }
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
        System.out.print(" [x");
        System.out.print(count);
        System.out.print("]");

        lastMsg = msg;

        event.consume();
    }

}
