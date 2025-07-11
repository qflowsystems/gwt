/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.user.client.rpc.core.java.sql;

import com.google.gwt.user.client.rpc.CustomFieldSerializer;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

import java.sql.Date;

/**
 * Custom field serializer for {@link java.sql.Date}. Similar to Time, we use
 * the three-arg constructor to account for variances in implementations of
 * Date.
 */
public final class Date_CustomFieldSerializer extends
    CustomFieldSerializer<Date> {

  @SuppressWarnings("unused")
  public static void deserialize(SerializationStreamReader streamReader,
      Date instance) {
    // No fields
  }

  public static Date instantiate(SerializationStreamReader streamReader)
      throws SerializationException {
	java.util.Date tempDate = new java.util.Date(streamReader.readInt(),streamReader.readInt(),streamReader.readInt(),streamReader.readInt(),streamReader.readInt(),streamReader.readInt());
    return new Date(tempDate.getTime());
  }

  public static void serialize(SerializationStreamWriter streamWriter,
      Date instance) throws SerializationException {
   	streamWriter.writeInt(instance.getYear());
	streamWriter.writeInt(instance.getMonth());
	streamWriter.writeInt(instance.getDate());
	// java.sql.Date doesn't have time components, so we write 0 for hours, minutes, seconds
	streamWriter.writeInt(0); // hours
	streamWriter.writeInt(0); // minutes
	streamWriter.writeInt(0); // seconds
  }

  @Override
  public void deserializeInstance(SerializationStreamReader streamReader,
      Date instance) throws SerializationException {
    deserialize(streamReader, instance);
  }

  @Override
  public boolean hasCustomInstantiateInstance() {
    return true;
  }

  @Override
  public Date instantiateInstance(SerializationStreamReader streamReader)
      throws SerializationException {
    return instantiate(streamReader);
  }

  @Override
  public void serializeInstance(SerializationStreamWriter streamWriter,
      Date instance) throws SerializationException {
    serialize(streamWriter, instance);
  }
}
