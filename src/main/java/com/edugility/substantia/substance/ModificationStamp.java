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

public final class ModificationStamp<V extends Comparable<V> & Serializable> implements LastModificationTimed, Serializable, Versioned<V> {

  private static final long serialVersionUID = 1L;

  private final V version;

  private final Date lastModificationTime;

  public ModificationStamp(final V version, final Date lastModificationTime) {
    super();
    this.version = version;
    this.lastModificationTime = lastModificationTime;
  }

  @Override
  public final V getVersion() {
    return this.version;
  }

  @Override
  public final boolean isVersioned() {
    return this.getVersion() != null;
  }

  @Override
  public final Date getLastModificationTime() {
    return this.lastModificationTime;
  }

  @Override
  public final int hashCode() {
    int hashCode = 17;

    final Object version = this.getVersion();
    if (version != null) {
      hashCode = 37 * hashCode + version.hashCode();
    }

    final Object lastModificationTime = this.getLastModificationTime();
    if (lastModificationTime != null) {
      hashCode = 37 * hashCode + lastModificationTime.hashCode();
    }
    
    return hashCode;
  }
  
  @Override
  public final boolean equals(final Object other) {
    if (other == this) {
      return true;
    } else if (other instanceof ModificationStamp) {
      final ModificationStamp<?> him = (ModificationStamp<?>)other;
      final Object myVersion = this.getVersion();
      if (myVersion == null) {
        if (him.getVersion() != null) {
          return false;
        }
      } else if (!myVersion.equals(him.getVersion())) {
        return false;
      }
      
      final Object myLastModificationTime = this.getLastModificationTime();
      if (myLastModificationTime == null) {
        if (him.getLastModificationTime() != null) {
          return false;
        }
      } else if (!myLastModificationTime.equals(him.getLastModificationTime())) {
        return false;
      }

      return true;      
    } else {
      return false;
    }
  }
  
}
