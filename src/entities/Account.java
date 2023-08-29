package entities;

import lombok.Data;

@Data
public class Account {
    private String username;
    private String password;
    private String email;

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Account() {

    }
}
