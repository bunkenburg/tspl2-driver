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
package org.fintrace.core.drivers.tspl.commands.system;

import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;
import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static org.fintrace.core.drivers.tspl.DriverConstants.*;
import static org.fintrace.core.drivers.tspl.commands.label.TSPLLabelUtils.hasFloatDecimals;


/**
 * This command defines the label width and length<br>
 * <p>
 * <b>Syntax</b><br>
 * English system (inch): SIZE m,n <br>
 * Metric system (mm): SIZE m mm,n mm <br>
 * Dot measurement: SIZE m dot,n dot <br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Size implements TSPLCommand {
    /**
     * Label width
     */
    private Float labelWidth;

    /**
     * Label length
     */
    private Float labelLength;

    /**
     * indicates the size measurement system to be used
     */
    @Builder.Default
    private MeasurementSystem sizeMeasurementSystem = MeasurementSystem.ENGLISH;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (labelWidth == null || labelLength == null) {
            throw new LabelParserException("ParseException SIZE Command: "
                    + "label width and label length should be specified");
        }

        StringBuilder commandBuilder = new StringBuilder(SystemCommand.SIZE.name());
        commandBuilder.append(EMPTY_SPACE);

        if (!hasFloatDecimals(labelWidth)) {
            commandBuilder.append(labelWidth.intValue());
        } else {
            commandBuilder.append(labelWidth);
        }

        if (sizeMeasurementSystem == MeasurementSystem.METRIC) {
            commandBuilder.append(EMPTY_SPACE).append(UNIT_MM);
        } else if (sizeMeasurementSystem == MeasurementSystem.DOT) {
            commandBuilder.append(EMPTY_SPACE).append("dot");
        }

        commandBuilder.append(COMMA);

        if (!hasFloatDecimals(labelLength)) {
            commandBuilder.append(labelLength.intValue());
        } else {
            commandBuilder.append(labelLength);
        }

        if (sizeMeasurementSystem == MeasurementSystem.METRIC) {
            commandBuilder.append(EMPTY_SPACE).append(UNIT_MM);
        } else if (sizeMeasurementSystem == MeasurementSystem.DOT) {
            commandBuilder.append(EMPTY_SPACE).append("dot");
        }

        commandBuilder.append(LF);

        return commandBuilder.toString();
    }
}
