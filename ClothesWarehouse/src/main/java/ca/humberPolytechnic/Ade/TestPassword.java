package ca.humberPolytechnic.Ade;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPassword {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "password"; // change this if you want
        String hash = encoder.encode(password);

        System.out.println(hash);
    }
}