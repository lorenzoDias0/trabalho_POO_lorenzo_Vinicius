package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class TelaInicial {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
