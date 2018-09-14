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
@Deprecated
//need to find a way to merge it with Problem, if possible
public class ProblemSource implements Comparable<Object> {

    private String name;
    private String author;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer idSource;

    public ProblemSource(String name, String author,Integer idSource) {
        this.name = name;
        this.author = author;
        this.idSource = idSource;

    }


    public ProblemSource() {
    }
    
    public String getFullName(){
    	
    	return (name==null?"":name) + (author==null?"":" [" + author + "]");
    }

    public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdSource() {
		return idSource;
	}


	public void setIdSource(Integer idSource) {
		this.idSource = idSource;
	}

    public int compareTo(Object o) {
        return idSource - ((ProblemSource) o).idSource;
    }

	@Override
    public String toString(){
        return this.name;
    }

}