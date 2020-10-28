package com.mydomain.crawler.model;

import com.mydomain.crawler.CrawlerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RadixTree {
	private final String domain;
	private final TreeNode root;

	public RadixTree(String uri) {
		domain = uri;
		root = new TreeNode();
	}

	public void put(String uri) throws CrawlerException {
		if (uri == null) {
			throw new CrawlerException("URI cannot be null");
		}
		if(uri.equals(domain)) {
			return;		// don't waste time
		}
		if(uri.startsWith(domain)) { // not an external link
			String params = uri.substring(domain.length());
			String[] parts = params.split("/");
			if(parts.length > 0) {
				recurse(root, parts, 0);
			}
		} else {
			if(root.getPaths() == null) {
				root.setPaths(new ArrayList<>());
			}
			// external node; don't even try to process
			Optional<TreeNode> opt = root.getPaths().stream().filter( p -> p.getPath().equals(uri)).findFirst();
			if(opt.isEmpty()) { // distinct; store
				TreeNode node = new TreeNode();
				node.setPath(uri);
				root.getPaths().add(node);
			}
		}
	}

	private void recurse(TreeNode tree, String[] nodes, int index) {
		// does this node exist?
		if(index >= nodes.length) {
			tree.setLeafnode(true);	// mark as leaf node
			return;	// end recursion
		}

		String path = nodes[index];
		if(tree.getPaths() != null) {
			Optional<TreeNode> node = tree.getPaths().stream().filter(p -> p.getPath().equals(path)).findAny();
			if(node.isEmpty()) { // not a duplicate; can add
				TreeNode newNode = new TreeNode();
				newNode.setPath(path);
				tree.getPaths().add(newNode);
				recurse(newNode, nodes, index + 1);
			} else {	// navigate down
				recurse(node.get(), nodes, index + 1);
			}
		} else {
			// just add
			TreeNode newNode = new TreeNode();
			newNode.setPath(path);
			tree.setPaths(new ArrayList<>());
			tree.getPaths().add(newNode);
			recurse(newNode, nodes, index + 1);
		}
	}

	public void display() {
		recurseDisplay(root, 0);
	}

	private void recurseDisplay(TreeNode tree, int level) {
		System.out.printf("%s %s%n", "\t".repeat(level * 2), tree.getPath() == null ? "root" : tree.getPath());
		if(tree.getPaths() != null) {
			for(TreeNode node: tree.getPaths()) {
				recurseDisplay(node, level + 1);
			}
		}
	}

	// returns list of URL's
	public List<String> normalize() {
		List<String> normal = new ArrayList<>();
		String crumbs = domain.endsWith("/") ? domain.substring(0, domain.length() - 1) : domain;
		recurseBuild(crumbs, normal, root);
		return normal;
	}

	private void recurseBuild(String crumbs, List<String> build, TreeNode tree) {
		String current = tree.getPath() == null ? crumbs : crumbs + "/" + tree.getPath();
		if(tree.isLeafnode()) {
			build.add(current);
		}
		if(tree.getPaths() != null) { // even "leaf" nodes can have children
			for(TreeNode node: tree.getPaths()) {
				recurseBuild(current, build, node);
			}
		}
	}
}
