package pawnbolo.com.pawnbolo.models;

public class AuthRequest {
    private String email;
    private String password;
    private String name;
    private Long pawnStoreId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Long getPawnStoreId() {
        return pawnStoreId;
    }
}
