/*
 * Copyright 2017 ${project.organization.name}
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cat.inspiracio.core.drivers.tspl.commands.label;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import cat.inspiracio.core.drivers.tspl.commands.TSPLCommand;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Builder
@Data
public class TSPLLabel {

    @Singular
    private List<TSPLCommand> elements;

    /**
     *
     * @return
     */
    public String getTsplCode() {
        return elements.stream().map(TSPLCommand::getCommand).collect(Collectors.joining(""));
    }
}