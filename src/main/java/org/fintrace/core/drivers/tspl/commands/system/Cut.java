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
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static org.fintrace.core.drivers.tspl.DriverConstants.LF;

/**
 * This command activates the cutter to immediately cut the labels without back feeding the label.
 * <p>
 * <b><i>Syntax</i></b><br>
 * CUT
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Builder
public class Cut implements TSPLCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        return SystemCommand.CUT.name() + LF;
    }
}
