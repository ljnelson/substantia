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

import java.util.Locale;

/**
 * An {@link AbstractSubstance} that serves as a <i>facet</i> of
 * another {@link Substance}, chiefly to support localization.
 *
 * <p>All {@link AbstractLocalizedSubstanceFacet}s are identified by
 * means of {@link LocalizedFacetId}s.</p>
 *
 * @param <SI> the type of identifier that the kinds of {@link
 * Substance}s a particular {@link AbstractLocalizedSubstanceFacet}
 * decorates have ({@code SI} is a mnemonic for "substance
 * identifier")
 *
 * @param <LFI> the concrete type of {@link LocalizedFacetId} that
 * identifies {@link AbstractLocalizedSubstanceFacet}s of the proper
 * kind ({@code LFI} is a mnemonic for "localized facet identifier")
 *
 * @param <V> the type of the version exposed by {@link
 * AbstractLocalizedSubstanceFacet}s ({@code V} is a mnemonic for
 * "version")
 *
 * @param <S> the kind of {@link Substance}s that this {@link
 * AbstractLocalizedSubstanceFacet} decorates ({@code S} is a mnemonic
 * for "substance")
 *
 * @author <a href="http://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see AbstractSubstance
 *
 * @see Substance
 *
 * @see LocalizedFacetId
 */
public abstract class AbstractLocalizedSubstanceFacet<SI extends Serializable, LFI extends LocalizedFacetId<SI>, V extends Comparable<V> & Serializable, S extends Substance<SI, ?>> extends AbstractSubstance<LFI, V> {


  /*
   * Static fields.
   */

  
  /**
   * The version of this class for {@linkplain Serializable
   * serialization} purposes.
   */
  private static final long serialVersionUID = 1L;


  /*
   * Instance fields.
   */


  /**
   * The {@link Substance} of which this {@link
   * AbstractLocalizedSubstanceFacet} implementation serves as a
   * facet.
   *
   * <p>This field may be {@code null}.</p>
   *
   * @see #getSubstance()
   *
   * @see #setSubstance(Substance) 
   *
   * @see Substance
   */
  private S substance;


  /*
   * Constructors.
   */

  
  /**
   * Creates a new {@link AbstractLocalizedSubstanceFacet}.
   */
  protected AbstractLocalizedSubstanceFacet() {
    super();
  }


  /*
   * Instance methods.
   */
  

  /**
   * Returns the {@link Substance} of which this {@link
   * AbstractLocalizedSubstanceFacet} implementation serves as a
   * facet.
   *
   * <p>This method may return {@code null}.</p>
   *
   * @return the {@link Substance} of which this {@link
   * AbstractLocalizedSubstanceFacet} implementation serves as a
   * facet, or {@code null}
   *
   * @see #setSubstance(Substance)
   */
  public S getSubstance() {
    return this.substance;
  }
  
  /**
   * Sets the {@link Substance} of which this {@link
   * AbstractLocalizedSubstanceFacet} implementation will serve as a
   * facet.
   *
   * @param substance the {@link Substance} in question; may be {@code
   * null}
   *
   * @see #getSubstance()
   */
  public void setSubstance(final S substance) {
    this.substance = substance;
  }

}
