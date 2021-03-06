package org.dataconservancy.packaging.tool.api;

/*
 * Copyright 2015 Johns Hopkins University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import org.dataconservancy.packaging.tool.api.support.NodeComparison;
import org.dataconservancy.packaging.tool.model.ipm.Node;

public interface IPMService {
    /**
     * Create a tree from the file system. The tree has file data associated
     * with it, but does not have types assigned to nodes.
     * 
     * @param path The path on the file system to create a node tree from.
     * @throws IOException If a file can't be read or a symbolic link cycle is found.
     * @return root of tree
     */
    Node createTreeFromFileSystem(Path path) throws IOException;

    /**
     * Change the ignored status of a node. This may cause the types of other
     * nodes to change.
     * 
     * If a node is marked as ignored, all descendants are also marked as
     * ignored.
     * 
     * If a node is marked as not ignored, all descendants and ancestors are
     * also marked as not ignored.
     * 
     * @param node The node to change the ignored status for.
     * @param status The new status of the node.
     */
    void ignoreNode(Node node, boolean status);
    
    /**
     * Compares the three provided under the existing tree root node with the tree under the comparison tree root node.
     * @param existingTree The root node of the existing tree to compare.
     * @param comparisonTree The root node of the new tree to compare against the existing tree.
     * @return A map of nodes and their status after comparison.
     */
    Map<Node, NodeComparison> compareTree(Node existingTree, Node comparisonTree);

    /**
     * Merges the provided comparison tree into the existing tree.
     * @param existingTree The existing tree that will receive the results of the merge.
     * @param comparisonResult A map of the comparison result to apply to the existing tree.
     * @return True if the tree was successfully merged, false otherwise
     */
    boolean mergeTree(Node existingTree, Map<Node, NodeComparison> comparisonResult);
    
    /**
     * Checks that the file backing the node is accessible on the file system.
     * Note that this method makes no assertion that the file or folder pointed to was the original one, just that something exists
     * at the file location stored in the nodes file info.
     * @param node The node to check if it's file info file location is still accessible.
     * @return true if the file is accessible false otherwise.
     */
    boolean checkFileInfoIsAccessible(Node node);

    /**
     * Remaps a node to the new path, will attempt to remap children under the provided path is possible,
     * will leave them unchanged if there is no match under the new path.
     * @param node The node to remap.
     * @param newPath The new path to assign to the node.
    */
    void remapNode(Node node, Path newPath) throws IOException;

    /**
     * Refreshes the tree content with the latest state from the file system. This will build a tree from the current file system backing the given node to compare
     * with the existing tree structure.
     * @param node The node to refresh.
     * @return A map of the nodes and their status after comparison.
     * @throws IOException  if we cannot build teh comparison tree
     */
    Map<Node, NodeComparison> refreshTreeContent(Node node) throws IOException;
}
