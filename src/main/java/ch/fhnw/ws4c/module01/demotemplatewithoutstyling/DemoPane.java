/*
 *
 * Copyright (c) 2015 by FHNW
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ch.fhnw.ws4c.module01.demotemplatewithoutstyling;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 * @author Dieter Holz
 *
 * Stack Pane ist vergleichbar mit swing Layout Manager (z.b Grid)
 */
public class DemoPane extends StackPane {
	/**
	 * Deklaration aller Elemente im UI
	 */
	private Button button;

	public DemoPane() {
		initializeControls();
		layoutControls();
	}

	/**
	 * Initialisiere Alle Controlls, instazierung und konfigurierung aller Buttons etc
	 */
	private void initializeControls() {
		button = new Button("Hello World");
	}

	/**
	 * Zusammensetzen des Layouts, einfügen aller Elemnet auf das StackPane element
	 */
	private void layoutControls() {
		getChildren().add(button);
	}
}
