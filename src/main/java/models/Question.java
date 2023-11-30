package models;

import java.util.List;

public class Question {
    private Long id; // obyazatelno id
    private String text;
    private List<Integer> options;
    private Integer correctAnswerIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public Integer getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(Integer correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public Question() {
    }

    public Question(Long id, String text,List<Integer> options, Integer correctAnswerIndex) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

//    public boolean checkAnswer(Integer userChoosenIndex){
//    }
}
