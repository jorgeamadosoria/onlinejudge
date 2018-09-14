/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model.entities;

import javax.persistence.Entity;

import cu.uci.coj.controller.interfaces.CommonScoreboardInterface;

@Entity
public class Country extends BaseEntity implements CommonScoreboardInterface{

    private String name;
    private String zip;
    private boolean enabled;
    private String even;
    private int users;
    private int acc;
    private int institutions;
    private double points;
    private int rank;
    private boolean hasmedal;
    private String medal;
    private String website;
    private String zip_two;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country() {
    }

    public Country(int id, String name, String zip) {
        this.id = id;
        this.name = name;
        this.zip = zip;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getZip_two() {
        return zip_two;
    }

    public void setZip_two(String zip_two) {
        this.zip_two = zip_two;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public boolean isHasmedal() {
        return hasmedal;
    }

    public void setHasmedal(boolean hasmedal) {
        this.hasmedal = hasmedal;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getInstitutions() {
        return institutions;
    }

    public void setInstitutions(int institutions) {
        this.institutions = institutions;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

       
    
}
