/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.log4j.core.lookup;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

/**
 * Tests {@link MapLookup}.
 */
public class MapLookupTest {

    @Test
    public void testEmptyMap() {
        final MapLookup lookup = new MapLookup(new HashMap<String, String>());
        assertEquals(null, lookup.lookup(null, null));
        assertEquals(null, lookup.lookup(null, "X"));
    }

    @Test
    public void testMap() {
        final HashMap<String, String> map = new HashMap<>();
        map.put("A", "B");
        final MapLookup lookup = new MapLookup(map);
        assertEquals(null, lookup.lookup(null, null));
        assertEquals("B", lookup.lookup(null, "A"));
    }

    @Test
    public void testNullMap() {
        final MapLookup lookup = new MapLookup();
        assertEquals(null, lookup.lookup(null, null));
        assertEquals(null, lookup.lookup(null, "X"));
    }

    @Test
    public void testMainMap() {
        MapLookup.setMainArguments(new String[] {
                "--file",
                "foo.txt" });
        final MapLookup lookup = MainMapLookup.MAIN_SINGLETON;
        assertEquals(null, lookup.lookup(null, null));
        assertEquals(null, lookup.lookup(null, "X"));
        assertEquals("--file", lookup.lookup(null, "0"));
        assertEquals("foo.txt", lookup.lookup(null, "1"));
        assertEquals("foo.txt", lookup.lookup(null, "--file"));
        assertEquals(null, lookup.lookup(null, "foo.txt"));
    }

}
