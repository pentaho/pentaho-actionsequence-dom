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


/**
 * 
 */

package org.pentaho.actionsequence.dom.actions;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputSource;
import org.pentaho.actionsequence.dom.IActionOutput;

public class HQLQueryAction extends HQLConnectionAction {
  public static final String COMPONENT_NAME = "org.pentaho.component.HQLLookupRule"; //$NON-NLS-1$
  public static final String HQL_ELEMENT = "HQL"; //$NON-NLS-1$
  public static final String QUERY_RESULT_ELEMENT = "query-result"; //$NON-NLS-1$
  public static final String QUERY_ELEMENT = "query"; //$NON-NLS-1$
  public static final String DEPRECATED_OUTPUT_NAME = "output-name"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { QUERY_ELEMENT, CLASSNAMES };

  public HQLQueryAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public HQLQueryAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME )
        && ( ( element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + QUERY_ELEMENT ) != null ) //$NON-NLS-1$
        || ( element.selectSingleNode( ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + QUERY_ELEMENT ) != null ) ); //$NON-NLS-1$
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public String[] getReservedOutputNames() {
    String expectedOutput = QUERY_RESULT_ELEMENT;
    if ( getOutput( expectedOutput ) == null ) {
      IActionOutput[] actionOutputs = getOutputs( ActionSequenceDocument.RESULTSET_TYPE );
      if ( actionOutputs.length > 0 ) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[] { expectedOutput };
  }

  public IActionInput getInputSharedConnection() {
    return getInput( PREPARED_COMPONENT_ELEMENT );
  }

  public void setInputSharedConnection( IActionInputSource value ) {
    setActionInputValue( PREPARED_COMPONENT_ELEMENT, value );
  }

  public void setOutputResultSetName( String name ) {
    setOutput( QUERY_RESULT_ELEMENT, name, ActionSequenceDocument.RESULTSET_TYPE );
    if ( ( name != null ) && ( name.trim().length() > 0 ) ) {
      setOutputPreparedStatementName( null );
    }
  }

  public String getOutputResultSetName() {
    return getPublicOutputName( QUERY_RESULT_ELEMENT );
  }

  public IActionOutput getOutputResultSetParam() {
    /*
     * First check for query-result as an output. If not present then check for DEPRECATED_OUTPUT_NAME Else get the
     * first output name that's not PREPARED_COMPONENT
     */
    IActionOutput actionOutput = getOutput( QUERY_RESULT_ELEMENT );
    if ( actionOutput == null ) {
      // NOTE: Code below is Deprecated.
      actionOutput = getOutput( DEPRECATED_OUTPUT_NAME );
      if ( actionOutput == null ) {
        IActionOutput[] actionOutputs = getOutputs();
        for ( int i = 0; i < actionOutputs.length; i++ ) {
          if ( !actionOutputs[i].getName().equals( PREPARED_COMPONENT_ELEMENT ) ) {
            actionOutput = actionOutputs[i];
            break;
          }
        }
      }
    }
    return actionOutput;

  }

  public void setOutputPreparedStatementName( String name ) {
    setOutput( PREPARED_COMPONENT_ELEMENT, name, ActionSequenceDocument.HQL_QUERY_TYPE );
    if ( ( name != null ) && ( name.trim().length() > 0 ) ) {
      setOutputResultSetName( null );
    }
  }

  public String getOutputPreparedStatementName() {
    return getPublicOutputName( PREPARED_COMPONENT_ELEMENT );
  }

  public IActionOutput getOutputPreparedStatementParam() {
    return getOutput( PREPARED_COMPONENT_ELEMENT );
  }

  public IActionInput getQuery() {
    IActionInput query = getInput( QUERY_ELEMENT );
    return query;
  }

  public void setQuery( IActionInputSource value ) {
    setActionInputValue( QUERY_ELEMENT, value );
  }
}
