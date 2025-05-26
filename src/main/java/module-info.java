module com.projet_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires transitive javafx.graphics;

    exports com.projet_final;
    exports com.projet_final.model;
    exports com.projet_final.service;
    exports com.projet_final.util;

    opens com.projet_final to javafx.fxml;
    opens com.projet_final.model ;
    opens com.projet_final.service to org.junit.platform.commons;
    opens com.projet_final.util;

}
