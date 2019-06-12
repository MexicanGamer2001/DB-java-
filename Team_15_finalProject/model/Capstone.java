/**
 * File: capstone.java
 * Team: 15
 * Members: Joseph Farrell, Eleazar Contreras, LinJian Chen, Feng Lin
 * Last Updated: 3 April, 2018
 *
 * Capstone model object
 */

package model;


import java.util.*;

public class Capstone {

    private String capstoneid;
    private String title;
    private String desc;
    private float plagiarismScore;
    private String grade;
    private String type;
    private String defenseDate;

    public Capstone(String _capstoneid, String _title, String _desc, String _type, String _grade, String _defenseDate) {
        this.capstoneid = _capstoneid;
        this.title = _title;
        this.desc = _desc;
        this.type = _type;
        this.grade = _grade;
        this.defenseDate = _defenseDate;
    }

    /**
     * Accessor method for capstoneid
     */
    public String getCapstoneid() {
        return capstoneid;
    }

    /**
     * Accessor method for title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mutator method for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor method for desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Mutator method for desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Accessor method for plagiarismScore
     */
    public float getPlagiarismScore() {
        return plagiarismScore;
    }

    /**
     * Mutator method for plagiarismScore
     */
    public void setPlagiarismScore(float plagiarismScore) {
        this.plagiarismScore = plagiarismScore;
    }

    /**
     * Accessor method for grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Mutator method for grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Accessor method for type
     */
    public String getType() {
        return type;
    }

    /**
     * Mutator method for type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Accessor method for defenseDate
     */
    public String getDefenseDate() {
        return defenseDate;
    }

    /**
     * Mutator method for defenseDate
     */
    public void setDefenseDate(String defenseDate) {
        this.defenseDate = defenseDate;
    }
}