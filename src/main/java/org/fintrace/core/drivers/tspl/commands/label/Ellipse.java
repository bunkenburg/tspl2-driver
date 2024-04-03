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
package org.fintrace.core.drivers.tspl.commands.label;

import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;
import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static org.fintrace.core.drivers.tspl.DriverConstants.*;
import static org.fintrace.core.drivers.tspl.commands.label.LabelFormatCommand.ELLIPSE;

/**
 * This command draws an ellipse on the label.<br>
 * <p>
 * <b>Syntax</b><br>
 * ELLIPSE x,y,width,height,thickness<br>
 * <p>
 * x Specify x-coordinate of upper left corner (in dots)<br>
 * y Specify y-coordinate of upper left corner (in dots)<br>
 * width Specify the width of the ellipse (in dots)<br>
 * height Specify the height of the ellipse (in dots)<br>
 * thickness Thickness of the ellipse (in dots)<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Ellipse implements TSPLCommand {

    /**
     * x-coordinate of upper left corner (in dots)
     */
    private Integer xCoordinate;

    /**
     * y-coordinate of upper left corner (in dots)
     */
    private Integer yCoordinate;

    /**
     * width of the ellipse (in dots)
     */
    private Integer width;

    /**
     * height of the ellipse (in dots)
     */
    private Integer height;

    /**
     * Line thickness (in dots)
     */
    private Integer lineThickness;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("ELLIPSE: x and y positions are required");
        }

        if (width == null) {
            throw new LabelParserException("ELLIPSE: please specify the width");
        }

        if (height == null) {
            throw new LabelParserException("ELLIPSE: please specify the height");
        }

        if (lineThickness == null) {
            throw new LabelParserException("ELLIPSE: please specify line thickness");
        }

        StringBuilder commandBuilder = new StringBuilder(ELLIPSE.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(width).append(COMMA)
                .append(height).append(COMMA)
                .append(lineThickness)
                .append(LF);

        return commandBuilder.toString();
    }
}
