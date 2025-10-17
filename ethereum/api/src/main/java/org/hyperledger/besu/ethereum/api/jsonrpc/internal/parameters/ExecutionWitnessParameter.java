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

import org.hyperledger.besu.ethereum.core.witness.ExecutionWitness;

import java.util.Objects;
import org.apache.tuweni.bytes.Bytes32;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;

public class ExecutionWitnessParameter {

  @JsonProperty("stateDiff")
  private final List<StateDiffParameter> stateDiffs;
  @JsonProperty("verkleProof")
  private final VerkleProofParameter verkleProof;

  @VisibleForTesting
  public ExecutionWitnessParameter() {
    this.stateDiffs = null;
    this.verkleProof = null;
  }

  @JsonCreator
  public ExecutionWitnessParameter(
      @JsonProperty("stateDiff") final List<StateDiffParameter> stateDiffs,
      @JsonProperty("verkleProof") final VerkleProofParameter verkleProof) {
    this.stateDiffs = stateDiffs;
    this.verkleProof = verkleProof;
  }

  public static ExecutionWitnessParameter fromExecutionWitness(
      final ExecutionWitness executionWitness) {
    return new ExecutionWitnessParameter(
        StateDiffParameter.fromStateDiff(executionWitness.getStateDiff()),
        VerkleProofParameter.fromVerkleProof(executionWitness.getVerkleProof()));
  }

  public ExecutionWitness toExecutionWitness() {
    return new ExecutionWitness(
        StateDiffParameter.toStateDiff(stateDiffParameter),
        VerkleProofParameter.toVerkleProof(verkleProofParameter));
  }

  @JsonGetter("stateDiff")
  public List<StateDiffParameter> getStateDiffs() {
    return stateDiffs;
  }

  @JsonGetter("verkleProof")
  public VerkleProofParameter getVerkleProof() {
    return verkleProof;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExecutionWitnessParameter that = (ExecutionWitnessParameter) o;
    return Objects.equals(stateDiffs, that.stateDiffs)
        && Objects.equals(verkleProof, that.verkleProof);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateDiffs, verkleProof);
  }
}
