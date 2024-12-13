# BINARY_TREE
creating a binary tree spring boot  API in DATA STRUCTURES AND ALGORITHMS in SEMESTER4 as final project

# Binary Search Tree Application

## Overview
This is a **Spring Boot** application that allows users to:
- Create a binary search tree (BST) by entering numbers.
- Visualize the resulting tree in JSON format.
- View previously stored trees.
- Modify existing trees or delete trees.

The project provides a user-friendly HTML interface with buttons for managing the tree operations.

## Features
1. **Create Trees**
   - Enter a number or a list of numbers to create a binary search tree.
   - Optionally, specify a parent ID to create a child node for an existing tree node.

2. **Show Trees**
   - View all previously created trees in JSON format.

3. **Modify Trees**
   - Update an existing tree node by providing its ID and a new value.

4. **Delete Trees**
   - Remove an existing tree node by providing its ID.

## Endpoints
The application exposes the following RESTful API endpoints:

| Method | Endpoint               | Description                                 |
|--------|------------------------|---------------------------------------------|
| GET    | `/api/tree_nodes`      | Fetch all trees.                           |
| POST   | `/api/tree_nodes`      | Add a new node or create a tree.           |
| PUT    | `/api/tree_nodes/{id}` | Modify a tree node by ID.                  |
| DELETE | `/api/tree_nodes/{id}` | Delete a tree node by ID.                  |

## User Interface
The application provides the following sections in the HTML page:
1. **Create Trees**:
   - Enter a single number or a list of numbers.
   - Optionally specify a parent ID for child nodes.
   - The resulting tree will be displayed dynamically.

2. **Show Trees**:
   - Displays all previously created trees in JSON format.

3. **Modify Trees**:
   - Modify an existing tree node by providing its ID and new value.

4. **Delete Trees**:
   - Delete a tree node by providing its ID.

The interface uses a grid layout for better usability with buttons like:
- **Create Trees**
- **Show Trees**
- **Modify Trees**
- **Delete Trees**

## How to Run the Application
1. Clone this repository:
   ```bash
   git clone <repository-url>

