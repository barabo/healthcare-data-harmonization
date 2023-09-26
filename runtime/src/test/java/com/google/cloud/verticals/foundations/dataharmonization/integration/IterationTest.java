/*
 * Copyright 2020 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.verticals.foundations.dataharmonization.integration;

import static com.google.cloud.verticals.foundations.dataharmonization.data.impl.TestDataTypeImplementation.testDTI;
import static com.google.cloud.verticals.foundations.dataharmonization.utils.AssertUtil.assertDCAPEquals;

import com.google.cloud.verticals.foundations.dataharmonization.data.Data;
import com.google.cloud.verticals.foundations.dataharmonization.data.NullData;
import com.google.cloud.verticals.foundations.dataharmonization.init.Engine;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Class to perform integration tests on iteration. */
@RunWith(JUnit4.class)
public class IterationTest {

  @Test
  public void iterate_nullData() throws Exception {
    IntegrationTest tester = new IntegrationTest();
    Engine engine = tester.initializeTestFile("iteration_null.wstl");
    Data actual = engine.transform(NullData.instance);
    Data expected =
        testDTI().containerOf(ImmutableMap.of("shouldBeThere", testDTI().primitiveOf("hello")));

    assertDCAPEquals(expected, actual);
  }

  @Test
  public void iterate_targets() throws Exception {
    IntegrationTest tester = new IntegrationTest("iteration/");
    Engine engine = tester.initializeTestFile("iterated_target.wstl");
    Data actual = engine.transform(NullData.instance);
    Data expected = tester.loadJson("iterated_target.json");

    assertDCAPEquals(expected, actual);
  }

  @Test
  public void iterate_directCall() throws Exception {
    IntegrationTest tester = new IntegrationTest("iteration/");
    Engine engine = tester.initializeTestFile("iterated_call.wstl");
    Data actual = engine.transform(NullData.instance);
    Data expected = tester.loadJson("iterated_call.json");

    assertDCAPEquals(expected, actual);
  }

  @Test
  public void iterate_operators() throws Exception {
    IntegrationTest tester = new IntegrationTest("iteration/");
    Engine engine = tester.initializeTestFile("iterated_operators.wstl");
    Data actual = engine.transform(NullData.instance);
    Data expected = tester.loadJson("iterated_operators.json");

    assertDCAPEquals(expected, actual);
  }
}
