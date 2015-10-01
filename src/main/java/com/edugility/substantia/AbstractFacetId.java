/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright (c) 2014-2015 Edugility LLC.
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
package com.edugility.substantia;

import java.io.Serializable; // for javadoc only

/**
 * A skeletal implementation of a {@link FacetId}.
 *
 * @param <SI> the <strong>s</strong>ubstrate
 * <strong>i</strong>dentifier type
 *
 * @param <I> the type of the identifier used for {@link FacetId}s of
 * this type
 *
 * @author <a href="http://about.me/lairdnelson/"
 * target="_parent">Laird Nelson</a>
 *
 * @see FacetId
 *
 * @see #equals(Object)
 *
 * @see #hashCode()
 *
 * @see #toString()
 */
public abstract class AbstractFacetId<SI, I> implements FacetId<SI, I> {


  /*
   * Static fields.
   */

  
  /**
   * The version of this class for {@linkplain Serializable
   * serialization purposes}.
   */
  private static final long serialVersionUID = 1L;


  /*
   * Constructors.
   */


  /**
   * Creates a new {@link AbstractFacetId}.
   */
  protected AbstractFacetId() {
    super();
  }
  

  /*
   * Instance methods.
   */


  /**
   * Returns a hashcode for this {@link AbstractFacetId}.
   *
   * @return a hashcode for this {@link AbstractFacetId}
   */
  @Override
  public int hashCode() {
    int hashCode = 17;

    final Object id = this.getId();
    int c = id == null ? 0 : id.hashCode();
    hashCode = hashCode * 37 + c;

    final Object substrateId = this.getSubstrateId();
    c = substrateId == null ? 0 : substrateId.hashCode();
    hashCode = hashCode * 37 + c;
    
    return hashCode;
  }

  /**
   * Returns {@code true} if the supplied {@link Object} is equal to
   * this {@link AbstractFacetId}.
   *
   * <p>This method returns {@code true} if and only if the supplied
   * {@link Object} is an {@link Class#isInstance(Object) instance of}
   * this {@link AbstractFacetId}'s class, and has values for its
   * {@code id} and {@code substrateId} properties that are equal to
   * the values of this {@link AbstractFacetId}'s {@code id} and
   * {@code substrateId} properties.</p>
   *
   * @param other the {@link Object} to test; may be {@code null}
   *
   * @return {@code true} if the supplied {@link Object} is equal to
   * this {@link FacetId}
   */
  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    } else if (other != null && this.getClass().isInstance(other)) {
      final FacetId<?, ?> him = (FacetId<?, ?>)other;

      final Object id = this.getId();
      final Object hisId = him.getId();
      if (id == null) {
        if (hisId != null) {
          return false;
        }
      } else if (!id.equals(hisId)) {
        return false;
      }

      final Object substrateId = this.getSubstrateId();
      final Object hisSubstrateId = him.getSubstrateId();
      if (substrateId == null) {
        if (hisSubstrateId != null) {
          return false;
        }
      } else if (!substrateId.equals(hisSubstrateId)) {
        return false;
      }

      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns a non-{@code null} {@link String} representation of this
   * {@link AbstractFacetId}.
   *
   * <p>This method never returns {@code null}.</p>
   *
   * @return a non-{@code null} {@link String} representation of this
   * {@link AbstractFacetId}
   */
  @Override
  public String toString() {
    return new StringBuilder("[").append(String.valueOf(this.getId())).append(", ").append(String.valueOf(this.getSubstrateId())).append("]").toString();
  }
  
}
