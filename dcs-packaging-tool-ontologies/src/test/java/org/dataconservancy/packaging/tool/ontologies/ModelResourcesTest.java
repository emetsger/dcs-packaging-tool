/*
 *
 *  * Copyright 2015 Johns Hopkins University
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.dataconservancy.packaging.tool.ontologies;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Insure that each Jena {@code Model} is available from {@code ModelResources}
 */
public class ModelResourcesTest {

    @Test
    public void testGetNonexistantNs() throws Exception {
        assertNull(ModelResources.get("foo"));
        assertFalse(ModelResources.RESOURCE_MAP.containsKey("foo"));
    }

    @Test
    public void testMapParseAndGetAllSupportedNamespaces() throws Exception {
        assertTrue(ModelResources.RESOURCE_MAP.size() > 0);
        ModelResources.RESOURCE_MAP.keySet().forEach(ns -> assertNotNull(ModelResources.get(ns)));
    }
}