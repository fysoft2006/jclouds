/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.ultradns.ws.parse;

import static org.jclouds.ultradns.ws.domain.ResourceRecord.rrBuilder;
import static org.testng.Assert.assertEquals;

import java.io.InputStream;

import org.jclouds.date.internal.SimpleDateFormatDateService;
import org.jclouds.http.functions.BaseHandlerTest;
import org.jclouds.ultradns.ws.domain.ResourceRecordMetadata;
import org.jclouds.ultradns.ws.xml.ResourceRecordListHandler;
import org.testng.annotations.Test;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

/**
 * @author Adrian Cole
 */
@Test(testName = "GetResourceRecordsOfDNameByTypeResponseTest")
public class GetResourceRecordsOfDNameByTypeResponseTest extends BaseHandlerTest {
   SimpleDateFormatDateService dateService = new SimpleDateFormatDateService();

   @Test
   public void test() {
      InputStream is = getClass().getResourceAsStream("/records_by_name_and_type.xml");

      FluentIterable<ResourceRecordMetadata> expected = expected();

      ResourceRecordListHandler handler = injector.getInstance(ResourceRecordListHandler.class);
      FluentIterable<ResourceRecordMetadata> result = factory.create(handler).parse(is);

      assertEquals(result.toImmutableList().toString(), expected.toImmutableList().toString());
   }

   public FluentIterable<ResourceRecordMetadata> expected() {
      ResourceRecordMetadata record = ResourceRecordMetadata.builder()
            .zoneId("03053D8E57C7A22A")
            .guid("04053D8E57C7A22F")
            .zoneName("adrianc.rr.ultradnstest.jclouds.org.")
            .created(dateService.iso8601DateParse("2013-02-22T08:22:48.000Z"))
            .modified(dateService.iso8601DateParse("2013-02-22T08:22:49.000Z"))
            .record(rrBuilder().type(6).name("adrianc.rr.ultradnstest.jclouds.org.").ttl(86400)
                               .rdata("pdns75.ultradns.com.")
                               .rdata("adrianc.netflix.com.")
                               .rdata("2013022200")
                               .rdata("86400")
                               .rdata("86400")
                               .rdata("86400")
                               .rdata("86400").build()).build();
      return FluentIterable.from(ImmutableList.of(record));
   }
}