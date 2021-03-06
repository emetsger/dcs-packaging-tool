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

package org.dataconservancy.packaging.tool.ser;

import org.junit.Before;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Base test class for all XStream related classes.
 */
public abstract class AbstractXstreamTest extends AbstractSerializationTest {

    private XmlPullParserFactory xppFactory;

    /**
     * Currently instantiates an XmlPullParserFactory.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        xppFactory = XmlPullParserFactory.newInstance();
    }

    /**
     * Obtain a new instance of an XmlPullParser, used when deserializing object instances.
     *
     * @return a new instance of XmlPullParser
     * @throws XmlPullParserException
     */
    public XmlPullParser getPullParser() throws XmlPullParserException {
        return xppFactory.newPullParser();
    }

}
