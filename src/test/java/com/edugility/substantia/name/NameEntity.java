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
package com.edugility.substantia.name;

import java.util.Date;

import com.edugility.nomen.Name;
import com.edugility.nomen.Named;
import com.edugility.nomen.NameValue;

import com.edugility.substantia.substance.Substance;

public class NameEntity extends Name implements Substance<Long, Integer> {

  private static final long serialVersionUID = 1L;

  private Long pk;

  private Integer version;

  private Long namedId;

  protected NameEntity() {
    super();
  }

  @Override
  public Long getId() {
    return this.pk;
  }

  @Override
  public Integer getVersion() {
    return this.version;
  }

  @Override
  public boolean isVersioned() {
    return this.getVersion() != null;
  }

  @Override
  public boolean isTransient() {
    return this.getId() == null;
  }

  public String getDisplayName() {
    return this.getValue();
  }

  public String getSortName() {
    return this.getDisplayName();
  }

  @Override
  public Named getNamed() {
    final Named named = super.getNamed();
    if (named == null && this.namedId != null) {
      throw new IllegalStateException();
    }
    return named;
  }

  @Override
  public void setNamed(final Named named) {
    super.setNamed(named);
    if (named == null) {
      this.namedId = null;
    } else if (named instanceof Substance) {
      this.namedId = ((Substance<Long, ?>)named).getId();
    } else {
      throw new IllegalArgumentException();
    }
  }

}
