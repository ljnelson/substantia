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

import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

public class PersonJPALoader implements MultiLoader {

  private EntityManager em;

  public PersonJPALoader(final EntityManager em) {
    super();
    this.em = em;
  }

  @Override
  public <I extends Serializable, V extends Comparable<V> & Serializable, S extends Substance<I, V>> S load(final Class<S> cls, final I id, final V version, final Locale locale) throws LoaderException {
    try {
      final S returnValue = this.em.find(cls, id);
      if (version != null) {
        checkVersions(this.em, returnValue, version);
      }
      return returnValue;
    } catch (final PersistenceException kaboom) {
      throw new LoaderException(cls, id, version, kaboom);
    }
  }

  public static final <V extends Comparable<V> & Serializable, S extends Substance<? extends Serializable, V>> boolean checkVersions(final EntityManager em, final S substance, final V version) {
    final boolean rollback;
    if (em != null && version != null && substance != null && substance.isVersioned()) {
      final V hisVersion = substance.getVersion();
      rollback = hisVersion == null || version.compareTo(hisVersion) < 0;
      if (rollback) {
        final EntityTransaction et = em.getTransaction();
        if (et != null) {
          et.setRollbackOnly();
          throw new OptimisticLockException(substance);
        }
      }
    } else {
      rollback = false;
    }
    return rollback;
  }

  @Override
  public <I extends Serializable, V extends Comparable<V> & Serializable, S extends Substance<I, V>> S refresh(final Class<S> cls, final I id, final V version, final Locale locale) throws LoaderException {
    throw new LoaderException("Not implemented");
  }
}
