package dev.codebase.gcj.sample;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PasswordEncrypter {

    public static void main(String[] args) {
        
        StandardPBEStringEncryptor passwordEncryptor = new StandardPBEStringEncryptor();
        
        passwordEncryptor.setPassword(args[0]);
        String encryptedPassword = passwordEncryptor.encrypt(args[1]);
        
        System.out.println("Encrypted form of [" + args[1] + "] is >" + encryptedPassword + "<");
    }

}
