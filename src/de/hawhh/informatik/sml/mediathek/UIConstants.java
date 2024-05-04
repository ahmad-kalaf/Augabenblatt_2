package de.hawhh.informatik.sml.mediathek;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Konstanten fuer die Benutzungsoberflaeche
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2024
 */
public interface UIConstants
{

    static final Color BACKGROUND_COLOR = Color.rgb(222, 231, 232);

    static final Color BUTTON_COLOR = Color.rgb(200, 200, 200);

    static final Font BUTTON_FONT = Font.font("Tahoma", FontWeight.BOLD, 14);

    static final Color BUTTON_SELECTED_COLOR = Color.rgb(200, 200, 255);

    static final Font HEADER_FONT = Font.font("Tahoma", FontWeight.BOLD, 14);

    static final Font TEXT_FONT = Font.font("Tahoma", FontWeight.NORMAL, 14);

    static final Font LABEL_TEXT_FONT = Font.font("Tahoma", FontWeight.BOLD, 14);

    static final Color TITLE_COLOR = Color.rgb(56, 56, 255);

    static final Font TITLE_FONT = Font.font("Tahoma", FontWeight.BOLD, 28);
}
