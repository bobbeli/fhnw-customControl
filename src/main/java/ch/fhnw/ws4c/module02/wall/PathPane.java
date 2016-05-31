package ch.fhnw.ws4c.module02.wall;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * @author Dimitri Suter
 */
public class PathPane extends HBox {
	private Region regionA;
	private Region regionB;

	public PathPane() {
		initializeControls();
		layoutControls();
	}

	private void initializeControls() {
		regionA = new Region();
		regionA.getStyleClass().addAll("playground", "pathPlayground");
		regionB = new Region();
		regionB.getStyleClass().addAll("playground", "path2Playground");
	}

	private void layoutControls() {
		setHgrow(regionA, Priority.ALWAYS);
		getChildren().addAll(regionA);
		setHgrow(regionB, Priority.ALWAYS);
		getChildren().addAll(regionB);
	}
}
