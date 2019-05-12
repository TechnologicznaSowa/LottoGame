package pl.technologicznasowa;

import pl.technologicznasowa.exception.ValidationException;
import pl.technologicznasowa.service.Game;
import pl.technologicznasowa.service.impl.LottoImpl;

public class Main {
    public static void main(String[] args) {
        Game game = new LottoImpl();

        boolean doYouWannaPlay = true;
        while(doYouWannaPlay) {
            try {
                game.play();
            } catch (ValidationException e) {
                System.out.println("Coś poszło nie tak: "+e.getMessage());
            } finally {
                doYouWannaPlay = game.askForAnotherRound();
            }
        }
    }
}
