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
 * Metadata about identifiers.
 *
 * @param <I> The Java type that identifiers of this type are.
 *
 * @param <R> The Java type that referents picked out by identifiers
 * of this type are.
 */
public class IdType<I, R> implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;

  private Class<I> referenceType;

  private Class<R> referentType;

  protected IdType() {
    super();
  }

  public IdType(final String name, final Class<I> referenceType, final Class<R> referentType) {
    super();
    this.name = name;
    this.referenceType = referenceType;
    this.referentType = referentType;
  }

  public String getName() {
    return this.name;
  }

  public Class<I> getReferenceType() {
    return this.referenceType;
  }

  public Class<R> getReferentType() {
    return this.referentType;
  }

  @Override
  public int hashCode() {
    // http://www.linuxtopia.org/online_books/programming_books/thinking_in_java/TIJ313_029.htm
    int hashCode = 17;
    int c;
    
    final Object name = this.getName();
    c = name == null ? 0 : name.hashCode();
    hashCode = 37 * hashCode + c;

    final Object referenceType = this.getReferenceType();
    c = referenceType == null ? 0 : referenceType.hashCode();
    hashCode = 37 * hashCode + c;

    final Object referentType = this.getReferentType();
    c = referentType == null ? 0 : referentType.hashCode();
    hashCode = 37 * hashCode + c;

    return hashCode;
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    } else if (other != null && other.getClass().equals(this.getClass())) {
      final IdType<?, ?> him = (IdType<?, ?>)other;
      
      final Object name = this.getName();
      final Object hisName = him.getName();
      if (name == null) {
        if (hisName != null) {
          return false;
        }
      } else if (!name.equals(hisName)) {
        return false;
      }

      final Object referenceType = this.getReferenceType();
      final Object hisReferenceType = him.getReferenceType();
      if (referenceType == null) {
        if (hisReferenceType != null) {
          return false;
        }
      } else if (!referenceType.equals(hisReferenceType)) {
        return false;
      }

      final Object referentType = this.getReferentType();
      final Object hisReferentType = him.getReferentType();
      if (referentType == null) {
        if (hisReferentType != null) {
          return false;
        }
      } else if (!referentType.equals(hisReferentType)) {
        return false;
      }

      return true;

    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return String.valueOf(this.getName());
  }

}
