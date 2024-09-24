/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2028-08-13
 ******************************************************************************/

package org.pentaho.actionsequence.dom;

import org.pentaho.actionsequence.dom.actions.ActionDefinition;

public class ActionSequenceValidationError implements IActionSequenceValidationError {
  public static final int INPUT_OK = 0;;
  public static final int INPUT_MISSING = 1;
  public static final int INPUT_REFERENCES_UNKNOWN_VAR = 2;
  public static final int INPUT_UNINITIALIZED = 3;
  public static final int OUTPUT_MISSING = 4;

  public int errorCode;
  public String errorMsg;
  public ActionDefinition actionDefinition;
  public String parameterName;

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public IActionDefinition getActionDefinition() {
    return actionDefinition;
  }

  public String getParameterName() {
    return parameterName;
  }

}
