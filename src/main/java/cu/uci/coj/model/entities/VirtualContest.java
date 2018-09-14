/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */

package cu.uci.coj.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VirtualContest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer vid;
    private Integer cid;
    private String  username;
    private String  ctime;
    private String  itime;
    private String  etime;
    private String  even;
    private boolean past;
    private boolean running;
    private boolean coming;
    private Integer father;
    private boolean ispublic;

    public Integer getFather() {
        return father;
    }

    public void setFather(Integer father) {
        this.father = father;
    }

    public boolean isIspublic() {
        return ispublic;
    }

    public void setIspublic(boolean ispublic) {
        this.ispublic = ispublic;
    }

    public VirtualContest() {
    }

    public VirtualContest(Integer vid, Integer cid, String username, String ctime, String itime, String etime) {
        this.vid = vid;
        this.cid = cid;
        this.username = username;
        this.ctime = ctime;
        this.itime = itime;
        this.etime = etime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getItime() {
        return itime;
    }

    public void setItime(String itime) {
        this.itime = itime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

    public boolean isComing() {
        return coming;
    }

    public void setComing(boolean coming) {
        this.coming = coming;
    }

    public boolean isPast() {
        return past;
    }

    public void setPast(boolean past) {
        this.past = past;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
