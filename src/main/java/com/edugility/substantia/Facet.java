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

/**
 * An {@link Identified} facet of another object, known as a
 * <em>substrate</em>.
 *
 * @param <FI> the kind of {@link FacetId} to be used to identify
 * facets of this kind (the <strong>f</strong>acet
 * <strong>i</strong>dentifier type)
 *
 * @param <SI> the <strong>s</strong>ubstrate
 * <strong>i</strong>dentifier type that is used to uniquely identify
 * the kinds of substrates that facets of this kind are properties of
 *
 * @param <I> the "discriminator" identifier of facets of this kind
 *
 * @author <a href="http://about.me/lairdnelson/"
 * target="_parent">Laird Nelson</a>
 *
 * @see Identified
 *
 * @see FacetId
 */
public interface Facet<FI extends FacetId<SI, I>, SI, I> extends Identified<FI> {

}
