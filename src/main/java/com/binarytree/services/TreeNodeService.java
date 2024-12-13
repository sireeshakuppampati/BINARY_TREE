package com.binarytree.services;

import com.binarytree.models.TreeNode;
import com.binarytree.repositories.TreeNodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeNodeService {
    private static final Logger logger = LoggerFactory.getLogger(TreeNodeService.class);

    private final TreeNodeRepository treeNodeRepository;

    @Autowired
    public TreeNodeService(TreeNodeRepository treeNodeRepository) {
        this.treeNodeRepository = treeNodeRepository;
    }

    public TreeNode createTreeNodeWithParent(int value, Integer parentId) {
        TreeNode parent = null;

        // Fetch the parent if the parentId is provided
        if (parentId != null) {
            parent = treeNodeRepository.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Parent TreeNode with ID " + parentId + " not found"));

            // Check where to insert the new node (left or right)
            if (value < parent.getValue()) {
                if (parent.getLeft() != null) {
                    throw new RuntimeException("Left child already exists for parent with ID " + parentId);
                }
                TreeNode newNode = new TreeNode(value, parent);
                parent.setLeft(newNode);
                treeNodeRepository.save(parent);
                return treeNodeRepository.save(newNode);
            } else if (value > parent.getValue()) {
                if (parent.getRight() != null) {
                    throw new RuntimeException("Right child already exists for parent with ID " + parentId);
                }
                TreeNode newNode = new TreeNode(value, parent);
                parent.setRight(newNode);
                treeNodeRepository.save(parent);
                return treeNodeRepository.save(newNode);
            } else {
                throw new RuntimeException("Duplicate value not allowed in the binary search tree.");
            }
        }


        // If parentId is null, create a root node
        return createTreeNode(value, null);
    }
    public TreeNode getTreeNodeById(int id) {
        logger.info("Fetching TreeNode with ID: {}", id);
        return treeNodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TreeNode with ID " + id + " not found"));
    }

    public TreeNode createTreeNode(int value, TreeNode parent) {
        TreeNode newNode = new TreeNode();
        newNode.setValue(value);
        newNode.setParent(parent);
        return treeNodeRepository.save(newNode);
    }


    public List<TreeNode> getAllTreeNodes() {
        return treeNodeRepository.findAll();
    }

    public TreeNode updateTreeNode(int id, int newValue) {
        // Fetch the node to update
        TreeNode node = treeNodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TreeNode with ID " + id + " not found"));

        // Update its value
        node.setValue(newValue);

        // Save and return the updated node
        return treeNodeRepository.save(node);
    }


    public void deleteTreeNode(int id) {
        logger.info("Attempting to delete TreeNode with ID: {}", id);

        // Find the TreeNode by ID
        TreeNode node = treeNodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TreeNode with ID " + id + " does not exist"));

        // Delete the node
        treeNodeRepository.delete(node);

        logger.info("Deleted TreeNode with ID: {}", id);
    }

}

