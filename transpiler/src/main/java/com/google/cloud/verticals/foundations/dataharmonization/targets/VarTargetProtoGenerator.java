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
package com.google.cloud.verticals.foundations.dataharmonization.targets;

import com.google.cloud.verticals.foundations.dataharmonization.Environment;
import com.google.cloud.verticals.foundations.dataharmonization.data.Functions;
import com.google.cloud.verticals.foundations.dataharmonization.proto.Pipeline.FieldMapping.VariableTarget;
import com.google.cloud.verticals.foundations.dataharmonization.proto.Pipeline.FunctionCall;
import com.google.cloud.verticals.foundations.dataharmonization.proto.Pipeline.ValueSource;
import com.google.protobuf.AbstractMessage;

/** Target proto creation for "var". */
public class VarTargetProtoGenerator implements TargetProtoGenerator {

  @Override
  public AbstractMessage create(String pathHead, String path, Environment environment) {
    environment.declareOrInheritVariable(pathHead);
    return VariableTarget.newBuilder().setName(pathHead).setPath(path).build();
  }

  @Override
  public FunctionCall createWithMerge(
      String pathHead, String path, Environment environment, String mergeMode) {
    environment.declareOrInheritVariable(pathHead);
    return FunctionCall.newBuilder()
        .setReference(Functions.SET_REF)
        .addArgs(ValueSource.newBuilder().setConstString(pathHead))
        .addArgs(ValueSource.newBuilder().setConstString(path))
        .addArgs(ValueSource.newBuilder().setConstString(mergeMode))
        .build();
  }
}
