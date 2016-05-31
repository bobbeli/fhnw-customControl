package ch.fhnw.ws4c.led.ledcanvas;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;


/**
 * User: hansolo
 * Date: 11.04.14
 * Time: 07:43
 */
public class Led extends Canvas {
    private static final double   PREFERRED_SIZE = 16;

    private ObjectProperty<Color> ledColor;
    private BooleanProperty       on;
    private BooleanProperty       blinking;
    private BooleanProperty       frameVisible;
    private InnerShadow           ledOnShadow;
    private InnerShadow           ledOffShadow;
    private LinearGradient        frameGradient;
    private LinearGradient        ledOnGradient;
    private LinearGradient        ledOffGradient;
    private RadialGradient        highlightGradient;
    private long                  lastTimerCall;
    private long                  interval = 500_000_000l;
    private AnimationTimer        timer;

    private GraphicsContext      ctx;
    private double               size;
    private double               offsetX;
    private double               offsetY;


    // ******************** Constructors **************************************
    public Led() {
        lastTimerCall = System.nanoTime();
        timer         = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + interval) {
                    setOn(!isOn());
                    lastTimerCall = NOW;
                }
            }
        };
        offsetX       = 0;
        offsetY       = 0;
        ledColor      = new SimpleObjectProperty<>(this, "ledColor", Color.RED);
        on            = new SimpleBooleanProperty(Led.this, "on", false);
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
        if (Double.compare(getWidth(), 0) <= 0 || Double.compare(getHeight(), 0) <= 0) {
            setWidth(PREFERRED_SIZE);
            setHeight(PREFERRED_SIZE);
        }        
    }

    private void initGraphics() {        
        ctx    = getGraphicsContext2D();        
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> recalc());
        heightProperty().addListener(observable -> recalc());
        frameVisibleProperty().addListener(observable -> draw());
        onProperty().addListener(observable -> draw());
        ledColorProperty().addListener(observable -> recalc());
        parentProperty().addListener(observable -> {
            if (null == getParent()) return;
            widthProperty().unbind();
            heightProperty().unbind();
            if (getParent() instanceof Pane) {
                widthProperty().bind(((Pane) getParent()).widthProperty());
                heightProperty().bind(((Pane) getParent()).heightProperty());
            }
        });
    }


    // ******************** Methods *******************************************

    @Override public boolean isResizable() {
        return true;
    }

    @Override public double prefWidth(double height) {
        return getWidth();
    }
    @Override public double prefHeight(double width) {
        return getHeight();
    }

    public void setPrefSize(final double WIDTH, final double HEIGHT) {
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

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


    // ******************** Resize/Redraw *************************************
    private void recalc() {
        double width  = getWidth();
        double height = getHeight();
        if (width <= 0 || height <= 0) return;

        size    = width < height ? width : height;
        offsetX = 0;
        offsetY = 0;
        if (width > height) {
            offsetX = 0.5 * (width - size);
        } else if (height > width) {
            offsetY = 0.5 * (height - size);
        }

        ledOffShadow = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.65), 0.07 * size, 0, 0, 0);

        ledOnShadow  = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.65), 0.07 * size, 0, 0, 0);
        ledOnShadow.setInput(new DropShadow(BlurType.TWO_PASS_BOX, ledColor.get(), 0.36 * size, 0, 0, 0));

        frameGradient = new LinearGradient(offsetX + 0.14 * size, offsetY + 0.14 * size,
                                           offsetX + 0.84 * size, offsetY + 0.84 * size,
                                           false, CycleMethod.NO_CYCLE,
                                           new Stop(0.0, Color.rgb(20, 20, 20, 0.65)),
                                           new Stop(0.15, Color.rgb(20, 20, 20, 0.65)),
                                           new Stop(0.26, Color.rgb(41, 41, 41, 0.65)),
                                           new Stop(0.26, Color.rgb(41, 41, 41, 0.64)),
                                           new Stop(0.85, Color.rgb(200, 200, 200, 0.41)),
                                           new Stop(1.0, Color.rgb(200, 200, 200, 0.35)));

        ledOnGradient = new LinearGradient(offsetX + 0.25 * size, offsetY + 0.25 * size,
                                           offsetX + 0.74 * size, offsetY + 0.74 * size,
                                           false, CycleMethod.NO_CYCLE,
                                           new Stop(0.0, ledColor.get().deriveColor(0d, 1d, 0.77, 1d)),
                                           new Stop(0.49, ledColor.get().deriveColor(0d, 1d, 0.5, 1d)),
                                           new Stop(1.0, ledColor.get()));

        ledOffGradient = new LinearGradient(offsetX + 0.25 * size, offsetY + 0.25 * size,
                                            offsetX + 0.74 * size, offsetY + 0.74 * size,
                                            false, CycleMethod.NO_CYCLE,
                                            new Stop(0.0, ledColor.get().deriveColor(0d, 1d, 0.20, 1d)),
                                            new Stop(0.49, ledColor.get().deriveColor(0d, 1d, 0.13, 1d)),
                                            new Stop(1.0, ledColor.get().deriveColor(0d, 1d, 0.2, 1d)));

        highlightGradient = new RadialGradient(0, 0,
                                               offsetX + 0.3 * size, offsetY + 0.3 * size,
                                               0.29 * size,
                                               false, CycleMethod.NO_CYCLE,
                                               new Stop(0.0, Color.WHITE),
                                               new Stop(1.0, Color.TRANSPARENT));
        draw();
    }

    private void draw() {        
        ctx.clearRect(0, 0, getWidth(), getHeight());

        if (isFrameVisible()) {
            ctx.setFill(frameGradient);
            ctx.fillOval(offsetX, offsetY, size, size);
        }

        ctx.save();
        if (isOn()) {
            ctx.setEffect(ledOnShadow);
            ctx.setFill(ledOnGradient);
        } else {
            ctx.setEffect(ledOffShadow);
            ctx.setFill(ledOffGradient);
        }
        ctx.fillOval(offsetX + 0.14 * size, offsetY + 0.14 * size, 0.72 * size, 0.72 * size);
        ctx.restore();

        ctx.setFill(highlightGradient);
        ctx.fillOval(offsetX + 0.21 * size, offsetY + 0.21 * size, 0.58 * size, 0.58 * size);
    }
}
