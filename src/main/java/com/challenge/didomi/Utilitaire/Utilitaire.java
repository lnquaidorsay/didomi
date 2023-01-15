package com.challenge.didomi.Utilitaire;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utilitaire {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789";


    public String generateStringId(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public String randomStringId() {
        StringBuilder id = new StringBuilder();

        int[] parts = {8, 4, 4, 4, 12};
        boolean isMiddle = true;
        for (int i = 0; i < parts.length; i++) {
            for (int j = 0; j < parts[i]; j++) {
                id.append(RANDOM.nextInt(9) + 1);
            }
            if (isMiddle && i!=parts.length-1) {
                id.append("-");
            }
            isMiddle = i!=parts.length-1;
        }

        return id.toString();
    }

    public String randomStringId2() {
        StringBuilder id = new StringBuilder();

        int[] parts = {8, 4, 4, 4, 12};
        for (int i = 0; i < parts.length; i++) {
            for (int j = 0; j < parts[i]; j++) {
                id.append(RANDOM.nextInt(9) + 1);
            }
            if (i!=parts.length-1) {
                id.append("-");
            }
        }
        return id.toString();
    }
}
