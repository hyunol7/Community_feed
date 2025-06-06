package org.example.auth.domain;

public class UserAuth {

    private final Email email;
    private final Password password;
    private final UserRole userRole;
    private Long userId;

    public UserAuth(String email, String password, String userRole) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncriptPassword(password);
        this.userRole = UserRole.valueOf(userRole);
    }

    public UserAuth(String email, String password, String userRole, Long userId) {
        this.email = Email.createEmail(email);
        this.password = Password.createPassword(password);
        this.userRole = UserRole.valueOf(userRole);
        this.userId = userId;
    }

    public String getEmail() {
        return email.getEmailText();
    }

    public String getPassword() {
        return password.getEncryptedPassword();
    }

    public String getUserRole() {
        return userRole.name();
    }

    public Long getUserId() {
        return userId;
    }

    public boolean matchPassword(String password) {
        return this.password.matchPassword(password);
    }
}
