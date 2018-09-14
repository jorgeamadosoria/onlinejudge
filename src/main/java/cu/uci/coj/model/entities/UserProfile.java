/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Deprecated
// need to find a way to merge with User
public class UserProfile {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer uid;
    private String username = null;

	private boolean[] tagsSelected;

	public boolean[] getTagsSelected() {
		return tagsSelected;
	}

	public void setTagsSelected(boolean[] tagsSelected) {
		this.tagsSelected = tagsSelected;
	}

	// en realidad son enteros, pero van a ser usados para computaciones de
	// numeros con precision, y es mejor que sean cargados directamente como
	// doubles sin necesidad de convertir
	private List<Double> tags = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String userId) {
		this.username = userId;
	}

	public List<Double> getTags() {
		return tags;
	}

	public void setTags(List<Double> tags) {
		this.tags = tags;
	}

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
