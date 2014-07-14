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
package com.edugility.substantia.id;

import java.io.Serializable;

/**
 * A class representing an identifier.
 *
 */
public class Id<I, R> implements Serializable {

  private static final long serialVersionUID = 1L;

  private IdType<I, R> type;

  private I value;

  protected Id() {
    super();
  }

  public Id(final IdType<I, R> idType, final I value) {
    super();
    this.type = idType;
    this.value = value;
  }

  public IdType<I, R> getType() {
    return this.type;
  }

  public I getValue() {
    return this.value;
  }

  @Override
  public int hashCode() {
    // http://www.linuxtopia.org/online_books/programming_books/thinking_in_java/TIJ313_029.htm
    int hashCode = 17;
    int c;
    
    final Object value = this.getValue();
    c = value == null ? 0 : value.hashCode();
    hashCode = 37 * hashCode + c;

    final Object type = this.getType();
    c = type == null ? 0 : type.hashCode();
    hashCode = 37 * hashCode + c;

    return hashCode;
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    } else if (other != null && other.getClass().equals(this.getClass())) {
      final Id<?, ?> him = (Id<?, ?>)other;

      final Object value = this.getValue();
      final Object hisValue = him.getValue();
      if (value == null) {
        if (hisValue != null) {
          return false;
        }
      } else if (!value.equals(hisValue)) {
        return false;
      }

      final Object type = this.getType();
      final Object hisType = him.getType();
      if (type == null) {
        if (hisType != null) {
          return false;
        }
      } else if (!type.equals(hisType)) {
        return false;
      }

      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return String.valueOf(this.getValue());
  }

}
