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

import org.hyperledger.besu.ethereum.core.json.ListHexString32Deserializer;
import org.hyperledger.besu.ethereum.core.json.ListHexString32Serializer;
import org.hyperledger.besu.ethereum.core.json.HexStringDeserializer;
import org.hyperledger.besu.ethereum.core.json.HexStringSerializer;
import org.hyperledger.besu.ethereum.core.json.HexString32Deserializer;
import org.hyperledger.besu.ethereum.core.json.HexString32Serializer;
import org.hyperledger.besu.ethereum.core.witness.VerkleProof;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.bytes.Bytes32;

public class VerkleProofParameter {
  // TODO Maybe create a Bytes31 in Tuweni for stems?

  @JsonProperty("otherStems")
  @JsonDeserialize(ListHexStringDeserializer.class)
  @JsonSerialize(ListHexStringSerializer.class)
  private final List<Bytes> otherStems;

  @JsonProperty("depthExtensionPresent")
  @JsonDeserialize(HexStringDeserializer.class)
  @JsonSerialize(HexStringSerializer.class)
  private final Bytes depthExtensionPresent;

  @JsonProperty("commitmentsByPath")
  @JsonDeserialize(ListHexString32Deserializer.class)
  @JsonSerialize(ListHexString32Serializer.class)
  private final List<Bytes32> commitmentsByPath;

  @JsonProperty("d")
  @JsonDeserialize(HexString32Deserializer.class)
  @JsonSerialize(HexString32Serializer.class)
  private final Bytes32 d;

  @JsonProperty("ipaProof")
  private final IPAProofParameter ipaProof;

  @JsonCreator
  public VerkleProofParameter(
      @JsonProperty("otherStems") @JsonDeserialize(ListHexStringDeserializer.class) final List<String> otherStems,
      @JsonProperty("depthExtensionPresent") @JsonDeserialize(using = HexStringDeserializer.class) final Bytes depthExtensionPresent,
      @JsonProperty("commitmentsByPath") @JsonDeserialize(ListHexString32Deserializer.class) final List<String> commitmentsByPath,
      @JsonProperty("d") @JsonDeserialize(using = HexString32Deserializer.class) final Bytes32 d,
      @JsonProperty("ipaProof") final IPAProofParameter ipaProof) {
    this.otherStems = otherStems;
    this.depthExtensionPresent = depthExtensionPresent;
    this.commitmentsByPath = commitmentsByPath;
    this.d = d;
    this.ipaProof = ipaProof;
  }

  public static VerkleProofParameter fromVerkleProof(final VerkleProof verkleProof) {
    return new VerkleProofParameter(
        verkleProof.otherStems().stream().map(Bytes::toHexString).toList(),
        verkleProof.depthExtensionPresent(),
        verkleProof.commitmentsByPath().stream().map(Bytes32::toHexString).toList(),
        verkleProof.d(),
        IPAProofParameter.fromIPAProof(verkleProof.ipaProof()));
  }

  public static VerkleProof toVerkleProof(final VerkleProofParameter verkleProofParameter) {
    return new VerkleProof(
        verkleProofParameter.getOtherStems().stream().map(Bytes::fromHexString).toList(),
        verkleProofParameter.getDepthExtensionPresent(),
        verkleProofParameter.getCommitmentsByPath().stream().map(Bytes32::fromHexString).toList(),
        verkleProofParameter.getD(),
        IPAProofParameter.toIPAProof(verkleProofParameter.getIpaProof()));
  }

  @JsonGetter("otherStems")
  public List<Bytes> getOtherStems() {
    return otherStems;
  }

  @JsonGetter("depthExtensionPresent")
  public Bytes getDepthExtensionPresent() {
    return depthExtensionPresent;
  }

  @JsonGetter("commitmentsByPath")
  public List<Bytes32> getCommitmentsByPath() {
    return commitmentsByPath;
  }

  @JsonGetter("d")
  public Bytes32 getD() {
    return d;
  }

  @JsonGetter("ipaProof")
  public IPAProofParameter getIpaProof() {
    return ipaProof;
  }
}
