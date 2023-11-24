module com.example.a2test {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.example.a2test to javafx.fxml;
	exports com.example.a2test;
}