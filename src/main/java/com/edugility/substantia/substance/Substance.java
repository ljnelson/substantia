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
package com.edugility.substantia.substance;

import java.io.Serializable;

import java.util.Date;

/**
 * A substrate to which additional properties adhere.
 *
 * @param <I> the type of identifier that implementations have
 *
 * @param <V> the type of version that implementations have
 *
 * @author <a href="http://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see LastModificationTimed
 *
 * @see Versioned
 */
public interface Substance<I extends Serializable, V extends Comparable<V> & Serializable> extends Versioned<V> {

  /**
   * Returns the persistent identifier of this {@link Substance}.
   *
   * <p>Implementations of this method may return {@code null},
   * particularly when a {@link Substance} is {@linkplain
   * #isTransient() transient}.</p>
   *
   * <p>The return value of this method must not be used as part of
   * the calculations performed by the {@link Object#equals(Object)}
   * or {@link Object#hashCode()} methods.</p>
   *
   * @return the persistent identifier of this {@link Substance};
   * possibly {@code null}
   *
   * @see #isTransient()
   *
   * @see Object#equals(Object)
   *
   * @see Object#hashCode()
   */
  public I getId();

  /**
   * Returns {@code true} if this {@link Substance} is not (perhaps
   * yet) backed by a persistent representation.
   *
   * @return {@code true} if this {@link Substance} is not backed by a
   * persistent representation; {@code false} in all other cases
   */
  public boolean isTransient();

}
