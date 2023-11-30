package models;

// metody v samom nizu
public class User {
    private Long id; // u kazdogo usera dolzen bit kluch (aydishka) dlya bazi dannyh, i dlya aydishek lucshe Long on dlinniy
    private String username;
    private String password;
    private int accessLevel; // pishu class s bolshoy bukvi chtobi ya mog delat usera i stavit etot parametr potom
    private int score;

    public User(Long id, String username, String password, int accessLevel, int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User() {
    }
}
