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

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

// This class is used to set an action input to a constant value.
public class ActionInputConstant implements IActionInput, IActionInputSource {
  Object value;
  String inputName;
  IActionParameterMgr actionParameterMgr;

  public ActionInputConstant( Element componentDefElement, IActionParameterMgr actionParameterMgr ) {
    inputName = componentDefElement.getName();
    value = componentDefElement.getText();
    this.actionParameterMgr = actionParameterMgr;
  }

  // Not intended for general use. Use one parameter option.
  public ActionInputConstant( Object value, IActionParameterMgr actionParameterMgr ) {
    this.value = value;
    this.actionParameterMgr = actionParameterMgr;
  }

  // Not intended for general use. Use one parameter option.
  public ActionInputConstant( String value, IActionParameterMgr actionParameterMgr ) {
    this.value = value;
    this.actionParameterMgr = actionParameterMgr;
  }

  // Not intended for general use. Use one parameter option.
  public ActionInputConstant( Boolean value, IActionParameterMgr actionParameterMgr ) {
    this.value = value;
    this.actionParameterMgr = actionParameterMgr;
  }

  // Not intended for general use. Use one parameter option.
  public ActionInputConstant( boolean value, IActionParameterMgr actionParameterMgr ) {
    this.value = new Boolean( value );
    this.actionParameterMgr = actionParameterMgr;
  }

  // Not intended for general use. Use one parameter option.
  public ActionInputConstant( Integer value, IActionParameterMgr actionParameterMgr ) {
    this.value = value;
    this.actionParameterMgr = actionParameterMgr;
  }

  // Not intended for general use. Use one parameter option.
  public ActionInputConstant( int value, IActionParameterMgr actionParameterMgr ) {
    this.value = new Integer( value );
    this.actionParameterMgr = actionParameterMgr;
  }

  public Object getValue() {
    return value;
  }

  public String getStringValue() {
    return getStringValue( true, null );
  }

  public String getStringValue( boolean replaceParamReferences ) {
    return getStringValue( replaceParamReferences, null );
  }

  public String getStringValue( boolean replaceParamReferences, String defaultValue ) {
    Object theValue = value;
    if ( replaceParamReferences && ( actionParameterMgr != null ) && ( theValue != null ) ) {
      theValue = actionParameterMgr.replaceParameterReferences( theValue.toString() );
    }
    return theValue != null ? theValue.toString() : defaultValue;
  }

  public String getStringValue( String defaultValue ) {
    return getStringValue( true, defaultValue );
  }

  public Boolean getBooleanValue() {
    Boolean boolValue = null;
    String stringValue = getStringValue();
    if ( stringValue != null ) {
      boolValue = new Boolean( stringValue );
    }
    return boolValue;
  }

  public boolean getBooleanValue( boolean defaultValue ) {
    Boolean boolValue = getBooleanValue();
    return boolValue != null ? boolValue.booleanValue() : defaultValue;
  }

  public Integer getIntValue() {
    Integer intValue = null;
    String stringValue = getStringValue();
    if ( stringValue != null ) {
      try {
        intValue = new Integer( Integer.parseInt( stringValue ) );
      } catch ( NumberFormatException e ) {
        intValue = null;
      }
    }
    return intValue;
  }

  public int getIntValue( int defaultValue ) {
    Integer intValue = getIntValue();
    return intValue != null ? intValue.intValue() : defaultValue;
  }

  public boolean equals( Object obj ) {
    return value != null && ( obj instanceof ActionInputConstant )
        && value.equals( ( (ActionInputConstant) obj ).getValue() );
  }

  public String getName() {
    return inputName;
  }

  public String getType() {
    return "";
  }

  public String getVariableName() {
    return getName();
  }

}
