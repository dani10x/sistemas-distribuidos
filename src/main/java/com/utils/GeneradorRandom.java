package com.utils;

import java.util.Random;

public class GeneradorRandom {

    private static final String[] PREFIXES = {
            "Al", "Bel", "Cor", "Dar", "El", "Fen", "Gal", "Hal", "Ith", "Jar", "Kel", "Lor", "Mor", "Nar", "Or", "Pel", "Quor", "Rin", "Sar", "Tar"
    };

    private static final String[] ROOTS = {
            "adr", "bor", "dan", "eth", "gal", "hil", "ith", "jor", "kal", "len", "mor", "nir", "or", "per", "quor", "rin", "sar", "tar", "var", "wen"
    };

    private static final String[] SUFFIXES = {
            "a", "an", "ar", "en", "el", "ian", "or", "on", "us", "yn"
    };

    public static String generateRandomName() {
        Random random = new Random();

        String prefix = PREFIXES[random.nextInt(PREFIXES.length)];
        String root = ROOTS[random.nextInt(ROOTS.length)];
        String suffix = SUFFIXES[random.nextInt(SUFFIXES.length)];

        return prefix + root + suffix;
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }
}
