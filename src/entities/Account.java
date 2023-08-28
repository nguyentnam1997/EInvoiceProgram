package entities;

import lombok.Data;

@Data
public class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
