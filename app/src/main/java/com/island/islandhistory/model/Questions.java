package com.island.islandhistory.model;

public class Questions {
    public String questionText;
    public String optA;
    public String optB;
    public String optC;
    public String optD;
    public int id;
    public String correcOpt;

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getOptD() {
        return optD;
    }

    public void setCorrecOpt(String correcOpt) {
        this.correcOpt = correcOpt;
    }

    public String getCorrecOpt() {
        return correcOpt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}