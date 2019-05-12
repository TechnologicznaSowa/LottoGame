package pl.technologicznasowa.service.impl;

import pl.technologicznasowa.exception.ValidationException;
import pl.technologicznasowa.model.AbstractGame;
import pl.technologicznasowa.service.Game;
import pl.technologicznasowa.service.Validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LottoImpl extends AbstractGame implements Game {

    private Validation validation;

    public LottoImpl() {
        this.validation = new ValidationImpl();
    }

    @Override
    public void play() {
        String userInput = takeUserInput();
        validation.validateUserInput(userInput);
        Set<Integer> randomNumbers =  randomMachine();
        checkNumbers(randomNumbers, userInput);
    }

    private void checkNumbers(Set<Integer> randomNumbers, String userInput) {
        Set<Integer> bullet = new HashSet<>();
        for (String s : userInput.split(",")) {
            if(randomNumbers.contains(Integer.valueOf(s))) bullet.add(Integer.valueOf(s));
        }
        System.out.println("Udało Ci się trafić: "+bullet.size()+" liczb! "+ Arrays.toString(bullet.toArray()));
    }

    private Set<Integer> randomMachine() {
        Set<Integer> randomNumbers = new HashSet<>();
        System.out.println("Wylosowane liczby...");
        for (int i = 0; i < 6; i++) {
            if (i>0) System.out.print(",");

            int randomNum = ThreadLocalRandom.current().nextInt(1, 49 + 1);
            boolean add = randomNumbers.add(randomNum);

            if (add){
                System.out.print(randomNum);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Coś poszło nie tak z opóźnieniem: "+e.getMessage());
                }

            } else {
                i--;
            }
        }
        if (randomNumbers.size() != 6) {
            throw new ValidationException("Cos poszło nie tak, nie wylosowałem 6 cyfr: "+randomNumbers.size());
        }

        System.out.println(" ");
        return randomNumbers;
    }


    private String takeUserInput() {
        System.out.println("Zaczynamy! Podaj 6 cyfr z przedziału od 1 do 49 oddzielając je przcinkiem");
        return scanner.nextLine();
    }

}
