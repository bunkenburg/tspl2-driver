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
package org.fintrace.core.drivers.tspl.commands.status;

import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static org.fintrace.core.drivers.tspl.DriverConstants.LF;
import static org.fintrace.core.drivers.tspl.DriverConstants.STATUS_COMMAND_PREFIX;

/**
 * These commands support RS-232, USB and Ethernet.
 *
 * <b>Syntax</b><br>
 * &lt;ESC&gt;!&lt;COMMAND&gt;<br><br>
 * <i>&lt;ESC&gt; is ASCII 27, escape character</i>
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum TSPLStatusPollCommands implements TSPLCommand {
    /**
     * This command obtains the printer status at any time, even in the event
     * of printer error. An inquiry request is solicited by sending
     * an &lt;ESC&gt; (ASCII 27, escape character) as the beginning control character
     * to the printer. A one byte character is returned, flagging the printer status.
     * A 0 signifies the printer is ready to print labels.
     *
     * <b>Syntax</b><br>
     * &lt;ESC&gt;!?
     *
     * <b>Response Format</b>
     * <b>Hex Receive Printer Status</b><br>
     * 00 Normal<br>
     * 01 Head opened<br>
     * 02 Paper Jam<br>
     * 03 Paper Jam and head opened<br>
     * 04 Out of paper<br>
     * 05 Out of paper and head opened<br>
     * 08 Out of ribbon<br>
     * 09 Out of ribbon and head opened<br>
     * 0A Out of ribbon and paper jam<br>
     * 0B Out of ribbon, paper jam and head opened<br>
     * 0C Out of ribbon and out of paper<br>
     * 0D Out of ribbon, out of paper and head opened<br>
     * 10 Pause<br>
     * 20 Printing<br>
     * 80 Other error<br>
     */
    STATUS("?", "Printer Status"),

    /**
     * This command obtains the printer status at any time, even in the
     * event of printer error. An inquiry request is solicited by sending
     * an &lt;ESC&gt; (ASCII 27, escape character) as the beginning control character
     * to the printer. 8 bytes will be returned, flagging the printer status.
     *
     * <b>Syntax </b><br>
     * &lt;ESC&gt;!S
     * <p><br>
     * <b>Response Format</b><br>
     * &lt;STX&gt;[4-byte status]&lt;ETX&gt;&lt;CR&gt;&lt;LF&gt;<br><br>
     * <b>Status Byte #1: message</b><br>
     * Bit 7 Bit 6 Bit 5 Bit 4 Bit 3 Bit 2 Bit 1 Bit 0 Hex ASCII Char Meaning<br>
     * 0 1 0 0 0 0 0 0 40 64 @ Normal<br>
     * 0 1 1 0 0 0 0 0 60 96 ` Pause<br>
     * 0 1 0 0 0 0 1 0 42 66 B Backing label<br>
     * 0 1 0 0 0 0 1 1 43 67 C Cutting<br>
     * 0 1 0 0 0 1 0 1 45 69 E Printer error<br>
     * 0 1 0 0 0 1 1 0 46 70 F Form feed<br>
     * 0 1 0 0 1 0 1 1 4B 75 K Waiting to press print key<br>
     * 0 1 0 0 1 1 0 0 4C 76 L Waiting to take label<br>
     * 0 1 0 1 0 0 0 0 50 80 P Printing batch<br>
     * 0 1 0 1 0 1 1 1 57 87 W Imaging<br><br>
     * <b>Status Byte #2: warning</b><br>
     * Bit 7 Bit 6 Bit 5 Bit 4 Bit 3 Bit 2 Bit 1 Bit 0 Hex ASCII Char Meaning<br>
     * 0 1 0 0 0 0 0 0 40 64 @ Normal<br>
     * 0 1 0 0 0 0 0 1 41 65 A Reversed<br>
     * 0 1 0 0 0 0 1 0 42 66 B Reversed<br>
     * 0 1 0 0 0 1 0 0 44 68 D Reversed<br>
     * 0 1 0 0 1 0 0 0 48 72 H Receive buffer bull<br>
     * 0 1 1 0 0 0 0 0 60 96 ` Reversed<br><br>
     * <b>Status Byte #3: error</b><br>
     * Bit 7 Bit 6 Bit 5 Bit 4 Bit 3 Bit 2 Bit 1 Bit 0 Hex ASCII Char Meaning<br>
     * 0 1 0 0 0 0 0 0 40 64 @ Normal<br>
     * 0 1 0 0 0 0 0 1 41 65 A Print head overheat<br>
     * 0 1 0 0 0 0 1 0 42 66 B Stepping motor overheat<br>
     * 0 1 0 0 0 1 0 0 44 68 D Print head error<br>
     * (since V7.01 EZ)<br>
     * 0 1 0 0 1 0 0 0 48 72 H Cutter jam<br>
     * 0 1 0 1 0 0 0 0 50 80 P Insufficient memory<br><br>
     * <b>Status Byte #4: error</b><br>
     * Bit 7 Bit 6 Bit 5 Bit 4 Bit 3 Bit 2 Bit 1 Bit 0 Hex ASCII Char Meaning<br>
     * 0 1 0 0 0 0 0 0 40 64 @ Normal<br>
     * 0 1 0 0 0 0 0 1 41 65 A Paper empty<br>
     * 0 1 0 0 0 0 1 0 42 66 B Paper jam<br>
     * 0 1 0 0 0 1 0 0 44 68 D Ribbon empty<br>
     * 0 1 0 0 1 0 0 0 48 72 H Ribbon jam<br>
     * 0 1 1 0 0 0 0 0 60 96 ` Print head open<br>
     */
    STATUS_LONG("S", "Printer Status"),

    /**
     * This command is using to PAUSE the printer. The beginning of the command
     * is an ESCAPE character
     * (ASCII 27).
     *
     * <b>Syntax</b><br>
     * &lt;ESC&gt;!P
     */
    PAUSE("P", "Pause Printer"),

    /**
     * This command is using to cancel the PAUSE status of printer. The
     * beginning of the command is an
     * ESCAPE character (ASCII 27).
     *
     * <b>Syntax</b><br>
     * &lt;ESC&gt;!O
     */
    CANCEL_PAUSE("O", "Cancel Printer Pause"),

    /**
     * This command resets the printer. The beginning of the command is
     * an ESCAPE character (ASCII 27). The files downloaded in memory
     * will be deleted. This command cannot be sent in dump mode.
     *
     * <b>Syntax</b><br>
     * &lt;ESC&gt;!R
     */
    RESET("R", "Reset Printer"),

    /**
     * This command is using to feed a label. This function is the same as
     * to press the FEED button. The beginning of the command is
     * an ESCAPE character (ASCII 27).
     *
     * <b>Syntax</b><br>
     * &lt;ESC&gt;!F
     */
    FEED_LABEL("F", "Feed Label"),

    /**
     * This command can cancel all printing files. The beginning of the
     * command is an ESCAPE character
     * (ASCII 27).
     *
     * <b>Syntax</b><br>
     * &lt;ESC&gt;!.
     */
    CANCEL_PRINT(".", "Cancel Print");


    private String description;
    private String command;

    /**
     * @param command
     * @param description
     */
    TSPLStatusPollCommands(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * returns description
     *
     * @return description of the Status command
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     *
     * @return TSPL Command in ASCII bytes
     */
    @Override
    public String getCommand() {
        return ((char) 27 + STATUS_COMMAND_PREFIX + command + LF);
    }
}
