/*
 * Copyright Hyperledger Besu Contributors
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
 *
 */
package org.hyperledger.besu.ethereum.core.json;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GenericListSerializer<T> extends JsonSerializer<List<T>> {
  private final JsonSerializer<T> elementSerializer;

  public GenericListSerializer(JsonSerializer<T> elementSerializer) {
    this.elementSerializer = elementSerializer;
  }

  @Override
  public void serialize(List<T> value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    if (value == null) {
      gen.writeNull();
      return;
    }

    gen.writeStartArray();
    for (T element : value) {
      elementSerializer.serialize(element, gen, serializers);
    }
    gen.writeEndArray();
  }
}
