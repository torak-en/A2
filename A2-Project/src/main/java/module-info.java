module Render {
	requires javafx.controls;
	requires javafx.fxml;


	opens Render to javafx.fxml;
	exports Render;
}