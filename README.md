# javafxevents

Small reproducer for scroll/zoom bug.

Seems there is a bug for scrolling/zooming gestures events. 
As per documentation, if scroll-events are sourced from touchpad SCROLL_STARTED event should be generated before any SCROLL events. And after scroll is done SCROLL_FINISHED should be fired.
The same for ZOOM_STARTED/ZOOM/ZOOM_FINISHED events.

See [ScrollEvent](https://openjfx.io/javadoc/14/javafx.graphics/javafx/scene/input/ScrollEvent.html) and [ZoomEvent](https://openjfx.io/javadoc/14/javafx.graphics/javafx/scene/input/ZoomEvent.html) for reference.

But these events works differently on different devices.
