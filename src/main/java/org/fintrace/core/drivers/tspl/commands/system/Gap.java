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
 * Defines the gap distance between two labels.<br>
 * <p>
 * <b>Syntax</b><br>
 * English system (inch): GAP m,n <br>
 * Metric system (mm): GAP m mm,n mm <br>
 * Dot measurement: GAP m dot,n dot <br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Gap implements TSPLCommand {

    /**
     * The gap distance between two labels
     */
    private Float labelDistance;

    /**
     * The offset distance of the gap
     */
    private Float labelOffsetDistance;

    /**
     * indicates which system to use for the gap
     */
    @Builder.Default
    private MeasurementSystem measurementSystem = MeasurementSystem.ENGLISH;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (labelDistance == null && labelOffsetDistance != null) {
            throw new LabelParserException("ParseException GAP Command: "
                    + "label distance and label offset should be specified");
        }

        StringBuilder commandBuilder = new StringBuilder(SystemCommand.GAP.name());
        commandBuilder.append(EMPTY_SPACE);

        if (!hasFloatDecimals(labelDistance)) {
            commandBuilder.append(labelDistance.intValue());
        } else {
            commandBuilder.append(labelDistance);
        }

        if (measurementSystem == MeasurementSystem.METRIC) {
            commandBuilder.append(EMPTY_SPACE).append(UNIT_MM);
        } else if (measurementSystem == MeasurementSystem.DOT) {
            commandBuilder.append(EMPTY_SPACE).append("dot");
        }

        commandBuilder.append(COMMA);

        if (!hasFloatDecimals(labelOffsetDistance)) {
            commandBuilder.append(labelOffsetDistance.intValue());
        } else {
            commandBuilder.append(labelOffsetDistance);
        }

        if (measurementSystem == MeasurementSystem.METRIC) {
            commandBuilder.append(EMPTY_SPACE).append(UNIT_MM);
        } else if (measurementSystem == MeasurementSystem.DOT) {
            commandBuilder.append(EMPTY_SPACE).append("dot");
        }

        commandBuilder.append(LF);

        return commandBuilder.toString();
    }
}
