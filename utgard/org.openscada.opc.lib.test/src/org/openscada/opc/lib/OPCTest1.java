/*
 * This file is part of the OpenSCADA project
 * Copyright (C) 2006-2009 TH4 SYSTEMS GmbH (http://th4-systems.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.openscada.opc.lib;

import java.util.Map;
import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.Leaf;

public class OPCTest1
{
    public static void dumpItemState ( final Item item, final ItemState state )
    {
        System.out.println ( String.format ( "Item: %s, Value: %s, Timestamp: %tc, Quality: %d", item.getId (), state.getValue (), state.getTimestamp (), state.getQuality () ) );
    }

    public static void dumpTree ( final Branch branch, final int level )
    {
        final StringBuilder sb = new StringBuilder ();
        for ( int i = 0; i < level; i++ )
        {
            sb.append ( "  " );
        }
        final String indent = sb.toString ();

        for ( final Leaf leaf : branch.getLeaves () )
        {
            System.out.println ( indent + "Leaf: " + leaf.getName () + " [" + leaf.getItemId () + "]" );
        }
        for ( final Branch subBranch : branch.getBranches () )
        {
            System.out.println ( indent + "Branch: " + subBranch.getName () );
            dumpTree ( subBranch, level + 1 );
        }
    }

    @SuppressWarnings ( "unused" )
    public static void main ( final String[] args ) throws Throwable
    {
        // create connection information
        final ConnectionInformation ci = new ConnectionInformation ();
        ci.setHost ( args[0] );
        ci.setDomain ( args[1] );
        ci.setUser ( args[2] );
        ci.setPassword ( args[3] );
        ci.setClsid ( args[4] );

        // create a new server
        final Server server = new Server ( ci, Executors.newSingleThreadScheduledExecutor () );
        try
        {
            // connect to server
            server.connect ();

            // browse
            dumpTree ( server.getTreeBrowser ().browse (), 0 );

            // add sync reader

            // Add a new group
            Group group = server.addGroup ( "test" );
            // group is initially active ... just for demonstration
            group.setActive ( true );

            // We already have our group ... just for demonstration
            group = server.findGroup ( "test" );

            // Add a new item to the group
            final Item item = group.addItem ( "Saw-toothed Waves.Int2" );
            // Items are initially active ... just for demonstration
            item.setActive ( true );

            // Add some more items ... including one that is already existing
            final Map<String, Item> items = group.addItems ( "Saw-toothed Waves.Int2", "Saw-toothed Waves.Int4" );

            // sync-read some values
            for ( int i = 0; i < 10; i++ )
            {
                Thread.sleep ( 100 );
                dumpItemState ( item, item.read ( false ) );
            }
        }
        catch ( final JIException e )
        {
            System.out.println ( String.format ( "%08X: %s", e.getErrorCode (), server.getErrorMessage ( e.getErrorCode () ) ) );
        }
    }
}
