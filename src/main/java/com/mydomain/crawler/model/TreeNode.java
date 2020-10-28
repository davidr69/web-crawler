package com.mydomain.crawler.model;

import java.util.List;

public class TreeNode {
	private String path;
	private List<TreeNode> paths;
	private boolean visited = false;
	private boolean leafnode = false;

	public String getPath() { return path; }
	public void setPath(String path) { this.path = path; }

	public List<TreeNode> getPaths() { return paths; }
	public void setPaths(List<TreeNode> paths) { this.paths = paths; }

	public boolean isVisited() { return visited; }
	public void setVisited(boolean visited) { this.visited = visited; }

	public boolean isLeafnode() { return leafnode; }
	public void setLeafnode(boolean leafnode) { this.leafnode = leafnode; }
}
