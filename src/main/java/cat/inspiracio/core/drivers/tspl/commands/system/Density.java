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
package cat.inspiracio.core.drivers.tspl.commands.system;

import cat.inspiracio.core.drivers.tspl.exceptions.LabelParserException;
import lombok.Builder;
import lombok.Data;
import cat.inspiracio.core.drivers.tspl.commands.TSPLCommand;

import static cat.inspiracio.core.drivers.tspl.DriverConstants.EMPTY_SPACE;
import static cat.inspiracio.core.drivers.tspl.DriverConstants.LF;

/**
 * This command sets the printing darkness.<br>
 * <p>
 * <b>Syntax</b><br>
 * DENSITY n<br>
 * <blockquote>n 0~15<br>
 * 0: specifies the lightest level<br>
 * 15: specifies the darkest leve</blockquote>
 * <p>
 * <b><i>NOTE: </i></b>Default printer DENSITY setting is 8.
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Density implements TSPLCommand {

    /**
     * printer darkness
     */
    private Integer darkness;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (darkness == null) {
            throw new LabelParserException("ParseException DENSITY Command: "
                    + "darkness can't be empty");
        }

        return SystemCommand.DENSITY.name()
                + EMPTY_SPACE + darkness
                + LF;
    }
}
