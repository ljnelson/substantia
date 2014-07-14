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

/**
 * A substrate to which attributes may be attached.
 *
 * <p>A {@link Substance} serves to "take up space" in a coordinate
 * system.</p>
 */
public abstract class Substance<I extends Serializable, V extends Comparable<V> & Serializable> implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Substance() {
    super();
  }

  public abstract I getId();

  public abstract V getVersion();

  public boolean isTransient() {
    return this.getId() == null;
  }

  public boolean isVersioned() {
    return this.getVersion() != null;
  }

  public final boolean represents(final Substance<?, ?> other) {
    if (this.isTransient() ||
        other == null ||
        other.isTransient()) {
      return false;
    }

    final Object myId = this.getId();
    final Object hisId = other.getId();
    if (myId == null || !myId.equals(hisId)) {
      return false;
    }
    
    return true;
  }

  public boolean isStalerThan(final Substance<? extends I, ? extends V> other) {
    if (this.isTransient() || 
        !this.isVersioned() ||
        other == null ||
        other.isTransient() || 
        !other.isVersioned()) {
      return false;
    }

    final Object id = this.getId();
    if (id == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!id.equals(other.getId())) {
      return false;
    }

    final Comparable<V> version = this.getVersion();
    if (version == null) {
      return other.getVersion() == null;
    } else {
      final V hisVersion = other.getVersion();
      return hisVersion != null && version.compareTo(hisVersion) < 0;
    }
  }

  public abstract String getDisplayName();

  public abstract String getSortName();

  @Override
  public int hashCode() {
    int hashCode = 17;
    int c;

    c = super.hashCode();
    hashCode = 37 * hashCode + c;

    // Note: no usage of id in hashcode computation on purpose.

    // TODO: should version be included here?
    final Object version = this.getVersion();
    c = version == null ? 0 : version.hashCode();
    hashCode = 37 * hashCode + c;

    final Object displayName = this.getDisplayName();
    c = displayName == null ? 0 : displayName.hashCode();
    hashCode = 37 * hashCode + c;

    return hashCode;
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    } else if (other instanceof Substance) {
      final Substance<?, ?> him = (Substance<?, ?>)other;
      
      // Note: no usage of id in equals computation on purpose.

      // TODO: should version be included here?
      final Object version = this.getVersion();
      final Object hisVersion = him.getVersion();
      if (version == null) {
        if (hisVersion != null) {
          return false;
        }
      } else if (!version.equals(hisVersion)) {
        return false;
      }
      
      final Object displayName = this.getDisplayName();
      if (displayName == null) {
        if (him.getDisplayName() != null) {
          return false;
        }
      } else if (!displayName.equals(him.getDisplayName())) {
        return false;
      }

      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return String.valueOf(this.getDisplayName());
  }

}
