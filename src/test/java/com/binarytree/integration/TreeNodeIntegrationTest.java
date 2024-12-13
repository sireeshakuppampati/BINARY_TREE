package com.binarytree.integration;

import com.binarytree.models.TreeNode;
import com.binarytree.repositories.TreeNodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class TreeNodeIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private TreeNodeRepository treeNodeRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testCreateTreeNode() throws Exception {
        // Clean database before running the test
        treeNodeRepository.deleteAll();

        mockMvc.perform(post("/api/tree_nodes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\": 5}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.value").value(5));
    }
}
