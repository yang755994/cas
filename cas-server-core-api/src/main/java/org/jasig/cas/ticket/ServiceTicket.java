/*
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.ticket;

import org.jasig.cas.authentication.Authentication;
import org.jasig.cas.authentication.principal.Service;

/**
 * Interface for a Service Ticket. A service ticket is used to grant access to a
 * specific service for a principal. A Service Ticket is generally a one-time
 * use ticket.
 *
 * @author Scott Battaglia
 * @since 3.0.0
 */
public interface ServiceTicket extends Ticket {

    /** Prefix generally applied to unique ids generated
     * by {@link org.jasig.cas.util.UniqueTicketIdGenerator}.
     **/
    String PREFIX = "ST";

    /** Proxy ticket prefix applied to unique ids
     * generated by {@link org.jasig.cas.util.UniqueTicketIdGenerator}.
     **/
    String PROXY_TICKET_PREFIX = "PT";

    /**
     * Retrieve the service this ticket was given for.
     *
     * @return the server.
     */
    Service getService();

    /**
     * Determine if this ticket was created at the same time as a
     * TicketGrantingTicket.
     *
     * @return true if it is, false otherwise.
     */
    boolean isFromNewLogin();

    /**
     * Attempts to ensure that the service specified matches the service associated with the ticket.
     * @param service The incoming service to match this service ticket against.
     * @return true, if the match is successful.
     */
    boolean isValidFor(Service service);

    /**
     * Method to grant a TicketGrantingTicket from this service to the
     * authentication. Analogous to the ProxyGrantingTicket.
     *
     * @param id The unique identifier for this ticket.
     * @param authentication The Authentication we wish to grant a ticket for.
     * @param expirationPolicy expiration policy associated with this ticket
     * @return The ticket granting ticket.
     */
    TicketGrantingTicket grantTicketGrantingTicket(String id,
        Authentication authentication, ExpirationPolicy expirationPolicy);
}