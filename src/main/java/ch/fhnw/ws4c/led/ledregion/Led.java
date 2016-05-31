package eu.hansolo.fx.customcontrols.ledregion;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.LongProperty;
import javafx.beans.property.LongPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


public class Led extends Region {
    private static final double      PREFERRED_SIZE    = 16;
    private static final double      MINIMUM_SIZE      = 8;
    private static final double      MAXIMUM_SIZE      = 1024;
    private static final PseudoClass ON_PSEUDO_CLASS   = PseudoClass.getPseudoClass("on");

    // Model/Controller related
    private ObjectProperty<Color>    ledColor;
    private BooleanProperty          on;
    private BooleanProperty          blinking;
    private BooleanProperty          frameVisible;
    private long                     lastTimerCall;
    private long                     interval = 500_000_000l;
    private AnimationTimer           timer;
    // View related
    private double                   size;
    private Region                   frame;
    private Region                   led;
    private Region                   highlight;
    private InnerShadow              innerShadow;
    private DropShadow               glow;


    // ******************** Constructors **************************************
    public Led() {
        getStylesheets().add(Led.class.getResource("led.css").toExternalForm());
        getStyleClass().add("led");
                
        lastTimerCall = System.nanoTime();
        timer         = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + interval) {
                    setOn(!isOn());
                    lastTimerCall = NOW;
                }
            }
        };
        ledColor      = new SimpleObjectProperty<>(this, "ledColor", Color.RED);
        on            = new BooleanPropertyBase(false) {
            @Override protected void invalidated() { pseudoClassStateChanged(ON_PSEUDO_CLASS, get()); }
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "on"; }
        };
        blinking      = new BooleanPropertyBase() {
            @Override protected void invalidated() {
                if (get()) {
                    timer.start();
                } else {
                    timer.stop();
                    setOn(false);
                }
            }
            @Override public Object getBean() {
                return Led.this;
            }
            @Override public String getName() {
                return "blinking";
            }
        };
        frameVisible  = new SimpleBooleanProperty(Led.this, "frameVisible", true);
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getWidth(), 0) <= 0 || Double.compare(getHeight(), 0) <= 0 ||
            Double.compare(getPrefWidth(), 0) <= 0 || Double.compare(getPrefHeight(), 0) <= 0) {
            setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }
        if (Double.compare(getMinWidth(), 0) <= 0 || Double.compare(getMinHeight(), 0) <= 0) {
            setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }
        if (Double.compare(getMaxWidth(), 0) <= 0 || Double.compare(getMaxHeight(), 0) <= 0) {
            setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        frame = new Region();
        frame.getStyleClass().setAll("frame");
        frame.setOpacity(isFrameVisible() ? 1 : 0);

        led = new Region();
        led.getStyleClass().setAll("main");
        led.setStyle("-led-color: " + (getLedColor()).toString().replace("0x", "#") + ";");

        innerShadow = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.65), 8, 0d, 0d, 0d);

        glow = new DropShadow(BlurType.TWO_PASS_BOX, getLedColor(), 20, 0d, 0d, 0d);
        glow.setInput(innerShadow);

        highlight = new Region();
        highlight.getStyleClass().setAll("highlight");

        // Add all nodes
        getChildren().addAll(frame, led, highlight);
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> resize());
        heightProperty().addListener(observable -> resize());
        frameVisibleProperty().addListener(observable -> frame.setOpacity(isFrameVisible() ? 1 : 0));
        onProperty().addListener(observable -> led.setEffect(isOn() ? glow : innerShadow));
        ledColorProperty().addListener(observable -> {
            led.setStyle("-led-color: " + (getLedColor()).toString().replace("0x", "#") + ";");
            resize();
        });
    }


    // ******************** Methods *******************************************   
    public final boolean isOn() { return on.get(); }
    public final void setOn(final boolean ON) { on.set(ON); }
    public final BooleanProperty onProperty() { return on; }

    public final boolean isBlinking() { return blinking.get(); }
    public final void setBlinking(final boolean BLINKING) { blinking.set(BLINKING); }
    public final BooleanProperty blinkingProperty() { return blinking; }

    public final boolean isFrameVisible() { return frameVisible.get(); }
    public final void setFrameVisible(final boolean VISIBLE) { frameVisible.set(VISIBLE); }
    public final BooleanProperty frameVisibleProperty() { return frameVisible; }

    public final Color getLedColor() { return null == ledColor ? Color.RED : ledColor.get(); }
    public final void setLedColor(final Color LED_COLOR) { ledColor.set(LED_COLOR); }
    public final ObjectProperty<Color> ledColorProperty() { return ledColor; }


    // ******************** Resizing ******************************************
    private void resize() {
        size = getWidth() < getHeight() ? getWidth() : getHeight();
        if (size > 0) {
            if (getWidth() > getHeight()) {
                setTranslateX(0.5 * (getWidth() - size));
            } else if (getHeight() > getWidth()) {
                setTranslateY(0.5 * (getHeight() - size));
            }

            innerShadow.setRadius(0.07 * size);
            glow.setRadius(0.36 * size);
            glow.setColor(getLedColor());

            frame.setPrefSize(size, size);

            led.setPrefSize(0.72 * size, 0.72 * size);
            led.relocate(0.14 * size, 0.14 * size);
            led.setEffect(isOn() ? glow : innerShadow);

            highlight.setPrefSize(0.58 * size, 0.58 * size);
            highlight.relocate(0.21 * size, 0.21 * size);
        }
    }
}
