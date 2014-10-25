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

import java.util.Date;

/**
 * A substrate to which attributes may be attached.
 *
 * <p>A {@link AbstractSubstance} serves to "take up space" in a coordinate
 * system.</p>
 *
 * @author <a href="http://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 */
public abstract class AbstractSubstance<I extends Serializable, V extends Comparable<V> & Serializable> implements Substance<I, V> {


  /*
   * Static fields.
   */


  private static final long serialVersionUID = 1L;


  /*
   * Instance fields.
   */


  private Date lastModificationTime;


  /*
   * Constructors.
   */


  /**
   * Creates a new {@link AbstractSubstance}.
   */
  protected AbstractSubstance() {
    super();
  }


  /*
   * Instance methods.
   */

  
  /**
   * Returns {@code true} if this {@link AbstractSubstance} is not (perhaps
   * yet) backed by a persistent representation.
   *
   * <p>The default implementation of this method returns {@code true}
   * if the return value of the {@link #getId()} method is {@code
   * null}.</p>
   *
   * @return {@code true} if this {@link AbstractSubstance} is not backed by a
   * persistent representation; {@code false} in all other cases
   */
  @Override
  public boolean isTransient() {
    return this.getId() == null;
  }

  /**
   * Returns {@code true} if this {@link AbstractSubstance} is a versioned
   * object, i.e. if it can be governed by <i>optimistic
   * concurrency</i>.
   *
   * <p>The default implementation of this method returns {@code true}
   * if the return value of the {@link #getVersion()} method is {@code
   * null}.</p>
   *
   * @return {@code true} if this {@link AbstractSubstance} is a versioned
   * object, i.e. if it can be governed by <i>optimistic
   * concurrency</i>; {@code false} otherwise
   *
   * @see #getVersion()
   */
  @Override
  public boolean isVersioned() {
    return this.getVersion() != null;
  }

  /**
   * Returns {@code true} if this {@link AbstractSubstance} and the supplied
   * {@link Substance} are both non-{@code null}, {@linkplain
   * #isTransient() non-transient} representations of the same,
   * common, underlying persistent representation.
   *
   * @param other the {@link Substance} to compare this {@link
   * AbstractSubstance} to; may be {@code null} in which case {@code false}
   * will be returned
   *
   * @return {@code true} if this {@link AbstractSubstance} and the supplied
   * {@link Substance} are both non-{@code null}, {@linkplain
   * #isTransient() non-transient} representations of the same,
   * common, underlying persistent representation
   */
  public final boolean represents(final Substance<?, ?> other) {

    // For this method to be sensibly invoked, the supplied Substance
    // and this AbstractSubstance must both be non-null and non-transient.
    // Otherwise false is returned.
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

  /**
   * Determines whether this {@link AbstractSubstance} is <i>staler</i> than
   * the supplied {@link Substance}.
   *
   * <p>Given two {@link Substance}s, <i>A</i> and <i>B</i> both
   * non-{@code null}, both {@linkplain #isVersioned() versioned}, and
   * both {@linkplain #isTransient() non-transient}, then <i>A</i> is
   * <i>staler than</i> <i>B</i> when <i>A</i>:</p>
   *
   * <ul>
   *
   * <li>Has an {@linkplain #getId() identifier} equal to the
   * {@linkplain #getId() identifier} of <i>B</i></li>
   *
   * <li>Has a {@linkplain #getVersion() version} that, when
   * {@linkplain Comparable#compareTo(Object) compared} to <i>B</i>'s
   * {@linkplain #getVersion()}, results in a value less than {@code
   * 0}</li>
   *
   * </ul>
   *
   * <p>In this case, this method will return {@code true}.  In all
   * other cases this method will return {@code false}.</p>
   *
   * @param other the {@link Substance} to compare against; may be
   * {@code null} in which case {@code false} will be returned
   *
   * @return {@code true} if this {@link AbstractSubstance} is staler than the
   * supplied {@link Substance}; {@code false} in all other cases
   */
  public boolean isStalerThan(final Substance<? extends I, ? extends V> other) {

    // There is no sensible way to determine staleness if the two
    // objects involved in the comparison are null, transient or not
    // versioned.  false will be returned in these cases.
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
    
    return this.isStalerThan(other.getVersion());
  }

  public boolean isStalerThan(final V otherVersion) {
    if (this.isTransient() ||
        !this.isVersioned()) {
      return false;
    }

    final Comparable<V> version = this.getVersion();
    if (version == null) {
      return otherVersion == null;
    } else {
      return otherVersion != null && version.compareTo(otherVersion) < 0;
    }
  }

  @Override
  public Date getLastModificationTime() {
    return this.lastModificationTime;
  }

  protected void recordModification() {
    this.lastModificationTime = new Date();
  }


  /*
   * Object implementation.
   */


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
      
      return true;
    } else {
      return false;
    }
  }

}
