package test;

public class Security {
    private static final String PASSWORD = "aaronbhasker"; // Replace with your actual password

    public static boolean authenticate(String inputPassword) {
        return PASSWORD.equals(inputPassword);
    }
}