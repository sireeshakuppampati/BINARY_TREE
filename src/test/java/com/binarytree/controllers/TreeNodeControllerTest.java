package com.binarytree.controllers;

import com.binarytree.models.TreeNode;
import com.binarytree.repositories.TreeNodeRepository;
import com.binarytree.services.TreeNodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TreeNodeServiceTest {

    @Mock
    private TreeNodeRepository treeNodeRepository;

    @InjectMocks
    private TreeNodeService treeNodeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTreeNodeWithParent() {
        // Arrange
        TreeNode parentNode = new TreeNode();
        parentNode.setId(1);
        parentNode.setValue(10);

        TreeNode newNode = new TreeNode();
        newNode.setId(2);
        newNode.setValue(15);
        newNode.setParent(parentNode);

        when(treeNodeRepository.save(any(TreeNode.class))).thenReturn(newNode);

        // Act
        TreeNode result = treeNodeService.createTreeNode(15, parentNode);

        // Assert
        assertNotNull(result);
        assertEquals(15, result.getValue());
        assertEquals(10, result.getParent().getValue());
        verify(treeNodeRepository, times(1)).save(any(TreeNode.class));
    }

    @Test
    void testCreateTreeNodeWithoutParent() {
        // Arrange
        TreeNode newNode = new TreeNode();
        newNode.setId(3);
        newNode.setValue(20);

        when(treeNodeRepository.save(any(TreeNode.class))).thenReturn(newNode);

        // Act
        TreeNode result = treeNodeService.createTreeNode(20, null);

        // Assert
        assertNotNull(result);
        assertEquals(20, result.getValue());
        assertNull(result.getParent());
        verify(treeNodeRepository, times(1)).save(any(TreeNode.class));
    }
}
