package org.example.auth.domain;

public class Password {

    private final String encryptedPassword;

    private Password(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;

    }

    public static Password createEncriptPassword(String password) {
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("패스워드는 빈 값이 될 수 없습니다.");
        }
        return new Password(SHA256.encrypt(password));
    }

    public static Password createPassword(String password) {
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("패스워드는 빈 값이 될 수 없습니다.");
        }
        return new Password(password);
    }

    public boolean matchPassword(String password) {
        return encryptedPassword.equals(SHA256.encrypt(password));
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
