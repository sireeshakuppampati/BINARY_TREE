package com.binarytree.controllers;

import com.binarytree.models.TreeNode;
import com.binarytree.services.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tree_nodes")
public class TreeNodeController {

    private final TreeNodeService treeNodeService;

    @Autowired
    public TreeNodeController(TreeNodeService treeNodeService) {
        this.treeNodeService = treeNodeService;
    }

    // Create a TreeNode (Root or Child)
    @PostMapping
    public ResponseEntity<TreeNode> createTreeNode(@RequestBody Map<String, Object> payload) {
        try {
            int value = (int) payload.get("value");
            Integer parentId = (Integer) payload.get("parentId");

            // Use the service method to create the node
            TreeNode newNode = treeNodeService.createTreeNodeWithParent(value, parentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newNode);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreeNode> updateTreeNode(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        try {
            int value = (int) payload.get("value");
            TreeNode updatedNode = treeNodeService.updateTreeNode(id, value);
            return ResponseEntity.ok(updatedNode);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get TreeNode by ID
    @GetMapping("/{id}")
    public ResponseEntity<TreeNode> getTreeNodeById(@PathVariable int id) {
        try {
            TreeNode treeNode = treeNodeService.getTreeNodeById(id);
            return ResponseEntity.ok(treeNode);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get all TreeNodes
    @GetMapping
    public ResponseEntity<List<TreeNode>> getAllTreeNodes() {
        try {
            List<TreeNode> allTreeNodes = treeNodeService.getAllTreeNodes();
            if (allTreeNodes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(allTreeNodes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete a TreeNode by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTreeNode(@PathVariable int id) {
        try {
            treeNodeService.deleteTreeNode(id);
            return ResponseEntity.ok("TreeNode deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TreeNode with ID " + id + " not found.");
        }
    }

}


