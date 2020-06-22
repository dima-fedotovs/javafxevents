# javafxevents

Small reproducer for scroll/zoom/rotate gestures events bug.

Seems there is a bug in firing scrolling/zooming/rotate gestures events. 
As per documentation, if scroll-events are sourced from touchpad SCROLL_STARTED event should be generated before any SCROLL events. And after scroll is done SCROLL_FINISHED should be fired.
The same for ZOOM_STARTED/ZOOM/ZOOM_FINISHED events and for ROTATION_STARTED/ROTATE/ROTATION_FINISHED.

References:
*[ScrollEvent](https://openjfx.io/javadoc/14/javafx.graphics/javafx/scene/input/ScrollEvent.html)
*[ZoomEvent](https://openjfx.io/javadoc/14/javafx.graphics/javafx/scene/input/ZoomEvent.html)
*[RotateEvent](https://openjfx.io/javadoc/14/javafx.graphics/javafx/scene/input/RotateEvent.html)

These events work differently on different devices.

## MacBook Pro - MacOS Catalina (10.15.5) - touch pad

Scroll events - dragging two fingers over a touch pad:
* SCROLL_STARTED event is never fired;
* SCROLL event works as expected;
* SCROLL_FINISHED event is never fired;
* touchCount is always zero.

Zoom events - dragging two fingers apart on touch pad:
* ZOOM_STARTED event is fired once per application instance on first zoom gesture detection and never again;
* ZOOM event works as expected;
* ZOOM_FINISHED event is never fired.

Rotate events - dragging two fingers around each other on touch pad:
* ROTATION_STARTED event is fired once per application instance on first rotation gesture detection and never again;
* ROTATE event works as expected;
* ROTATION_FINISHED event is never fired.




