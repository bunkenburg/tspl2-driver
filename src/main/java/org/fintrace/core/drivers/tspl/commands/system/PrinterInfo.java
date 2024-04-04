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

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static org.fintrace.core.drivers.tspl.DriverConstants.EMPTY_SPACE;
import static org.fintrace.core.drivers.tspl.DriverConstants.LF;

/**
 * At this command, the printer will print out the printer information.
 * <p><b><i>Syntax</i></b><br>
 * SELFTEST [page]
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data @Builder public class PrinterInfo implements TSPLCommand {

    /**
     * if omitted, prints a self-test page with whole printer information.
     */
    private PrinterInfoPage page;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        StringBuilder commandBuilder = new StringBuilder(SystemCommand.SELFTEST.name());
        if (page != null) {
            commandBuilder.append(EMPTY_SPACE)
                    .append(page.name());
        }

        commandBuilder.append(LF);

        return commandBuilder.toString();
    }
}
