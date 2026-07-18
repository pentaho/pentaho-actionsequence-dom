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



package org.pentaho.actionsequence.dom;

public interface IActionSequenceInputSource {

  void setOrigin( String origin );

  String getOrigin();

  void setName( String name );

  String getName();

  IActionSequenceInput getActionSequenceInput();

  void delete();

}
