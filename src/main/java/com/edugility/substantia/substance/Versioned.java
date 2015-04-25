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

/**
 * An interface indicating that its implementations are capable of
 * being versioned for the purposes of optimistic concurrency control.
 *
 * @author <a href="http://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 */
public interface Versioned<V extends Comparable<V> & Serializable> {

  /**
   * Returns the version of this {@link Versioned} implementation, for
   * use in optimistic concurrency use cases only.
   *
   * <p>Implementations of this method may return {@code null},
   * particularly when a {@link Versioned} implementation is
   * {@linkplain #isVersioned() not versioned}.</p>
   *
   * @return the optimistic concurrency-related version of this {@link
   * Substance}; possibly {@code null}
   *
   * @see #isVersioned()
   */
  public V getVersion();

  /**
   * Returns {@code true} if this {@link Versioned} implementation is
   * a versioned object, i.e. if it can be governed by <i>optimistic
   * concurrency</i>.
   *
   * @return {@code true} if this {@link Versioned} implementation is
   * a versioned object, i.e. if it can be governed by <i>optimistic
   * concurrency</i>; {@code false} otherwise
   *
   * @see #getVersion()
   */
  public boolean isVersioned();
  
}
