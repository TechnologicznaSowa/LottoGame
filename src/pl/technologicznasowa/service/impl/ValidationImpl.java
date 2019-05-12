package pl.technologicznasowa.service.impl;

import pl.technologicznasowa.exception.ValidationException;
import pl.technologicznasowa.service.Validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static pl.technologicznasowa.exception.ValidationException.BAD_FORMAT;
import static pl.technologicznasowa.exception.ValidationException.DUPLICATES_FOUND;

public class ValidationImpl implements Validation {

    @Override
    public void validateUserInput(String data) {
        checkForDataFormat(data);
        checkForDuplicates(data);
    }

    private void checkForDataFormat(String data) {
        String regexPattern = "(([1-9]|[1-4][0-9]),){5}([1-9]|[1-4][1-9])";
        if(!data.matches(regexPattern)) throw new ValidationException(BAD_FORMAT);
    }

    private void checkForDuplicates(String data) {

        Set<String> foundDuplicates = new HashSet<>();
        String[] splitArray = data.split(",");
        for (int i = 0; i < splitArray.length-1 ; i++) {
            for (int j = 0; j < splitArray.length-1; j++) {
                if(i!=j && splitArray[i].equalsIgnoreCase(splitArray[j])) foundDuplicates.add(splitArray[i]);
            }
        }
        if(!foundDuplicates.isEmpty())throw new ValidationException(DUPLICATES_FOUND+ Arrays.toString(foundDuplicates.toArray()));


    }

}
