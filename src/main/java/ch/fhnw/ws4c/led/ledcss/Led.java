package eu.hansolo.fx.customcontrols.ledcss;

import com.sun.javafx.css.converters.PaintConverter;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.LongProperty;
import javafx.beans.property.LongPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Led extends Control {
    // CSS Styleable property
    private static final Color             DEFAULT_LED_COLOR = Color.RED;
    private StyleableObjectProperty<Paint> ledColor;

    // CSS pseudo classes
    private static final PseudoClass       ON_PSEUDO_CLASS   = PseudoClass.getPseudoClass("on");
    private BooleanProperty                on;

    // Properties
    private BooleanProperty                blinking;
    private BooleanProperty                frameVisible;
    private long                           lastTimerCall;
    private long                           interval = 500_000_000l;
    private AnimationTimer                 timer;


    // ******************** Constructors **************************************
    public Led() {
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


    // ******************** CSS Stylable Properties ***************************
    public final Paint getLedColor() {
        return null == ledColor ? DEFAULT_LED_COLOR : ledColor.get();
    }
    public final void setLedColor(final Paint VALUE) {
        ledColorProperty().set(VALUE);
    }
    public final StyleableObjectProperty<Paint> ledColorProperty() {
        if (null == ledColor) {
            ledColor = new StyleableObjectProperty<Paint>(DEFAULT_LED_COLOR) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.LED_COLOR; }
                @Override public Object getBean() { return Led.this; }
                @Override public String getName() { return "ledColor"; }
            };
        }
        return ledColor;
    }

    
    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new LedSkin(this);
    }

    @Override public String getUserAgentStylesheet() {
        return getClass().getResource("led.css").toExternalForm();
    }

    private static class StyleableProperties {
        private static final CssMetaData<Led, Paint> LED_COLOR =
            new CssMetaData<Led, Paint>("-led-color", PaintConverter.getInstance(), DEFAULT_LED_COLOR) {

                @Override public boolean isSettable(Led led) {
                    return null == led.ledColor || !led.ledColor.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Led led) {
                    return (StyleableProperty) led.ledColorProperty();
                }

                @Override public Color getInitialValue(Led led) {
                    return (Color) led.getLedColor();
                }
            };


        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               LED_COLOR
            );
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    @Override public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }
}
