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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class Substances {

  private Substances() {
    super();
  }

  public static final <I extends Serializable, V extends Serializable & Comparable<V>, S extends Substance<I, V>> S merge(final MultiLoader multiLoader, final Class<? extends S> cls, final I id, final V version, final Locale locale, final Map<? extends String, ?> beanProperties) throws IllegalAccessException, IntrospectionException, InvocationTargetException, LoaderException {
    if (multiLoader == null) {
      throw new IllegalArgumentException("multiLoader", new NullPointerException("multiLoader"));
    }
    if (cls == null) {
      throw new IllegalArgumentException("cls", new NullPointerException("cls"));
    }
    final S target = multiLoader.load(cls, id, version, locale);
    if (target == null) {
      throw new IllegalArgumentException("No substance found corresponding to " + id + ", " + version);
    }
    final BeanInfo beanInfo = Introspector.getBeanInfo(cls);
    assert beanInfo != null;
    final PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    if (pds == null || pds.length <= 0) {
      throw new IllegalArgumentException("Merge target has no properties");
    }
    final Collection<? extends Entry<? extends String, ?>> entries = beanProperties.entrySet();
    if (entries == null || entries.isEmpty()) {
      throw new IllegalArgumentException("Merge target has no properties");
    }
    for (final Entry<? extends String, ?> entry : entries) {
      if (entry != null) {
        final String propertyName = entry.getKey();
        if (propertyName != null) {
          for (final PropertyDescriptor pd : pds) {
            if (pd != null && propertyName.equals(pd.getName())) {
              final Method writeMethod = pd.getWriteMethod();
              if (writeMethod != null) {
                writeMethod.invoke(target, entry.getValue());
                break;
              }
            }
          }
        }
      }
    }
    return target;
  }

}
