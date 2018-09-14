package cu.uci.coj.model;

import org.apache.commons.io.FilenameUtils;

import cu.uci.coj.model.entities.BaseEntity;

public class SharedFile extends BaseEntity{

	private Integer fid;
	private String name;
	private String path;
	private Long size;

	public Long getSize() {return size/1024;}
	public void setSize(Long size) {this.size = size;	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getExtension(){
		return FilenameUtils.getExtension(name);
	}
}
