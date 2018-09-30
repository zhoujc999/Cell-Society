package View;

import javafx.scene.control.ChoiceDialog;

import java.util.*;

/*
    @author: xp19
 */

public class AppLanguageManager {

    private static final String[] langChoices = {"English", "Simplified Chinese"};
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String ENGLISH = "English";
    private static final String CHINESE = "Simplified Chinese";
    private static final String CHINESE_LOCALE = "cn";
    private static Locale currentLocale;

    public AppLanguageManager(){
        displayLanguageChoosingDialog();
    }

    private void displayLanguageChoosingDialog(){
        ChoiceDialog<String> dialog = new ChoiceDialog<>(DEFAULT_LANGUAGE, Arrays.asList(langChoices));
        dialog.setTitle("Welcome to Cell Society");
        dialog.setHeaderText("Language Setting");
        dialog.setContentText("Choose your preferred language:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresentOrElse(letter -> setCurrentLocale(letter), () -> setCurrentLocale(DEFAULT_LANGUAGE));
    }


    private void setCurrentLocale(String language){
        System.out.println(language);
        switch (language){
            case ENGLISH:
                currentLocale = Locale.getDefault();
                break;
            case CHINESE:
                currentLocale = new Locale(CHINESE_LOCALE);
                break;
            default:
                currentLocale = Locale.getDefault();
        }
    }

    public static Locale getCurrentLocale(){
        return currentLocale;
    }

}
