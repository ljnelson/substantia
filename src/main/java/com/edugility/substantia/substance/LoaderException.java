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

public class LoaderException extends Exception {

  private static final long serialVersionUID = 1L;

  private final Class<? extends Substance<? extends Serializable, ?>> type;

  private final Serializable id;

  private final Comparable<?> version;

  public LoaderException() {
    this(null, null, null, null, null);
  }

  public LoaderException(final Throwable cause) {
    this(null, null, null, cause, null);
  }

  public LoaderException(final String message) {
    this(null, null, null, null, message);
  }

  public LoaderException(final String message, final Throwable cause) {
    this(null, null, null, cause, message);
  }

  public LoaderException(final Throwable cause, final String message) {
    this(null, null, null, cause, message);
  }

  public <I extends Serializable, V extends Comparable<V> & Serializable, S extends Substance<I, V>> LoaderException(final Class<S> cls, final I id, final V version) {
    this(cls, id, version, null, null);
  }

  public <I extends Serializable, V extends Comparable<V> & Serializable, S extends Substance<I, V>> LoaderException(final Class<S> cls, final I id, final V version, final Throwable cause) {
    this(cls, id, version, cause, null);
  }

  public <I extends Serializable, V extends Comparable<V> & Serializable, S extends Substance<I, V>> LoaderException(final Class<S> cls, final I id, final V version, final Throwable cause, final String message) {
    super(message, cause);
    this.type = cls;
    this.id = id;
    this.version = version;
  }

  public Class<? extends Substance<? extends Serializable, ?>> getType() {
    return this.type;
  }

  public Serializable getId() {
    return this.id;
  }

  public Comparable<?> getVersion() {
    return this.version;
  }

}
