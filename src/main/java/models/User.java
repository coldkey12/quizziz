package models;

import java.util.ArrayList;

// metody v samom nizu
public class User {
    private Long id; // u kazdogo usera dolzen bit kluch (aydishka) dlya bazi dannyh, i dlya aydishek lucshe Long on dlinniy
    private String name;
    private Integer accessLevel; // pishu class s bolshoy bukvi chtobi ya mog delat usera i stavit etot parametr potom
    private Integer score;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    public User() {
    }

    public User(Long id, String name, Integer accessLevel, Integer scores) {
        this.id = id;
        this.name = name;
        this.accessLevel = accessLevel;
        this.score = 0;
    }

    public void updateScore(double points){

    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
