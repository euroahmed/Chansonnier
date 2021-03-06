/****************************************************************************
 * Copyright (c) 2010 Giorgio Sironi. All rights reserved.
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ****************************************************************************/

// CHECKSTYLE:OFF

package org.w3._2001.xmlschema;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (org.eclipse.smila.connectivity.framework.schema.tools.SimpleDateFormatter.parse(value));
    }

    public String marshal(Date value) {
        return (org.eclipse.smila.connectivity.framework.schema.tools.SimpleDateFormatter.print(value));
    }

}

// CHECKSTYLE:ON
