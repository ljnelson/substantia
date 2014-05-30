/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright (c) 2014 Edugility LLC.
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * The original copy of this license is available at
 * http://www.opensource.org/license/mit-license.html.
 */
package com.edugility.substantia.presence;

import com.edugility.nomen.AbstractNamed;
import com.edugility.nomen.Valued;
import com.edugility.nomen.NameType;

import com.edugility.substantia.id.Id;

public class Presence extends AbstractNamed {

  private static final long serialVersionUID = 1L;

  private Id<?> id;

  private Object version;

  protected Presence() {
    super();
  }

  public Presence(final Id<?> id) {
    super();
    this.setId(id);
  }

  public Id<?> getId() {
    return this.id;
  }

  public void setId(final Id<?> id) {
    this.id = id;
  }

  public Object getVersion() {
    return this.version;
  }

  public String getDisplayName() {
    final String returnValue;
    final Valued displayName = this.getName(NameType.valueOf("displayName"));
    if (displayName == null) {
      returnValue = null;
    } else {
      returnValue = displayName.getValue();
    }
    return returnValue;
  }

  public String getSortName() {
    final String returnValue;
    final Valued sortName = this.getName(NameType.valueOf("sortName"));
    if (sortName == null) {
      returnValue = this.getDisplayName();
    } else {
      returnValue = sortName.getValue();
    }
    return returnValue;
  }

  @Override
  public int hashCode() {
    int hashCode = 17;
    int c;

    c = super.hashCode();
    hashCode = 37 * hashCode + c;

    final Object version = this.getVersion();
    c = version == null ? 0 : version.hashCode();
    hashCode = 37 * hashCode + c;

    final Object displayName = this.getDisplayName();
    c = displayName == null ? 0 : displayName.hashCode();
    hashCode = 37 * hashCode + c;

    return hashCode;
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    } else if (other != null && other.getClass().equals(this.getClass())) {
      final Presence him = (Presence)other;
      
      final Object version = this.getVersion();
      final Object hisVersion = him.getVersion();
      if (version == null) {
        if (hisVersion != null) {
          return false;
        }
      } else if (!version.equals(hisVersion)) {
        return false;
      }
      
      final Object displayName = this.getDisplayName();
      final Object hisDisplayName = him.getDisplayName();
      if (displayName == null) {
        if (hisDisplayName != null) {
          return false;
        }
      } else if (!displayName.equals(hisDisplayName)) {
        return false;
      }

      return true;
    } else {
      return false;
    }
  }

}
