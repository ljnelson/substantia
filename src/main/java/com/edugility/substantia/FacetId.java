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

import java.io.Serializable;

/**
 * A composite identifier used to identify {@link Facet}s.
 *
 * @param <SI> the <strong>s</strong>ubstrate
 * <strong>i</strong>dentifier type
 *
 * @param <I> the type of the identifier used for {@link FacetId}s of
 * this type
 *
 * @author <a href="http://about.me/lairdnelson/"
 * target="_parent">Laird Nelson</a>
 */
public interface FacetId<SI, I> extends Identified<I>, Serializable {

  /**
   * Returns the identifier of the <em>substrate</em> this {@link
   * FacetId} identifies.
   *
   * <p>Implementations of this method may return {@code null}.
   * {@code null} return values indicate only that this {@link
   * FacetId} implementation has not been fully initialized.</p>
   *
   * @return the identifier of the substrate this {@link FacetId}
   * identifies, or {@code null}
   *
   * @see #getId()
   */
  public SI getSubstrateId();
  
}
