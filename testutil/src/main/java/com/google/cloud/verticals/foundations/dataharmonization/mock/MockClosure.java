/*
 * Copyright 2021 Google LLC.
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
package com.google.cloud.verticals.foundations.dataharmonization.mock;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.google.cloud.verticals.foundations.dataharmonization.data.Data;
import com.google.cloud.verticals.foundations.dataharmonization.data.NullData;
import com.google.cloud.verticals.foundations.dataharmonization.function.Closure;
import com.google.cloud.verticals.foundations.dataharmonization.function.context.RuntimeContext;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import java.util.List;
import java.util.SortedSet;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/** Simple function based closure with optional free params. */
public class MockClosure implements Closure {
  private final int numFree;
  private final List<Data> args;
  private final BiFunction<List<Data>, RuntimeContext, Data> fn;

  private MockClosure(
      int numFree, List<Data> args, BiFunction<List<Data>, RuntimeContext, Data> fn) {
    this.numFree = numFree;
    this.args = args;
    this.fn = fn;
  }

  public MockClosure(int numFree, BiFunction<List<Data>, RuntimeContext, Data> fn) {
    this(numFree, ImmutableList.of(), fn);
  }

  @Override
  public boolean isNullOrEmpty() {
    return false;
  }

  @Override
  public Data deepCopy() {
    return new MockClosure(numFree, args, fn);
  }

  @Override
  public boolean isWritable() {
    return false;
  }

  @Override
  public Data[] getArgs() {
    return args.toArray(Data[]::new);
  }

  @Override
  public Closure bindNextFreeParameter(Data value) {
    return new MockClosure(
        numFree - 1, ImmutableList.<Data>builder().addAll(args).add(value).build(), fn);
  }

  @Override
  public Data execute(RuntimeContext context) {
    return fn.apply(args, context);
  }

  @Override
  public int getNumFreeParams() {
    return numFree;
  }

  @Override
  public SortedSet<Integer> getFreeArgIndices() {
    return ImmutableSortedSet.copyOf(
        IntStream.range(0, numFree).boxed().collect(toImmutableList()));
  }

  @Override
  public String getName() {
    return "MockClosure";
  }

  /** Returns a closure with no free params that always returns null. */
  public static Closure noop() {
    return new MockClosure(0, (args, ctx) -> NullData.instance);
  }
}
