package pl.technologicznasowa.model;

import pl.technologicznasowa.service.Game;

import java.util.Scanner;

public abstract class AbstractGame implements Game {

    protected Scanner scanner;

    protected AbstractGame() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean askForAnotherRound() {
        System.out.println("Czy chcesz zagrać jeszcze jedną rundę? T/N");
        while (true) {
            String userInput = scanner.nextLine();
            switch (userInput.trim().toUpperCase()) {
                case "T":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Nie rozpoznano wprowadzonej wartości (wpisz T lub N)");
                    System.out.println("Czy chcesz zagrać jeszcze jedną rundę? T/N");
            }
        }
    }
}
