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
package com.edugility.substantia.substance;

import java.io.Serializable;

import com.edugility.substantia.annotations.RelatedBug;

public abstract class EnumerationElement<I extends Serializable, V extends Serializable & Comparable<V>> extends AbstractSubstance<I, V> implements Comparable<EnumerationElement<I, V>> {

  private static final long serialVersionUID = 1L;

  @Deprecated
  private int ordinalPosition;

  private boolean required;

  protected EnumerationElement() {
    super();
  }
  
  protected EnumerationElement(final boolean required) {
    this();
    this.required = required;
  }

  @Deprecated
  @RelatedBug(bugId = "1", bugUrl = "https://github.com/ljnelson/substantia/issues/1")
  public int getOrdinalPosition() {
    return this.ordinalPosition;
  }

  @Deprecated
  public void setOrdinalPosition(final int ordinalPosition) {
    final int old = this.getOrdinalPosition();
    if (ordinalPosition != old) {
      this.ordinalPosition = ordinalPosition;
    }
  }

  public boolean isRequired() {
    return this.required;
  }

  @Override
  public int compareTo(final EnumerationElement<I, V> e) {
    if (e == null) {
      return -1; // nulls sort to the bottom
    } else if (this.isRequired()) {
      if (e.isRequired()) {
        return 0;
      } else {
        return -1;
      }
    } else if (e.isRequired()) {
      return 1;
    } else {
      return 0;
    }
  }

}
