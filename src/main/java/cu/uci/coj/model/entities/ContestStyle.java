/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContestStyle {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int sid;
    private String name;
    private boolean enabled;

    public ContestStyle() {
    }

    public ContestStyle(int sid, String name, boolean enabled) {
        this.sid = sid;
        this.name = name;
        this.enabled = enabled;
    }

    public ContestStyle(int sid, String name) {
        this.sid = sid;
        this.name = name;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

}
