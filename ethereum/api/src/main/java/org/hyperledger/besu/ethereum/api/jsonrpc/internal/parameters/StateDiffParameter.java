/*
 * Copyright contributors to Hyperledger Besu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.hyperledger.besu.ethereum.api.jsonrpc.internal.parameters;

import org.hyperledger.besu.ethereum.core.json.HexStringDeserializer;
import org.hyperledger.besu.ethereum.core.json.HexStringSerializer;
import org.hyperledger.besu.ethereum.core.witness.StateDiff;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonDeserialize;

import org.apache.tuweni.bytes.Bytes;

public class StateDiffParameter {
  @JsonProperty("stem")
  @JsonSerialize(using = HexStringSerializer.class)
  @JsonDeserialize(using = HexStringDeserializer.class)
  private final Bytes stem;

  @JsonProperty("suffixDiffs")
  private final List<SuffixStateDiffParameter> suffixDiffs;

  @JsonCreator
  public StateDiffParameter(
      @JsonProperty("stem") @JsonDeserialize(using = HexStringDeserializer.class) @JsonProperty("stem") final Bytes stem,
      @JsonProperty("suffixDiffs") final List<SuffixStateDiffParameter> suffixDiffs) {
    this.stem = stem;
    this.suffixDiffs = suffixDiffs;
  }

  @JsonGetter("stem")
  public Bytes getStem() {
    return stem;
  }

  @JsonGetter("suffixDiffs")
  public List<SuffixStateDiffParameter> getSuffixDiffs() {
    return suffixDiffs;
  }
}
