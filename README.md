# Overview

Small reproducer for scroll/zoom/rotate gestures events bug.

Seems there is a bug in firing scrolling/zooming/rotate gestures events. 
As per documentation, if scroll-events are sourced from touchpad SCROLL_STARTED event should be generated before any SCROLL events. And after scroll is done SCROLL_FINISHED should be fired.
The same for ZOOM_STARTED/ZOOM/ZOOM_FINISHED events and for ROTATION_STARTED/ROTATE/ROTATION_FINISHED.

References:
* [ScrollEvent](https://openjfx.io/javadoc/14/javafx.graphics/javafx/scene/input/ScrollEvent.html)
* [ZoomEvent](https://openjfx.io/javadoc/14/javafx.graphics/javafx/scene/input/ZoomEvent.html)
* [RotateEvent](https://openjfx.io/javadoc/14/javafx.graphics/javafx/scene/input/RotateEvent.html)

# How To use

1. start the app by executing

    mvn exec:exec
    
2. try gestures inside opened window
3. Messages will appear in the console

# Actual behaviour

These events work differently on different devices.

## MacBook Pro - MacOS Catalina (10.15.5)
### touch pad

#### Scroll events
dragging two fingers over a touch pad:
* SCROLL_STARTED event is never fired;
* SCROLL event works as expected;
* SCROLL_FINISHED event is never fired;
* touchCount is always zero.

#### Zoom events
dragging two fingers apart on touch pad:
* ZOOM_STARTED event is fired once per application instance on first zoom gesture detection and never again;
* ZOOM event works as expected;
* ZOOM_FINISHED event is never fired.

#### Rotate events
dragging two fingers around each other on touch pad:
* ROTATION_STARTED event is fired once per application instance on first rotation gesture detection and never again;
* ROTATE event works as expected;
* ROTATION_FINISHED event is never fired.

## ASUS ZenBook - Windows 10 Pro (1909)
### touch pad

#### Scroll events
dragging two fingers over a touch pad:
* SCROLL_STARTED event is never fired;
* SCROLL event works as expected;
* SCROLL_FINISHED event is never fired;
* touchCount is always zero.

#### Zoom events
dragging two fingers apart on touch pad:
* ZOOM_STARTED is never fired;
* ZOOM event is never fired, but isntead of it SCROLL event generated with isControlDown and isShortcutDown;
* ZOOM_FINISHED event is never fired.

#### Rotate events
dragging two fingers around each other on touch pad:
* gesture not detected - no events fired

### touch screen

#### Scroll events
dragging two fingers over a touch screen:
* SCROLL_STARTED, SCROLL, SCROLL_FINISHED are working as expected. Touch count is detected correctly.

#### Zoom events
dragging two fingers apart on touch screen:
* ZOOM_STARTED, ZOOM, ZOOM_FINISHED are working as expected.

#### Rotate events
dragging two fingers around each other on touch screen:
* SCROLL_STARTED, SCROLL, SCROLL_FINISHED are working as expected

# Summary

Only correct result is observed on gestures' detection for touch screen. 
JavaFX on MacOS with touch pad is generating ZOOM_STARTED/SCROLL_STARTED only once per application instance. 
SCROLL_STARTED, ZOOM_FINISHED, ROTATION_FINISHED are never fired.

I wasn't able to find how to get SWIPE gestures detected. 

