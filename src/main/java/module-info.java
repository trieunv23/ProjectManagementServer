module com.gui.servermanagerwork {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires mysql.connector.j;
    requires java.sql;
    requires java.mail;

    opens com.gui.projectmanagementserver to javafx.fxml;
    opens com.gui.projectmanagementserver.entity to com.google.gson;
}