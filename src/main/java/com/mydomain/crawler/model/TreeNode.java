package com.mydomain.crawler.model;

import java.util.List;

public class TreeNode {
	private String path;
	private List<TreeNode> paths;
	private boolean leafnode = false;
	/*
		"leaf node" is a misnomer. Let's assume that both of these are distinct, valid URI's:

		https://mydomain.com/foobar/
		https://mydomain.com/foobar/archives/

		"/foobar/" should not be viewed as a "pass-through" to deeper levels; it is a valid destination.
		It is possible to write controllers that have "intermediate" paths that do not actually exist. For example:

		@Controller
		@RequestMapping(path = "/car")
		public class CarController {
			@RequestMapping(path = "/mazda")
			...
			@RequestMapping(path = "/toyota")
		}

		In this case, there is no actual "/car" endpoint; it is used for logical grouping. In this case, "/car"
		would not be a "leaf node".
	 */

	public String getPath() { return path; }
	public void setPath(String path) { this.path = path; }

	public List<TreeNode> getPaths() { return paths; }
	public void setPaths(List<TreeNode> paths) { this.paths = paths; }

	public boolean isLeafnode() { return leafnode; }
	public void setLeafnode(boolean leafnode) { this.leafnode = leafnode; }
}
