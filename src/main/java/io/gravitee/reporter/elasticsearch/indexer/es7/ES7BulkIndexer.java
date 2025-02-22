/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.reporter.elasticsearch.indexer.es7;

import io.gravitee.reporter.elasticsearch.indexer.BulkIndexer;
import io.vertx.core.buffer.Buffer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class ES7BulkIndexer extends BulkIndexer {

    @Override
    protected Buffer generateData(String templateName, Map<String, Object> data) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            freeMarkerComponent.generateFromTemplate("/es7x/index/" + templateName, data, new OutputStreamWriter(baos));

            return Buffer.buffer(baos.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }
}
