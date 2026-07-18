/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 - 2026 by Pentaho Canada Inc. : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2030-06-15
 ******************************************************************************/



package org.pentaho.actionsequence.dom.actions;

import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.IActionInput;

public class ConstantActionInputFilter implements IActionInputFilter {

  public boolean accepts( IActionInput actionInput ) {
    return ( actionInput instanceof ActionInputConstant );
  }

}
