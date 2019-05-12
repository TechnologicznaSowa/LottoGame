package pl.technologicznasowa.exception;

public class ValidationException extends RuntimeException {

    public static String BAD_FORMAT = "Zły format danych! Podaj 6 cyfr oddzielajac je przecinkiem!";
    public static String DUPLICATES_FOUND = "Podałeś duplikaty!";

    public ValidationException(String message) {
        super(message);
    }
}
