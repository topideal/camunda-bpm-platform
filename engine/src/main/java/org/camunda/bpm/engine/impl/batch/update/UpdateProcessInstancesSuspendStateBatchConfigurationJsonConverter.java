/*
 * Copyright © 2013-2019 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.batch.update;

import java.util.List;
import org.camunda.bpm.engine.impl.json.JsonObjectConverter;
import org.camunda.bpm.engine.impl.util.JsonUtil;
import com.google.gson.JsonObject;

public class UpdateProcessInstancesSuspendStateBatchConfigurationJsonConverter extends JsonObjectConverter<UpdateProcessInstancesSuspendStateBatchConfiguration> {

  public static final UpdateProcessInstancesSuspendStateBatchConfigurationJsonConverter INSTANCE = new UpdateProcessInstancesSuspendStateBatchConfigurationJsonConverter();

  public static final String PROCESS_INSTANCE_IDS = "processInstanceIds";
  public static final String SUSPENDING = "suspended";

  public JsonObject toJsonObject(UpdateProcessInstancesSuspendStateBatchConfiguration configuration) {
    JsonObject json = JsonUtil.createObject();

    JsonUtil.addListField(json, PROCESS_INSTANCE_IDS, configuration.getIds());
    JsonUtil.addField(json, SUSPENDING, configuration.getSuspended());
    return json;
  }

  public UpdateProcessInstancesSuspendStateBatchConfiguration toObject(JsonObject json) {
    UpdateProcessInstancesSuspendStateBatchConfiguration configuration =
      new UpdateProcessInstancesSuspendStateBatchConfiguration(readProcessInstanceIds(json), JsonUtil.getBoolean(json, SUSPENDING));

    return configuration;
  }

  protected List<String> readProcessInstanceIds(JsonObject jsonObject) {
    return JsonUtil.asStringList(JsonUtil.getArray(jsonObject, PROCESS_INSTANCE_IDS));
  }
}
