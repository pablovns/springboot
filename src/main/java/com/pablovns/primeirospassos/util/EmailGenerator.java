package com.pablovns.primeirospassos.util;

import java.util.concurrent.ThreadLocalRandom;

public final class EmailGenerator {

    private static final String[] DOMAINS = {
            "gmail.com", "yahoo.com", "hotmail.com", "email.com", "outlook.com"
    };

    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MIN_USERNAME_LENGTH = 5;
    private static final int MAX_USERNAME_LENGTH = 10;

    private EmailGenerator() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated.");
    }

    /**
     * Gera um endereço de e-mail aleatório com nome de usuário entre 5 e 10 caracteres.
     *
     * @return String contendo o e-mail gerado.
     */
    public static String generateRandomEmail() {
        String username = generateRandomUsername();
        String domain = selectRandomDomain();
        return username + "@" + domain;
    }

    public static String generateRandomEmail(String username) {
        String domain = selectRandomDomain();
        return username + "@" + domain;
    }

    public static String generateRandomUsername() {
        int length = ThreadLocalRandom.current().nextInt(MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH + 1);
        StringBuilder username = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(ALLOWED_CHARACTERS.length());
            username.append(ALLOWED_CHARACTERS.charAt(randomIndex));
        }

        return username.toString();
    }

    private static String selectRandomDomain() {
        int index = ThreadLocalRandom.current().nextInt(DOMAINS.length);
        return DOMAINS[index];
    }
}
