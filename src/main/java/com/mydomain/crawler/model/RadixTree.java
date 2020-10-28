package com.mydomain.crawler.model;

import com.mydomain.crawler.CrawlerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RadixTree {
	private static String domain;
	private TreeNode root;

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
			String[] parts = uri.substring(domain.length()).split("/");
			if(parts.length > 0) {
				recurse(root, parts, 0);
			}
		} else {
			// leaf node; don't even try to process
/*			Optional<TreeNode> opt = paths.stream().filter( p -> p.getPath().equals(uri)).findFirst(); // findAny runs in parallel
			if(opt.isEmpty()) {
				System.out.println("Leaf node: " + uri);
				TreeNode node = new TreeNode();
				node.setPath(uri);*/
//				paths.add(node);
//			}
		}
	}

	private void recurse(TreeNode tree, String[] nodes, int index) {
		// does this node exist?
		if(index >= nodes.length) {
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
		System.out.print("\t".repeat(level * 2));
		System.out.println("data: " + tree.getPath());
		System.out.print("\t".repeat(level * 2));
		System.out.println("children:");
		if(tree.getPaths() != null) {
			for(TreeNode node: tree.getPaths()) {
				recurseDisplay(node, level + 1);
			}
		}
	}
}
