/*
 * SOURCE FILE GENERATATED BY JACOB CHANNEL CLASS GENERATOR
 * 
 *               !!! DO NOT EDIT !!!! 
 * 
 * Generated On  : Fri Apr 27 13:30:11 EEST 2012
 * For Interface : org.apache.ode.bpel.runtime.channels.LinkStatus
 */

package org.apache.ode.bpel.runtime.channels;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * An auto-generated channel listener abstract class for the 
 * {@link org.apache.ode.bpel.runtime.channels.LinkStatus} channel type. 
 * @see org.apache.ode.bpel.runtime.channels.LinkStatus
 * @see org.apache.ode.bpel.runtime.channels.LinkStatusChannel
 */
public abstract class LinkStatusChannelListener
    extends org.apache.ode.jacob.ChannelListener<org.apache.ode.bpel.runtime.channels.LinkStatusChannel>
    implements org.apache.ode.bpel.runtime.channels.LinkStatus
{

    private static final Log __log = LogFactory.getLog(org.apache.ode.bpel.runtime.channels.LinkStatus.class);

    protected Log log() { return __log; } 

    protected LinkStatusChannelListener(org.apache.ode.bpel.runtime.channels.LinkStatusChannel channel) {
       super(channel);
    }
}
