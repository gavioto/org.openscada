/*******************************************************************************
 * Copyright (c) 2014 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.openscada.protocol.iec60870.client.data;

import io.netty.channel.ChannelHandlerContext;

import org.openscada.protocol.iec60870.asdu.message.DoublePointInformationSequence;
import org.openscada.protocol.iec60870.asdu.message.DoublePointInformationSingle;
import org.openscada.protocol.iec60870.asdu.message.DoublePointInformationTimeSingle;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueNormalizedSequence;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueNormalizedSingle;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueNormalizedTimeSingle;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueScaledSequence;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueScaledSingle;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueScaledTimeSingle;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueShortFloatingPointSequence;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueShortFloatingPointSingle;
import org.openscada.protocol.iec60870.asdu.message.MeasuredValueShortFloatingPointTimeSingle;
import org.openscada.protocol.iec60870.asdu.message.SinglePointInformationSequence;
import org.openscada.protocol.iec60870.asdu.message.SinglePointInformationSingle;
import org.openscada.protocol.iec60870.asdu.message.SinglePointInformationTimeSingle;

public interface DataHandler
{
    public void activated ( DataModuleContext dataModuleContext, ChannelHandlerContext ctx );

    public void started ();

    public void disconnected ();

    public void process ( SinglePointInformationTimeSingle msg );

    public void process ( SinglePointInformationSingle msg );

    public void process ( SinglePointInformationSequence msg );

    public void process ( DoublePointInformationTimeSingle msg );

    public void process ( DoublePointInformationSequence msg );

    public void process ( DoublePointInformationSingle msg );

    public void process ( MeasuredValueShortFloatingPointTimeSingle msg );

    public void process ( MeasuredValueShortFloatingPointSingle msg );

    public void process ( MeasuredValueShortFloatingPointSequence msg );

    public void process ( MeasuredValueScaledTimeSingle msg );

    public void process ( MeasuredValueScaledSequence msg );

    public void process ( MeasuredValueScaledSingle msg );

    public void process ( MeasuredValueNormalizedTimeSingle msg );

    public void process ( MeasuredValueNormalizedSequence msg );

    public void process ( MeasuredValueNormalizedSingle msg );

}
