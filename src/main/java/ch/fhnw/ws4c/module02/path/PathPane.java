package ch.fhnw.ws4c.module02.path;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * @author Dieter Holz
 */
public class PathPane extends HBox {
	private Region regionA;

	public PathPane() {
		initializeControls();
		layoutControls();
	}

	private void initializeControls() {
		regionA = new Region();
		regionA.getStyleClass().addAll("playground", "pathPlayground");
	}

	private void layoutControls() {
		setHgrow(regionA, Priority.ALWAYS);
		getChildren().addAll(regionA);
	}
}
