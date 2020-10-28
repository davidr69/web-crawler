package com.mydomain.crawler.model;

import java.util.List;

public class TreeNode {
	private String path;
	private List<TreeNode> paths;
	private Boolean visited;

	public String getPath() { return path; }
	public void setPath(String path) { this.path = path; }

	public List<TreeNode> getPaths() { return paths; }
	public void setPaths(List<TreeNode> paths) { this.paths = paths; }

	public Boolean getVisited() { return visited; }
	public void setVisited(Boolean visited) { this.visited = visited; }
}
