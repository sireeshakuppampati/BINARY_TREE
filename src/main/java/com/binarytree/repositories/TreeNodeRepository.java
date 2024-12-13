package com.binarytree.repositories;

import com.binarytree.models.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeNodeRepository extends JpaRepository<TreeNode, Integer> {
    boolean existsByValue(int value);
}

