/*
 * SOURCE FILE GENERATATED BY JACOB CHANNEL CLASS GENERATOR
 * 
 *               !!! DO NOT EDIT !!!! 
 * 
 * Generated On  : Fri May 25 16:30:10 EEST 2012
 * For Interface : org.apache.ode.bpel.runtime.channels.ActivityRecovery
 */

package org.apache.ode.bpel.runtime.channels;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * An auto-generated channel listener abstract class for the 
 * {@link org.apache.ode.bpel.runtime.channels.ActivityRecovery} channel type. 
 * @see org.apache.ode.bpel.runtime.channels.ActivityRecovery
 * @see org.apache.ode.bpel.runtime.channels.ActivityRecoveryChannel
 */
public abstract class ActivityRecoveryChannelListener
    extends org.apache.ode.jacob.ChannelListener<org.apache.ode.bpel.runtime.channels.ActivityRecoveryChannel>
    implements org.apache.ode.bpel.runtime.channels.ActivityRecovery
{

    private static final Log __log = LogFactory.getLog(org.apache.ode.bpel.runtime.channels.ActivityRecovery.class);

    protected Log log() { return __log; } 

    protected ActivityRecoveryChannelListener(org.apache.ode.bpel.runtime.channels.ActivityRecoveryChannel channel) {
       super(channel);
    }
}
